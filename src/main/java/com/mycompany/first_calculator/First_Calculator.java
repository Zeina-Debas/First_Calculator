

package com.mycompany.first_calculator;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class First_Calculator extends JFrame {

    JTextField textfield;
    JPanel Panel;
    String Button[] = {
        "7", "8", "9", "/", "C", "4", "5", "6", "*", "(", "1", "2", "3", "-", ")", "0", "00", ".", "+", "="
    };
    
    First_Calculator() {
        setVisible(true);
        setTitle("Calculator");
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(232, 242, 251));
        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(400, 60));
        add(textfield);
        Panel = new JPanel();
        Panel.setBackground(new Color(229, 229, 229));
        Panel.setPreferredSize(new Dimension(400, 400));
        Panel.setLayout(new GridLayout(4, 5, 5, 5));
        for (String l : Button) {
            JButton button = new JButton(l);
            Panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(l.equals("C")){
                  textfield.setText("");
                   }
                   else if(l.equals("=")){
                    String res=  calculateExpression(textfield.getText()) ;
                    textfield.setText(res);
                   }
                   else{
                   textfield.setText(textfield.getText()+l );
                   }
                   
                }
            });
        }
        add(Panel);
        
    }

    public String calculateExpression(String expr) {
    try {
        double result = 0;
        double currentNumber = 0;
        double lastNumber = 0;
        char operation = '+';
        int i = 0;

        while (i < expr.length()) {
            char c = expr.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                currentNumber = Double.parseDouble(sb.toString()); //
                i--; 
            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || i == expr.length() - 1) {
                switch (operation) {
                    case '+':
                          result += lastNumber; 
                        lastNumber = currentNumber; 
                        break;
                    case '-':
                        result += lastNumber;
                        lastNumber = -currentNumber;
                        break;
                    case '*':
                        lastNumber *= currentNumber;
                        break;
                    case '/':
                        lastNumber /= currentNumber;
                        break;
                }
                operation = c;
                currentNumber = 0;
            }
            i++;
        }

        result += lastNumber;
        return String.valueOf(result);
    } catch (Exception e) {
        return "Error";
    }
}
    public static void main(String[] args) {
        new First_Calculator();
    }
}
