
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ActionEventDemo implements ActionListener {

	public static int toggle = 1;
	JFrame frame = new JFrame();
	JButton button = new JButton("Click me");
	
	public ActionEventDemo() {
		
		frame.setTitle("My window");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setBounds(200,200,400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		button.setBounds(130,200,100,40);
		frame.add(button);
		button.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (toggle ==1) {
			frame.getContentPane().setBackground(Color.pink);
			System.out.println("Moied");
			toggle = 0;
			
		}
		else {
			frame.getContentPane().setBackground(Color.blue);
			System.out.println("Ahmed");
			toggle = 1;
		
		}
		
	
	}
		
		
		}


public class GUI1 {
	
	public static void main(String[] args) {

		new ActionEventDemo();
	}

}

