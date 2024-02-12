import java.util.Stack;

public class PostfixConverter {

    public static String convertToPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char nextCharacter = infix.charAt(i);

            switch (nextCharacter) {
                case ' ':
                    break;
                case 'a': case 'b': case 'c': // Add more variables as needed
                    postfix.append(nextCharacter);
                    break;
                case '^':
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                    while (!operatorStack.isEmpty() && getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek())) {
                        postfix.append(operatorStack.pop());
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case ')':
                    char topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix.append(topOperator);
                        topOperator = operatorStack.pop();
                    }
                    break;
                default:
                    // Ignore unexpected characters
                    break;
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    private static int getPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        String infixExpression = "a + b * (c - d) / e ^ f";
        String postfixExpression = convertToPostfix(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
    }
}