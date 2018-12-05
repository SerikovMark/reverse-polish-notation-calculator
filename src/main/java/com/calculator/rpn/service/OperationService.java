package com.calculator.rpn.service;

import com.calculator.rpn.operation.OperationExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperationService {
    private static Map<String, OperationExecutor> operations = new HashMap<>();

    private OperationService() {
    }

    public static void init() {
        operations.put("+", (firstNumber, secondNumber) -> firstNumber + secondNumber);
        operations.put("-", (firstNumber, secondNumber) -> firstNumber - secondNumber);
        operations.put("*", (firstNumber, secondNumber) -> firstNumber * secondNumber);
        operations.put("/", (firstNumber, secondNumber) -> firstNumber / secondNumber);
    }

    public static OperationExecutor getOperation(String key) {
        return operations.get(key);
    }

    public static Set<String> getSupportedOperations() {
        return operations.keySet();
    }
}
