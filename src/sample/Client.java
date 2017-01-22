package sample;

import javax.jws.Oneway;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Jakub on 22.01.2017.
 */
public class Client extends JFrame implements ActionListener {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    JButton playButton;
    Dice[] dices = new Dice[2];
    JLabel mainLabel;
    boolean myTurn;
    JPanel draw;



    public Client(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, 444);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());


        // initialize dices
        for(int i = 0; i <dices.length; i++){
            Dice dice = new Dice();
            dices[i] = dice;
        }


        setSize(300, 300);
        setTitle("Dice Game");
        setLocation(200, 100);
        setLayout(null);
        playButton = new JButton();
        playButton.setBorderPainted(true);
        playButton.setText("THROW!");
        playButton.addActionListener(this);

        mainLabel = new JLabel();
        mainLabel.setForeground(Color.BLACK);
        mainLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        mainLabel.setText("Please wait for opponent");
        add(mainLabel);


        draw = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                for (int i = 0, xRect = 40, yRect = 100; i < dices.length; i++, xRect += 120) {
                    g.setColor(Color.red);
                    g.fillRect(xRect, yRect, 100, 100);
                    g.setColor(Color.black);
                    if (dices[i].getNumber() == 1) {
                        g.fillOval(xRect + 35, yRect + 35, 30, 30);
                    } else if (dices[i].getNumber() == 2) {
                        g.fillOval(xRect + 65, yRect + 5, 30, 30);
                        g.fillOval(xRect + 5, yRect + 65, 30, 30);
                    } else if (dices[i].getNumber() == 3) {
                        g.fillOval(xRect + 65, yRect + 5, 30, 30);
                        g.fillOval(xRect + 35, yRect + 35, 30, 30);
                        g.fillOval(xRect + 5, yRect + 65, 30, 30);
                    } else if (dices[i].getNumber() == 4) {
                        g.fillOval(xRect + 5, yRect + 5, 30, 30);
                        g.fillOval(xRect + 65, yRect + 5, 30, 30);
                        g.fillOval(xRect + 5, yRect + 65, 30, 30);
                        g.fillOval(xRect + 65, yRect + 65, 30, 30);
                    } else if (dices[i].getNumber() == 5) {
                        g.fillOval(xRect + 5, yRect + 5, 30, 30);
                        g.fillOval(xRect + 65, yRect + 5, 30, 30);
                        g.fillOval(xRect + 35, yRect + 35, 30, 30);
                        g.fillOval(xRect + 5, yRect + 65, 30, 30);
                        g.fillOval(xRect + 65, yRect + 65, 30, 30);
                    } else if (dices[i].getNumber() == 6) {
                        g.fillOval(xRect + 5, yRect + 5, 30, 30);
                        g.fillOval(xRect + 65, yRect + 5, 30, 30);
                        g.fillOval(xRect + 35, yRect + 5, 30, 30);
                        g.fillOval(xRect + 5, yRect + 65, 30, 30);
                        g.fillOval(xRect + 65, yRect + 65, 30, 30);
                        g.fillOval(xRect + 35, yRect + 65, 30, 30);
                    }
                }
            }};


        draw.setSize(300,300);
        draw.add(mainLabel);
        draw.add(playButton);
        playButton.setVisible(true);
        playButton.setEnabled(false);
        add(draw);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public void play(){
        while(this.isVisible()){
            try {
                myTurn = in.readBoolean();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(myTurn) {
                playButton.setEnabled(true);
                mainLabel.setText("Now you can throw dices");

            }
            else {
                playButton.setEnabled(false);
                mainLabel.setText("Please wait for your turn");
            }
        }

    }

    public int throwDice(){
        int points=0;
        for (Dice currentDice : dices) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
            currentDice.setNumber(randomNum);
            points+= currentDice.getNumber();
        }
        return points;
    }


    public static void main(String[] args){
        while (true) {
            Client client = null;
            String serverAddress = "localhost";
            try {
                client = new Client(serverAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
            client.play();
        }
    }

    public void isEnabled(boolean status) {
        for (Component comp : this.getComponents()) {
            comp.setEnabled(status);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == playButton){
            int points = throwDice();
            try {
                out.writeObject(points);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        revalidate();
        repaint();
    }
}
