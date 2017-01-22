package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Jakub on 21.01.2017.
 */
public class Game extends JFrame implements Runnable, ActionListener{


    private boolean myTurn;
    Player currentPlayer;
    Player anotherPlayer;

    Game(Player player1, Player player2){
        currentPlayer = player1;
        anotherPlayer = player2;
//        player1.start();
//        player2.start();


    }


//    public int getResult(Player player) {
//        if (player == currentPlayer) {
//            try {
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            currentPlayer = currentPlayer.opponent;
//        }
//    }






    @Override
    public void run() {

        int result =0 ,secondResult=0;



        while (currentPlayer.getScore() < 3 && anotherPlayer.getScore() < 3) {

            try {
                result = (int) currentPlayer.in.readObject();
                System.out.println(result);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                secondResult = (int) anotherPlayer.in.readObject();
                System.out.println(secondResult);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (result > secondResult) {
                System.out.println("Player " + currentPlayer.getID() + " won this round");
                currentPlayer.setScore(currentPlayer.getScore()+1);
            } else {
                System.out.println("Player " + anotherPlayer.getID() + " won this round");
                anotherPlayer.setScore(anotherPlayer.getScore()+1);
            }

        }
        System.out.println("The game has ended");
        System.out.println("Player " + currentPlayer.getID() +" score is: " + currentPlayer.getScore());
        System.out.println("Player " + anotherPlayer.getID() +" score is: " + anotherPlayer.getScore());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
