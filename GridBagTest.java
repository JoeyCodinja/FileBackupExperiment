import javax.swing.*;
import java.awt.*;



@SuppressWarnings("serial")
public class GridBagTest extends JPanel {
	
	protected void makeButton(String name, GridBagLayout gridbag, GridBagConstraints c){
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		add(button);
	}
	
	public GridBagTest()
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints  c= new GridBagConstraints();
		
		setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1.0;
		makeButton("Button1",gridbag,c);
		makeButton("Button2",gridbag,c);
		makeButton("Button3",gridbag,c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;//end row
		makeButton("Button4",gridbag,c);
		
		c.weightx=0.0; //reset to the default
		makeButton("Button5",gridbag,c);
		
		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
		makeButton("Button6",gridbag,c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;// end row
		makeButton("Button 7", gridbag,c);
		
		c.gridwidth = 1;	//reset to default
		c.gridheight = 2;
		c.weighty = 1.0;
		makeButton("Button 8", gridbag,c);
		
		c.weighty = 0.0; // reset to the default
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		c.gridheight = 1; 	//reset to the default
		makeButton("Button9", gridbag,c);
		makeButton("Button10",gridbag,c);
		
		setSize(300,500);
		
		
		
		
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagTest panel = new GridBagTest();
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}

}
