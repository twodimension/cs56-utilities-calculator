package edu.ucsb.cs56.projects.utilities.calculator;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

/**
	Keypad is a class to represent a keypad which sends signals to a calculator
	@author Sam Dowell
*/
public class Keypad extends JComponent implements KeyListener{

	private Calculator calculator;
	
/**
	Constructor
	@param calc Takes a Calculator object as an argument, to send signals to
*/
	public Keypad(Calculator calc){
		super();

		calculator = calc;
		setFocusable(true);
		addKeyListener(this);
		this.setLayout(new GridLayout(0,4));

		makeButton("Clear");
		makeButton("<--");
		makeButton("-->");
		makeButton("Delete");
		makeButton("7");
		makeButton("8");
		makeButton("9");
		makeButton("/");
		makeButton("4");
		makeButton("5");
		makeButton("6");
		makeButton("*");
		makeButton("1");
		makeButton("2");
		makeButton("3");
		makeButton("-");
		makeButton("0");
		makeButton(".");
		makeButton("Enter");
		makeButton("+");
		makeButton("^");
		makeButton("sqrt");
		makeButton("sin");
		makeButton("cos");
		makeButton("(");
		makeButton(")");


	}
/**
	Helper method that initializes the JButtons for the GUI
	@param s String representation of what signal the button will send
*/
	private void makeButton(String s){
		JButton jb = new JButton(s);
        Font bigFont = new Font("Ariel",Font.BOLD,20);
		jb.addActionListener(new ButtonListener(s));
        if (s.length() < 2 && s.length() > 0)
        {
            try {
                int num = Integer.parseInt(s);
                if (num >= 0 && num <= 9)
                {
                    jb.setBackground(Color.BLUE);
                    jb.setContentAreaFilled(false);
                    jb.setOpaque(true);
                }
            }
            catch (NumberFormatException e){
                jb.setBackground(Color.CYAN);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
            }
        }
        else
        {
            if (s.equals("Clear") || s.equals("Delete"))
            {
                jb.setBackground(Color.RED);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
            }
            else if (s.equals("Enter"))
            {
                jb.setBackground(Color.GREEN);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
            }
            else if (s.equals("<--") || s.equals("-->"))
            {
                jb.setBackground(Color.YELLOW);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
            }
            else
            {
                jb.setBackground(Color.CYAN);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
            }
        }
        jb.setFont(bigFont);
		this.add(jb);
	}
/**
	Helper method to ensure that this window has the focus
*/
	private void resetFocus(){
		setFocusable(true);
		requestFocusInWindow();
	}
/**
	Inner class that implements an ActionListener to handle button presses
*/
   class ButtonListener implements ActionListener{
	private String num;

	public ButtonListener(String s) {
	    super();  // is this line necessary? what does it do?
	    this.num = s;

	}

	public void actionPerformed (ActionEvent event) {
		calculator.append(num);
		resetFocus();
		
	}


    }

    @Override
    public void keyReleased(KeyEvent ke){}
    @Override
    public void keyTyped(KeyEvent ke){}
    @Override
    public void keyPressed(KeyEvent ke){
	String key = java.awt.event.KeyEvent.getKeyText(ke.getKeyCode());
	char k = ke.getKeyChar();
	if((k >= '0' && k <= '9') || k == '+' || k == '-' || k == '*' || k == '/' || k == '.' || k == '^' || k == '(' || k == ')')
		calculator.append("" + k);
    else if (key.equals("<--"))
        calculator.append("<--");
    else if (key.equals("-->"))
        calculator.append("-->");
	else if(k == 'c' || k == 'C')
		calculator.append("Clear");
	else if(key.equals("Enter") || key.equals("Equals"))
		calculator.append("Enter");
	else if(key.equals("NumPad +"))
		calculator.append("+");
	else if(key.equals("NumPad -"))
		calculator.append("-");
	else if(key.equals("NumPad *"))
		calculator.append("*");
	else if(key.equals("NumPad /"))
		calculator.append("/");
	else if(key.equals("Backspace"))
		calculator.append("Delete");
    else if (key.equals("NumPad ^"))
        calculator.append("^");
    else if (key.equals("sqrt"))
        calculator.append("sqrt");
    else if (key.equals("sin"))
        calculator.append("sin");
    else if (key.equals("cos"))
        calculator.append("cos");
    else if (key.equals(")Clear"))
        calculator.append("Clear");
    else if (key.equals(")Delete"))
        calculator.append("Delete");
    }
}
