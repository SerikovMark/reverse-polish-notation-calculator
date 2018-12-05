package com.calculator.rpn.entity;

public class CalculationResult {
    private CalculationCode calculationCode;
    private Double result;
    private String input;

    public CalculationResult(CalculationCode calculationCode, Double result, String input) {
        this.calculationCode = calculationCode;
        this.result = result;
        this.input = input;
    }

    public CalculationCode getCalculationCode() {
        return calculationCode;
    }

    public void setCalculationCode(CalculationCode calculationCode) {
        this.calculationCode = calculationCode;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
