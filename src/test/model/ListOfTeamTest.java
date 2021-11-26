package model;

import model.ListOfTeam;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListOfTeamTest {
    private ListOfTeam testListOfTeam;
    private Team canada;
    private Team china;
    private Team japan;
    private Team usa;

    @BeforeEach
    public void runBefore() {
        canada = new Team("Canada");
        china = new Team("China");
        japan = new Team("Japan");
        usa = new Team("USA");
        testListOfTeam = new ListOfTeam();
    }

    @Test
    public void testAddTeam() {
        assertEquals(0, testListOfTeam.getListOfTeams().size());
        testListOfTeam.addTeam(canada);
        assertEquals(1, testListOfTeam.getListOfTeams().size());
        assertTrue(testListOfTeam.getListOfTeams().contains(canada));
    }

    @Test
    public void testRemoveTeam() {
        testListOfTeam.addTeam(canada);
        assertEquals(1, testListOfTeam.getListOfTeams().size());
        assertTrue(testListOfTeam.getListOfTeams().contains(canada));
        testListOfTeam.removeTeam(0);
        assertEquals(0, testListOfTeam.getListOfTeams().size());
        assertFalse(testListOfTeam.getListOfTeams().contains(canada));
    }

    @Test
    public void testGetTeam() {
        testListOfTeam.addTeam(canada);
        testListOfTeam.addTeam(china);
        assertEquals("Canada", testListOfTeam.getTeam(0).getName());
        assertEquals("China", testListOfTeam.getTeam(1).getName());
    }

    @Test
    public void TestIsEmpty() {
        assertTrue(testListOfTeam.isEmpty());
        testListOfTeam.addTeam(china);
        assertFalse(testListOfTeam.isEmpty());
    }

    @Test
    public void testGetListOfTeams() {
        assertEquals(0, testListOfTeam.getListOfTeams().size());
        assertFalse(testListOfTeam.getListOfTeams().contains(canada));
        testListOfTeam.addTeam(canada);
        assertEquals(1, testListOfTeam.getListOfTeams().size());
        assertTrue(testListOfTeam.getListOfTeams().contains(canada));
    }

    @Test
    public void testAddListOfTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(canada);
        teams.add(china);
        teams.add(japan);
        assertEquals(3, teams.size());
        testListOfTeam.addTeam(usa);
        assertEquals(1, testListOfTeam.getListOfTeams().size());
        testListOfTeam.addListOfTeams(teams);
        assertEquals(4, testListOfTeam.getListOfTeams().size());
        for (Team s : teams) {
            assertTrue(testListOfTeam.getListOfTeams().contains(s));
        }
        assertTrue(testListOfTeam.getListOfTeams().contains(usa));
    }

    @Test
    public void testClearListOfTeam() {
        assertEquals(0,testListOfTeam.getListOfTeams().size());
        testListOfTeam.addTeam(canada);
        testListOfTeam.addTeam(usa);
        assertEquals(2,testListOfTeam.getListOfTeams().size());
        testListOfTeam.clearListOfTeam();
        assertEquals(0,testListOfTeam.getListOfTeams().size());
    }

    @Test
    public void testDrawCeremony() {
        testListOfTeam.addTeam(canada);
        testListOfTeam.addTeam(usa);
        testListOfTeam.addTeam(japan);
        testListOfTeam.addTeam(china);
        testListOfTeam.drawCeremony();
        testListOfTeam.getListOfTeams().contains(canada);
        testListOfTeam.getListOfTeams().contains(usa);
        testListOfTeam.getListOfTeams().contains(china);
        testListOfTeam.getListOfTeams().contains(japan);
    }
}

