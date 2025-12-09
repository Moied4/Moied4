
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI3 extends JFrame {

	int i=0;
    static JPanel panel;

    public GUI3(){
        JButton[] buttons = new JButton[26];
        panel = new JPanel(new GridLayout(4,6));
        String  b[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
                     "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        
        for(i = 0; i < buttons.length; i++) {
           buttons[i] = new JButton(b[i]);
           buttons[i].setSize(80, 80);
           buttons[i].setActionCommand(b[i]);
           buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                       String choice = e.getActionCommand();
                       JOptionPane.showMessageDialog(null, "You have clicked: "+ choice);
                }
             }
           );
           
         panel.add(buttons[i]);
      }
}

public static void main(String[]args){
	GUI3 frame = new GUI3();
	frame.add(panel);
    frame.pack();
    frame.setVisible(true);
 }
}
