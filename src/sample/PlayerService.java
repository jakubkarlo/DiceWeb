package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jakub on 21.01.2017.
 */
public class PlayerService implements Runnable{

    static ServerSocket serverSocket;
    static Socket clientSocket;

    PlayerService(){
        try {
            serverSocket = new ServerSocket(444);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Game(Server.playerList.get(1), false);
    }
}
