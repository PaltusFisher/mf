package mafia;

public class Mafia extends Role
{
    @Override
    public String GetRoleName() {return "Мафия";}

    @Override
    public void NightMoves()
    {
        super.NightMoves();

        boolean TargetFinded = false;
        for (int i = 0; i < List.size(); i++)
            if (List.get(i).getNightDeath())
                TargetFinded = true;

        if (!TargetFinded)
        {
            int max = -1, deathnum = -1;
            for (int i = 0; i < List.size(); i++) {
                if (Target[i] > max) {
                    max = Target[i];
                    deathnum = i;
                }
            }
            if (deathnum != -1) {
                List.get(deathnum).setNightDeath(true);
                setNightInf("+=", "убивает " + List.get(deathnum).getName());
            }
        }
    }
}
