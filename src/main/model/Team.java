package model;

public class Team {
    private String name;
    private Boolean winner;


    public Team(String name) {
        this.name = name;
    }

    public void winTheGame() {
        winner = true;
    }

    public void loseTheGame() {
        winner = false;
    }

    // getter
    public String getName() {
        return name;
    }
}
