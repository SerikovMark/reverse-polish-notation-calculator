package com.calculator.rpn.ui;

import com.calculator.rpn.entity.CalculationResult;
import com.calculator.rpn.service.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.calculator.rpn.entity.CalculationCode.*;

public class CommandLineUserInterface implements UserInterface {

    private Calculator calculator;

    public CommandLineUserInterface(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String userInput;
        Deque<Double> numbers = new ArrayDeque<>();
        while (true) {
            printMessageToUser();
            userInput = getUserInput(br);
            if (exitFromApplication(userInput)) break;
            if (emptyLineInput(userInput)) continue;
            List<CalculationResult> results = calculator.calculate(numbers, userInput);
            printResults(results);
        }
    }

    private void printResults(List<CalculationResult> results) {
        for (CalculationResult result : results) {
            if (result.getCalculationCode() == CALCULATED) {
                System.out.println(result.getResult());
            }
            if (result.getCalculationCode() == ERROR_NUMBER_PARSING) {
                System.out.println("Only numbers supported");
            }
            if (result.getCalculationCode() == ERROR_CALCULATION) {
                System.out.println("Please add more numbers");
            }
        }
    }

    private void printMessageToUser() {
        System.out.println("Please enter numbers or/and operations");
    }

    private boolean emptyLineInput(String userInput) {
        if (userInput.equals("")) {
            printMessageToUser();
            return true;
        }
        return false;
    }

    private boolean exitFromApplication(String userInput) {
        return userInput == null || userInput.equals("q");
    }

    private String getUserInput(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            String message = "Some problem with input";
            System.out.println(message);
            throw new RuntimeException(message);
        }
    }
}
