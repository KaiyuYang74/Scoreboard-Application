package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfGameTest {
    private ListOfGame testListOfGame;
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
        testListOfGame = new ListOfGame();
    }

    @Test
    public void testAddGame() {
        assertEquals(0,testListOfGame.getSize());
        testListOfGame.addGame(testGame21);
        assertEquals(1,testListOfGame.getSize());
        assertTrue(testListOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testRemoveGame() {
        testListOfGame.addGame(testGame21);
        assertEquals(1,testListOfGame.getSize());
        assertTrue(testListOfGame.getListOfGame().contains(testGame21));
        testListOfGame.removeGame(0);
        assertEquals(0,testListOfGame.getSize());
        assertFalse(testListOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testRemoveAllGames() {
        testListOfGame.addGame(testGame21);
        testListOfGame.addGame(testGame23);
        assertEquals(2,testListOfGame.getSize());
        testListOfGame.removeAllGames();
        assertEquals(0,testListOfGame.getSize());
    }

    @Test
    public void testGetSize() {
        assertEquals(0,testListOfGame.getSize());
        testListOfGame.addGame(testGame21);
        assertEquals(1,testListOfGame.getSize());
        testListOfGame.addGame(testGame23);
        assertEquals(2,testListOfGame.getSize());
    }

    @Test
    public void testGetGame() {
        testListOfGame.addGame(testGame21);
        testListOfGame.addGame(testGame23);
        assertEquals("Canada", testListOfGame.getGame(0).getTeam1().getName());
        assertEquals("China", testListOfGame.getGame(0).getTeam2().getName());
        assertEquals("Japan", testListOfGame.getGame(1).getTeam1().getName());
        assertEquals("England", testListOfGame.getGame(1).getTeam2().getName());
    }

    @Test
    public void testGetListOfGame() {
        assertEquals(0,testListOfGame.getListOfGame().size());
        assertFalse(testListOfGame.getListOfGame().contains(testGame21));
        testListOfGame.addGame(testGame21);
        assertEquals(1,testListOfGame.getListOfGame().size());
        assertTrue(testListOfGame.getListOfGame().contains(testGame21));
    }

    @Test
    public void testIsFinalGame() {
        assertFalse(testListOfGame.isFinalGame());
        testListOfGame.addGame(testGame21);
        assertTrue(testListOfGame.isFinalGame());
        testListOfGame.addGame(testGame23);
        assertFalse(testListOfGame.isFinalGame());
    }
}
