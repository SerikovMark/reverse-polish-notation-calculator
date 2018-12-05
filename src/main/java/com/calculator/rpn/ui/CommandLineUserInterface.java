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
            printMessageToUser();
            userInput = getUserInput(br);
            if (handleExitFromApplication(userInput)) break;
            if (handleEmptyLineInput(userInput)) continue;
            String[] splittedUserInput = userInput.split(" ");
            calculate(numbers, splittedUserInput);
        }
    }

    private void calculate(Deque<Double> numbers, String[] splittedUserInput) {
        Arrays.stream(splittedUserInput).forEach(element -> {
            OperationExecutor operation = OperationService.getOperation(element);
            if (operation != null) {
                if (numbers.size() > 1) {
                    Double secondNumber = numbers.pop();
                    Double firstNumber = numbers.pop();
                    Double result = operation.calculate(firstNumber, secondNumber);
                    numbers.push(result);
                    System.out.println(numbers.peek());
                } else {
                    System.out.println("Please add more numbers");
                }
            } else {
                try {
                    numbers.push(Double.valueOf(element));
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers supports in input");
                }
            }
        });
    }

    private void printMessageToUser() {
        System.out.println("Please enter numbers or/and operations");
    }

    private boolean handleEmptyLineInput(String userInput) {
        if (userInput.equals("")) {
            printMessageToUser();
            return true;
        }
        return false;
    }

    private boolean handleExitFromApplication(String userInput) {
        return userInput == null || userInput.equals("q");
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
