/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author marcio
 */
public class GuessClient {

    /**
     * @param args the command line arguments
     */
 
    public static void main(String[] args) throws UnknownHostException, IOException {
        new GuessClient("127.0.0.1", 12345).execute();
    }
   
    private String host;
    private int port;
   
    public GuessClient (String host, int port) {
        this.host = host;
        this.port = port;
    }
   
    public void execute() throws UnknownHostException, IOException {
        for(int i=1; i<=3 ;i++) {
            Socket cliente = new Socket(this.host, this.port);
            System.out.println("Client "+ i +" connected to server!");

            ClientThread r = new ClientThread(i, cliente.getInputStream(), cliente.getOutputStream());
            new Thread(r).start();
        }
    }
}
