package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Represents a list of teams
public class ListOfTeam implements Writable {
    private List<Team> listOfTeam;
    //private static EventLog eventLog;

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
        Event e = new Event("Add the candidate teams:" + team.getName());
        EventLog.getInstance().logEvent(e);
    }

    /*
    MODIFIES: this
    EFFECTS: remove the team on the given index from the list of teams
    */
    public void removeTeam(int index) {
        listOfTeam.remove(index);
        Event e = new Event("Remove the team in listOfTeam");
        EventLog.getInstance().logEvent(e);
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
        StringBuffer  teamNames = new StringBuffer("");
        this.listOfTeam = Stream.concat(this.listOfTeam.stream(), listOfTeam.stream()).collect(Collectors.toList());
        for (Team team: listOfTeam) {
            teamNames.append(" " + team.getName());
        }
        Event e = new Event("Add the candidate teams:" + teamNames);
        EventLog.getInstance().logEvent(e);
    }

    /*
    MODIFIES: this
    EFFECTS: simulate the draw ceremony
             randomly pairing up teams from all qualified teams to generate games
    */
    public void drawCeremony() {
        Collections.shuffle(listOfTeam);
        Event e = new Event("The draw is conducted");
        EventLog.getInstance().logEvent(e);
    }

    //MODIFIES: this
    //EFFECTS: remove all the teams from the listOfTeam
    public  void clearListOfTeam() {
        listOfTeam = new ArrayList<>();
        Event e = new Event("The listOfTeam is cleared");
        EventLog.getInstance().logEvent(e);
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
        Event e = new Event("The listOfTeam is saved");
        EventLog.getInstance().logEvent(e);
        return jsonArray;
    }
}


