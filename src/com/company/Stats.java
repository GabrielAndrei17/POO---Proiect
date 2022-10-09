package com.company;

class Ability implements Cloneable{
    int dmg;
    boolean stunt;
    boolean dodge;
    int cooldown = 0;

    int defaultCD;

    public Ability(int dmg, boolean stunt, boolean dodge, int defaultCD) {
        this.dmg = dmg;
        this.stunt = stunt;
        this.dodge = dodge;
        this.defaultCD = defaultCD;
    }
    public void UpdateCooldown(){
        cooldown = defaultCD + 1;
    }
    public void DecCooldown(){
        if(cooldown != 0)
            cooldown--;
    }

    @Override
    public Ability clone() {
            return new Ability(dmg, stunt, dodge, defaultCD);

    }

    @Override
    public String toString() {
        return "{" +
                "dmg=" + dmg +
                ", stunt=" + stunt +
                ", dodge=" + dodge +
                ", cooldown=" + defaultCD +
                '}';
    }
}
public class Stats implements Cloneable{
    int hp;

    int attack;
    int sAttack;
    int defence;
    int sDefence;

    public Stats(int hp, int attack, int sAttack, int defence, int sDefence) {
        this.hp = hp;
        this.attack = attack;
        this.sAttack = sAttack;
        this.defence = defence;
        this.sDefence = sDefence;
    }

    public void Apply(Stats s){
        this.hp += s.hp;

        if(this.attack == 0)
            this.sAttack += s.sAttack;
        else
            this.attack += s.attack;

        this.defence += s.defence;
        this.sDefence += s.sDefence;
    }

    public boolean Damage(Stats s){
        if(s.sAttack == 0){
            if(defence > s.attack)
                return false;
            hp = hp - s.attack + defence;
        }
        if(s.attack == 0){
            if(sDefence > s.sAttack)
                return false;
            hp = hp - s.sAttack + sDefence;
        }

        return (hp <= 0);
    }

    @Override
    public Stats clone() {
        try {
            return (Stats) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return " {" +
                "hp=" + hp +
                ", attack=" + attack +
                ", sAttack=" + sAttack +
                ", defence=" + defence +
                ", sDefence=" + sDefence +
                '}';
    }
    public String getNume() {
        return "Obiectul nu are un nume";
    }

}
class Item extends Stats{
    private final String nume;

    public Item(int hp, int attack, int sAttack, int defence, int sDefence, String nume) {
        super(hp, attack, sAttack, defence, sDefence);
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
}
