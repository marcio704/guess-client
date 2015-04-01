/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.client;

import guess.server.Tip;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcio
 */
public class ClientThread implements Runnable  {
    
    private static final int DEFAULT_START = 1;
    private static final int DEFAULT_END = 10;
    
    private InputStream servidor;
    private OutputStream client;
    int id;

    public ClientThread(int id, InputStream servidor, OutputStream client) {
        this.id = id;
        this.servidor = servidor;
        this.client = client;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(client);
                ObjectInputStream ois = new ObjectInputStream(servidor)) {
            boolean stopTrying = false;
            Client clientGuess = new Client(this.id, DEFAULT_START, DEFAULT_END);
            do {
                oos.writeObject(clientGuess);
                Object serverResponse = ois.readObject();
                if(serverResponse instanceof String && ((String) serverResponse).equals("exit")) {
                    System.out.println("Closing client " + id);
                    return;
                }
                if(serverResponse instanceof Tip) {
                    clientGuess = new Client(this.id, clientGuess.getStart(), clientGuess.getEnd(), (Tip) serverResponse);
                }
                oos.flush();
            } while (!stopTrying);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
