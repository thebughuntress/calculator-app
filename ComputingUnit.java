public class ComputingUnit {
    /**
     * Performs the chosen mathematical operation on the numbers num1 and num2.
     * 
     * @param num1 The first number.
     * @param num2 The second number.
     * @param operation The mathematical operation ('+', '-', '*', '/').
     * @return The result of the calculation.
     */
    public static int calculate(int num1, int num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2; // Division
                } else {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}
