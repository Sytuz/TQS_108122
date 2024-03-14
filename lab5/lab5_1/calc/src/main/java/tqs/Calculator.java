package tqs;

import java.util.Stack;

public class Calculator {
    Stack<Double> stack;
    boolean error;
    String errorMessage;

    public Calculator() {
        this.stack = new Stack<Double>();
    }

    public double value() {
        if (error) {
            error = false;
            return Double.NaN;
        }
        return stack.peek();
    }

    public void push(double value) {
        stack.push(value);
        error = false;
    }

    public void push(String value) {
        if (value.equals("+")) {
            stack.push(stack.pop() + stack.pop());
        } else if (value.equals("-")) {
            stack.push(-stack.pop() + stack.pop());
        } else if (value.equals("*")) {
            stack.push(stack.pop() * stack.pop());
        } else if (value.equals("/")) {
            double divisor = stack.pop();
            if (divisor == 0) {
                stack.push(divisor);
                error = true;
                errorMessage = "Cannot divide by zero";
                return;
            }
            stack.push(stack.pop() / divisor);
        } else {
            error = true;
            errorMessage = "Invalid operation: " + value;
            return;
        }
        error = false;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
