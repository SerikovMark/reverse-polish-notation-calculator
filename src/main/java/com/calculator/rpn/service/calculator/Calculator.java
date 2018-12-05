package com.calculator.rpn.service.calculator;

import com.calculator.rpn.entity.CalculationResult;

import java.util.Deque;
import java.util.List;

public interface Calculator {

    List<CalculationResult> calculate(Deque<Double> numbers, String userInput);
}
