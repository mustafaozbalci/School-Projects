import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	static int port;
	ArrayList<String> Quest = new ArrayList<String>();
	
	public Server(int port, ArrayList<String> Quest) {
		this.port = port;
		this.Quest = Quest;
	}
	/*
	 * sends the Questions from given arraylist
	 * according to the question number
	 * @param socket
	 */
	public void distributeQuestions(Socket socket) {
		try {
			
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(Quest.get(Questions.QNumber));
		}	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void run() {
		
		try {
			ServerSocket server = new ServerSocket(this.port);
			Socket socket; 
			System.out.println("Server created waiting for a socket...");
			
			while (true) {
				socket = server.accept();
				System.out.println("Socket accepted...");
				distributeQuestions(socket);
				System.out.println("Changes applied." +" question :"+Quest.get(Questions.QNumber));
				socket.close();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
