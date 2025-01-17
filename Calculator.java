import java.awt.*;
import java.awt.event.*;

/**
 * Interface and event handling for the calculator. 
 * AWT is part of the standard library and nothing extra needs to be installed.
 */
public class Calculator extends Frame {	
    /**
     * first entered number
     */
    private int num1 = 0;
    /**
     * desired mathematical operation
     */
    private char operation = ' ';
    /**
     * Label for displaying numbers
     */
    private Label display = new Label();

    /**
     * Constructor activates the Windows events and starts the initialization
     */
    public Calculator() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        init();
    }

    /**
     * init method creates the interface with GridLayout and a display
     */
    private void init() {       
    	Font fDisplay = new Font("fDisplay", Font.BOLD, 27);
    	Font fBtn = new Font("fBtn", Font.BOLD, 16);
    	
        // Settings for the frame
        this.setLayout(new GridLayout(6, 1));
        this.setSize(new Dimension(198, 295));
        this.setBackground(Color.lightGray);
        this.setTitle("Calculator");
        this.setResizable(false);

        Button[] digits = new Button[10];
        // Attach fonts, color, and action listeners to the digit buttons
        for(int i = 0; i < 10; i++) {
        	Button b = new Button("" + i);
        	b.setFont(fBtn);
        	b.addActionListener(e -> this.digitButton(b.getLabel()));
        	digits[i] = b;
        }
        // Mathematical operation buttons
        Button btnPlus = new Button("+");
        Button btnMinus = new Button("-");
        Button btnMultiply = new Button("*");
        Button btnDivide = new Button("/");
        Button[] operations = {btnPlus, btnMinus, btnMultiply, btnDivide};     
        for(Button b: operations) {      
	        b.setFont(fBtn);
	        b.addActionListener(e -> operationButton(b.getLabel().charAt(0)));
	        b.setForeground(Color.blue);
        }
        
        // The remaining buttons:
        Button btnEqual = new Button("=");
        Button btnEmpty = new Button("");
        Button btnClear = new Button("C");
        btnEqual.setFont(fBtn);
        btnEqual.addActionListener(e -> equalButton());
        btnEqual.setForeground(Color.red);
        btnClear.setFont(fBtn);
        btnClear.addActionListener(e -> clearButton());
        btnClear.setForeground(new Color(19, 140, 44));

        display.setAlignment(Label.RIGHT);
        display.setFont(fDisplay);
        display.setForeground(Color.green);
        display.setBackground(Color.black);
        display.setText("0");

        // Panels for the layout
        Panel pnDisplay = new Panel(new BorderLayout()); // Calculator display at the top
        Panel pnDigits1 = new Panel(new GridLayout(1, 4)); // Digits 7-9 and /
        Panel pnDigits2 = new Panel(new GridLayout(1, 4)); // Digits 4-6 and *
        Panel pnDigits3 = new Panel(new GridLayout(1, 4)); // Digits 1-3 and -
        Panel pnDigits4 = new Panel(new GridLayout(1, 4)); // Empty, 0, C and +
        Panel pnEqual = new Panel(new BorderLayout()); // = button
        
        // Add individual components to the panels
        pnDisplay.add(display);
        pnDigits1.add(digits[7]);
        pnDigits1.add(digits[8]);
        pnDigits1.add(digits[9]);
        pnDigits1.add(btnDivide);
        pnDigits2.add(digits[4]);
        pnDigits2.add(digits[5]);
        pnDigits2.add(digits[6]);
        pnDigits2.add(btnMultiply);
        pnDigits3.add(digits[1]);
        pnDigits3.add(digits[2]);
        pnDigits3.add(digits[3]);
        pnDigits3.add(btnMinus);
        pnDigits4.add(btnEmpty);
        pnDigits4.add(digits[0]);
        pnDigits4.add(btnClear);
        pnDigits4.add(btnPlus);
        pnEqual.add(btnEqual);

        // Add panels to the frame
        this.add(pnDisplay);
        this.add(pnDigits1);
        this.add(pnDigits2);
        this.add(pnDigits3);
        this.add(pnDigits4);
        this.add(pnEqual);
    }

    /**
     * processWindowEvent for handling window closing messages via the X button
     * 
     * @param WindowEvent
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
    
    /**
     * simulates pressing the Clear button
     */
    private void clearButton() {
        display.setText("0");
        operation = ' ';
    }
    
    /**
     * simulates pressing one of the digit buttons
     */
    private void digitButton(String digit) {
    	String number = display.getText();
    	if (number.length() == 0 || Integer.parseInt(number) == 0) {
    		display.setText(digit);
        } else if (number.length() < 8) {
        	display.setText(number + digit);
        }
    }
    
    /**
     * simulates pressing one of the operation buttons
     */
    private void operationButton(char operationSymbol) {
        if (operation == ' ') { 
            operation = operationSymbol;
            num1 = Integer.parseInt(display.getText());
            display.setText("");
        }
    }
    
    /**
     * simulates pressing the Equal button
     */
    private void equalButton() {
        if (operation != ' ') {
            int result = 0;
            int num2 = Integer.parseInt(display.getText());
            result = ComputingUnit.calculate(num1, num2, operation);
            display.setText("" + result);
            operation = ' ';
        }
    }
    
    /**
     * brings up a calculator on the screen
     * @param args not used
     */
    public static void main(String[] args) {
		// Create an instance of the Calculator class and display it
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
    }
}
