package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jakub on 21.01.2017.
 */
public class Player extends Thread {

    private int ID; // potrzebne do identyfikacji przez serwer
    private Socket playerSocket;
    Player opponent;

    ObjectInputStream in;
    ObjectOutputStream out;


    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player(Socket socket, int playerNumber) {
        this.playerSocket = socket;
        this.ID = playerNumber;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int number) {
        this.ID = number;
    }

    public Socket getSocket(){
        return this.playerSocket;
    }

    public void run(){

        try {
            int result = (int)in.readObject();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
