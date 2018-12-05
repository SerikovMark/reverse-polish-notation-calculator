package com.calculator.rpn.ui;

import com.calculator.rpn.operation.OperationExecutor;
import com.calculator.rpn.service.OperationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CommandLineUserInterface implements UserInterface {

    @Override
    public void run() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String userInput;
        Deque<Double> numbers = new ArrayDeque<>();
        while (true) {
            System.out.println("Please enter numbers or/and operations");
            userInput = getUserInput(br);
            if (userInput == null || userInput.equals("q")) {
                break;
            }
            if (userInput.equals("")) {
                System.out.println("Please enter number or/and operations");
                continue;
            }
            String[] splittedUserInput = userInput.split(" ");
            Arrays.stream(splittedUserInput).forEach(element -> {
                OperationExecutor operation = OperationService.getOperation(element);
                if (operation != null) {
                    Double secondNumber = numbers.pop();
                    Double firstNumber = numbers.pop();
                    Double result = operation.calculate(firstNumber, secondNumber);
                    numbers.push(result);
                    System.out.println(numbers.peek());
                } else {
                    numbers.push(Double.valueOf(element));
                }
            });
        }
    }

    private String getUserInput(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
