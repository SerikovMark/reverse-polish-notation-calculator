package com.calculator.rpn.service.calculator;

import com.calculator.rpn.entity.CalculationResult;
import com.calculator.rpn.operation.OperationExecutor;
import com.calculator.rpn.service.OperationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import static com.calculator.rpn.entity.CalculationCode.*;

public class ReversePolishNotationCalculator implements Calculator {

    @Override
    public List<CalculationResult> calculate(Deque<Double> numbers, String userInput) {
        checkParameters(numbers, userInput);
        String[] userInputs = userInput.split(" ");
        List<CalculationResult> results = new ArrayList<>();
        Arrays.stream(userInputs).forEach(element -> {
            element = element.trim();
            OperationExecutor operation = OperationService.getOperation(element);
            if (operation != null) {
                if (numbers.size() > 1) {
                    Double secondNumber = numbers.pop();
                    Double firstNumber = numbers.pop();
                    Double result = operation.calculate(firstNumber, secondNumber);
                    numbers.push(result);
                    results.add(new CalculationResult(CALCULATED, numbers.peek(), element));
                } else {
                    results.add(new CalculationResult(ERROR_CALCULATION, null, element));
                }
            } else {
                try {
                    numbers.push(Double.valueOf(element));
                    results.add(new CalculationResult(ADDED_NUMBER, numbers.peek(), element));
                } catch (NumberFormatException e) {
                    results.add(new CalculationResult(ERROR_NUMBER_PARSING, null, element));
                }
            }
        });
        return results;
    }

    private void checkParameters(Deque<Double> numbers, String userInput) {
        if (numbers == null || userInput == null) {
            String message = "Numbers and user input should be initialized. Numbers: " +
                    numbers + ", user input: " + userInput;
            throw new RuntimeException(message);
        }
    }
}
