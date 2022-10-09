package com.company;

import java.util.ArrayList;
import java.util.List;

//DP: Builder
public class AntrenorBuilder{
    private String nume;
    private int varsta;
    private final List<Pokemon> pokemons;
    private final List<Stats> inventory;

    public AntrenorBuilder() {
        pokemons = new ArrayList<>();
        inventory = new ArrayList<>();
    }

    public AntrenorBuilder withNume(String nume) {
        this.nume = nume;
        return this;
    }

    public AntrenorBuilder withVarsta(int varsta) {
        this.varsta = varsta;
        return this;
    }

    public AntrenorBuilder addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return this;
    }

    public AntrenorBuilder addItem(Stats item) {
        this.inventory.add(item);
        return this;
    }

    public Antrenor get(){
        return new Antrenor(nume, varsta, pokemons, inventory);
    }
}
