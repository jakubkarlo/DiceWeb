package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Jakub on 21.01.2017.
 */
public class Player {

    private int ID; // potrzebne do identyfikacji przez serwer
    private Socket playerSocket;

    public Player(Socket socket, int playerNumber){
        this.playerSocket = socket;
        this.ID = playerNumber;
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



    public static void main(String[] args) throws IOException {

        Socket socket = null;
        int portNumber = 444; // tu trzeba wczytać jak na serwerze

        try {
            socket = new Socket(InetAddress.getLocalHost(), portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayerService playerService = new PlayerService();
        Thread server = new Thread(playerService);
        server.start();

    }

}
