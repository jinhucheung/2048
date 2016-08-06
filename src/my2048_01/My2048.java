package my2048_01;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class My2048 extends JFrame {

	public My2048() {
		setTitle("2048");
		setSize(400, 400);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(4, 4, 5, 5));
		new Operation(this);
		this.setVisible(true);
	
	}

	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		new My2048();
	}

}
