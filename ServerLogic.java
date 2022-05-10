package com.company;

import java.net.ServerSocket;

public class ServerLogic
{
    public ServerSmth c1;
    public ServerSmth c2;
    private ServerSocket server;

    ServerLogic(ServerSmth c_1, ServerSmth c_2, ServerSocket serv)
    {
        this.c1 = c_1;
        this.c2 = c_2;
        this.server = serv;
    }

    public void write(String whom, String word)
    {
        try {
            if (whom.equals(c1.name)) {
                this.c1.write(word);
            }
            else if(whom.equals(c2.name))
            {
                this.c2.write(word);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void recalc(String who)
    {
        String word = this.c1.info;
        int cat = Integer.parseInt(word.substring(0, word.indexOf('/')));
        int dog = Integer.parseInt(word.substring(word.indexOf('/')+1));

        word = this.c2.info;
        cat += Integer.parseInt(word.substring(0, word.indexOf('/')));
        dog += Integer.parseInt(word.substring(word.indexOf('/')+1));

       if(who.equals(this.c1.name))
       {
            this.c1.info = new String(cat + "/0");
            this.c2.info = new String("0/" + dog);
       }
       else
       {
           this.c1.info = new String("0/" + dog);
           this.c2.info = new String(cat + "/0");
       }
    }

    public void mailNew()
    {
        this.c1.write(this.c1.info);
        this.c2.write(this.c2.info);
    }

}
