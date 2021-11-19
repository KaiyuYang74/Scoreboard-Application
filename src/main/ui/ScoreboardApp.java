package ui;

import model.Game;
import model.ListOfGame;
import model.ListOfTeam;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

//Represents a schedule maker and scoreboard application for the knockout stage of a football cup event
public class ScoreboardApp {
    private static final String JSON_STORE = "./data/scoreboard.json";
    private final Scanner input;
    private String command;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private ListOfTeam listOfTeam;
    private ListOfGame listOfGame;

    /*
    MODIFIES: this
    EFFECTS: initialize some fields and run the scoreboard application
    */
    public ScoreboardApp() {
        input = new Scanner((System.in));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runScoreboard();
    }

    public ScoreboardApp(ListOfTeam listOfTeam) {
        input = new Scanner((System.in));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.listOfTeam = listOfTeam;
        listOfGame = new ListOfGame();
        listOfGame = new ListOfGame();
    }

    /*
    MODIFIES: this
    EFFECTS: run the scoreboard application
    */
    public void runScoreboard() {
        loadScoreboard();
        generateGames();
    }

    /*
    MODIFIES: this
    EFFECTS: ask the user whether to load Scoreboard from file or not
    */
    public void loadScoreboard() {
        System.out.println("Reload the scoreboard?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        command = input.nextLine();
        command = command.toLowerCase();
        if (command.equals("y")) {
            loadListOfTeam();
            listOfGame = new ListOfGame();
        } else {
            initNewScoreboard();
        }
    }

    /*
    MODIFIES: this
    EFFECTS: set up a new scoreboard and initialize it
    */
    public void initNewScoreboard() {
        listOfTeam = new ListOfTeam();
        listOfGame = new ListOfGame();
        List<String> splitedTeamNames = parseTeamNames();
        convertToListOfTeam(splitedTeamNames);
        quitIfInValid(listOfTeam);
        drawCeremony(listOfTeam);
    }

    /*
    MODIFIES: this
    EFFECTS: quite the program when the # of input teams is invalid (# of input don't equal to 2 to the power of n)
             n are positive integers
    */
    public void quitIfInValid(ListOfTeam listOfTeam) {
        int numberOfTeams = listOfTeam.getListOfTeams().size();
        if ((numberOfTeams & (numberOfTeams - 1)) != 0 || numberOfTeams == 1) {
            System.out.println("The number of input teams is invalid");
            System.exit(0);
        }
    }

    /*
    EFFECTS: give the instruction for the user to correctly input the team names
             split the user-typed String into separated team names by space and store them in a list
    */
    public List<String> parseTeamNames() {
        System.out.println("Enter the team names in the knockout stage of the football cup event separated by space: ");
        String teamNames = input.nextLine();
        return Arrays.asList(teamNames.split(" "));
    }

    //EFFECTS: convert a list of team names represented by strings into the corresponding ListOfTeam
    public void convertToListOfTeam(List<String> splitedTeamNames) {
        listOfTeam.addListOfTeams(splitedTeamNames.stream().map(Team::new).collect(Collectors.toList()));
    }

    /*
    MODIFIES: this
    EFFECTS: simulate the draw ceremony
             randomly pairing up teams from all qualified teams to generate games
    */
    public void drawCeremony(ListOfTeam listOfTeam) {
        Collections.shuffle(listOfTeam.getListOfTeams());
    }

    /*
    MODIFIES: this
    EFFECTS: a natural recursive call to iterate over the ListOfTeam, generate all games in the current round
             and provoke a mutual recursive call to simulate the games in the current round
    */
    public void generateGames() {
        listOfGame.addGame(new Game((listOfTeam.getTeam(0)), (listOfTeam.getTeam(1))));
        listOfTeam.removeTeam(0);
        listOfTeam.removeTeam(0);
        if (!listOfTeam.isEmpty()) {
            generateGames();
        }
        oneRoundGames();
    }


    /*
    MODIFIES: this
    EFFECTS: a natural recursive call to iterate over the ListOfTeam, generate all games in the current round
         and provoke a mutual recursive call to simulate the games in the current round
    */
    public void generateGamesGui() {
        listOfGame.addGame(new Game((listOfTeam.getTeam(0)), (listOfTeam.getTeam(1))));
        listOfTeam.removeTeam(0);
        listOfTeam.removeTeam(0);
        if (!listOfTeam.isEmpty()) {
            generateGames();
        }
    }

    /*
    MODIFIES: this
    EFFECTS: simulate one round of knockout games for a football cup event
             determine the winner of each game in one round of games
             store all the winners of the current round in a list
             and provoke a mutual recursive call to generate games for the next round
    */
    public void oneRoundGames() {
        if (listOfGame.isFinalGame()) {
            Game finalGame = listOfGame.getGame(0);
            playTheFinal(finalGame);
            listOfGame.removeAllGames();
        } else if (listOfGame.getSize() != 0) {
            showTheMatchups();
            for (Game g : listOfGame.getListOfGame()) {
                String[] splitedScore = parseGameResult(g);
                int goalTeam1 = Integer.parseInt(splitedScore[0]);
                int goalTeam2 = Integer.parseInt(splitedScore[1]);
                g.getTeam1().setGoals(goalTeam1);
                g.getTeam2().setGoals(goalTeam2);
                Team winner = g.decideWinner();
                showWinner(goalTeam1, goalTeam2, winner);
                listOfTeam.addTeam(winner);
            }
            listOfGame.removeAllGames();
            displayMenuQuitContinue();
            quit();
            drawCeremony(listOfTeam);
            generateGames();
        }
    }

    /*
    MODIFIES: this
    EFFECTS: The program will end if q was entered
             the user will be reminded to save the teams for next round to file and have the option to do so or not.
    */
    public void quit() {
        command = input.nextLine();
        command = command.toLowerCase();
        if (command.equals("q")) {
            saveScoreboard();
            System.exit(0);
            System.out.println("\nGoodbye!");
        }
    }

    /*
    MODIFIES: this
    EFFECTS: save the teams for next round to file if the user chose to do so
    */
    public void saveScoreboard() {
        System.out.println("save the scoreboard?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        command = input.nextLine();
        command = command.toLowerCase();
        if (command.equals("y")) {
            saveListOfTeam();
        }
    }

    /*
    EFFECTS: displays menu of options to user after having finished one round of games
             press c to continue to next round of games
             press q to quite the scoreboard application
    */
    public void displayMenuQuitContinue() {
        System.out.println("\tc -> continue");
        System.out.println("\tq -> quit");
    }

    /*
    EFFECTS: show the winner of the game
             give the user some feedbacks on the exciting level of the game and report the total goals in the game
    */
    public void showWinner(int goalTeam1, int goalTeam2, Team winner) {
        if (goalTeam1 != goalTeam2) {
            System.out.println(winner.getName() + " wins the game");
            feedbackForTheGame(goalTeam1, goalTeam2);
        } else {
            System.out.println("The result is a draw and " + winner.getName() + " wins the penalty shootout");
            System.out.println("What a close game！");
        }
    }

    /*
    EFFECTS: give the instruction for the user to correctly input the game result
                 split the user-typed String into separated scores by space
    */
    public String[] parseGameResult(Game g) {
        String team1Name = g.getTeam1().getName();
        String team2Name = g.getTeam2().getName();
        System.out.println("Enter the game result for " + team1Name + " vs " + team2Name + " separated by space: ");
        String scoreGame1 = input.nextLine();
        return scoreGame1.split(" ");
    }

    //EFFECTS: show all the matchups (the team names on both sides of all the games)
    public void showTheMatchups() {
        System.out.println("The next round of listOfGame are: ");
        for (int k = 0; k < listOfGame.getSize(); k++) {
            String team1Name = listOfGame.getGame(k).getTeam1().getName();
            String team2Name = listOfGame.getGame(k).getTeam2().getName();
            System.out.println(team1Name + " vs " + team2Name);
        }
    }

    /*
    MODIFIES: this
    EFFECTS: simulate the final game in a football cup event
             show the simulated champion team to the user
    */
    public void playTheFinal(Game g) {
        System.out.println("the final game is between " + g.getTeam1().getName() + " and " + g.getTeam2().getName());
        String[] splitedScore = parseGameResult(g);
        int goalTeam1 = Integer.parseInt(splitedScore[0]);
        int goalTeam2 = Integer.parseInt(splitedScore[1]);
        g.getTeam1().setGoals(goalTeam1);
        g.getTeam2().setGoals(goalTeam2);
        Team champion = g.decideWinner();
        showWinner(goalTeam1, goalTeam2, champion);
        System.out.println("The winner of 2022 World Cup is " + champion.getName());
        System.out.println("Congratulations to " + champion.getName() + "!");
    }

    //EFFECTS: give the user some feedbacks on the exciting level of the game and report the total goals in the game
    public void feedbackForTheGame(int goalTeam1, int goalTeam2) {
        if (goalTeam1 + goalTeam2 < 2) {
            System.out.println("What a boring game with only one goal!");
        } else if (goalTeam1 != goalTeam2 && goalTeam1 + goalTeam2 > 3) {
            int totalGoals = goalTeam1 + goalTeam2;
            System.out.println("What a thrilling game with " + totalGoals + " goals");
        } else if (goalTeam1 != goalTeam2) {
            int totalGoals = goalTeam1 + goalTeam2;
            System.out.println("That's a pretty uneventful game with " + totalGoals + " goals");
        }
    }

    // EFFECTS: saves the listOfTeam to file
    public void saveListOfTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfTeam);
            jsonWriter.close();
            System.out.println("Saved " + listOfTeam + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads listOfTeam from file if the data file is not empty
    //          initialize a new Scoreboard if the data file is empty
    public void loadListOfTeam() {
        try {
            listOfTeam = jsonReader.readTeams();
            if (!listOfTeam.isEmpty()) {
                System.out.println("Loaded " + listOfTeam + " from " + JSON_STORE);
            } else {
                initNewScoreboard();
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}



