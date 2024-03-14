package main;
import java.net.*;
import java.io.*;
import main.multiServerThread.MultiServerThread;

public class MultiServer {
	private static int noOfClients=0;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(2222);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 2222.");
            System.exit(-1);
        }

        while (listening)
	    new MultiServerThread(serverSocket.accept()).start();

        serverSocket.close();
    }
    public static void addMe(){
		noOfClients++;
		System.out.println("Current no. of clients connected: "+noOfClients);
	}
    public static void removeMe(){
		noOfClients--;
		System.out.println("Current no. of clients connected: "+noOfClients);
	}

}
