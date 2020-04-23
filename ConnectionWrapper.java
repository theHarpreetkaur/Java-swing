/*
Author Name  - Harpreet Kaur

*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class ConnectionWrapper {
    
    
   //private Fields
private ObjectOutputStream outputStream;
private ObjectInputStream inputStream;
private Socket socket;
//Constructor
ConnectionWrapper(Socket socket){

    this.socket = socket;
}
//The constructor initializes the socket field only.

    ConnectionWrapper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  



/*Methods
Socket getSocket()
ObjectOutputStream getOutputStream()
ObjectInputStream getInputStream()
ObjectInputStream createObjectIStreams() throws IOException*/

public Socket getSocket(){

    return this.socket;
}

public ObjectOutputStream getOutputStream(){

    return this.outputStream;

}

public ObjectInputStream getInputStream(){

    return this.inputStream;
}
/*The method instantiates an object of ObjectOutputStream using the output stream of
the socket , assigns the reference to the outputStream field and returns the
outputStream reference.*/
public ObjectInputStream createObjectIStreams() throws IOException{

    try{
    return this.inputStream = new ObjectInputStream(this.socket.getInputStream());
    }
    
    catch(IOException e ){
         return printf("cannot create output stream");
    }
   
}

    private ObjectInputStream printf(String cannot_create_output_stream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

/*ObjectOutputStream createObjectOStreams() throws IOException
The method instantiates an object of ObjectOutputStream using the output stream of
the socket , assigns the reference to the outputStream field and returns the
outputStream reference.*/
  public ObjectOutputStream createObjectOStreams() throws IOException {
      
    try {
	return this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
    }
            catch (IOException e) {
           // return printf("cannot create output stream");
           throw new IOException();
            }
	}  
    

/*void createStreams() throws IOException
The method instantiates an object of ObjectOutputStream and assigns the reference to
the outputStream field. Then it instantiates an object of ObjectInputStream and assigns
the reference to the inputStream field.*/

    public void createStreams() throws IOException{
    
        try{
        
            this.outputStream = createObjectOStreams();
            this.inputStream = createObjectIStreams();
        }
        
        catch(IOException e){
        // return null; 
        throw new IOException();
        }
    
    }
  
    /*public void closeConnection()throws IOException
The method closes the output stream, the input stream, and the socket. Make sure that
you do not call the close() method on null references. Also make sure that you do not
call close() on a closed socket.*/
    
    public void closeConnection()throws IOException{

    
   try{
   if(null != this.inputStream){
   this.inputStream.close();
   
   }
   
   if(null != this.outputStream){
   this.outputStream.close();
   
   }
   if(null != this.socket){
   this.socket.close();
   
   }
    }
    catch (IOException e){
        // return null; 
        throw new IOException();
    }
    
    
    
    }

}
