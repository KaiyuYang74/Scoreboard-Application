package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game testGame21;
    private Game testGame23;
    private Game testGame11;
    private Game testGame22;

    @BeforeEach
    public void runBefore() {
        Team canada = new Team("Canada");
        canada.setGoals(2);
        Team china = new Team("China");
        china.setGoals(1);
        testGame21 = new Game(canada, china);
    }

    @Test
    public void testConstructor() {
        assertEquals("Canada", testGame21.getTeam1().getName());
        assertEquals("China", testGame21.getTeam2().getName());
    }

    @Test
    public void testDecideWinner() {
        assertEquals("Canada", testGame21.decideWinner().getName());
        Team japan = new Team("Japan");
        japan.setGoals(2);
        Team england = new Team("England");
        england.setGoals(3);
        testGame23 = new Game(japan, england);
        assertEquals("England", testGame23.decideWinner().getName());
        Team usa = new Team("USA");
        usa.setGoals(1);
        Team brazil = new Team("Brazil");
        brazil.setGoals(1);
        testGame11 = new Game(usa, brazil);
        List<String> usaAndBrazil = new ArrayList<>();
        usaAndBrazil.add("USA");
        usaAndBrazil.add("Brazil");
        assertTrue(usaAndBrazil.contains(testGame11.decideWinner().getName()));
    }

    @Test
    public void testPenaltyKick() {
        Team usa = new Team("USA");
        usa.setGoals(1);
        Team brazil = new Team("Brazil");
        brazil.setGoals(1);
        testGame11 = new Game(usa, brazil);
        List<String> usaAndBrazil = new ArrayList<>();
        usaAndBrazil.add("USA");
        usaAndBrazil.add("Brazil");
        Team japan = new Team("Japan");
        japan.setGoals(2);
        Team england = new Team("England");
        england.setGoals(2);
        testGame22 = new Game(japan, england);
        List<String> japanAndEngland = new ArrayList<>();
        japanAndEngland.add("Japan");
        japanAndEngland.add("English");
        assertTrue(usaAndBrazil.contains(testGame11.penaltyKick().getName()));
        assertTrue(usaAndBrazil.contains(testGame11.penaltyKick().getName()));
        assertTrue(japanAndEngland.contains(testGame22.penaltyKick().getName()));
        assertTrue(japanAndEngland.contains(testGame22.penaltyKick().getName()));
    }
}