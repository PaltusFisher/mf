package mafia;

import java.util.ArrayList;
import java.util.Random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game
{
    private static final int COUNT_OF_PLAYERS = 6;//MIN 6

    private final ArrayList<Player> players = new ArrayList();

    private int KillNum = -1;

    public String FindName(int Num)
    {
        // построчное считывание файла
            try
            {
                File file = new File("./src/docs/Names.txt"); //создаем объект FileReader для объекта File
                FileReader fr = new FileReader(file); //создаем BufferedReader с существующего FileReader для построчного считывания
                BufferedReader reader = new BufferedReader(fr); // считаем сначала первую строку
                String line = reader.readLine();
                for(int i = 0; i < Num; i++)
                    line = reader.readLine();// считываем остальные строки в цикле
                return line;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return "";
    }

    public void Generate_Players()
    {
        int singleChoiseChecker;
        Random rand = new Random();
        int RoleNumber;

        int MafiaCount = COUNT_OF_PLAYERS / 3 - 1;
        int CopCount = 1;
        int MedicCount = 1;
        int CityGuyCount = COUNT_OF_PLAYERS - MafiaCount - CopCount - MedicCount;

        for (int i = 0; i < COUNT_OF_PLAYERS; i++)
        {
            Role role = new Role();
            do
            {
                singleChoiseChecker = 0;
                RoleNumber = rand.nextInt(4) + 1;
                switch (RoleNumber)
                {
                    case 1:
                        if (CityGuyCount != 0)
                        {
                            singleChoiseChecker = 1;
                            role = new CityGuy();
                            CityGuyCount--;
                        }
                        break;
                    case 2:
                        if (MafiaCount != 0)
                        {
                            singleChoiseChecker = 1;
                            role = new Mafia();
                            MafiaCount--;
                        }
                        break;
                    case 3:
                        if (CopCount != 0)
                        {
                            singleChoiseChecker = 1;
                            role = new Cop();
                            CopCount--;
                        }
                        break;
                    case 4:
                        if (MedicCount != 0)
                        {
                            singleChoiseChecker = 1;
                            role = new Medic();
                            MedicCount--;
                        }
                        break;
                }
            } while (singleChoiseChecker == 0);

            Player g = new Player(FindName(rand.nextInt(10)) , rand.nextInt(90) + 10, rand.nextInt(10) + 1,
                    rand.nextInt(10) + 1, rand.nextBoolean(), role);
            players.add(g);
        }
        for (int i = 0; i < COUNT_OF_PLAYERS; i++)
            players.get(i).getRole().List.addAll(players);

    }

    public void NightMoves()
    {
        for (Player hero : players)
        {
            if (hero.getIsAlive())
                hero.NightMoves();
        }
    }

    public boolean DayMoves()
    {
        boolean beKill = false;
        for (Player hero : players)
            if (hero.getNightDeath())
                beKill = true;

        if (beKill)
        {
            int KillList[] = new int[players.size()];
            for (int i = 0; i < players.size(); i++)
                KillList[i] = -1;

            for (Player hero : players) {
                if (hero.getIsAlive()) {
                    if (hero.getNightDeath()) {
                        if (hero.getNightHill()) {
                            hero.setNightHill(false);
                            hero.setNightDeath(false);
                        } else {
                            hero.setIsAlive(false);
                            hero.setNightDeath(false);
                        }
                    } else
                        hero.setNightHill(false);
                }
            }

            int playernum = 0;
            for (Player hero : players) {
                if (hero.getIsAlive())
                    KillList[playernum] = hero.DayMoves();
                playernum++;
            }

            int min = -1, killnum = -1;
            for (int i = 0; i < playernum; i++) {
                int num = KillList[i];
                if (num != -1) {
                    int k = 1;
                    for (int j = 0; j < playernum; j++)
                        if (i != j && num == KillList[j])//////////////////////////////////////////////////////////////////
                            k++;

                    if (min < k) {
                        min = k;
                        killnum = num;
                    }
                }
            }

            if (killnum != -1)
            {
                players.get(killnum).setIsAlive(false);
                setKillNum(killnum);
            }

            for (Player hero : players)
                if (hero.getIsAlive() && hero.getRoleName() == "Мафия")
                    return false;
        }
        return true;
    }

    public String getArray(int playerNum)
    {
        int i = 0;
        for (Player hero : players)
        {
            if (playerNum == i)
                return hero.GetStats();
            i++;
        }
        return "";
    }

    public Player getPlayer(int playerNum)
    {
        int i = 0;
        for (Player hero : players)
        {
            if (playerNum == i)
                return hero;
            i++;
        }

        Player a = new Player();
        return a;
    }

    public void setKillNum(int killNum) { KillNum = killNum;}
    public int getKillNum() {return KillNum;}

    public int getCountOfPlayers() { return COUNT_OF_PLAYERS; }
}
