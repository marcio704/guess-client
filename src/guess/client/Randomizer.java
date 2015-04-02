/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.client;

import guess.client.dto.Guess;
import guess.server.Tip;
import java.util.Random;

/**
 *
 * @author marcio
 */
public class Randomizer {
    
    private Guess guess;
    private int start;
    private int end;  
    private int guessNumber;
    
    private static final long serialVersionUID = 6380051259455797804L;
    
    public Randomizer(int id, int start, int end) {
        this.guess = new Guess();
        this.guess.setId(id);
        this.start = start;
        this.end = end;
        this.guessNumber = generateRandomInteger(this.start, this.end, new Random());
        this.guess.setGuessNumber(guessNumber);
    }
    
    public synchronized void randomize (Tip tip) {
        this.start = tip == Tip.BIGGER ? this.guessNumber : this.start;
        this.end = tip == Tip.LESSER ? this.guessNumber :  this.end;
        this.guessNumber = generateRandomInteger(this.start, this.end, new Random());
        this.guess.setGuessNumber(this.guessNumber);
    }
    
    private  Integer generateRandomInteger(int start, int end, Random random) {
        if (start > end) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)end - (long)start + 1;
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + start);    
        
        System.out.println("Client " +  this.guess.getId() + " attempting between " + this.start + " and " + this.end);
        System.out.println("Value generated by client "+ this.guess.getId() +": " + randomNumber);
        return randomNumber;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
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

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }
    
    
}
