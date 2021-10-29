package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a team with the name of the team and the # of goals scored by the team in the game
public class Team implements Writable {
    private String name; // the name of the team
    private int goals; // the # of goals scored by the team in the game

    /*
    MODIFIES: this
    EFFECTS: construct a new Team using the team name
    */
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

    @Override
    // EFFECTS: returns a team as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("goals", goals);
        return json;
    }
}
