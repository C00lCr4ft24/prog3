package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main {
    protected static String wd = System.getProperty("user.dir");

    protected static String space(int num)
    {
        var string = " ";
        for (int i = 1; i <= num; ++i) { string = string.concat(" "); }
        return string;
    }

    protected static void exit(String[] cmd) { System.exit(0); }

    protected static void pwd(String[] cmd)
    {
        String directory = System.getProperty("user.dir");
        File dir = new File(directory);
        File[] filesArray = dir.listFiles();
        System.out.println(directory);
        if (filesArray != null) { System.out.println("Fajlok es mappak szama: " + filesArray.length); }
    }

    protected static void ls(String[] cmd)
    {
        File dir = new File(wd);
        File[] filesArray = dir.listFiles();
        if (cmd.length > 1 && filesArray != null) {
            if (Objects.equals(cmd[1], "-l")) {
                for (File file : filesArray) {
                    System.out.print(file + space(50-file.toString().length()));
                    if (file.isDirectory()) System.out.print ("d      ");
                    if (!file.isDirectory()) System.out.print("f      ");
                    System.out.print(file.length() + "b\n");
                }
            }
        }
        if (cmd.length <= 1 && filesArray != null) { for (File file : filesArray) { System.out.println(file); } }
    }

    protected static void cd(String[] cmd)
    {
        File dir = new File(wd);
        File[] filesArray = dir.listFiles();
        if (cmd.length > 1 && filesArray != null) {
            for (File file : filesArray) {
                if ((wd + "\\" + cmd[1]).equals(file.toString()) && file.isDirectory()) {
                    wd = wd + "\\" + cmd[1];
                    return;
                }
            }
        }
        if (cmd.length > 1 && cmd[1].equals("..")) { wd = dir.getParent(); return; }
        System.out.println("Nem letezik ilyen nevu konyvtar!");
    }

    protected static void rm(String[] cmd)
    {
        File dir = new File(wd);
        if (cmd.length > 1) {
            File F = new File(cmd[1]);
            if (F.delete()) System.out.println("Fajl sikeresen torolve!");
            else System.out.println("Sikertelen muvelet!");
        }
    }

    protected static void mkdir(String[] cmd)
    {
        File dir = new File(wd);
        if (cmd.length > 1) {
            File F = new File(cmd[1]);
            if (F.mkdir()) System.out.println("Konyvtar sikeresen letrehozva!");
            else System.out.println("Sikertelen muvelet!");
        }
    }

    protected static void length(String[] cmd)
    {
        File dir = new File(wd);
        File[] filesArray = dir.listFiles();
        if (cmd.length > 1 && filesArray != null) {
            for (File file : filesArray) {
                if ((wd + "\\" + cmd[1]).equals(file.toString())) {
                    System.out.println("Fajl hossza: " + file.toString().length());
                    return;
                }
            }
            System.out.println("Sikertelen muvelet!");
        } else System.out.println("Sikertelen muvelet!");
    }

    protected static void tail(String[] cmd) throws IOException
    {
        File dir = new File(wd);
        File[] filesArray = dir.listFiles();
        if (cmd.length == 2 && filesArray != null) {
            for (File file : filesArray) {
                if ((wd + "\\" + cmd[1]).equals(file.toString())) {
                    List<String> lines = Files.readAllLines(Paths.get(wd + "\\" + cmd[1]));
                    System.out.println(lines.subList(Math.max(lines.size() - 10, 0), lines.size()));
                    return;
                }
            }
            System.out.println("Sikertelen muvelet!");
        }
        if (cmd.length >= 4 && filesArray != null) {
            for (File file : filesArray) {
                if ((wd + "\\" + cmd[3]).equals(file.toString())) {
                    List<String> lines = Files.readAllLines(Paths.get(wd + "\\" + cmd[3]));
                    System.out.println(lines.subList(Math.max(lines.size() - Integer.parseInt(cmd[2]), 0), lines.size()));
                    return;
                }
            }
            System.out.println("Sikertelen muvelet!");
        } else System.out.println("Sikertelen muvelet!");
    }

    protected static void grep(String[] cmd) {
        File dir = new File(wd);
        File[] filesArray = dir.listFiles();
        if (cmd.length >= 3 && filesArray != null)
        {
            for (File file : filesArray) {
                if ((wd + "\\" + cmd[2]).equals(file.toString())) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        System.out.println(bufferedReader.readLine());
                        String string = null;
                        while ((string = bufferedReader.readLine()) != null) {
                            if (string.matches(cmd[1])) { System.out.println(string); }
                        }
                        bufferedReader.close();
                    } catch (IOException exception) { exception.printStackTrace(); }
                    return;
                }
            }
        } else System.out.println("Hiba");
    }

    public static void main(String[] args)
    {

        while (true) {
            try {
                System.out.println("Adjon meg egy parancsot: ");
                BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
                String argument = buffer.readLine();
                //[0] Parancs, [1,2,3,...] Argumentum
                String[] cmd = argument.split(" ");

                //System.out.println(Arrays.toString(cmd));

                if (cmd[0].equals("exit")) {
                    exit(cmd);
                }
                if (cmd[0].equals("pwd")) {
                    pwd(cmd);
                }
                if (cmd[0].equals("ls")) {
                    ls(cmd);
                }
                if (cmd[0].equals("cd")) {
                    cd(cmd);
                }
                if (cmd[0].equals("rm")) {
                    rm(cmd);
                }
                if (cmd[0].equals("mkdir")) {
                    mkdir(cmd);
                }
                if (cmd[0].equals("length")) {
                    length(cmd);
                }
                if (cmd[0].equals("tail")) {
                    tail(cmd);
                }
                if (cmd[0].equals("grep")) {
                    grep(cmd);
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}