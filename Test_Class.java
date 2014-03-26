import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Write a description of class Test_Class here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
@SuppressWarnings("serial")
public class Test_Class extends JPanel 
{
    // instance variables - replace the example below with your own
    public static int cnt;

    /**
     * Constructor for objects of class Test_Class
     */
    public Test_Class()
    {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
    	// initialise instance variables
    }
    
    public static void testClass1()
    {
    	InputStream inStream = null;
    	OutputStream outStream = null;
    	int length;
    	byte[] buffer = new byte[1024];
    	try
    	{
    		inStream = new FileInputStream(new File("C:\\Users\\STATION1\\Desktop\\Test Folder A\\"));
        	outStream = new FileOutputStream(new File("C\\Users\\STATION1\\Desktop\\Test Folder C\\New Folder"));
            while((length=inStream.read(buffer))>0){outStream.write(buffer,0,length);}
        }
    	catch(FileNotFoundException e)
    	{
    		
    	}
    	catch(IOException e)
    	{
    	
    	}
    	
    }
    
    
    public static boolean testClass3(File filePathA, File filePathB)
    {
        File_Access f_acc = new File_Access(); 
        String[] dirFiles = f_acc.listPath(filePathA);
        
        InputStream inStream = null;     //Input Stream initialized for the importing of the file into the system.
        OutputStream outStream = null;     //Input Stream initialized for the exporting of the file from the system to the desired path.
        
        byte[] buffer = new byte[1024];
        int length;
        if(filePathA.isDirectory() == true)
            {
                System.out.println("Processing : Directory Found");
                
                directorysearch:for(String string: dirFiles){
                    String newFilePathA = filePathA.toString()+"\\"+string;
                    String newFilePathB = filePathB.toString()+"\\"+string;
                    System.out.println(filePathA.toString()+"\t\t"+filePathB.toString()+"\n"+newFilePathA+"\t\t"+newFilePathB+"\n");
                    if(new File(newFilePathA).isDirectory()){
                    	newFilePathB = filePathB.toString()+"\\"+filePathA.getName()+"\\"+string;
                    	
                    	System.out.println("\n\n"+newFilePathB);
                    	File newDir = new File(newFilePathB);
                    	newDir.mkdir();
                        if(f_acc.directoryEmpty(new File(newFilePathA))==true && new File(newFilePathA).canRead()==false && new File(newFilePathB).canRead()==false)
                    	{System.out.println("File Directory is either empty or the files in the folder cannot be copied to do permissions\n");
                        	continue directorysearch;}
                    	testClass3(new File(newFilePathA),newDir);
                        try{
                            inStream = new FileInputStream(new File(newFilePathA));
                            outStream= new FileOutputStream(new File(newFilePathB));
                            while ((length =inStream.read(buffer))>0){
                                outStream.write(buffer,0,length);}
                        cnt++;
                        }
                    
                    
                    catch(IOException e){
                        e.printStackTrace();}
                    }
                    else{
                     try{
                         inStream = new FileInputStream(new File(newFilePathA));
                         outStream = new FileOutputStream(new File(newFilePathB));
                         while((length=inStream.read(buffer))>0){outStream.write(buffer,0,length);}
                         cnt++;
                        }
                     catch(IOException e){e.printStackTrace();}
                      
                     }
                    }
                System.out.println("\n"+cnt+" Files(s) Copied Successfully");
                try{inStream.close();
                    outStream.close();}
                catch(IOException e){e.printStackTrace();}
                    
                }

                
               
                
                System.out.println("File Copied Succesfully");
                
            
          
            return true;
            }
    
    /**
     * This method when given a directory with only files within it, will transfer the directory with its files 
     * to the destination stated
     * @param filePathA - Folder of Origin for file transfer
     * @param filePathB - Folder of Destination for file transfer
     * 
     */
    public static void fileMoveF(File filePathA, File filePathB)
    {
    	File_Access f_acc=new File_Access();
    	InputStream inStream=null;
    	OutputStream outStream=null;
    	byte[] buffer = new byte[1024];
    	int length =0;
    	
    	filePathB=new File(filePathB.toString()+"\\"+filePathA.getName());
    	filePathB.mkdir();
    	
    	for(String string:f_acc.listPath(filePathA))
    	{
    		if(new File(filePathA+"//"+string).isDirectory())
    		{
    			fileMoveF(new File(filePathA+"//"+string),filePathB);
    		}
    		else
    		{
    			try{
    				if (string.equals("Thumbs.db"))
    				{
    					continue;
    				}
    				else
    				{
    					inStream=new FileInputStream(new File(filePathA.toString()+"//"+string));
    					outStream=new FileOutputStream(new File(filePathB.toString()+"//"+string));
    					while((length=inStream.read(buffer))>0){outStream.write(buffer,0,length);}}
    				}
    	    		catch(IOException e)
    	    		{
    	    			System.out.println(filePathA+"\t\t"+filePathB);
    	    			System.out.println("\nFile Stream Error");
    	    		}
    	    		cnt++;
    		}
    		
    	}
    	try
    	{
    		inStream.close();
    		outStream.close();
    	}
    	catch(FileNotFoundException e)
    	{
    		System.out.println("\nFile not Found");
    	}
    	catch(IOException e)
    	{
    		System.out.println("\nFile Stream Error");
    	}
    	System.out.println(cnt+" File(s) Copied Successfully");
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String[] args)
    {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	Test_Class panel = new Test_Class();
    	
    	frame.getContentPane().add(panel);
    	frame.pack();
    	frame.setVisible(true);
    	
    	File_Access fa = new File_Access();
    	String[] test = fa.listPath(new File("C:\\Users\\STATION1\\Desktop\\Test Folder B"));
    	for(String string:test)
    	{System.out.println(string+"\n");}
    	fileMoveF(new File("C:\\Users\\STATION1\\Desktop\\Test Folder B"), new File("C:\\Users\\STATION1\\Desktop\\Test Folder D"));
    	  
    }
}
