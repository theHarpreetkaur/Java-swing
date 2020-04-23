/*
Author Name - Harpreet Kaur


*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ClientChatUI chatUI = new ClientChatUI("Harpreet's chatIU");
                    chatUI.setSize(588, 500); // set frame size
                    chatUI.setLocationByPlatform(true);
                    chatUI.setVisible( true );
    
    
    }
    
    
}