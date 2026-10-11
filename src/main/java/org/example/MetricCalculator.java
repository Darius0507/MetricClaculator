package org.example;

import java.util.Scanner;

public class MetricCalculator {

    private double resultInmm = 0;


    public double calculator(char operator, double value, String scale) {

        double valueInmm = switchTomm(value, scale);

        switch (operator) {
            case '+':
                resultInmm += valueInmm;
                break;
            case '-':
                resultInmm -= valueInmm;
                break;
            default:
                throw new IllegalArgumentException("Operator invalid!");
        }
        return resultInmm;
    }

    public double readValue(Scanner scanner) {

        System.out.println("Give the value:");
        while (!scanner.hasNextDouble()) {
            System.out.println("You give a wrong value!");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }


    public String getUnit(Scanner scanner) {

        while (true) {
            System.out.println("Give the scale: (mm, cm, dm, m, km)");
            String unit = scanner.nextLine().toLowerCase().trim();

            if ("mm, cm, dm, m, km)".contains(unit) && !unit.isEmpty()) {
                return unit;
            }

            System.out.println("the scale is not valid!");
        }
    }

    public char getOperator(Scanner scanner) {
        while (true) {
            System.out.println("Give the operator: (+,- or = to finish)");
            String operator = scanner.nextLine().trim();

            if (operator.equals("+") || operator.equals("-") || operator.equals("=")) {
                return operator.charAt(0);
            }
            System.out.println("Invalid operator!");
        }

    }

    public double switchTomm(double value, String unit) {
        switch (unit.toLowerCase().trim()) {
            case "mm":
                return value * 1;
            case "cm":
                return value * 10;
            case "dm":
                return value * 100;
            case "m":
                return value * 1000;
            case "km":
                return value * 1000000;
            default:
                throw new IllegalArgumentException("Invalid unit!");


        }
    }

    public double getResult() {

        return this.resultInmm;
    }

    public void reset() {

        this.resultInmm = 0;
    }
}
