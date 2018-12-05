import com.calculator.rpn.entity.CalculationCode;
import com.calculator.rpn.entity.CalculationResult;
import com.calculator.rpn.service.OperationService;
import com.calculator.rpn.service.calculator.Calculator;
import com.calculator.rpn.service.calculator.ReversePolishNotationCalculator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReversePolishNotationCalculatorTest {

    private static Calculator calculator;
    private Deque<Double> numbers;

    @BeforeClass
    public static void beforeClass() {
        OperationService.init();
        calculator = new ReversePolishNotationCalculator();
    }

    @Before
    public void beforeMethods() {
        numbers = new ArrayDeque<>();
    }

    @Test
    public void calculatorShouldBeAbleToCalculateSumOfToDigits() {
        String userInput = "-3 2 +";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals( -1.0, results.get(2).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToCalculateDifferenceBetweenToDigits() {
        String userInput = "5 2 -";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals(3.0, results.get(2).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToCalculateMultiplicationOfToDigits() {
        String userInput = "1 4 *";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals(4.0, results.get(2).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToCalculateDivisionOfToDigits() {
        String userInput = "9 2 /";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals(4.5, results.get(2).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToCalculateThreeDigits() {
        String userInput = "9 6 2 / +";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 5, results.size());
        assertEquals(12.0, results.get(4).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToCalculateFourDigits() {
        String userInput = "11 1 5 3 / + *";
        List<CalculationResult> results = calculator.calculate(numbers, userInput);
        assertEquals("Action should take three iterations", 7, results.size());
        assertEquals(29.3333, results.get(6).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleTakeSequenceOfUserInputs() {
        String firstInput = "5";
        String secondInput = "7";
        String thirdInput = "4 - *";
        calculator.calculate(numbers, firstInput);
        calculator.calculate(numbers, secondInput);
        List<CalculationResult> results = calculator.calculate(numbers, thirdInput);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals(15.0, results.get(2).getResult(), 0.001);
    }

    @Test
    public void calculatorShouldBeAbleToHandleCharactersInUserInput() {
        String input = "7 wewe +";
        List<CalculationResult> results = calculator.calculate(numbers, input);
        assertEquals("Action should take three iterations", 3, results.size());
        assertEquals(CalculationCode.ERROR_NUMBER_PARSING, results.get(1).getCalculationCode());
    }

    @Test
    public void calculatorShouldBeAbleToHandleWrongInputFormat() {
        String input = "3 +";
        List<CalculationResult> results = calculator.calculate(numbers, input);
        assertEquals("Action should take three iterations", 2, results.size());
        assertEquals(CalculationCode.ERROR_CALCULATION, results.get(1).getCalculationCode());
    }

    @Test(expected = RuntimeException.class)
    public void calculatorShouldBeAbleHandleEmptyUserInput() {
        calculator.calculate(numbers, null);
    }

    @Test(expected = RuntimeException.class)
    public void calculatorShouldBeAbleHandleEmptyNumbers() {
        calculator.calculate(null, "5 3 /");
    }

    @Test(expected = RuntimeException.class)
    public void calculatorShouldBeAbleHandleEmptyParameters() {
        calculator.calculate(null, null);
    }
}
