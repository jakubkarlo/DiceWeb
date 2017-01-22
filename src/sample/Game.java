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

    Game(){




    }


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
