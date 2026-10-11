package org.example;


import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        MetricCalculator calculator = new MetricCalculator();
        char currentopperator = '+';

        while (true) {
            double value = calculator.readValue(scanner);
            String unit = calculator.getUnit(scanner);
            double result = calculator.calculator(currentopperator, value, unit);

            char opperator = calculator.getOperator(scanner);
            if (opperator == '=') {
                break;
            }

            currentopperator = opperator;

        }

        System.out.println("The result is: " + calculator.getResult() + " mm");


    }
}
