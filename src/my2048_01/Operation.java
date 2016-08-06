package my2048_01;

import java.awt.event.*;
import javax.swing.*;

public class Operation implements KeyListener{
	Block[] block;
	JPanel panel;
	int moveFlag;
	boolean numFlag;

	public Operation(JFrame frame) {
		this.panel = (JPanel)frame.getContentPane();
		block = new Block[16];
		numFlag = true;
		moveFlag = 0;
		addBlock();
		for (int i = 0; i < 2; i++)
			appearBlock();
		frame.addKeyListener(this);

	}

	private void addBlock() {
		for (int i = 0; i < 16; i++) {
			block[i] = new Block();
			block[i].setHorizontalAlignment(JLabel.CENTER);
			// ²»Í¸Ã÷
			block[i].setOpaque(true);
			panel.add(block[i]);
			
		}
	}

	public void appearBlock() {
		while (numFlag) {
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
						
						
						if(j>i-12)
							if(block[j-4].getValue()==(valueI+valueJ))
						break;

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
						
						if(j<i+12)
							if(block[j+4].getValue()==(valueI+valueJ))
						break;
						
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

						if(j<i+3)
							if(block[j+1].getValue()==(valueI+valueJ))
						break;
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
						
						if(j<i-3)
							if(block[j-1].getValue()==(valueI+valueJ))
						break;
					} else if (numFlag == false)
						moveFlag += 1;
				}
				index = j;
			}
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

	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
