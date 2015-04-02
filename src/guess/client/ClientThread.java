/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.client;

import guess.client.dto.Guess;
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
    
    private InputStream server;
    private OutputStream client;
    int id;
    private Randomizer randomizer;

    public ClientThread(int id, InputStream server, OutputStream client) {
        this.id = id;
        this.server = server;
        this.client = client;
        this.randomizer = new Randomizer(this.id, DEFAULT_START, DEFAULT_END);
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(client);
                ObjectInputStream ois = new ObjectInputStream(server)) {
            boolean stopTrying = false;
            do {
                oos.writeObject(randomizer.getGuess());
                Tip serverResponse = (Tip) ois.readObject();
                if(serverResponse == Tip.OVER || serverResponse == Tip.WIN) {
                    System.out.println("Closing client " + id);
                    return;
                }
                randomizer.randomize(serverResponse);
                oos.reset();
            } while (!stopTrying);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
