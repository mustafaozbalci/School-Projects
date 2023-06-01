import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JPanel implements ActionListener, Runnable{
	
	private JLabel lblName, lblLastname, lblAge, lblTotal;
	private JTextField txtName, txtLastname, txtAge, txtTotal;
	private JButton btnSave, btnReset, btnQuestions, btnExit;
	private JButton btnInstructors;
	private int screenX, screenY;
	private Thread t2;
	String Instructors =  "\n FIRST : Fill the blanks" + 
			"\nSECOND : Click questions button and solve them." + 
	"\nTHIRD : After the test ends click on save button" + 
"\n Restart button is sets all stats to default";
	
	public Main() {
	// INSTRUCTORS
		JOptionPane.showMessageDialog(null, "                  INSTRUCTORS !" + Instructors);
		t2 = new Thread(this);
		t2.start();
	// DEFINITONS OF THE BUTTONS LABELS AND TEXTFIELDS
		btnSave = new JButton("Save");
		btnSave.setBackground(Color.GREEN);
		btnReset = new JButton("Restart");
		btnReset.setBackground(Color.red);
		btnQuestions = new JButton("Questions Page");
		btnQuestions.setBackground(Color.YELLOW);
		btnInstructors = new JButton("Instructions");
		btnInstructors.setBackground(Color.CYAN);
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.MAGENTA);
		lblName = new JLabel("What is your Name ?");
		txtName = new JTextField();
		lblLastname = new JLabel("What is your Lastname ?");
		txtLastname = new JTextField();
		lblAge = new JLabel("How old are you ?");
		txtAge = new JTextField();
		lblTotal = new JLabel("Total Score : ");
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtName.setColumns(30);
		txtLastname.setColumns(30);
		txtAge.setColumns(30);
		txtTotal.setColumns(30);	
	// REGISTER ACTION LISTENER
		btnSave.addActionListener(this);
		btnReset.addActionListener(this);
		btnQuestions.addActionListener(this);
		btnInstructors.addActionListener(this);
		btnExit.addActionListener(this);
	// ADD LABELS BUTTONS AND TEXTFIELDS TO THE FRAME
		add(lblName);
		add(txtName);
		add(lblLastname);
		add(txtLastname);
		add(lblAge);
		add(txtAge);
		add(lblTotal);
		add(txtTotal);
		add(btnSave);
		add(btnReset);
		add(btnInstructors);
		add(btnQuestions);
	// FRAME OPTIONS
		screenX = 1000;
		screenY = 500;
		setBackground(Color.lightGray);
		setLayout(new GridLayout(6,2));
		setSize(screenX, screenY);
		setVisible(true);
		UpdateTotalPoint();
	}
	/**
	 * SAVES THE INFO AND MAKE TXT FIELDS CLOSE
	 */
	public void SaveButtonFeatures() {
		if(AnswerSheet.total== 0) {
			JOptionPane.showMessageDialog(null, "Solve the questions first");		
		}
		else {
		JOptionPane.showMessageDialog(null, "SAVED");
		txtAge.setEditable(false);
		txtLastname.setEditable(false);
		txtName.setEditable(false);	
		lblTotal.setText(txtName.getText().trim()+"'s total score is : ");
		t2.interrupt();
		add(btnExit);
		try {
			DiskWriter();
			t2.wait();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	}
	/**
	 * DISPLAYS A LOADING SCREEN AND OPENS NEW QUESTION PAGE
	 * REMOVES QUESTIONS BUTTONS IN ORDER NOT TO GAIN MORE POINT
	 */
	public void QuestionsButtonFeatures() {
		new loadingScreen();
		remove(btnQuestions);
		JOptionPane.showMessageDialog(null, "Welcome  "+txtName.getText().trim()+"\nAnswer the following questions");
		new Questions();
	}
	/**
	 * SETS ALL FEATURES TO DEFAULT 
	 */
	public void Restart() {
		txtAge.setEditable(true);
		txtAge.setText("");
		txtLastname.setEditable(true);
		txtLastname.setText("");
		txtName.setEditable(true);
		txtName.setText("");
		lblTotal.setText("Total Score : ");
		txtTotal.setText("");
		AnswerSheet.total = 0;
		Questions.SolveChecker = 0;
		remove(btnExit);
		add(btnQuestions);
		JOptionPane.showMessageDialog(null, "Application Restarted");
		notifyAll();
	}	
	/*
	 *  SAVE USER'S INFORMATION TO A TXT FILE
	 */
	public void DiskWriter() throws Exception {
		//RESULTS OF THE USER
		String UsersLastInfo = "Name : "+txtName.getText().trim()+"\t"
				+"Lastname : "+txtLastname.getText().trim()+"\t"
				+"Age : "+txtAge.getText().trim()+"\t"
				+"Total Point : "+txtTotal.getText().trim()+"\n";
		
		File file = new File("dosya.txt");
		if (!file.exists()) {
            file.createNewFile();
        }
		FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(UsersLastInfo);
        bWriter.close();
	}
		public static void main(String[] args) {
			//define an arraylist for the server to host them.
			ArrayList<String> Quest = new ArrayList<String>();
			Quest.add(0, "naber");
			Quest.add(1, "I see myself as a successful person.");
			Quest.add(2, "I can control my excitement around others.");
			Quest.add(3, "I am not dependent on others for my choices.");
			Quest.add(4, "I can deal with difficulties in life.");
			Quest.add(5, "There is no insurmountable problem for me.");
			Server testServer = new Server(5000, Quest);
			testServer.start();
			JFrame screen = new JFrame();
			Main PT= new Main();
			screen.setSize(PT.getSize());
			screen.setTitle("Personality Test");
			screen.setVisible(true);
			screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			screen.add(PT);		
		}	
		public void UpdateTotalPoint(){
			try {
				t2.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtTotal.setText(AnswerSheet.total+""); 	
			}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSave) {
				SaveButtonFeatures();
			}
			else if (e.getSource() == btnReset) {
				Restart();
			}
			else if (e.getSource() == btnQuestions) {
				QuestionsButtonFeatures();
			}
			else if (e.getSource() == btnInstructors) {
				JOptionPane.showMessageDialog(null, "                  INSTRUCTORS !" +Instructors);
			}
			else if (e.getSource() == btnExit) {
				JOptionPane.showMessageDialog(null,"Thank you for joining...");
				System.exit(1);
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				UpdateTotalPoint();
			}
		}
	}