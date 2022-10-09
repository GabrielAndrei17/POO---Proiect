package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadInput {

    public Antrenor ReadAntrenor(int index) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("antrenor.in"));
        String line;
        while ((line = br.readLine()) != null) {
            if (index == 0) {
                String[] array = line.split("###");

                try {
                    return new AntrenorBuilder()
                            .addPokemon(Adapter.CreatePokemonByName(array[2]))
                            .addPokemon(Adapter.CreatePokemonByName(array[3]))
                            .addPokemon(Adapter.CreatePokemonByName(array[4]))
                            .addItem(Adapter.CreateItem(array[5]))
                            .addItem(Adapter.CreateItem(array[6]))
                            .addItem(Adapter.CreateItem(array[7]))
                            .withNume(array[0])
                            .withVarsta(Integer.parseInt(array[1]))
                    .get();

                } catch (NumberFormatException e) {
                    System.err.println("Invalid Input");
                }
            }
            else index--;
        }
        return null;
    }
}
