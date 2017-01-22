package sample;

import java.io.Serializable;

/**
 * Created by Jakub on 22.01.2017.
 */
public class Dice implements Serializable {

    private int number;


    public Dice(){
        this.number = 0;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return this.number;
    }
}
