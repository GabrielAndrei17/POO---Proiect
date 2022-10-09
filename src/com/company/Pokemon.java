package com.company;

import java.util.Arrays;

public class Pokemon implements Cloneable{
    String nume;
    Stats stats;

    Ability[] abilities;
    boolean isStunt = false;
    boolean isDodging = false;

    public Pokemon(String nume, Stats stats, Ability[] abilities) {
        this.nume = nume;
        this.stats = stats;
        this.abilities = abilities;
    }

    public void ApplyItem(Stats s){
        this.stats.Apply(s);
    }
    public boolean TakeDmgBy(Stats s, Logger l){
        boolean dead = this.stats.Damage(s);

        l.Concat(this.nume + " Hp = " + stats.hp + " a primit dmg: ");
        if(s.attack != 0)
            l.AddInLogger(s.attack + "");
        else
            l.AddInLogger(s.sAttack + "");
        return dead;
    }
    public boolean TakeDmgSpecialAbility(Ability a, Logger l){
        isStunt = a.stunt;
        if(isStunt)
            l.AddInLogger(this.nume + " a primit stunt");

        stats.hp -= a.dmg;
        l.AddInLogger(this.nume + " Hp = " + stats.hp + " a primit dmg: " + a.dmg);

        return (stats.hp <= 0);
    }
    public boolean Attack(Pokemon p, Logger l){

        if(this.isStunt){
            l.AddInLogger(this.nume + " este stuned si nu a executat nicio miscare");
            this.isStunt = false;
            return false;
        }

        int ability = (int)(Math.random() * 3);

        if(p.isDodging){
            l.AddInLogger(p.nume + " are dodge " + this.nume + " nu a reusit sa il atace cu miscara " + ability);
            p.isDodging = false;
            return false;
        }

        boolean dead;
        if(abilities != null) {
            l.AddInLogger("[Abilitati: 1 - " + abilities[0].cooldown + " cooldown; 2 - " + abilities[1].cooldown + " cooldown]");

            if(ability != 0) {
                Ability myAbility = abilities[ability - 1];

                if (myAbility.cooldown == 0) {
                    l.AddInLogger(this.nume + " executa abilitatea speciala: " + ability);
                    if (myAbility.dodge) {
                        this.isDodging = true;
                        l.AddInLogger(this.nume + " are dodge si nu va primi damage la urmatoarea miscare");
                    }
                    dead = p.TakeDmgSpecialAbility(myAbility, l);

                    myAbility.UpdateCooldown();
                    l.AddInLogger("Abilitatea speciala: " + ability + " are cooldown : " + myAbility.cooldown);
                    return dead;
                }
            }
        }

        l.AddInLogger(this.nume + " executa miscarea standard ");
        dead = p.TakeDmgBy(stats, l);
        return dead;
    }
    public void DecAbilitiesCD(){
        //fiecarei abilitati ii va scadea cooldownul
        if(abilities == null)
            return;
        for(Ability aux : abilities)
            if(aux != null)
                aux.DecCooldown();
    }

    @Override
    public String toString() {
        return  nume + "{" +
                " stats=" + stats +
                ", abilities=" + Arrays.toString(abilities) +
                '}';
    }

    @Override
    public Pokemon clone() {
        try {
            Pokemon clone = (Pokemon) super.clone();

            clone.setStats(stats.clone());
            if(abilities != null)
                clone.setAbilities(abilities.clone());

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public void castiga(Logger l) {
        l.AddInLogger(nume + " castiga aventura!!!");
    }

    public void Evolution(Logger l){
        ApplyItem(new Stats(1, 1, 1, 1, 1));
        l.AddInLogger(nume + " are +1 la caracteristicile din status");
    }

    public boolean compare(Pokemon p1){
        if(p1 == null)
            return true;

        int sum1 = stats.sDefence + stats.hp + stats.defence + stats.attack + stats.sAttack;
        int sum2 = p1.stats.sDefence + p1.stats.hp + p1.stats.defence + p1.stats.attack + p1.stats.sAttack;

        return  (sum1 > sum2);
    }
}


