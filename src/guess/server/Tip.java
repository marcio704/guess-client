/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guess.server;

import java.io.Serializable;

/**
 *
 * @author marcio
 */
public class Tip implements Serializable {
    
    public static final int ABOVE = 0;
    public static final int BELOW = 1;
    
    private static final long serialVersionUID = 6380051259455797805L;
    
    private int position;
    private int value;
    
    public Tip(int position, int value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
