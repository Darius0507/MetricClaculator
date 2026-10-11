package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;
import java.util.stream.Stream;

class MetricCalculatorTest {

    private final MetricCalculator calculator = new MetricCalculator();

    @BeforeEach
    void setUp() {
        calculator.reset();
    }

    @Test
    public void calculator() {
        //when
        double addition = calculator.calculator('+', 20, "cm");
        double substraction = calculator.calculator('-', 10, "mm");

        //then
        assertEquals(200, addition);
        assertEquals(190, substraction);

    }

    @Test
    public void invalidoperator() {

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculator('/', 100, "m");
        });
    }

    @Test
    void readValue() {
        //when
        double value = calculator.readValue(new Scanner("14.5\n"));

        //then
        assertEquals(14.5, (Double) value);
    }

    @Test
    void getUnit() {
        //when
        String unit = calculator.getUnit(new Scanner("cm"));

        //then
        assertEquals("cm", unit);
    }

    @Test
    void getOperator() {
        //when
        char operator = calculator.getOperator(new Scanner("+"));

        //then
        assertEquals('+', operator);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void switchTomm(double value, String unit, double expetedResult) {
        //when
        double result = calculator.switchTomm(value, unit);
        //then
        assertEquals(expetedResult, result);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1, "mm", 1),
                Arguments.of(1, "cm", 10),
                Arguments.of(1, "dm", 100),
                Arguments.of(1, "m", 1000),
                Arguments.of(1, "km", 1000000));
    }

    @Test
    void getResult() {
        //given
        calculator.calculator('+', 5, "m");
        //when
        double result = calculator.getResult();
        //then
        assertEquals(5000.0, result);
    }

    @Test
    void reset() {
        //given
        calculator.calculator('+', 10, "mm");
        //when
        calculator.reset();
        double resetedResult = calculator.getResult();
        //then
        assertEquals(0.0, resetedResult);

    }
}