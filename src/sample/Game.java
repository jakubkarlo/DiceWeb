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
public class Game extends JFrame implements Runnable {


    Player player1;
    Player player2;
    Player currentPlayer;
    private boolean myTurn;


    Game(Player player1, Player player2) {
        System.out.println("Starting new room");
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        // first player can start now
        try {
            player1.getOutputStream().writeBoolean(true);
            player1.getOutputStream().flush();
            player2.getOutputStream().writeBoolean(false);
            player2.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void interactWithUser (int result, ObjectInputStream input, ObjectOutputStream player1Output, ObjectOutputStream player2Output, Player nextPlayer) throws IOException, ClassNotFoundException {
        result = (int)input.readObject();
        System.out.println(result);
        player1Output.writeBoolean(false);
        player1Output.flush();
        currentPlayer = nextPlayer;
        player2Output.writeBoolean(true);
        player2Output.flush();
    }


    @Override
    public void run() {

        int result = 0, secondResult = 0;
        ObjectInputStream player1Input = player1.getInputStream(), player2Input = player2.getInputStream();
        ObjectOutputStream player1Output = player1.getOutputStream(), player2Output = player2.getOutputStream();


        while (player1.getScore() < 3 && player2.getScore() < 3) {

            if (currentPlayer == player1) {
                try {
                    interactWithUser(result, player1Input, player1Output, player2Output, player2);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (currentPlayer == player2) {
                try {
                    interactWithUser(secondResult, player2Input, player2Output, player1Output, player1);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (result > secondResult) {
                    System.out.println("Player " + player1.getID() + " won this round");
                    player1.setScore(player1.getScore() + 1);
                } else {
                    System.out.println("Player " + player2.getID() + " won this round");
                    player2.setScore(player2.getScore() + 1);
                }

            }
        }
        System.out.println("The game has ended");
        System.out.println("Player " + player1.getID() + " score is: " + player1.getScore());
        System.out.println("Player " + player2.getID() + " score is: " + player2.getScore());
    }


}
