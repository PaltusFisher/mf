package mafia;

public class Medic extends Role
{
    @Override
    public String GetRoleName() {return "Доктор";}

    @Override
    public void NightMoves()
    {
        super.NightMoves();

        int min = 999, hillnum = -1;
        for (int i = 0; i < List.size(); i++)
        {
            if (Target[i] < min && Target[i] > -1)
            {
                min = Target[i];
                hillnum = i;
            }
        }
        if (hillnum != -1) {
            List.get(hillnum).setNightHill(true);
            setNightInf("+=", "лечит " + List.get(hillnum).getName());
        }
    }
}
