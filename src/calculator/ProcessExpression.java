package calculator;

public class ProcessExpression {

    private StringBuilder expression;

    public ProcessExpression(StringBuilder expression) {
        this.expression = expression;
    }

    public StringBuilder getExpression() {
        return expression;
    }

    public void setExpression(StringBuilder expression) {
        this.expression = expression;
    }
    
    private void processSqrt() {
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '√' && expression.charAt(i) == '(') {
                for (int j = i; j < expression.length(); j++) {
                    if (expression.charAt(j) == ')') {
                        expression.insert(j + 1, "^0.5");
                        expression.deleteCharAt(i);
                    }
                }
            }
        }
    }
    
    private void processSubSymbol() {
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' && !Character.isDigit(expression.charAt(i - 1))) {
                expression.insert(i, "0");
            }
        }
    }
    
    public StringBuilder refactor() {
        this.processSqrt();
        this.processSubSymbol();
        return this.expression;
    }
}