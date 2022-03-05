/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Youssef
 */
public class NetworkConnection implements NetworkConnectionInterface {
    
    private static NetworkConnection instance;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;

    public Socket getServer() {
        return server;
    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public PrintStream getPrintStream() {
        return ps;
    }
    
    private NetworkConnection() throws IOException {
        server = new Socket("127.0.0.1", 8080);
        dis = new DataInputStream(server.getInputStream());
        ps = new PrintStream(server.getOutputStream());
    }
    
    public static NetworkConnection getInstance() throws IOException {
        if(instance == null) {
            return new NetworkConnection();
        }
        else {
            return instance;
        }
    }
}
