package calculator;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {

    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Calculator(String expression) {
        this.expression = expression;
    }

    private int getPriority(char operator) {
        switch (operator) {
            case '√':
                return 4;
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    private boolean isOperator(char c) {
        char operator[] = {'+', '-', '*', '%', '/', ')', '(', '^', '√'};
        Arrays.sort(operator);
        return Arrays.binarySearch(operator, c) > -1;
    }

    private String[] convertToPostfix(String[] elementhMath) {
        String output[];
        Stack<String> stack = new Stack<>();
        String number = "";

        for (String elementh : elementhMath) {
            if (elementh.length() > 0) {
                char c = elementh.charAt(0);
                if (!this.isOperator(c)) {
                    number = number + " " + elementh;
                } else if (c == '(') {
                    stack.push(elementh);
                } else if (c == ')') {
                    char c1;
                    do {
                        c1 = stack.peek().charAt(0);
                        if (c1 != '(') {
                            number += " " + stack.peek();
                        }
                        stack.pop();
                    } while (c1 != '(');
                } else {
                    while (!stack.isEmpty()
                            && this.getPriority(stack.peek().charAt(0)) >= this.getPriority(c)) {
                        number = number + " " + stack.peek();
                        stack.pop();
                    }
                    stack.push(elementh);
                }
            }
        }
        while (!stack.isEmpty()) {
            number = number + " " + stack.peek();
            stack.pop();
        }
        output = number.split(" ");
        return output;
    }

    public String[] processString(String sMath) {
        String elementMath[];
        String s1 = "";
        sMath = sMath.trim();
        sMath = sMath.replaceAll("s+", " ");
        for (int i = 0; i < sMath.length(); i++) {
            char c = sMath.charAt(i);
            if (!this.isOperator(c)) {
                s1 = s1 + c;
            } else {
                s1 = s1 + " " + c + " ";
            }
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("s+", " ");
        elementMath = s1.split(" ");
        return elementMath;
    }

    private String calculate(String[] postFix) {

        Stack<String> stack = new Stack<>();

        for (String element : postFix) {
            if (element.length() > 0) {
                char c = element.charAt(0);
                if (!this.isOperator(c)) {
                    stack.push(element);
                } else {
                    double num = 0;
                    double num1 = Double.parseDouble(stack.pop());
                    if (c == '√') {
                        num = Math.sqrt(num1);
                    } else {
                        double num2 = Double.parseDouble(stack.pop());
                        switch (c) {
                            case '+':
                                num = num2 + num1;
                                break;
                            case '-':
                                num = num2 - num1;
                                break;
                            case '*':
                                num = num2 * num1;
                                break;
                            case '/':
                                num = num2 / num1;
                                break;
                            case '%':
                                num = num2 % num1;
                                break;
                            case '^':
                                num = Math.pow(num2, num1);
                                break;
                            default:
                                break;
                        }
                    }
                    stack.push(Double.toString(num));
                }
            }
        }
        return stack.pop();
    }

    public String getResult() {
        String[] elementhMath = this.processString(expression);
        String[] stack = this.convertToPostfix(elementhMath);
        return this.calculate(stack);
    }

}
