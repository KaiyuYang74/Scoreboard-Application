package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Represents a list of teams
public class ListOfTeam implements Writable {
    private List<Team> listOfTeam;

    /*
    MODIFIES: this
    EFFECTS: construct a new listOfTeam
    */
    public ListOfTeam() {
        listOfTeam = new ArrayList<>();
    }

    /*
    MODIFIES: this
    EFFECTS: add the input team to the list of teams
    */
    public void addTeam(Team team) {
        listOfTeam.add(team);
    }

    /*
    MODIFIES: this
    EFFECTS: remove the team on the given index from the list of teams
    */
    public void removeTeam(int index) {
        listOfTeam.remove(index);
    }

    /*
    EFFECTS: get the team on the given index of the list of teams
    */
    public Team getTeam(int i) {
        return listOfTeam.get(i);
    }

    /*
    EFFECTS: determine if the given list of teams is empty
    */
    public Boolean isEmpty() {
        return listOfTeam.size() == 0;
    }

    //getter
    public List<Team> getListOfTeams() {
        return listOfTeam;
    }

    //setter
    public void addListOfTeams(List<Team> listOfTeam) {
        this.listOfTeam = Stream.concat(this.listOfTeam.stream(), listOfTeam.stream()).collect(Collectors.toList());
    }

    @Override
    // EFFECTS: returns a listOfTeam as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfTeam",teamsToJson());
        return json;
    }

    //EFFECTS: returns a JsonArray with all the teams in a listOfTeam being stored as JSON objects
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t: listOfTeam) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}


