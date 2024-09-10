package org.example;

public class Main {
    public static void main(String[] args)
    {
        var asztal = new Asztal();
        asztal.ujJatek();
        asztal.kor();
        asztal.addJatekos(new Robot());
        asztal.addJatekos(new Kezdo("Boti"));
        asztal.addJatekos(new Kezdo("Peti"));
        asztal.ujJatek();
        asztal.kor();
    }
}