package calculator;

public class Validate {

    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Validate(String expression) {
        this.expression = expression;
        this.expression = this.expression.trim();
    }

    public boolean isValid() {
        return !hasAlphabetic() && isFullBrackets() && isValidBrackets()
                && !isOperaterLastString() && !isDuplicateDot();
    }

    public boolean hasAlphabetic() {
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetter(expression.charAt(i))
                    || expression.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }

    public boolean isFullBrackets() {
        int count = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                count++;
            } else if (expression.charAt(i) == ')') {
                count--;
            }
        }
        return count == 0;
    }

    public boolean isValidBrackets() {
        String[] exceptions = {"()", ")(", "(*", "(/", "(%", "(^"};
        for (String element : exceptions)
            if (expression.contains(element))
                return false;
        
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(' && i != 0) {
                if (Character.isDigit(expression.charAt(i - 1))) {
                    return false;
                }
            }
            
        }

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))
                    && expression.charAt(i) == '(') {
                return false;
            }
        }

        return true;
    }

    public boolean isOperaterLastString() {
        return expression.charAt(expression.length() - 1) == '+'
                || expression.charAt(expression.length() - 1) == '-'
                || expression.charAt(expression.length() - 1) == '*'
                || expression.charAt(expression.length() - 1) == '/'
                || expression.charAt(expression.length() - 1) == '%'
                || expression.charAt(expression.length() - 1) == '^';
    }

    private boolean isDuplicateDot() {
        int dotAmount = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '.') {
                dotAmount++;
            } else if (!Character.isDigit(expression.charAt(i)) || i == expression.length() - 1) {
                if (dotAmount > 1) {
                    return true;
                }
                dotAmount = 0;
            }
        }
        return false;
    }
    
}