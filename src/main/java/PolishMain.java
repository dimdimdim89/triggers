import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by dmitry on 11.06.16.
 */
public class PolishMain {


    public static void main(String[] args) {
        Deque<String> stack = new LinkedList<>();
        Deque<String> result = new LinkedList<>();
        Deque<String> expr = new LinkedList<>();

        expr.push("1");
        expr.push("+");
        expr.push("1");
        expr.push("+");
        expr.push("2");
        expr.push("/");
        expr.push("(");
        expr.push("2");
        expr.push("-");
        expr.push("3");
        expr.push(")");
        expr.push("+");
        expr.push("5");
        expr.push("-");
        expr.push("(");
        expr.push("7");
        expr.push("*");
        expr.push("2");
        expr.push("-");
        expr.push("14");
        expr.push(")");


        Deque<String> expr1 = new LinkedList<>();
        while (!expr.isEmpty()) expr1.push(expr.pop());
        expr = expr1;

        while (!expr.isEmpty()) {
            String el = expr.pop();
            if (isOperator(el)) {
                while (!stack.isEmpty() &&
                        getPriority(stack.peek()) >= getPriority(el)) {
                    result.push(stack.pop());
                }
                stack.push(el);
            } else if ("(".equals(el))
                stack.push(el);
            else if (")".equals(el)) {
                while (!stack.peek().equals("(")) {
                    result.push(stack.pop());
                }
                stack.pop();
            } else {
                result.push(el);
            }

        }
        while (!stack.isEmpty()) result.push(stack.pop());

        while (!result.isEmpty()) {
            String el = result.pollLast();
            if (isOperator(el)) {
                apply(stack, el);
            } else stack.push(el);
        }

        System.out.println(stack.pop());
    }

    private static void apply(Deque<String> stack, String el) {
        switch (el) {
            case "+": {
                String operandOne = stack.pop();
                String operandTwo = stack.pop();
                int rz = Integer.valueOf(operandOne) + Integer.valueOf(operandTwo);
                stack.push(String.valueOf(rz));
                break;
            }
            case "-": {
                String operandOne = stack.pop();
                String operandTwo = stack.pop();
                int rz = Integer.valueOf(operandTwo) - Integer.valueOf(operandOne);
                stack.push(String.valueOf(rz));
                break;
            }
            case "*": {
                String operandOne = stack.pop();
                String operandTwo = stack.pop();
                int rz = Integer.valueOf(operandOne) * Integer.valueOf(operandTwo);
                stack.push(String.valueOf(rz));
                break;
            }
            case "/": {
                String operandOne = stack.pop();
                String operandTwo = stack.pop();
                int rz = Integer.valueOf(operandTwo) / Integer.valueOf(operandOne);
                stack.push(String.valueOf(rz));
                break;
            }
        }
    }

    public static boolean isOperator(String el) {
        if ("+".equals(el) || "-".equals(el) || "/".equals(el) || "*".equals(el))
            return true;
        return false;
    }

    public static int getPriority(String op) {
        switch (op) {
            case "(":
                return 0;
            case ")":
                return 0;
            case "+":
                return 1;
            case "-":
                return 1;
            case "/":
                return 2;
            case "*":
                return 2;
        }
        return -1;
    }
}
