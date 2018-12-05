package com.calculator.rpn;

import com.calculator.rpn.service.OperationService;

public class Main {
    public static void main(String[] args) {
        InitEnv();
        StartApp();
    }

    private static void StartApp() {

    }

    private static void InitEnv() {
        OperationService.init();
    }
}
