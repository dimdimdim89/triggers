package triggers;

import triggers.impls.AndTrigger;
import triggers.impls.GtTrigger;
import triggers.impls.OrTrigger;
import triggers.impls.val.AlwaysFalseValue;
import triggers.impls.val.AlwaysTrueValue;
import triggers.impls.val.BooleanValue;
import triggers.impls.val.NumericValue;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by dmitry on 13.06.16.
 */
public class Processor {
    public static final String ALWAYS_TRUE = "alwayTrue";
    public static final String ALWAYS_FALSE = "alwayFalse";

    public static Deque<String> test = new LinkedList<>();
    public static Deque<String> stack = new LinkedList<>();
    public static Deque<String> result = new LinkedList<>();

    public static Boolean process(String test) {
        return false;
    }

    public static void main(String[] args) {
        /*test.add(ALWAYS_TRUE);
        test.add("&");
        test.add(ALWAYS_TRUE);
        test.add("&");
        test.add(ALWAYS_FALSE);*/

        test.push("12");
        test.push("gt");
        test.push("11");
        test.push("|");
        test.push(ALWAYS_TRUE);
        test.push("&");
        test.push(ALWAYS_FALSE);

        //To Polish notation
        while (!test.isEmpty()) {
            String el = test.pop();
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
        System.out.println(result);

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
            case "&": {
                String operandOne = stack.pop();
                BooleanValue r1 = mapBoolean(operandOne);
                String operandTwo = stack.pop();
                BooleanValue r2 = mapBoolean(operandTwo);
                stack.push(mapBoolean(new AndTrigger().process(r1, r2)));
                break;
            }
            case "|": {
                String operandOne = stack.pop();
                BooleanValue r1 = mapBoolean(operandOne);
                String operandTwo = stack.pop();
                BooleanValue r2 = mapBoolean(operandTwo);
                stack.push(mapBoolean(new OrTrigger().process(r1, r2)));
                break;
            }

            case "gt": {
                String operandOne = stack.pop();
                NumericValue r1 = mapNumeric(operandOne);
                String operandTwo = stack.pop();
                NumericValue r2 = mapNumeric(operandTwo);
                stack.push(mapBoolean(new GtTrigger().process(r1, r2)));
                break;
            }
        }
    }

    private static String mapBoolean(BooleanValue operand) {
        if (operand.get()) return ALWAYS_TRUE;
        else return ALWAYS_FALSE;
    }

    private static BooleanValue mapBoolean(String operand) {
        switch (operand) {
            case ALWAYS_TRUE: {
                return new AlwaysTrueValue();
            }
            case ALWAYS_FALSE: {
                return new AlwaysFalseValue();
            }
        }
        return null;
    }

    private static NumericValue mapNumeric(String operand) {
        return new NumericValue(new BigDecimal(operand));
    }

    private static int getPriority(String op) {
        switch (op) {
            case "(":
                return 0;
            case ")":
                return 0;
            case "|":
                return 1;
            case "&":
                return 2;
            case "gt":
                return 3;
        }
        return -1;
    }

    private static boolean isOperator(String el) {
        if ("&".equals(el) || "|".equals(el) || "gt".equals(el))
            return true;
        return false;
    }
}
