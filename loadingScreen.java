import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class loadingScreen extends JFrame implements Runnable{
	private int [] Boundaries = {400, 200, 600, 200, 400, 400, 600, 400};
	private int RadiusWidth, RadiusHeight;
	private Thread t1;
	
	public loadingScreen() {
		t1 = new Thread(this);
		t1.start();
		RadiusWidth = 75;
		RadiusHeight = 75;
		setTitle("Loading...");
		getContentPane().setBackground(Color.CYAN);
		setLayout(null);
		setSize(1200,700);
		setVisible(true);
		setAlwaysOnTop(true);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.blue);
		g.fillOval(Boundaries[0], Boundaries[1], RadiusWidth, RadiusHeight);
		g.setColor(Color.red);
		g.fillOval(Boundaries[2], Boundaries[3], RadiusWidth, RadiusHeight);
		g.setColor(Color.black);
		g.fillOval(Boundaries[4], Boundaries[5], RadiusWidth, RadiusHeight);
		g.setColor(Color.green);
		g.fillOval(Boundaries[6], Boundaries[7], RadiusWidth, RadiusHeight);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Move();
	}
	public static void main(String[] args) {
		loadingScreen ld = new loadingScreen();
		JFrame loadsc = new JFrame();
		loadsc.setSize(ld.getSize());
		loadsc.setTitle("Loading...");
		loadsc.add(ld);
	}
	/**
	 * MOVES THE CIRCLES
	 */
	public void Move() {
		while(true) {
			try {
				t1.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		if(Boundaries[0] <= 600 && Boundaries[1] == 200) {
			Boundaries[0] += 5;
			repaint();
		}
		if(Boundaries[2] == 600 && Boundaries[3] <= 400) {
			Boundaries[3] += 5;
			repaint();
		}
		if(Boundaries[4] == 400 && Boundaries[5] >= 200) {
			Boundaries[5] -= 5;
			repaint();
		}
		if(Boundaries[6] >= 400 && Boundaries[7] == 400) {
			Boundaries[6] -= 5;
			repaint();
		}
		if(t1.isAlive() && Boundaries[0] == 600 && Boundaries[3] == 400 && 
				Boundaries[5] == 200 && Boundaries[6] == 400) {
			t1.interrupt();
			setVisible(false);
		}
	}
	}
}