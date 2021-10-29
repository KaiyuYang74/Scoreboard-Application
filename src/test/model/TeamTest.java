package model;

import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team testTeam;

    @BeforeEach
    public void runBefore() {
        testTeam = new Team("Canada");
    }

    @Test
    public void testConstructor() {
        assertEquals("Canada", testTeam.getName());
    }
}
