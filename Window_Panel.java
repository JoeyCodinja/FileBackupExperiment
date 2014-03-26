import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;

import javax.swing.*;

/**
 * Write a description of class Window_Panel here.
 * 
 * @author (Danuel Williams) 
 * @version (v:1.0a)
 */
@SuppressWarnings("serial")
public class Window_Panel extends JPanel implements ActionListener, PropertyChangeListener
{
	
	
   private JTextField td, fd;
   public static JLabel fromlabel,  tolabel,  statusTxt;
   private JButton backup, browsefbtn,browsetbtn, exit;
   private JProgressBar progressBar;
   final JFileChooser filechoose;
   static int cnt  , fcnt; // ProgressCounter - counts the current amount of files that have been already transferred - -  fileCount - this is the total number of files to be transferred 
   static final int DEFAULT_GRIDCONSTRAINT = 1;
   File_Access file_acc = new File_Access();
   
    /**
     * Constructor for objects of class Window_Panel
     */
    private Window_Panel()
    {
     GridBagLayout gridbl = new GridBagLayout(); 
     setLayout(gridbl);
     GridBagConstraints c = new GridBagConstraints();
     
     c.fill = GridBagConstraints.BOTH;
     
     //First Row
     c.weightx = 0;
     fromlabel = new JLabel("From:");
     gridbl.setConstraints(fromlabel, c);
     add(fromlabel);
     
     c.weightx= 2;
     c.weighty = 1;
     c.ipadx = 200;
     fd = new JTextField();
     c.gridwidth = GridBagConstraints.RELATIVE;
     gridbl.setConstraints(fd,c);
     c.ipadx = 0;
     add(fd);
     
     
     browsefbtn = new JButton("Browse");
     browsefbtn.addActionListener(this);
     c.gridwidth = GridBagConstraints.REMAINDER;
     c.weightx = 0;
     c.weighty =1;
     gridbl.setConstraints(browsefbtn, c);
     add(browsefbtn);
     
     //Second Row
     c.weightx = 0;
     c.weighty = 0;
     c.gridwidth = DEFAULT_GRIDCONSTRAINT;
     tolabel = new JLabel("To:");
     gridbl.setConstraints(tolabel,c);
     add(tolabel);
     
     c.weightx =1; 
     c.weighty =1;
     c.ipadx = 200; 
     td = new JTextField();
     c.gridwidth = GridBagConstraints.RELATIVE;
     gridbl.setConstraints(td,c);
     c.ipadx = 0;
     add(td);
     
     c.weightx =0;
     browsetbtn = new JButton("Browse");
     browsetbtn.addActionListener(this);
     c.gridwidth = GridBagConstraints.REMAINDER;
     gridbl.setConstraints(browsetbtn,c);
     add(browsetbtn);
     
     
     //Third Row
     c.weightx = 0;
     c.gridwidth = DEFAULT_GRIDCONSTRAINT;
     backup = new JButton("Backup");
     backup.addActionListener(this);
     gridbl.setConstraints(backup,c);
     add(backup);
     
     
     statusTxt = new JLabel("Ready");
     c.gridwidth = GridBagConstraints.RELATIVE;
     gridbl.setConstraints(statusTxt,c);
     add(statusTxt);
     
     exit = new JButton("Exit");
     exit.addActionListener(this);
     c.gridwidth = GridBagConstraints.REMAINDER;
     gridbl.setConstraints(exit,c);
     add(exit);
     
     //Fourth Row 
     c.weightx = 0;
     c.gridwidth = GridBagConstraints.REMAINDER;
     progressBar = new JProgressBar(0,100);
     progressBar.setValue(0);
     progressBar.setStringPainted(true);
     gridbl.setConstraints(progressBar,c);
     add(progressBar);
     
     //FileChooser Initialization
     filechoose = new JFileChooser();
     filechoose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
     
     this.addPropertyChangeListener(this);
}
    
    
    
    
    	public void propertyChange(PropertyChangeEvent event)
    	{
    		if ("cnt" == event.getPropertyName())
    		{
    			Toolkit.getDefaultToolkit().beep();
    			cnt = (Integer) event.getNewValue();
    			statusTxt.setText("Updating");
    			progressBar.setValue(Math.round((float)(cnt/fcnt)*100));
    			
    			try 
    			{
    				Thread.sleep(1000);
    			}
    			catch(InterruptedException e)
    			{
    				
    			}
    			
    			
    		}
    	}
    	
    
    
           public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == browsefbtn){
                statusTxt.setText("Retrieiving");
                int returnVal = filechoose.showOpenDialog(Window_Panel.this);
            	
            	if(returnVal == JFileChooser.APPROVE_OPTION){
            		String filePath= filechoose.getSelectedFile().getPath();
            		fd.setText(filePath);
            	}
            	statusTxt.setText("Ready");
            }
            
            if(event.getSource() == browsetbtn){
            	statusTxt.setText("Retrieving");
                
            	int returnVal = filechoose.showOpenDialog(Window_Panel.this);
            	
            	if(returnVal == JFileChooser.APPROVE_OPTION){
            		String filePath = filechoose.getSelectedFile().getPath();
            		td.setText(filePath);
            	}
            	statusTxt.setText("Ready");	
            }
           
            if(event.getSource() == backup)
                {
            	 System.out.println(file_acc.fileAmtCheck(new File(fd.getText().toString())));
            	 backup.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            	 browsefbtn.setEnabled(false);
            	 browsetbtn.setEnabled(false);
            	 backup.setEnabled(false);
            	 file_acc.fileMove(new File(fd.getText().toString()), new File(td.getText().toString()));
            	 browsefbtn.setEnabled(true);
            	 browsetbtn.setEnabled(true);
            	 backup.setEnabled(true);
            	 backup.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            	 try {
					Desktop.getDesktop().open(new File(td.getText()+"\\"+new File(fd.getText()).getName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
                }
            
            if(event.getSource() == exit)
                {
            	 try 
            	 {
            		 Thread.sleep(1000);
            	 }
            	 catch(InterruptedException e)
            	 {
            		 statusTxt.setText("Exiting");
                     System.exit(1); 
            	 }
                 }
            
        }
    

 
    
        
    /**
     * This main function creates the Frame that starts the application.
     * 
     */
    public static void main(String[] args){
        JFrame m_Frame = new JFrame("Backup");
        m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Window_Panel panel = new Window_Panel();
       
        
        m_Frame.getContentPane().add(panel);
        m_Frame.pack();
        m_Frame.setVisible(true);
    }

}
