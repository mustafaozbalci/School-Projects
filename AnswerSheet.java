import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AnswerSheet extends JFrame implements ActionListener{
	private JButton btn1, btn2, btn3, btn4, btn5;
	private JLabel lblScore;
	public static int total;
	
	public AnswerSheet() {
		// DEFINITONS OF THE BUTTONS LABELS AND TEXTFIELDS
		lblScore = new JLabel("\n(Please answer the question as 1 is very bad and 5 is very good)");			
		btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");
		// ADD BUTTONS AND LABEL TO THE FRAME
		add(Questions.Q);		
		add(lblScore);		
		add(btn1);
		add(btn2);		
		add(btn3);		
		add(btn4);	
		add(btn5);
		// REGISTER ACTION LISTENER
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		// PANEL OPTIONS
		setLayout(new FlowLayout());
		setSize(400, 200);
		setVisible(true);
		setAlwaysOnTop(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn1) {
			total += 1;
			setVisible(false);
		}
		else if(e.getSource() == btn2) {
			total += 2;
			setVisible(false);
		}
		else if(e.getSource() == btn3) {
			total += 3;
			setVisible(false);
		}
		else if(e.getSource() == btn4) {
			total += 4;
			setVisible(false);
		}
		else if(e.getSource() == btn5) {
			total += 5;
			setVisible(false);
		}
	}
}
