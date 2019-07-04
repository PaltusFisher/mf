package mafia;

public class Cop extends Role
{
    @Override
    public String GetRoleName() {return "Детектив";}

    @Override
    public void NightMoves()
    {
        super.NightMoves();

        int max = -1, mafianum = -1;
        for (int i = 0; i < List.size(); i++) {
            if (Target[i] > max) {
                max = Target[i];
                mafianum = i;
            }
        }
        if (mafianum != -1) {
            if (List.get(mafianum).getRoleName() == "Мафия")
                List.get(mafianum).setNightIsMafia(true);
            else
                List.get(mafianum).setNightIsNotMafia(true);
            setNightInf("+=", "проверяет " + List.get(mafianum).getName());
        }

    }
}
