import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


public class Button {
	private JButton button;
	private TextField tf;
	private String val;
	
	public Button(String text, TextField txt){
		setTf(txt);
		setVal(text);
		button = new JButton(text);
		button.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				getTf().setText(getTf().getText()+getVal());
			}
		});
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public TextField getTf() {
		return tf;
	}

	public void setTf(TextField tf) {
		this.tf = tf;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}