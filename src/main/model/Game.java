package model;

import java.util.Random;

public class Game {
    private Team team1;
    private Team team2;
    private int team1Goals;
    private int team2Goals;

    public enum OutCome {
        WIN, LOSS, DRAW
    }

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;

    }

    public Team decideWinner() {
        if (team1Goals > team2Goals) {
            team1.winTheGame();
            team2.loseTheGame();
            System.out.println(team1.getName() + " wins the game");
            return team1;
        } else if (team1Goals < team2Goals) {
            team1.loseTheGame();
            team2.winTheGame();
            System.out.println(team2.getName() + " wins the game");
            return team2;
        } else {
            Random ran = new Random();
            int penaltyKick = ran.nextInt(2) + 1;
            if (penaltyKick == 0) {
                System.out.println("The result is a draw and " + team1.getName() + " wins the penalty shootout");
                System.out.println("What a close game！");
                return team1;
            } else {
                System.out.println("The result is a draw and " + team1.getName() + " wins the penalty shootout");
                System.out.println("What a close game！");
                return team2;
            }
        }
    }


    //getters
    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
   //setters
    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }
}
