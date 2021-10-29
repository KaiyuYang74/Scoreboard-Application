package persistence;

import model.Team;
import model.Game;
import model.ListOfGame;
import model.ListOfTeam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads listOfTeams from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads listOfTeam from file and returns it;
    // throws an IOException if an error occurs when reading listOfTeam from file
    public ListOfTeam readTeams() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfTeam from JSON object and returns it
    private ListOfTeam parseListOfTeam(JSONObject jsonObject) {
        ListOfTeam listOfTeam = new ListOfTeam();
        addTeams(listOfTeam, jsonObject);
        return listOfTeam;
    }

    // MODIFIES: listOfTeam
    // EFFECTS: parses listOfTeam from JSON object and adds them to listOfTeam
    private void addTeams(ListOfTeam listOfTeam, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfTeam");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(listOfTeam, nextTeam);
        }
    }

    // MODIFIES: teams
    // EFFECTS: parses team from JSON object and adds it to listOfTeam
    private void addTeam(ListOfTeam listOfTeam, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
        listOfTeam.addTeam(team);
    }
}


