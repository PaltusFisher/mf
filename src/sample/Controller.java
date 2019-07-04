package sample;

import mafia.Game;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Controller {

    public Button StartGameB;
    public Button NextMoveB;

    public TextArea Inf;

    public TextArea P1;
    public TextArea P2;
    public TextArea P3;
    public TextArea P4;
    public TextArea P5;
    public TextArea P6;

    private Game game = new Game();

    public void StartGameClick(ActionEvent actionEvent) {
        game.Generate_Players();
        ShowStats();
        NextMoveB.setText("NightStart");
        StartGameB.setDisable(true);
        NextMoveB.setDisable(false);
    }

    public void NextMoveClick(ActionEvent actionEvent) {
        boolean end = false;
        String information = "";
        if (NextMoveB.getText() == "NightStart")
        {
            game.NightMoves();
            NextMoveB.setText("DayStart");
            information = "Наступает ночь. Город засыпает.";
            boolean MafiaIsChecked = false;
            for (int i = 0; i < game.getCountOfPlayers(); i++) {
                if (game.getPlayer(i).getRoleName() == "Мафия" && !MafiaIsChecked && game.getPlayer(i).getRole().getNightInf() != "") {
                    information += "\nПросыпается мафия и делает свой выбор(" + game.getPlayer(i).getRole().getNightInf() + ")";
                    MafiaIsChecked = true;
                }
                if (game.getPlayer(i).getRoleName() == "Детектив")
                    information += "\nПросыпается детектив и делает свой выбор(" + game.getPlayer(i).getRole().getNightInf() + ")";
                if (game.getPlayer(i).getRoleName() == "Доктор")
                    information += "\nПросыпается доктор и делает свой выбор(" + game.getPlayer(i).getRole().getNightInf() + ")";
                game.getPlayer(i).getRole().setNightInf("=", "");
            }
            Inf.setText(information);
        }
        else
            if (NextMoveB.getText() == "DayStart")
            {
                String res = "";
                for (int i = 0; i < game.getCountOfPlayers(); i++)
                {
                    if (game.getPlayer(i).getNightDeath()) {
                        if (game.getPlayer(i).getNightHill())
                            res += "\nНочью никто не погиб.";
                        else
                            res += "\nНочью погиб " + game.getPlayer(i).getName();
                    }
                }

                end = game.DayMoves();
                NextMoveB.setText("NightStart");
                information = "Наступает день. Город просыпается." + res;
                for (int i = 0; i < game.getCountOfPlayers(); i++) {
                    int n = game.getPlayer(i).getRole().getKillTargetNum();
                    if (n != -1)
                        information += "\n" + game.getPlayer(i).getName() + " голосует против " + game.getPlayer(n).getName();
                    game.getPlayer(i).getRole().setKillTargetNum(-1);
                }
                if (game.getKillNum() != -1)
                    information += "\nГород убивает " + game.getPlayer(game.getKillNum()).getName();
                game.setKillNum(-1);
                Inf.setText(information);

                if (end)
                {
                    int mafiacount = 0, alivecount = 0;
                    for (int i = 0; i < game.getCountOfPlayers(); i++) {
                        if (game.getPlayer(i).getRoleName() == "Мафия" && game.getPlayer(i).getIsAlive())
                            mafiacount++;
                        if (game.getPlayer(i).getIsAlive())
                            alivecount++;
                    }
                    if (alivecount == mafiacount)
                        Inf.appendText("\nМафия победила");
                    else
                        Inf.appendText("\nГород победил");

                    StartGameB.setDisable(true);
                    NextMoveB.setDisable(true);
                }
            }
        ShowStats();
    }

    public void ShowStats()
    {
        for (int i = 0; i < game.getCountOfPlayers(); i++)
        {
            switch (i)
            {
                case 0:
                    P1.setText(game.getArray(i));
                break;
                case 1:
                    P2.setText(game.getArray(i));
                    break;
                case 2:
                    P3.setText(game.getArray(i));
                    break;
                case 3:
                    P4.setText(game.getArray(i));
                    break;
                case 4:
                    P5.setText(game.getArray(i));
                    break;
                case 5:
                    P6.setText(game.getArray(i));
                    break;
            }
        }
    }

}
