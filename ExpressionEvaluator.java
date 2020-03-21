import java.util.Stack;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vijini
 */
public class ExpressionEvaluator {
    
    public static void main(String[] args) {
        System.out.println("Evaluate 2*2+((0.5+7*4+5)+1)*2*8");
        System.out.println("Answer = " + ExpressionEvaluator.evaluate("2*2+((0.5+7*4+5)+1)*2*8"));
        System.out.println("Calculator answer = 556.0");
    }

    public static double evaluate(String expression) {

        //Tokenize the expression
        StringTokenizer st = new StringTokenizer(expression, "+*()", true);
        
        Stack<String> nums_operators = new Stack();
        
        //Create numbers stack
        Stack<Double> numbers = new Stack<>();

        //Create operators stack
        Stack<String> operators = new Stack<>();

        //Read a token
        while (st.hasMoreTokens()) {
            
            String literal = st.nextToken();
            
            //If the token is a number, then add it to numbers stack.
            if (literal.matches("-?\\d+(\\.\\d+)?")) {
                numbers.add(Double.parseDouble(literal));

            } //If the token is an operator op1
            else if (literal.equals("+") || literal.equals("*")) {

                //while there is an operator token, op2, at the top of the operator stack, and op1 is having precedence less than or equal to that of op2
                while (!operators.empty() && hasLowPrecedence(literal, operators.peek())) {
                    numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                //push onto operator stack
                operators.push(literal);

            } //If the token is a left parenthesis, then push it onto the operators stack
            else if (literal.equals("(")) {
                operators.push(literal);

            } //If the token is a right parenthesis
            else if (literal.equals(")")) {

                //Until the token at the top of the operators stack is a left parenthesis, pop operators and perform operation
                while (!operators.peek().equals("(")) {
                    numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                //Pop the left parenthesis from the operators stack
                operators.pop();

            } else {
                //If the literal is not a number or an operand the expression is invalid
                return Double.NaN;

            }
        }

        //While there are still operator tokens in the operators stack perform operations
        while (!operators.empty()) {
            numbers.push(performOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        //Return the final answer
        return numbers.pop();

    }

    //Method to check operator precedence
    public static boolean hasLowPrecedence(String op1, String op2) {
        if (op1.equals("(") || op1.equals(")") && (op2.equals("+") || op2.equals("*"))) {
            return false;
        } else if (op1.equals("*") && op2.equals("+")) {
            return false;
        } else if (op1.equals("+") && op2.equals("*")) {
            return true;
        }

        return false;
    }

    //Perform operations ^,*,/,+,-
    public static double performOperation(String op, double num2, double num1) {

        switch (op) {
            case "*":
                return num1 * num2;
            case "+":
                return num1 + num2;
        }

        return Double.NaN;
    }
}
