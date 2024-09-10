package org.example;

import java.util.HashSet;
import java.util.Random;

public class Asztal {
    private static final int meret = 10;
    private static double tet;
    private static int korCount;
    private static double goal;
    private static Jatekos winner = null;
    private static boolean aroundGoal;
    private static boolean overGoal;
    private final HashSet<Jatekos> jatekosok = new HashSet<>();
    public void addJatekos(Jatekos jatekos)
    {
        try
        {
            if (jatekosok.size() < meret)
            {
                jatekosok.add(jatekos);
                jatekos.setAsztal(this);
            }
            else throw new ArrayIndexOutOfBoundsException("Az asztal megtelt!");
        } catch (ArrayIndexOutOfBoundsException e) { e.printStackTrace(); }
    }
    public void ujJatek()
    {
        tet = 0;
        korCount = 0;
        winner = null;
        Random random = new Random();
        goal = random.nextDouble(100);
        aroundGoal();
        overGoal();
        System.out.println(goal); /////////////////////////////////////////////////
    }
    public void kor()
    {
        try
        {
            if (jatekosok.isEmpty()) throw new NincsJatekos();
            while (!aroundGoal && !overGoal)
            {
                for (Jatekos jatekos : jatekosok)
                {
                    jatekos.lep();
                    aroundGoal();
                    overGoal();
                    if (aroundGoal || overGoal)
                    {
                        winner = jatekos;
                        break;
                    }
                }
                if (aroundGoal || overGoal) {break;}
                System.out.println("Aktualis Tet: " + tet);
                korCount++;
            }
            if (aroundGoal) gameOver(winner);
            if (overGoal) System.out.println("A tet tulment a golon!\nNincs gyoztes!");
        } catch (NincsJatekos ignored) {}
    }
    public int getKorCount() { return korCount; }
    public void emel(double ujTet) { tet += ujTet; }
    public double getTet() { return tet; }
    public void printInfo() { System.out.println("Kor: " + korCount + "\nTet: " + tet + "\n"); }
    private void gameOver(Jatekos jatekos) { System.out.println("\nGol Kozeli tet!\nGyozott: " + jatekos.toString()); }
    private void aroundGoal()
    {
        aroundGoal = (tet >= (goal * 0.9) && tet <= (goal * 1.1));
    }
    private void overGoal()
    {
        overGoal = (tet > (goal * 1.1));
    }

}