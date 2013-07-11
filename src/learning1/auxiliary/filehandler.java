package learning1.auxiliary;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;


public class filehandler {
    int m_actual_size = 0;
/*
// Root-Verzeichnis / SD-Karten-Root anzeigen
        try { 
            FileConnection fc = (FileConnection)Connector.open("file:///E:/", Connector.READ);
            Enumeration filelist = fc.list("*", true); //also hidden files are shown        
//            Enumeration filelist = FileSystemRegistry.listRoots();
            while (filelist.hasMoreElements()) {
                String filelistString = filelist.nextElement().toString();
                helloTextBox.insert("#"+filelistString+"#",helloTextBox.size());  
            }
        }
        catch (IOException ioe) {
            System.out.println("IOException: "+ioe.getMessage());            
        }
        catch (SecurityException se) {
            System.out.println("SecurityException: "+se.getMessage());            
        }
*/
/*       
// Schreib-Test auf File
        StringBuffer sb = new StringBuffer("BlaBla");
        try {
            FileConnection fconn = (FileConnection)Connector.open("file:///E:/output-text.txt", Connector.READ_WRITE);
            if (!fconn.exists()) {
                fconn.create();
            }
            OutputStream ops = fconn.openOutputStream();
            ops.write(sb.toString().getBytes("UTF-8"));
            ops.close();
            fconn.close();
        }
        catch (IOException ioe) {
            System.out.println("IOException: "+ioe.getMessage());
        }
        catch (SecurityException se) {
            System.out.println("Security exception:" + se.getMessage());
        }        
 */

// 
    public String[][] file_read(String filepath) {
        byte bytes[] = "".getBytes();
        try {
            FileConnection fc = (FileConnection)Connector.open(filepath, Connector.READ);
            if(!fc.exists()) {
                System.out.println("File doesn't exist!");
            }
            else {
                int size = (int)fc.fileSize();
                bytes = new byte[size];
                InputStream is = fc.openInputStream();
                is.read(bytes, 0, size);
            }
 
        } catch (IOException ioe) {
            System.out.println("IOException: "+ioe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException: "+iae.getMessage());
        }  
        String parsestr = new String(bytes);
        String myList[] []= new String[2][10000];
        int splitPoint = 1;
        int line = 0;
        while(splitPoint > 0) {
            splitPoint = parsestr.indexOf(";");            
            if (splitPoint > 0) 
            {
                myList[0][line] = parsestr.substring(0,splitPoint).trim();
                parsestr = parsestr.substring(splitPoint+1).trim();
                splitPoint = parsestr.indexOf("\n");
                if (splitPoint > 0)
                {
                    myList[1][line++] = parsestr.substring(0,splitPoint).trim();
                    parsestr = parsestr.substring(splitPoint+1).trim();
                }
                else
                    if (parsestr.length() != 0)
                            myList[1][line++] = parsestr;
            }
            m_actual_size = line;
        }
        return myList;
    }
        
    
    public int get_num_entries()
    {
        return m_actual_size;
    }
 /*       
  * 
  *                 helloTextBox.insert(new String(bytes),helloTextBox.size()); 
  * 
        InputStream is = getClass().getResourceAsStream("/lernprog.csv");
        
        try {
            int chr = 0;
            String testString="";
            String testString2="";
            helloTextBox.insert(sb.toString(),helloTextBox.size());
            while ((chr = is.read()) != -1) {
                sb.append((char) chr);
            }
            testString = sb.toString();
            int Pos;
            helloTextBox.insert("B",helloTextBox.size());            
            while ((Pos = testString.indexOf("\n")) > 0) {
                testString2 = testString.substring(0, Pos);
                                 // Inhalt entfernt...
                testString = testString.substring(Pos+1, testString.length());
                helloTextBox.insert(testString, helloTextBox.size());
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {}                 
            }
            helloTextBox.insert("C",helloTextBox.size());            
        } catch (Exception e) {
            helloTextBox.insert("D",helloTextBox.size());               
        } 
        */

}