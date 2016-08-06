package my2048;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class My2048 extends JFrame implements KeyListener {

	Block[] block;
	JPanel panel;
	boolean numFlag;
	int moveFlag;

	public My2048() {

		numFlag = true;
		moveFlag = 0;
		block = new Block[16];
		setTitle("2048");
		setSize(400, 400);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new GridLayout(4, 4, 5, 5));
		addBlock();
		for (int i = 0; i < 2; i++)
			appearBlock();
		this.addKeyListener(this);

		this.setVisible(true);
	}

	public void addBlock() {
		for (int i = 0; i < 16; i++) {
			block[i] = new Block();
			block[i].setHorizontalAlignment(JLabel.CENTER);
			// ²»Í¸Ã÷
			block[i].setOpaque(true);
			panel.add(block[i]);
		}
	}

	public void appearBlock() {
	while(numFlag) {
			int index = (int) (Math.random() * 16);
			if (block[index].getValue() == 0) {
				if (Math.random() < 0.5)
					block[index].setValue(2);
				else
					block[index].setValue(4);
				break;
			}
		}

	}

	public void judgeAppear() {
		int sum = 0;
		for (int i = 0; i < 16; i++) {
			if (block[i].getValue() != 0)
				sum++;
		}
		if (sum == 16)
			numFlag = false;

	}

	public void upBlock() {

		for (int i = 12; i < 16; i++) {
			int index = i;
			for (int j = i - 4; j >= i - 12; j -= 4) {
				int valueI = block[index].getValue(), valueJ = block[j]
						.getValue();
				if (valueJ == 0) {
					block[index].setValue(0);
					block[j].setValue(valueI);
				} else {
					if (valueI == valueJ) {
						block[index].setValue(0);
						block[j].setValue(valueI + valueJ);
						if (valueI + valueJ == 4096)
							win();
						numFlag = true;
						moveFlag = 0;
					} else if (numFlag == false)
						moveFlag += 1;
				}
				index = j;
			}
		}

	}

	public void downBlock() {

		for (int i = 0; i < 4; i++) {
			int index = i;
			for (int j = i + 4; j <= i + 12; j += 4) {
				int valueI = block[index].getValue(), valueJ = block[j]
						.getValue();
				if (valueJ == 0) {
					block[index].setValue(0);
					block[j].setValue(valueI);
				} else {
					if (valueI == valueJ) {
						block[index].setValue(0);
						block[j].setValue(valueI + valueJ);
						if (valueI + valueJ == 4096)
							win();
						numFlag = true;
						moveFlag = 0;
					} else if (numFlag == false)
						moveFlag += 1;
				}
				index = j;
			}
		}

	}

	public void rightBlock() {

		for (int i = 0; i <= 12; i += 4) {
			int index = i;
			for (int j = i + 1; j <= i + 3; j++) {
				int valueI = block[index].getValue(), valueJ = block[j]
						.getValue();
				if (valueJ == 0) {
					block[index].setValue(0);
					block[j].setValue(valueI);
				} else {
					if (valueI == valueJ) {
						block[index].setValue(0);
						block[j].setValue(valueI + valueJ);
						if (valueI + valueJ == 4096)
							win();
						numFlag = true;
						moveFlag = 0;
					} else if (numFlag == false)
						moveFlag += 1;
				}
				index = j;
			}
		}

	}

	public void leftBlock() {

		for (int i = 3; i <= 15; i += 4) {
			int index = i;
			for (int j = i - 1; j >= i - 3; j--) {
				int valueI = block[index].getValue(), valueJ = block[j]
						.getValue();
				if (valueJ == 0) {
					block[index].setValue(0);
					block[j].setValue(valueI);
				} else {
					if (valueI == valueJ) {
						block[index].setValue(0);
						block[j].setValue(valueI + valueJ);
						if (valueI + valueJ == 4096)
							win();
						numFlag = true;
						moveFlag = 0;
					} else if (numFlag == false)
						moveFlag += 1;
				}
				index = j;
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			upBlock();
			judgeAppear();
			appearBlock();
			over();
			break;
		case KeyEvent.VK_DOWN:
			downBlock();
			judgeAppear();
			appearBlock();
			over();
			break;
		case KeyEvent.VK_LEFT:
			leftBlock();
			judgeAppear();
			appearBlock();
			over();
			break;
		case KeyEvent.VK_RIGHT:
			rightBlock();
			judgeAppear();
			appearBlock();
			over();
			break;
		}

	}

	public void over() {
		if (!numFlag && moveFlag >= 36) {
			block[4].setText("G");
			block[5].setText("A");
			block[6].setText("M");
			block[7].setText("E");
			block[8].setText("O");
			block[9].setText("V");
			block[10].setText("E");
			block[11].setText("R");
			
			block[11].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					reStart();
				}
			});
		}
	}
    
	public void win() {
		
		block[0].setText("Y");
		block[1].setText("O");
		block[2].setText("U");
		block[13].setText("W");
		block[14].setText("I");
		block[15].setText("N");
		
		block[15].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				reStart();
			}
		});

	}
    public void reStart(){
    	numFlag=true;
		moveFlag=0;
		for(int i=0;i<16;i++)
			block[i].setValue(0);
    	for (int i = 0; i < 2; i++)
			appearBlock();
    }
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

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
