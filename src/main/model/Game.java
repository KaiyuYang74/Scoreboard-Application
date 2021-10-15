package model;

import java.util.Random;

//Represents a game with the names of two engaged teams
public class Game {
    private Team team1; // name of the first team in the game
    private Team team2; // name of the second team in the game

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    /*
     *EFFECTS: Compare the goals scored by both teams in a game and return the winner team
     *         if the result is a draw (the number of goals scored by the two teams are equal),
     *         then play a penalty kick game to determine the winner
     */
    public Team decideWinner() {
        if (team1.getGoals() > team2.getGoals()) {
            return team1;
        } else if (team1.getGoals() < team2.getGoals()) {
            return team2;
        } else {
            return penaltyKick();
        }
    }

    /*
     *EFFECTS: Simulate the penalty kick game when the game is a draw,
     *          randomly return a team as the winner of the penalty kick game
     */
    public Team penaltyKick() {
        Random random = new Random();
        int penaltyKickResult = random.nextInt(2);
        if (penaltyKickResult == 0) {
            return team1;
        } else {
            return team2;
        }
    }

    //getter
    public Team getTeam1() {
        return team1;
    }

    //getter
    public Team getTeam2() {
        return team2;
    }
}
