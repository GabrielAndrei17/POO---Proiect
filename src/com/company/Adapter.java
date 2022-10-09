package com.company;

//DP: Adapter
public class Adapter {
    private static Pokemon neutrel1 = null;
    private static Pokemon neutrel2 = null;
    public static Pokemon CreatePokemonByName(String name){
        switch (name) {
            case "Pikachu" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(6, false, false, 4);
                abilities[1] = new Ability(4, true, true, 5);
                return new Pokemon(name, new Stats(35, 0, 4, 2, 3), abilities);
            }
            case "Bulbasaur" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(6, false, false, 4);
                abilities[1] = new Ability(5, false, false, 3);
                return new Pokemon(name, new Stats(42, 0, 5, 3, 1), abilities);
            }
            case "Charmander" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(4, true, false, 4);
                abilities[1] = new Ability(7, false, false, 6);
                return new Pokemon(name, new Stats(50, 4, 0, 3, 2), abilities);
            }
            case "Squirtle" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(4, false, false, 3);
                abilities[1] = new Ability(2, true, false, 2);
                return new Pokemon(name, new Stats(60, 0, 3, 5, 5), abilities);
            }
            case "Snorlax" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(6, false, false, 4);
                abilities[1] = new Ability(4, true, true, 5);
                return new Pokemon(name, new Stats(62, 3, 0, 6, 4), abilities);
            }
            case "Vulpix" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(8, true, false, 6);
                abilities[1] = new Ability(2, false, true, 7);
                return new Pokemon(name, new Stats(36, 5, 0, 2, 4), abilities);
            }
            case "Eevee" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(5, false, false, 3);
                abilities[1] = new Ability(3, true, false, 3);
                return new Pokemon(name, new Stats(39, 0, 4, 3, 3), abilities);
            }
            case "Jigglypuff" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(4, true, false, 4);
                abilities[1] = new Ability(3, true, false, 4);
                return new Pokemon(name, new Stats(34, 4, 0, 2, 3), abilities);
            }
            case "Meowth" -> {
                Ability[] abilities = new Ability[2];
                abilities[0] = new Ability(5, false, true, 4);
                abilities[1] = new Ability(1, false, true, 3);
                return new Pokemon(name, new Stats(41, 3, 0, 4, 2), abilities);
            }
            //DP: Singleton
            case "Neutrel1" -> {
                if (neutrel1 == null)
                    return (neutrel1 = new Pokemon(name, new Stats(10, 3, 0, 1, 1), null));
                else
                    return neutrel1;
            }
            case "Neutrel2" -> {
                if (neutrel2 == null)
                    return (neutrel1 = new Pokemon(name, new Stats(20, 4, 0, 1, 1), null));
                else
                    return neutrel2;
            }
        }
        return  new PokemonAdapter(name).getPokemon();
    }

    public static Stats CreateItem(String name){
        return switch (name) {
            case "Scut" -> new Item(0, 0, 0, 2, 2, "Scut");
            case "Vestă" -> new Item(10, 0, 0, 0, 0, "Vesta");
            case "Săbiuță" -> new Item(0, 3, 0, 0, 0, "Sabiuta");
            case "Baghetă Magică" -> new Item(0, 0, 3, 0, 0, "Bagheta Magica");
            case "Vitamine" -> new Item(2, 2, 2, 0, 0, "Vitamine");
            case "Brad De Crăciun" -> new Item(0, 3, 0, 1, 0, "Brad de Craciun");
            default -> new ItemAdapter(name).getItem();
        };
    }
}
class PokemonAdapter{
    private Pokemon pokemon;

    public PokemonAdapter(String nume) {
        pokemon = new Pokemon(nume, new Stats(20, 4, 0, 1, 1), null);
    }
    public Pokemon getPokemon(){
        return pokemon;
    }
}

class ItemAdapter{
    private Item item;

    public ItemAdapter(String nume) {
        item = new Item(1, 1, 1, 1, 1, nume);
    }
    public Item getItem(){
        return item;
    }
}
