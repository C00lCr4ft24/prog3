package org.example;

public class Robot extends Jatekos {
    private static int StaticID = 0;
    private final Integer id;
    public Robot() { id = StaticID; StaticID++; }
    @Override
    public String toString()
    {
        return "Robot" + id;
    }
    @Override
    public void lep()
    {
        System.out.println("\033[H\033[2J");
        System.out.println("Jatekos: " + this + "\n");
        currentAsztal.printInfo();
    }
}
