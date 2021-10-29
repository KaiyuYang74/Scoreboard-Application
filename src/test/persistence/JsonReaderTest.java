package persistence;

import model.ListOfTeam;
import model.Team;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    ListOfTeam listOfTeam;

    @Test
    void testReaderNonSuchFile() {
        JsonReader readerTeam = new JsonReader("./data/nonExistentFile.json");
        try {
            listOfTeam = readerTeam.readTeams();
            fail("IOException is expected");
        } catch (IOException e) {
            // pass the test
        }
    }

    @Test
    void testReaderEmptyScoreboard() {
        JsonReader readerTeam = new JsonReader("./data/testReaderEmptyScoreboard.json");
        try {
            listOfTeam = readerTeam.readTeams();
        } catch (IOException e) {
            fail("Cannot read from file");
        }
        assertTrue(listOfTeam.isEmpty());
    }

    @Test
    void testReaderGenericScoreboard() {
        JsonReader readerTeam = new JsonReader("./data/testReaderGenericScoreboard.json");
        try {
            listOfTeam = readerTeam.readTeams();
            List<Team> teams = listOfTeam.getListOfTeams();
            assertEquals(4, teams.size());
            checkTeam("Canada", listOfTeam.getTeam(0));
            checkTeam("USA", listOfTeam.getTeam(1));
            checkTeam("Japan", listOfTeam.getTeam(2));
            checkTeam("China", listOfTeam.getTeam(3));
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }
}
