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

    Game(Player player1, Player player2){
        currentPlayer = player1;

        player1.start();
        player2.start();


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




    public void isEnabled(boolean status) {
        for (Component comp : this.getComponents()) {
            comp.setEnabled(status);
        }
    }

    @Override
    public void run() {




    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
