/*
Author  - Harpreet Kaur


*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ClientChatUI extends JFrame implements Accessible{
    

private JTextField message;
private JButton sendButton;
private JTextArea display;
private ObjectOutputStream outputStream;
private Socket socket;
private ConnectionWrapper connection;
    
private JButton conB;
private JTextField hptf ;
private JComboBox<String> pcbox;
  //constructor that takes the frame title. 
//frame title and call a method runClient().
    public ClientChatUI (String ft) { 
	
            this.setTitle(ft);
            this.runClient();
	}



 @Override
	public JTextArea getDisplay() {
		return this.display;
	}  
   
    
    public void closeChat(){
        if(!socket.isClosed()){
            
            try {
                this.connection.closeConnection();
                enableConnectButton();
            } catch (IOException ex) {
                Logger.getLogger(ClientChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

  
 /*reateClientUI() which takes no
parameters and returns JPanel. In that method the entire GUI must be created in a
JPanel and panel that returned at the end of the method. A handler of type
Controller must be added to all buttons and the combo box*/
       
    public JPanel createClientUI() {  
      
        Controller controller = new Controller(); 
        JPanel jp = new JPanel(new BorderLayout());  
          
           
  
    JPanel hp = new JPanel(new BorderLayout()); // host panel = hp
        JLabel hpl = new JLabel("Host:"); // host port label
            hpl.setLabelFor(hpl);
            hpl.setPreferredSize(new Dimension(35 ,30));
            hpl.setEnabled(true);
            hpl.setBounds(0,5,0,0); // amrgin
            hpl.requestFocusInWindow();
        hptf =new JTextField("localhost");   
        
        hp.add(hpl);
        hp.add(hptf);
        hpl.setHorizontalAlignment(JTextField.LEFT);
        
       /////port label
       
    JPanel pp = new JPanel(new BorderLayout()); // port panle = pp
        JLabel ppl = new JLabel("Port:"); 
        ppl.setPreferredSize(new Dimension(35 ,30));
        
        String pnum []  = {"8088" ,"65001", "6555"};
        
        ///comobo box
            pcbox = new  JComboBox<String> (pnum);
            ppl.setLabelFor(pcbox);
            pcbox.setEnabled(true);
            pcbox.addActionListener(controller);
            pcbox.setAlignmentX(RIGHT_ALIGNMENT);
          
           
  ///.........combo box
        
        
 ///------All buttons
    
        conB = new JButton("Connect");
            conB.setBackground(Color.RED);
	    conB.addActionListener(controller);
            
            
            
    
    /*The labels must have mnemonics and when the corresponding Alt-Key is
pressed the focus that transferred to the corresponding host text field or the
port combo box.
 At launch the host text field that  has the focus and (the insertion caret (cursor))
that  blinking at the beginning of the text field in front of the latter l of the text
localhost. The text (localhost) in*/ 
        pp.add(ppl);
       
        pp.add(pcbox , BorderLayout.WEST);
        pp.add(conB , BorderLayout.EAST);
        
        
    //--------CONNECTION panel
    /*The CONNECTION panel must have a 10 pixels red titled line border with
centered title*/ 
        JPanel cp = new JPanel(new BorderLayout());
         Border cpb = BorderFactory.createLineBorder(Color.RED, 10); 
         cp.setBorder(BorderFactory.createTitledBorder(cpb, "CONNECTION" ,TitledBorder.CENTER , TitledBorder.CENTER));
         cp.add(hp, BorderLayout.NORTH);
	 cp.add(pp, BorderLayout.SOUTH);
         

//-----------MESSAGE panel
    /*The MESSAGE panel must have a 10 pixels blue titled line border. */ 
        JPanel mp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Border mpb = BorderFactory.createLineBorder(Color.BLUE, 10);
		mp.setBorder(BorderFactory.createTitledBorder(mpb, "MESSAGE"));
    
        JTextField mtf = new JTextField(" Type message");
            mtf.setEditable(true);
            mtf.setHorizontalAlignment(JTextField.LEFT);
             /*The Send button must be disabled at launch and must have the same height as
the adjacent text field. The text field must display the specified text at launch.*/   
        sendButton = new JButton("Send");
              sendButton.setEnabled(false);
              sendButton.addActionListener(controller);

           mp.add(mtf);
           mp.add(sendButton);
                
                
                
    //-------- CHAT DISPLAY
    /*The CHAT DISPLAY panel must have a 10 pixels black titled line border with
centered title
    The CHAT DISPLAY text area must have 30 rows and 45 columns. The text area
that have horizontal and vertical scroll bars. Only the vertical scroll bar that
visible at launch. The horizontal scroll bar is not visible at launch but 
become visible if the displayed text exceeds the number of columns displayed.
The text area must not be editable*/ 
        
    JPanel cd = new JPanel(new BorderLayout());
         Border cdb = BorderFactory.createLineBorder(Color.BLACK, 10);
        // cd.setBorder(BorderFactory.createTitledBorder(mpb, " CHAT DISPLAY"));
         cd.setBorder(BorderFactory.createTitledBorder(cdb, " CHAT DISPLAY" ,TitledBorder.CENTER , TitledBorder.CENTER));
         JTextArea cta = new JTextArea(30, 45);
	 cta.setEditable(false);
         
         JScrollPane cspane = new JScrollPane(cta ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            cd.add(cspane , BorderLayout.CENTER);
         
         
         
    /*All buttons must have mnemonics.*/ 
        hpl.setDisplayedMnemonic(KeyEvent.VK_H);
        ppl.setDisplayedMnemonic(KeyEvent.VK_P);
        conB.setMnemonic(KeyEvent.VK_C);
        sendButton.setMnemonic(KeyEvent.VK_S); 
    
   JPanel npanel  = new JPanel(new BorderLayout()); // north panel comatining two panel 
        npanel.add(cp , BorderLayout.NORTH);
        npanel.add(mp , BorderLayout.SOUTH);
    
        
    jp.add(npanel , BorderLayout.NORTH );
  //  jp.add(cd , BorderLayout.SOUTH );
    jp.add(cd , BorderLayout.CENTER );
    
          return jp;
      }
        
  
        private void runClient() {
            
            this.setContentPane(createClientUI());
           this.addWindowListener(new WindowController());
        
       }

        
        /*void enableConnectButton()
The private method enables the Connect button, sets the background of the Connect
button to red, disables the Send button, and request the focus to the host text field. .*/
    private void enableConnectButton() {
        
        this.conB.setEnabled(true);
        this.conB.setBackground(Color.RED);
        this.sendButton.setEnabled(false);
        this.hptf.requestFocusInWindow();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
        /* WindowController which
inherits from WindowAdapter. You have to override the windowClosing() method. For
this part of the assignment you must simply call System.exit(0) inside the method*/
   /*The method using the outputStream tries to write the following object:
ChatProtocolConstants.CHAT_TERMINATOR
If exception occurs it calls System.exit(0); otherwise it calls System.exit(0).*/
    private class WindowController extends WindowAdapter{

        @Override
        public void windowClosing( WindowEvent we){
            try {
                outputStream.writeObject(ChatProtocolConstants.CHAT_TERMINATOR);
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(ClientChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
      
        
       
    }
 /*Controller class
    another private inner class Controller which inherits
from ActionListener. The Controller will handle the user evens of the GUI. You have
to override the actionPerformed() method. */
        
    
    
      private class Controller implements ActionListener {  
        
        @Override
        public void actionPerformed(ActionEvent ae) {
        
        boolean connected = false;
        if (ae.getActionCommand().equals("connect")){
            
            String host = hptf.getText();
            String textpc = (String) pcbox.getSelectedItem(); // text por tbox
            
            
            
            int port;
            port = Integer.parseInt(textpc);
            connected = connect(host, port);
            
      if (connected) {
            conB.setEnabled(false);/**/
            conB.setBackground(Color.BLUE);
            sendButton.setEnabled(true);
            message.requestFocusInWindow();
                /*it calls the method connect() and assigns the return value to the connect
variable. If the connect variable is true, the method disables the Connect button, makes
the background color of the Connect blue, enables the Send button, and requests the
focus to the message text field.*/
            // creates the client chat in a new thread
    ChatRunnable<ClientChatUI> runnable = new ChatRunnable<>(ClientChatUI.this, connection);
        Thread thread = new Thread(runnable);
        thread.start();
	}
      else {
      
      return;
      }
      if(ae.getActionCommand().equals("send")){
      send();
      
      }else{
      display.append("Error: NumberFormatException: Port value is not an integer: " + textpc
	+ ChatProtocolConstants.LINE_TERMINATOR);
        }}
        }//ChatProtocolConstants.DISPLACMENT+ sendMessage+ChatProtocolConstants.LINE_TERMINATOR

        
        
        private boolean connect(String host, int port) {
            
            try {
                Socket timeout = new Socket();
                
                if(socket.getSoLinger()!= -1) {
                    try {
                        socket.setSoLinger(true,5);
                    } catch (SocketException ex) {
                        Logger.getLogger(ClientChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(!socket.getTcpNoDelay()){
                    socket.setTcpNoDelay(true);
                }
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } catch (SocketException ex) {
                Logger.getLogger(ClientChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return false;
        
        }
      
        
        
        
        /*The method gets the text from the message text field assigns it to a local variable
sendMessage, appends it to the display adding a line terminator, and then uses the
outputStream to write the following string object:
ChatProtocolConstants.DISPLACMENT
+ sendMessage
+ChatProtocolConstants.LINE_TERMINATOR
If some run-time errors occur during the operation of the method, it must first call
enableConnectButton() method and then must display the errors on the chat display.
*/
        
        
       

        private void send() {
            
            try {
                String sendMessage = message.getText();
                display.append(sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
                
                outputStream.writeObject(
                        ChatProtocolConstants.DISPLACMENT + sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } catch (IOException ex) {
               display.append(" error!!!" + ChatProtocolConstants.LINE_TERMINATOR);
              // Logger.getLogger(ClientChatUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
      }
      
        
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
