import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Questions	extends JFrame implements ActionListener {
	private JButton btnQuestion1, btnQuestion2, btnQuestion3, btnQuestion4, btnQuestion5, btnEndOfTheTest;
	public static JLabel Q;
	static int QNumber, SolveChecker = 0;

	
	
	public Questions() {
		//DEFINITIONS OF LABELS AND BUTTONS
		Q = new JLabel("");	
		btnQuestion1 = new JButton("Question 1");
		btnQuestion2 = new JButton("Question 2");
		btnQuestion3 = new JButton("Question 3");
		btnQuestion4 = new JButton("Question 4");
		btnQuestion5 = new JButton("Question 5");		
		btnEndOfTheTest = new JButton("End Of The Test");
		// ADD BUTTONS TO THE FRAME
		add(btnQuestion1);
		add(btnQuestion2);
		add(btnQuestion3);
		add(btnQuestion4);
		add(btnQuestion5);		
		add(btnEndOfTheTest);
		// REGISTER ACTION LISTENER
		btnQuestion1.addActionListener(this);
		btnQuestion2.addActionListener(this);
		btnQuestion3.addActionListener(this);
		btnQuestion4.addActionListener(this);
		btnQuestion5.addActionListener(this);		
		btnEndOfTheTest.addActionListener(this);
		// FRAME OPTIONS
		setLayout(new GridLayout(3,3));
		setSize(1000, 700);
		setVisible(true);
		
	}
	/*
	 * Requests for questions according to the given number
	 * @param socket
	 * @param QuestionNumber
	 */
	public void QuestionRequest(Socket socket, int QNumber) {
		try {
			QNumber = this.QNumber;
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream dos = new ObjectOutputStream(os);
			dos.writeUTF(String.valueOf(QNumber));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Gets the Question info from SERVER
	 * @param socket
	 */
	public String getQuestionsFromServer(Socket socket) {
		String tempo ="";
		try {
			InputStream is = socket.getInputStream();
			ObjectInputStream dis = new ObjectInputStream(is);
			tempo = (String) dis.readObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempo;
	}
	/*
	 * Makes new client and makes a request and gets a respond from SERVER.
	 * Sets the Q label according to the respond form SERVER
	 * @param QuestionNumber
	 */
	public void setQuestions(int QNumber) throws Exception {
		this.QNumber = QNumber;
		Socket clientsocket = new Socket("127.0.0.1", 5000);
		QuestionRequest(clientsocket, QNumber);
		Q.setText(getQuestionsFromServer(clientsocket));
		new AnswerSheet();
		clientsocket.close();
		SolveChecker += 1;
	}
	
		//END OF THE TEST BUTTON FEATURES
	public  void endOfTestMessage() {
		if(SolveChecker == 5)
		setVisible(false);
		else {
			JOptionPane.showMessageDialog(this, "Please solve all questions");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnQuestion1) {
			try{
				setQuestions(1);
				remove(btnQuestion1);
		}	catch(Exception e1){
		e1.getMessage(); }
		}
		else if(e.getSource() == btnQuestion2) {
			try{
				setQuestions(2);
				remove(btnQuestion2);
		}	catch(Exception e1){
		e1.getMessage(); }
		}
		else if(e.getSource() == btnQuestion3) {
			try{
				setQuestions(3);
				remove(btnQuestion3);
		}	catch(Exception e1){
		e1.getMessage(); }
		}
		else if(e.getSource() == btnQuestion4) {
			try{
				setQuestions(4);
				remove(btnQuestion4);
		}	catch(Exception e1){
		e1.getMessage(); }
		}
		else if(e.getSource() == btnQuestion5) {
			try{
				setQuestions(5);
				remove(btnQuestion5);
		}	catch(Exception e1){
		e1.getMessage(); }
		}
		else if(e.getSource() == btnEndOfTheTest) {
			endOfTestMessage();
			}
	}
}
