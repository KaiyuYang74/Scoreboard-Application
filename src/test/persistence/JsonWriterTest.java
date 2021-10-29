package persistence;

import model.ListOfTeam;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    private ListOfTeam listOfTeam;

    @Test
    void testWriterInvalidDataFile() {
        try {
            listOfTeam = new ListOfTeam();
            JsonWriter writer = new JsonWriter("./data/my\2testListOfTeam.json");
            writer.open();
            fail("IOException is expected");
        } catch (IOException e) {
            // pass the test
        }
    }

    @Test
    void testWriterEmptyScoreboard() {
        try {
            listOfTeam = new ListOfTeam();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfTeam.json");
            writer.open();
            writer.write(listOfTeam);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfTeam.json");
            listOfTeam = reader.readTeams();
            assertTrue(listOfTeam.isEmpty());
        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }

    @Test
    void testWriterGenericScoreboard() {
        try {
            listOfTeam = new ListOfTeam();
            listOfTeam.addTeam(new Team("Korea"));
            listOfTeam.addTeam(new Team("France"));
            listOfTeam.addTeam(new Team("Canada"));
            JsonWriter writer = new JsonWriter("./data/testWriterGenericWorkroom.json");
            writer.open();
            writer.write(listOfTeam);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGenericWorkroom.json");
            listOfTeam = reader.readTeams();
            List<Team> teams = listOfTeam.getListOfTeams();
            assertEquals(3, teams.size());
            checkTeam("Korea", listOfTeam.getTeam(0));
            checkTeam("France", listOfTeam.getTeam(1));
            checkTeam("Canada", listOfTeam.getTeam(2));

        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }
}
