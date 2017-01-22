package sample;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by Jakub on 21.01.2017.
 */
public class Server extends Thread {

    static ServerSocket serverSocket = null;
    static Socket connectionSocket = null;
    private int portNumber;

    static ArrayList<Player>playerList = new ArrayList<>();


    // TU damy metodke z parserem do sczytywania xml
    public void getServerData (String fileName){
        this.portNumber = 444;
    }

    public Server(){

        getServerData("elo"); // do wymiany, najlepiej tu dać parserem szukanie w konstruktorze, bo to wywołanie bez sensu jest

        try {
            serverSocket = new ServerSocket(portNumber); // here we've got number from xml

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error finding port");
            System.exit(1);
        }


    }


    public static void main(String[] args) {

        Server server = new Server();
        server.start();

    }

    public void run(){

        int playerCounter = 0;

            while(true){

                try {
                    connectionSocket = serverSocket.accept();
                    System.out.println("New player has connected");
                    playerList.add(new Player(connectionSocket, ++playerCounter)); // id trzeba

                    //wystartuj grę
                    if(playerCounter%2 == 0) {
                        System.out.println("Starting new room...");
                        Game gamePlayer1 = new Game(playerList.get(0), true); // player1 always will start
                        Game gamePlayer2 = new Game(playerList.get(1), false);
                        Thread t1 = new Thread(gamePlayer1);
                        Thread t2 = new Thread(gamePlayer2);
                        t1.start();
                        t2.start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

    }

}
