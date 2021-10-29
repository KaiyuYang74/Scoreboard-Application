package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of games
public class ListOfGame {
    private List<Game> listOfGame;

    /*
    MODIFIES: this
    EFFECTS: construct a new listOfGame
    */
    public ListOfGame() {
        listOfGame = new ArrayList<>();
    }

    /*
    MODIFIES: this
    EFFECTS: add the input game to the list of games
    */
    public void addGame(Game game) {
        listOfGame.add(game);
    }

    /*
    MODIFIES: this
    EFFECTS: remove the game on the given index from the list of games
    */
    public void removeGame(int index) {
        listOfGame.remove(index);
    }

    /*
    MODIFIES: this
    EFFECTS: remove all the games from the list of games
    */
    public void removeAllGames() {
        listOfGame.clear();
    }

    /*
    EFFECTS: get the size of the list of games
    */
    public int getSize() {
        return listOfGame.size();
    }

    /*
    EFFECTS: get the game on the given index of the list of games
    */
    public Game getGame(int i) {
        return listOfGame.get(i);
    }

    //getter
    public List<Game> getListOfGame() {
        return listOfGame;
    }

    /*
    EFFECTS: return true if the next round of game is the final game
     */
    public Boolean isFinalGame() {
        return listOfGame.size() == 1;
    }

}
