/*
Author Name - Harpreet Kaur


*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*The class ChatRunnable has the following declaration:
ChatRunnable <T extends JFrame & Accessible> implements Runnable*/


public class ChatRunnable<T extends JFrame & Accessible> implements Runnable {
  
  private final T ui;  
  private final Socket socket;  
  private final ObjectInputStream inputStream;  
  private final ObjectOutputStream outputStream; ;  
  private final JTextArea display ;  
  
  
  
  /*Constructor
ChatRunnable (T ui, ConnectionWrapper connection)
The constructor uses the connection parameter and its get methods to initialize the
socket, inputStream, and outputStream fields. It uses the ui parameter to initialize the
display and the ui fields.*/

  public ChatRunnable (T ui, ConnectionWrapper connection){
  
      this.ui = ui;
      this.socket = connection.getSocket();
      this.inputStream = connection.getInputStream() ;
      this.outputStream = connection.getOutputStream() ;
      this.display = ui.getDisplay();
  }
  
  
  
    @Override
    public void run() {
        
        String str ;
        
            do{
            if(this.socket.isClosed()){
                break;
            }
            
            try {
                str = (String) this.inputStream.readObject();
                
                
                /*. Trim the strin and compare it to the CHAT_TERMINATOR string. If the trimmed
                strin equal to the CHAT_TERMINATOR string, a final string terminate is declared.
                Then terminate is assigned a string made of the following substrings:
                DISPLACMENT, current date and time, LINE_TERMINATOR, and strin.
                For the current date and time you must use the DateTimeFormmater class to
                format the date and time returned by the LocalDateTime now() method. The
                format must display the name of the month, the day of the month followed by a
                comma (,), the time, and the AM or PM. For example: October 31, 13:13 PM
                Finally, it uses the display field append() method to append the terminate string
                and then it breaks the loop. */
            } catch (IOException ex) {
                Logger.getLogger(ChatRunnable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChatRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        }       while(true);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
