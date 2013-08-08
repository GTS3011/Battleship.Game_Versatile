package gr.epp.thesis.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author George Tsoutsas, 2542
 */
/**
 * Deals with incoming and outgoing data from/to the clients.
 */
public class NumbersThread extends Thread implements Runnable {

    private DataInputStream in = null;
    private DataOutputStream out = null;
    private Socket clientSocket;
    private static NumbersThread[] threads;
    private int maxClientsCount; //Total number of clients connected to the server in the current session (even the disconnected).
    private int value;
    private boolean hh;

    public NumbersThread(Socket clientSocket, NumbersThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    /**
     * Thread to read incoming data from a client and send data to the rest of
     * the clients.
     */
    @Override
    public void run() {
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            while (true) {
                synchronized (this) {
                    value = in.readInt();
                    //hh = in.readBoolean();
                    // Send to all clients except itself.
                    for (int i = 0; i < maxClientsCount; i++) {
                        if (threads[i] != null && threads[i] != this) {
                            System.out.println("Sending " + value + " to PC " + i);
                            threads[i].out.writeInt(value);
                            //threads[i].out.writeBoolean(hh);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            //When a user disconnects, its position is emptied
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }
        }
    }
}
