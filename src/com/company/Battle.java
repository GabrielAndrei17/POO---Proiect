package com.company;

public class Battle {
    public static void PvEBattle(Pokemon pokemon1, Pokemon pokemon2, Logger l){

        l.AddInLogger(". \na) " + pokemon1.nume + " turn");
        pokemon1.Attack(pokemon2, l);

        l.AddInLogger("\nb) " + pokemon2.nume + " turn");
        pokemon2.Attack(pokemon1, l);
    }

    public static void PvPBattle(Pokemon pokemon1, Pokemon pokemon2, Logger l){
        l.AddInLogger(". \na) " + pokemon1.nume + " turn");

        Logger l1 = new Logger();
        Logger l2 = new Logger();
        Thread t1 = new Thread(new Attack(pokemon1, pokemon2, l1));
        Thread t2 = new Thread(new Attack(pokemon2, pokemon1, l2));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        l.Concat(l1.getBuffer());
        l.AddInLogger("\nb) " + pokemon2.nume + " turn");
        l.Concat(l2.getBuffer());

    }

    public static boolean BattlePokemon(Pokemon p1, Pokemon p2, Logger l){
        boolean dead1 = false;
        boolean dead2 = false;
        Pokemon pokemon1 = p1.clone();
        Pokemon pokemon2 = p2.clone();

        boolean PVP = (p1.abilities != null);

        int moves = 0;
        l.AddInLogger("\nBatalia dintre: " + p1.nume + " vs " + p2.nume );
        while(!dead1 && !dead2){
            moves ++;
            l.Concat("\n" + moves);

            if(PVP)
                PvPBattle(pokemon1, pokemon2, l);
            else
                PvEBattle(pokemon1, pokemon2, l);

            dead1 = (pokemon1.stats.hp <= 0);
            dead2 = (pokemon2.stats.hp <= 0);
            pokemon1.DecAbilitiesCD();
            pokemon2.DecAbilitiesCD();
        }

        if(!dead1) {
            l.AddInLogger("\n" + pokemon1.nume + " castiga cu " + pokemon1.stats.hp + " hp; " + pokemon2.nume + " pierde");
            p1.Evolution(l);
            return false;
        }

        l.AddInLogger("\n" + pokemon2.nume + " castiga cu " + pokemon2.stats.hp + " hp; " + pokemon1.nume + " pierde");
        p2.Evolution(l);
        return true;

    }

    public static boolean Aventura(Antrenor a1, Antrenor a2, int order){
        //Batalia dintre pokemonul i al primului antrenor si p i celui de-al doilea antrenor cu neutrelii
        Pokemon p1 = a1.getPokemoni().get(order);
        Pokemon p2 = a2.getPokemoni().get(order);
        Pokemon n1 = Adapter.CreatePokemonByName("Neutrel1");
        Pokemon n2 = Adapter.CreatePokemonByName("Neutrel2");

        boolean dead1 = false, dead2 = false;
        Logger l = new Logger();
        l.AddInLogger(order+1 + " ) Antrenorul " + a1.getNume() + " cu pokemonul " + p1.nume + " vs " +
                "Antrenorul " + a2.getNume() + " cu pokemonul " + p2.nume);

        int round = (int)(Math.random() * 6);
        while(round != 5 && !dead1 && !dead2){
            int enemy = (int)(Math.random() * 2);

            if(enemy == 0){
                dead1 = BattlePokemon(p1, n1, l);
                dead2 = BattlePokemon(p2, n1, l);
            }
            else {
                dead1 = BattlePokemon(p1, n2, l);
                dead2 = BattlePokemon(p2, n2, l);
            }

            round = (int)(Math.random() * 6);
        }
        if(dead1) {
            p2.castiga(l);
            l.CleanLogger();
            return false;
        }
        if(dead2) {
            p1.castiga(l);
            l.CleanLogger();
            return true;
        }

        l.AddInLogger("Se vor confrunta cei 2 pokemoni");

        dead1 = BattlePokemon(p1, p2, l);
        dead2 = (p2.stats.hp <= 0);

        if(dead1 && dead2){
            l.AddInLogger("DRAW");
            l.CleanLogger();
            return true;
        }

        if(dead1)
            p2.castiga(l);
        else
            p1.castiga(l);

        l.CleanLogger();
        return !dead1;
    }
    public static void ArenaBattle(Antrenor a1, Antrenor a2) {
        Logger l = new Logger();
        a1.AddItems(l);
        a2.AddItems(l);
        l.CleanLogger();

        Pokemon bestPokemon1 = null, bestPokemon2 = null;

        boolean firstWon;
        for (int i = 0; i < a1.getPokemoni().size(); i++) {
            firstWon = Aventura(a1, a2, i);

            if (firstWon && a1.getPokemoni().get(i).compare(bestPokemon1)) {
                bestPokemon1 = a1.getPokemoni().get(i);
            }
            if (!firstWon && a2.getPokemoni().get(i).compare(bestPokemon2)) {
                bestPokemon2 = a2.getPokemoni().get(i);
            }
        }

        if (bestPokemon1 == null){
            l.AddInLogger(a1.getNume() + " nu are niciun pokemon castigator, deci nu va intra in lupta finala");
            a2.Wins(bestPokemon2, l);
            l.CleanLogger();
            return;
        }
        if(bestPokemon2 == null) {
            l.AddInLogger(a2.getNume() + " nu are niciun pokemon castigator, deci nu va intra in lupta finala");
            a1.Wins(bestPokemon1, l);
            l.CleanLogger();
            return;
        }

        l.AddInLogger("Marea finala dintre antrenorii: " + a1.getNume() + " vs " + a2.getNume() + " si cei mai buni pokemoni");
        firstWon = BattlePokemon(bestPokemon1, bestPokemon2, l);

        if(firstWon)
            a1.Wins(bestPokemon1, l);
        else a2.Wins(bestPokemon2, l);

        l.CleanLogger();
    }
}