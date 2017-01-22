package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Jakub on 21.01.2017.
 */
public class Game extends JFrame implements Runnable, ActionListener{


    private boolean myTurn;
    private Player player;
    JButton playButton;

    Game(Player player, boolean myTurn){
        this.myTurn = myTurn;
        this.player = player;



        setSize(300, 300);
        setTitle("Dice Game made by Jakub Karło");
        setLocation(200, 100);
        setLayout(null);
        playButton = new JButton();
        playButton.setBounds(100, 100, 100, 40);
        playButton.setBorderPainted(true);
        playButton.setText("PLAY!");
        playButton.addActionListener(this);
        this.add(playButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);



    }


    public void isEnabled(boolean status) {
        for (Component comp : this.getComponents()) {
            comp.setEnabled(status);
        }
    }

    @Override
    public void run() {

        System.out.println("Welcome player " + player.getID());


        if (myTurn) isEnabled(true);
        else isEnabled(false);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
