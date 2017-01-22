package sample;

import javax.jws.Oneway;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by Jakub on 22.01.2017.
 */
public class Client extends JFrame implements ActionListener {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    JButton playButton;


    public Client(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, 444);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());


        setSize(300, 300);
        setTitle("Dice Game made by Jakub Karło");
        setLocation(200, 100);
        setLayout(null);
        playButton = new JButton();
        playButton.setBounds(100, 100, 100, 40);
        playButton.setBorderPainted(true);
        playButton.setText("THROW!");
        playButton.addActionListener(this);


        JPanel draw = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                int xRect = 40, yRect = 100;
                g.setColor(Color.red);
                g.fillRect(xRect, yRect, 100, 100);
                g.setColor(Color.black);
                g.fillOval(xRect + 5, yRect + 5, 30, 30);
                g.fillOval(xRect + 65, yRect + 5, 30, 30);
                g.fillOval(xRect + 35, yRect + 5, 30, 30);
                g.fillOval(xRect + 5, yRect + 65, 30, 30);
                g.fillOval(xRect + 65, yRect + 65, 30, 30);
                g.fillOval(xRect + 35, yRect + 65, 30, 30);
                xRect = 160;
                g.setColor(Color.red);
                g.fillRect(xRect, yRect, 100, 100);
                g.setColor(Color.black);
                g.fillOval(xRect + 5, yRect + 5, 30, 30);
                g.fillOval(xRect + 65, yRect + 5, 30, 30);
                g.fillOval(xRect + 35, yRect + 5, 30, 30);
                g.fillOval(xRect + 5, yRect + 65, 30, 30);
                g.fillOval(xRect + 65, yRect + 65, 30, 30);
                g.fillOval(xRect + 35, yRect + 65, 30, 30);
            }};


        draw.setSize(300,300);
        draw.add(playButton);
        add(draw);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }


    public void play(){
        while(true){
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == playButton){

        }
    }
}
