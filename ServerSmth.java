package com.company;

import java.io.*;
import java.net.Socket;

import static com.company.Main.sL;

public class ServerSmth extends Thread
{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public String name;
    public String info;
    private String nBour;

    public ServerSmth(Socket socket) throws Exception
    {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        this.name = in.readLine();

        System.out.println(this.name);

        start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                String w = this.in.readLine();

                if(w.equals("Cat")) {
                    this.write("Get");
                    sL.write(this.nBour, "Get");

                    this.info = this.in.readLine();

                    System.out.println(this.name + " " + this.info);

                    sL.recalc(this.name);
                    sL.mailNew();
                }

                if(w.indexOf('/') != -1) {
                    this.info = w;
                    System.out.println(this.name + " " + this.info);
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
                if(e.getMessage().equals("Connection reset")) {
                    break;
                }
            }
        }
    }

    public void write(String w)
    {
        try{

            this.out.write(w + "\n");
            this.out.flush();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setNB(String nB)
    {
        this.nBour = nB;
    }

    public void close()
    {
        try{

            this.socket.close();
            this.in.close();
            this.out.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
