package com.company;

public class Attack implements Runnable {
    Pokemon pokemon1;
    Pokemon pokemon2;
    Logger logger;

    public Attack(Pokemon pokemon1, Pokemon pokemon2, Logger logger) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.logger = logger;
    }

    @Override
    public void run() {
        pokemon1.Attack(pokemon2, logger);
    }

}
