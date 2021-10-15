package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of teams
public class ListOfTeam {
    private List<Team> listOfTeam;

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
    public void setListOfTeams(List<Team> listOfTeam) {
        this.listOfTeam = listOfTeam;
    }


}
