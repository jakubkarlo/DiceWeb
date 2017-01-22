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

    ServerSocket serverSocket = null;
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
                    Player player1 = new Player(serverSocket.accept(), ++playerCounter);
                    System.out.println("New player has connected");
                    Player player2 = new Player(serverSocket.accept(), ++playerCounter);
                    System.out.println("New player has connected");
                    player1.setOpponent(player2);
                    player2.setOpponent(player1);
                    Game game = new Game(player1, player2);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

    }

}
