package sample;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jakub on 21.01.2017.
 */
public class Server extends Thread {

    static ServerSocket serverSocket = null;
    static Socket connectionSocket = null;
    private int portNumber;

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

            while(true){

                try {
                    connectionSocket = serverSocket.accept();
                    System.out.println("New player has connected");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

    }

}
