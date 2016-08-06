package my2048;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Block extends JLabel {
	private int value;

	public Block() {
		value = 0;
		setFont(new Font("font", Font.PLAIN, 40));
		setBackground(Color.gray);

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		String text = String.valueOf(value);
		if (value != 0)
			setText(text);
		else
			setText("");
		setColor();
	}

	public void setColor() {
		if (this.value == 0)
			setBackground(Color.gray);
		else if (this.value == 2)
			setBackground(new Color(238, 228, 218));
		else if (this.value == 4)
			setBackground(new Color(238, 224, 198));
		else if (this.value == 8)
			setBackground(new Color(243, 177, 116));
		else if (this.value == 16)
			setBackground(new Color(243, 177, 116));
		else if (this.value == 32)
			setBackground(new Color(248, 149, 90));
		else if (this.value == 64)
			setBackground(new Color(249, 94, 50));
		else if (this.value == 128)
			setBackground(new Color(239, 207, 108));
		else if (this.value == 256)
			setBackground(new Color(239, 207, 99));
		else if (this.value == 512)
			setBackground(new Color(239, 203, 82));
		else if (this.value == 1024)
			setBackground(new Color(239, 199, 57));
		else if (this.value == 2048)
			setBackground(new Color(239, 195, 41));
		else if (this.value == 4096)
			setBackground(new Color(255, 60, 57));
	}
}
