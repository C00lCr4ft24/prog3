package org.example;

import java.util.Scanner;

public abstract class Jatekos {
    protected Asztal currentAsztal = null;
    public void setAsztal(Asztal asztal) {currentAsztal = asztal;}
    public void lep()
    {
        System.out.println("\033[H\033[2J");
        System.out.println("Jatekos: " + this + "\n");
        currentAsztal.printInfo();
        System.out.print("Tet Emelese Ennyivel: ");
        Scanner scanner = new Scanner(System.in);
        double tet = scanner.nextDouble();
        currentAsztal.emel(tet);
    }
}
