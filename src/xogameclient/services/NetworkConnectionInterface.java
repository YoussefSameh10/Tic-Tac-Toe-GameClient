/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Youssef
 */
interface NetworkConnectionInterface {
    public Socket getServer();
    public DataInputStream getDataInputStream();
    public PrintStream getPrintStream();
}
