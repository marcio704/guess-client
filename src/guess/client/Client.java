package guess.client;


import guess.server.Tip;
import java.io.Serializable;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcio
 */
public class Client implements Serializable {
    
    private int id;
    private int start;
    private int end;
    private int guessNumber;
    
    private static final long serialVersionUID = 6380051259455797804L;
    
    public Client(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.guessNumber = generateRandomInteger(this.start, this.end, new Random());
    }
    
    public Client (int id, int oldStart, int oldEnd, Tip tip) {
        this.id = id;
        this.start = tip.getPosition() == Tip.ABOVE ? tip.getValue() : oldStart;
        this.end = tip.getPosition() == Tip.BELOW ? tip.getValue() : oldEnd;
        this.guessNumber = generateRandomInteger(this.start, this.end, new Random());
    }
    
    private Integer generateRandomInteger(int start, int end, Random random) {
        if (start > end) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)end - (long)start + 1;
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + start);    
        
        System.out.println("Client " + id + " attempting between " + this.start + " and " + this.end);
        System.out.println("Value generated by client "+ id +": " + randomNumber);
        return randomNumber;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }  

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
}
