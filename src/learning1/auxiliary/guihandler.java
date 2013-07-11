/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learning1.auxiliary;

import javax.microedition.lcdui.TextField;

/**
 *
 * @author Wurzel
 */
public class guihandler {
    int my_element = 0;
    int ready_for_next = 0;
    
    public void QnA_handler(String[][] QnA, int num_entries, TextField my_textbox) {
        my_textbox.delete(0,my_textbox.size());
        for (int i=0; i<num_entries; i++)
        {
            my_textbox.insert("Frage : "+QnA[0][i], 0);
            my_textbox.setPreferredSize(-1,-1);
            while (ready_for_next == 0) {}
            ready_for_next = 0;
            my_textbox.delete(0,my_textbox.size());
            my_textbox.insert("Antwort : "+QnA[1][i], 0);
            my_textbox.setPreferredSize(-1,-1);            
            while (ready_for_next == 0) {}
            ready_for_next = 0;
            my_textbox.delete(0,my_textbox.size());
        }
    }
     
    public void next() {
        ready_for_next = 1;
    }
            
}
