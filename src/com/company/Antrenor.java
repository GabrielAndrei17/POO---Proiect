package com.company;

import java.util.List;

public class Antrenor{

    private final String nume;
    private final int varsta;
    private List<Pokemon> pokemoni;
    private final List<Stats> inventory;

    public Antrenor(String nume, int varsta, List<Pokemon> pokemoni, List<Stats> inventory) {
        this.nume = nume;
        this.varsta = varsta;
        this.pokemoni = pokemoni;
        this.inventory = inventory;
    }

    public void AddItems(Logger l){
        for (Pokemon pokemon : pokemoni)
            for(Stats stats : inventory){
                int add = (int) (Math.random() * 1.5);

                if(add == 1){
                    pokemon.ApplyItem(stats);
                    l.AddInLogger(this.nume + " i-a aplicat pokemonului " + pokemon.nume + " obiectul " + stats.getNume());
                }
            }
    }
    public void Wins(Pokemon p, Logger l){
        l.AddInLogger("Antrenorul " + nume + " in varsta de " + varsta + " ani, a castigat marea finala" +
                " cu cel mai bun pokemon al sau: " + p.nume);
    }

    public String getNume() {
        return nume;
    }

    public List<Pokemon> getPokemoni() {
        return pokemoni;
    }

    @Override
    public String toString() {
        return nume + ":{" +
                " varsta=" + varsta +
                ", pokemoni=" + pokemoni +
                ", inventory=" + inventory +
                '}';
    }
}
