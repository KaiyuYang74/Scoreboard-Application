package model;

import model.Game;
import model.ListOfGame;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfGameTest {
    private ListOfGame listOfGame;
    private Game testGame21;
    private Game testGame23;

    @BeforeEach
    public void runBefore() {
        Team canada = new Team("Canada");
        canada.setGoals(2);
        Team china = new Team("China");
        china.setGoals(1);
        testGame21 = new Game(canada, china);
        Team japan = new Team("Japan");
        japan.setGoals(2);
        Team england = new Team("England");
        england.setGoals(3);
        testGame23 = new Game(japan, england);
        listOfGame = new ListOfGame();
    }

    @Test
    public void testAddGame() {
        assertEquals(0,listOfGame.getSize());
        listOfGame.addGame(testGame21);
        assertEquals(1,listOfGame.getSize());
        assertTrue(listOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testRemoveGame() {
        listOfGame.addGame(testGame21);
        assertEquals(1,listOfGame.getSize());
        assertTrue(listOfGame.getListOfGame().contains(testGame21));
        listOfGame.removeGame(0);
        assertEquals(0,listOfGame.getSize());
        assertFalse(listOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testRemoveAllGames() {
        listOfGame.addGame(testGame21);
        listOfGame.addGame(testGame23);
        assertEquals(2,listOfGame.getSize());
        listOfGame.removeAllGames();
        assertEquals(0,listOfGame.getSize());
    }

    @Test
    public void testGetSize() {
        assertEquals(0,listOfGame.getSize());
        listOfGame.addGame(testGame21);
        assertEquals(1,listOfGame.getSize());
        listOfGame.addGame(testGame23);
        assertEquals(2,listOfGame.getSize());
    }

    @Test
    public void testGetGame() {
        listOfGame.addGame(testGame21);
        listOfGame.addGame(testGame23);
        assertEquals("Canada", listOfGame.getGame(0).getTeam1().getName());
        assertEquals("China", listOfGame.getGame(0).getTeam2().getName());
        assertEquals("Japan", listOfGame.getGame(1).getTeam1().getName());
        assertEquals("England", listOfGame.getGame(1).getTeam2().getName());
    }

    @Test
    public void testGetListOfGame() {
        assertEquals(0,listOfGame.getListOfGame().size());
        assertFalse(listOfGame.getListOfGame().contains(testGame21));
        listOfGame.addGame(testGame21);
        assertEquals(1,listOfGame.getListOfGame().size());
        assertTrue(listOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testIsFinalGame() {
        assertFalse(listOfGame.isFinalGame());
        listOfGame.addGame(testGame21);
        assertTrue(listOfGame.isFinalGame());
        listOfGame.addGame(testGame23);
        assertFalse(listOfGame.isFinalGame());
    }
}
