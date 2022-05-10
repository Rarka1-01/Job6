package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    private static ServerSocket server;
    public static ServerSmth s_1;
    public static ServerSmth s_2;
    public static ServerLogic sL;

    public static void main(String[] args) throws IOException
    {

        try {
            try {
                server = new ServerSocket(4004);

                Socket s = server.accept();
                s_1 = new ServerSmth(s);

                s = server.accept();
                s_2 = new ServerSmth(s);

                s_1.setNB(s_2.name);
                s_2.setNB(s_1.name);

                sL = new ServerLogic(s_1, s_2, server);

            } finally {
                server.close();
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
