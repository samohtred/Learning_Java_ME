/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learning1;

import javax.microedition.midlet.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.util.Enumeration;
import javax.microedition.lcdui.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import learning1.auxiliary.*;

/**
 * @author Wurzel
 */
public class Midlet extends MIDlet implements CommandListener {

    private guihandler mygui;
    private Command exitCommand; // The exit command
    private Command okayCommand; // The exit command    
    private Display display;     // The display for this MIDlet    

    private boolean go_on = false;
    private boolean do_exit = false;   
    private boolean exit_question = false;
    
    public Midlet() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Beenden", Command.EXIT, 1);        
        okayCommand = new Command("Weiter", Command.OK, 1);
    }
    
    public void startApp() {
        TextField helloTextField = new TextField("", "Lade Fragen aus Datei ...", 100, TextField.UNEDITABLE);
        Form myform = new Form("Learning 1.0");
        myform.append(helloTextField);        
        helloTextField.setPreferredSize(-1,-1);
        myform.addCommand(okayCommand);        
        myform.setCommandListener(this);
        display.setCurrent(myform);        

        filehandler myfhand = new filehandler();
        String QnA_list[][] = myfhand.file_read("file:///E:/lernprog.csv");
        mygui = new guihandler();
        do {
            go_on = false;
            do_exit = false;
            exit_question = false;
            mygui.QnA_handler(QnA_list, myfhand.get_num_entries(), helloTextField);
            exit_question = true;
            helloTextField.insert("Noch einmal starten ?", 0);
            helloTextField.setPreferredSize(-1,-1);            
            myform.addCommand(exitCommand); 
            while (!go_on && !do_exit) {}
            try {
                Thread.sleep(300);
            } catch (Exception e) {} 
            myform.removeCommand(exitCommand);                    
        } while (!do_exit);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
//            try {
                destroyApp(true);
                notifyDestroyed();
/*
        }
            catch (MIDletStateChangeException excep)
            {
                System.out.println(excep.getMessage());
            }*/
            return;
        }
        
        if (c == okayCommand) {
            if (!exit_question)
                mygui.next();
            else
                go_on = true;
        }
    }    
}
