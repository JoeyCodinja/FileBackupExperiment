import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



/**
 * Write a description of class File_Access here.
 * This is the class that will control the movement of files from one directory/device
 * to another using the <b>java.io.file</b>. 
 * 
 * @author (Danuel Williams) 
 * @version (0.1A)
 */
public class File_Access
{

	public File_Access()
	{
		Window_Panel.cnt = 0;
		Window_Panel.fcnt = 0;
	}
	
	
	
	
    /**
     * This method will verify that the file path that was given does indeed have files in it and it is not empty
     * 
     * @param  filePath File path that will be checked by this method 
     * @return TRUE - For a file path that is occupied<br>FALSE - for a file path that is vacant
     */
    public boolean filePathVerify(String filePath)
    {
           return true;
    }
    
    /**
     *This method will check and return the file size of the path that was given
     *@param  filePath - This is the file path that will be checked by this method 
     *@return size of the folder which is given by the file path
     */
    public int fileAmtCheck(File filePath)
    {
    	Window_Panel.fcnt = 0;
    	
    	for(String string: listPath(filePath))
    	{
    		if(new File(filePath.toString()+"\\"+string).isFile())
    		{
    			Window_Panel.fcnt++;
    		}
    		else if(new File(filePath.toString()+"\\"+string).isDirectory())
    		{
    			fileAmtCheck(new File(filePath.toString()+"\\"+string));
    		}
    			
    	}
    	return Window_Panel.fcnt;
    }
    
    /**
     *@param dir - The directory to be checked 
     */
    public boolean directoryEmpty(File dir){
    	String[] flc = new String[1000];
    	flc = dir.list();
    	for(String string:flc)
    	{
    		if(string.length() >0)
    		{Window_Panel.cnt++;}
    	}
//    	System.out.println(cnt);
    	if(Window_Panel.cnt == 0)
    	{return true;}
    	else
    	{
    		Window_Panel.cnt =0;
    		return false;}	
    	
    }
    
    /**
     * Moves a file from an origin path to a destination path and returns a value of true if the process was successful
     * 
     * @param filePathA - The origin file address from which the files will be copied 
     * @param filePathB - The destination file address to which the files will be copied
     * 
     * @return true - Process was conducted successfully 
     * @return false - There was an error and the file was not copied 
     */
    public boolean fileMove(File filePathA, File filePathB) 
    {
    	InputStream inStream = null; 
    	OutputStream outStream= null;
    	
    	byte[] buffer = new byte[1024];
    	int length= 0;
    	
    	filePathB = new File(filePathB.toString()+"\\"+filePathA.getName());
    	filePathB.mkdir();
    	for(String string:listPath(filePathA))
    	{
    		if(new File(filePathA+"\\"+string).isDirectory())
    		{
    			fileMove(new File(filePathA + "\\"+string),filePathB);
    		}
    		else
    		{
    			try
    			{
    				if(string.equals("Thumbs.db"))
    				{
    					continue;
    				}
    				else
    				{
    					inStream = new FileInputStream(new File(filePathA.toString()+"//"+string));
    					outStream = new FileOutputStream(new File(filePathB.toString()+"//"+string));
    					while((length=inStream.read(buffer))>0){outStream.write(buffer,0,length);}
    				}
    			}
    			catch(IOException e)
    			{
    				Window_Panel.statusTxt.setText(filePathA.toString()+"\t\t"+filePathB.toString() +"\nFile Stream Error");
    				return false;
    			}	
    		Window_Panel.cnt++;
    		}
    	}
    	try
    	{
    		inStream = new FileInputStream(filePathA);
    		outStream = new FileOutputStream(filePathB);
    		System.out.println(inStream +"\n"+outStream);
    		inStream.close();
    		outStream.close();
    	}
    	catch(IOException e)
    	{
    		Window_Panel.statusTxt.setText("File Stream Error");
    		Window_Panel.statusTxt.setText(Window_Panel.cnt +" File(s) Copied Successfully");
    		return false;
    	}
    	Window_Panel.statusTxt.setText(Window_Panel.cnt+" File(s) Copied Successfully");
    	return true;
    }
        
    /**
     * Lists the files at a certain file path that has been passed as an argument
     * 
     * @param pathName - Path address to be checked 
     * @return returns a String[] with the names of the files in the directory
     */
    @SuppressWarnings("unused")
	public String[] listPath(File pathName)
    {
        int cnt=0;
        String[] fileList = new String[128];
       
        if(pathName.isDirectory())
        {
            Window_Panel.statusTxt.setText("Compiling");
            try
            {
            	Thread.sleep(1000);
            }
            catch(InterruptedException e){}
            fileList =  pathName.list();
        }
        
        folderSearch:for(String string:fileList)
        {
        	try
        	{
        			cnt++;
            }
        	catch(NullPointerException e)
        	{
        		Window_Panel.statusTxt.setText("Folder is empty");
        		break folderSearch;
        	}
        	
        }
        	
        return fileList;
    }
}

