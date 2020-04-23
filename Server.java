/*
Author Name - Harpreet Kaur


*//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Class that tests the Server.
import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    
   public static void main( String[] args ){
       
     // Socket s = null;
      int port = 65535;
     // String host = "localhost"; //"127.0.0.1"
     if(args.length == 0){
     
         System.out.print("defalut port = " +port);
     
     }
     else{
     
         port = Integer.parseInt(args[0]);
     }
       
       
         
      try {  
         int friend = 1;
         ServerSocket s = new ServerSocket(port);

         while (true)
         {  
            Socket socket = s.accept();
            if(socket.getSoLinger()!= -1) {
                socket.setSoLinger(true,5);}
            
            if(!socket.getTcpNoDelay()) {
                socket.setTcpNoDelay(true);}

         
          System.out.println("server connecter to client  " + socket.toString());
          
            friend++;
            
            final String stringtitle = "Harpreet's friend " + friend;
            launchClient(socket , stringtitle );
         }
      }
      catch (IOException e) {  
         e.printStackTrace();
      }
       

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   } // end main

   
    private static void launchClient(Socket socket , String stringtitle) {
     
        
        EventQueue.invokeLater(new Runnable() {


        @Override
        public void run() {
                ServerChatUI application = new ServerChatUI(socket); // create server
              //  application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
             //   application.waitForPackets(); //
                application.setTitle(stringtitle);
                application.setVisible(true);

                application.setLocationRelativeTo(null);
        }
                });
        }
       
        
        
    

}  // end class ServerTes

/*Class Server
The class Server must contain two method – the main() method and a static
launchClient() method. The launchClient() method contains two parameters – a Socket
and a String title. In the main method you must call launchClient() with a null argument
for the socket and a title string.
The launchClient() method creates the GUI in the event-dispatch thread. It creates a
frame calling the ServerChatUI() constructor with a null argument. The method sets the
frame title and the location of the frame before making the frame visible.*/