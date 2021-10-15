package model;

// Represents a team with the name of the team and the # of goals scored by the team in the game
public class Team {
    private String name; // the name of the team
    private int goals; // the # of goals scored by the team in the game

    public Team(String name) {
        this.name = name;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public int getGoals() {
        return goals;
    }

    // setter
    public void setGoals(int goals) {
        this.goals = goals;
    }
}
