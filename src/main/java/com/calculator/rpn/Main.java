package com.calculator.rpn;

import com.calculator.rpn.service.OperationService;
import com.calculator.rpn.ui.CommandLineUserInterface;

public class Main {
    public static void main(String[] args) {
        InitEnv();
        StartApp();
    }

    private static void StartApp() {
        StartCommandLineUserInterface();
        // Start other user interfaces
    }

    private static void StartCommandLineUserInterface() {
        new Thread(new CommandLineUserInterface()).start();
    }

    private static void InitEnv() {
        OperationService.init();
    }
}
