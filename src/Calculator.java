import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Calculator extends JFrame {
	private String temp = "";
	public Calculator(){
		super();
		Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		setSize(200, 370);
		setVisible(true);
		setResizable(true);
		setMaximumSize(new Dimension(maxBounds.width, maxBounds.height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Joe's Calculator");
		setLocationRelativeTo(null);
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);

		Container content = getContentPane();

		content.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		final TextField tf = new TextField();
		tf.setEditable(false);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		content.add(tf, c);

		c.gridwidth = 1;

		int k = 1;

		for(int i=1;i<4;i++){
			for(int j=0;j<3;j++){
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = j+1;
				c.gridy = i;
				content.add(new Button(""+(k), tf).getButton(), c);
				k++;
			}
		}

		JButton button0 = new JButton("0");
		button0.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				tf.setText(tf.getText()+"0");
			}
		});
		c.gridx = 2;
		c.gridy = 5;
		content.add(button0, c);

		JButton buttonPlus = new JButton("+");
		buttonPlus.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				temp += tf.getText();
				tf.setText("");
			}
		});
		c.gridx = 1;
		c.gridy = 6;
		content.add(buttonPlus, c);

		JButton buttonMinus = new JButton("-");
		c.gridx = 3;
		c.gridy = 6;
		content.add(buttonMinus, c);	

		JButton buttonMultiply = new JButton("x");
		c.gridx = 1;
		c.gridy = 7;
		content.add(buttonMultiply, c);

		JButton buttonDivide = new JButton("/");
		c.gridx = 3;
		c.gridy = 7;
		content.add(buttonDivide, c);
		
		final JButton equals = new JButton("=");
		equals.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				try{
					tf.setText((Integer.parseInt(tf.getText())+Integer.parseInt(temp))+"");
				}
				catch(NumberFormatException c){
					tf.setText((Double.parseDouble(tf.getText())+Double.parseDouble(temp))+"");
					temp += tf.getText();
				}
			}	
		});
		c.gridx = 2;
		c.gridy = 6;
		c.gridheight = 2;
		content.add(equals, c);
		c.gridheight = 1;

		JButton clear = new JButton("Clear");
		clear.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				tf.setText(null);
				temp = "";
			}
		});
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 3;
		content.add(clear, c);		
		
		getContentPane().paintAll(getContentPane().getGraphics());
	}
	public static double add(double a, double b){
		return a+b;
	}
	public static double subtract(double a, double b){
		return a-b;
	}
	public static double multiply(double a, double b){
		return a*b;
	}
	public static double divide(double a, double b){
		return a/b;
	}

	KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	AbstractAction escapeAction = new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	};
}
