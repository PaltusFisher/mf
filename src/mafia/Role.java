package mafia;

import java.util.ArrayList;
import java.util.Random;

public class Role
{
    public ArrayList<Player> List = new ArrayList();
    public int Target[];
    private String NightInf = "";
    private int KillTargetNum = -1;

    public String GetRoleName() {return "";}

    public int DayMoves()
    {
        setTarget();
        int max = -1, deathnum = -1;

        for (int i = 0; i < List.size(); i++) {
            if (Target[i] > max) {
                max = Target[i];
                deathnum = i;
            }
        }
        KillTargetNum = deathnum;
        return deathnum;
    }

    public void NightMoves() { setTarget();}

    public void setTarget()
    {
        Target = new int[List.size()];
        Random r = new Random();
        for (int i = 0; i < List.size(); i++)
            if (!List.get(i).getIsAlive())
                Target[i] = -1;
            else
                if (List.get(i).getRoleName() == GetRoleName())
                    Target[i] = -1;
                else
                    if (List.get(i).getNightIsMafia() && GetRoleName() == "Детектив")
                        Target[i] = 101;
                    else
                        if (List.get(i).getNightIsNotMafia() && GetRoleName() == "Детектив")
                            Target[i] = -1;
                        else
                        {
                            int o = r.nextInt(List.get(i).getOratory()) + 1;
                            int t = r.nextInt(List.get(i).getTrickly()) + 1;
                            Target[i] = 100 - o * t;
                        }
    }

    public String getNightInf() { return NightInf; }
    public void setNightInf(String mod, String nightInf)
    {
        if (mod == "=")
            NightInf = nightInf;
        if (mod == "+=")
            NightInf += nightInf;
    }

    public void setKillTargetNum(int killTargetNum) {KillTargetNum = killTargetNum; }
    public int getKillTargetNum() { return KillTargetNum; }
}
