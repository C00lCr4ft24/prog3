package org.example;

public class Kezdo extends Jatekos {

    private final String nev;
    public Kezdo(String nev) {this.nev = nev; }

    @Override
    public String toString()
    {
        return nev;
    }
    @Override
    public void lep()
    {
        System.out.println("\033[H\033[2J");
        System.out.println("Jatekos: " + this + "\n");
        currentAsztal.printInfo();
        if (currentAsztal.getKorCount() % 2 == 1) { currentAsztal.emel(0); }
        else                                      { currentAsztal.emel(1); }

    }
}
