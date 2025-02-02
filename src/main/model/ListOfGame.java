package model;

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
        Event e = new Event("A new game is added to listOfGame");
        EventLog.getInstance().logEvent(e);
    }

    /*
    MODIFIES: this
    EFFECTS: remove all the games from the list of games
    */
    public void removeAllGames() {
        listOfGame.clear();
        Event e = new Event("Remove all the finished games in listOfGame");
        EventLog.getInstance().logEvent(e);
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
