package com.company;

import java.io.FileWriter;
import java.io.IOException;

//DP: Facade
public class Logger {
    private String buffer = "";
    private static int i = 0;

    public void AddInLogger(String s){
        buffer = buffer + s + "\n";
    }

    public void Concat(String s){
        buffer = buffer + s;
    }

    public void CleanLogger(){
        System.out.print(buffer);
        String temp = "aventura_" + i + ".txt";
        try {
            FileWriter writer = new FileWriter(temp);
            writer.write(buffer);
            writer.close();

        } catch (IOException e) {
            System.err.println(temp + " fail to create");
        }
        buffer = "";
        i++;
    }

    public String getBuffer() {
        return buffer;
    }
}
