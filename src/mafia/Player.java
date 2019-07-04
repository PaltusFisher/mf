package mafia;//import java.util.ArrayList;

public class Player
{
    private String Name;
    private int Age;
    private int Trickly;
    private int Oratory;
    private boolean IsWomen;
    private boolean IsAlive = true;

    private boolean NightDeath = false;
    private boolean NightHill = false;
    private boolean NightIsMafia = false;
    private boolean NightIsNotMafia = false;

    private Role role;
    private String RoleName;

    public int DayMoves() { return role.DayMoves(); }

    public void NightMoves() { role.NightMoves(); }

    public String GetStats()
    {
        String statsstr = "";
        statsstr += "Имя: " + Name + "\n";
        statsstr += "Возраст: " + Integer.toString(getAge()) + "\n";
        statsstr += "Пол: ";
        if (getIsWomen())
            statsstr += "Ж" + "\n";
        else
            statsstr += "M" + "\n";
        statsstr += "Хитрость: " + Integer.toString(getTrickly()) + "\n";
        statsstr += "Красноречие: " + Integer.toString(getOratory()) + "\n\n";

        statsstr += "Состояние: ";
        if (getIsAlive())
            statsstr += "живой" + "\n";
        else
            statsstr += "мертвый" + "\n";

        statsstr += "Роль: " + RoleName;
        return statsstr;
    }

    public Player(String name, int age, int trickly, int oratory, boolean isWomen, Role Role)
    {
        Name = name;
        Age = age;
        Trickly = trickly;
        Oratory = oratory;
        IsWomen = isWomen;
        role = Role;
        RoleName = role.GetRoleName();
    }

    public Player() {}

    public boolean getIsWomen() {return IsWomen;}

    public int getAge() {return Age;}

    public void setNightHill(boolean nightHill) {NightHill = nightHill;}
    public boolean getNightHill() {return NightHill;}

    public int getTrickly() {return Trickly;}

    public int getOratory() {return Oratory;}

    public void setNightDeath(boolean nightDeath) {NightDeath = nightDeath; }
    public boolean getNightDeath() {return NightDeath;}

    public void setNightIsNotMafia(boolean nightIsNotMafia) {NightIsNotMafia = nightIsNotMafia; }
    public boolean getNightIsNotMafia() {return NightIsNotMafia;}

    public void setNightIsMafia(boolean nightIsMafia) {NightIsMafia = nightIsMafia; }
    public boolean getNightIsMafia() {return NightIsMafia;}

    public Role getRole() {return role;}

    public String getRoleName() {return RoleName;}

    public String getName() {return Name;}

    public boolean getIsAlive() {return IsAlive;}
    public void setIsAlive(boolean isAlive) {IsAlive = isAlive;}
}
