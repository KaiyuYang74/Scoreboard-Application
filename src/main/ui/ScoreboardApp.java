package ui;

import model.Game;
import model.ListOfGame;
import model.ListOfTeam;
import model.Team;

import java.util.*;
import java.util.stream.Collectors;

//Schedule maker and scoreboard application for the knockout stage of a football cup event
public class ScoreboardApp {
    private Scanner input;

    //EFFECTS: run the scoreboard application
    public ScoreboardApp() {
        runScoreboard();
    }

    //MODIFIES: this
    //EFFECTS: runs the scoreboard application
    private void runScoreboard() {
        ListOfTeam listOfTeam = new ListOfTeam();
        ListOfGame listOfGame = new ListOfGame();
        List<String> splitedTeamNames = parseTeamNames();
        convertToListOfTeam(splitedTeamNames, listOfTeam);
        quitIfInValid(listOfTeam);
        drawCeremony(listOfTeam);
        generateGames(listOfTeam, listOfGame);
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
    private List<String> parseTeamNames() {
        input = new Scanner((System.in));
        System.out.println("Enter the team names in the knockout stage of the football cup event separated by space: ");
        String teamNames = input.nextLine();
        return Arrays.asList(teamNames.split(" "));
    }

    //EFFECTS: convert a list of team names represented by strings into the corresponding ListOfTeam
    private void convertToListOfTeam(List<String> splitedTeamNames, ListOfTeam listOfTeam) {
        listOfTeam.setListOfTeams(splitedTeamNames.stream().map(Team::new).collect(Collectors.toList()));
    }

    /*
    MODIFIES: this
    EFFECTS: simulate the draw ceremony
             randomly pairing up teams from all qualified teams to generate games
    */
    private void drawCeremony(ListOfTeam listOfTeam) {
        Collections.shuffle(listOfTeam.getListOfTeams());
    }

    /*
    MODIFIES: this
    EFFECTS: generate a game for the current round
               provoke a natural recursive call to iterate over the ListOfTeam, generate all games in the current round
               and provoke a mutual recursive call to simulate the games in the current round
    */
    public void generateGames(ListOfTeam listOfTeam, ListOfGame listOfGame) {
        listOfGame.addGame(new Game((listOfTeam.getTeam(0)), (listOfTeam.getTeam(1))));
        listOfTeam.removeTeam(0);
        listOfTeam.removeTeam(0);
        if (!listOfTeam.isEmpty()) {
            generateGames(listOfTeam, listOfGame);
        }
        oneRoundGames(listOfGame, listOfTeam);
    }

    /*
    MODIFIES: this
    EFFECTS: simulate one round of knockout listOfGame for a football cup event
             determine the winner of each game in one round of listOfGame
             store all the winners of the current round in a list
             and provoke a mutual recursive call to generate listOfGame for the next round
    */
    public void oneRoundGames(ListOfGame listOfGame, ListOfTeam listOfTeam) {
        if (listOfGame.isFinalGame()) {
            Game finalGame = listOfGame.getGame(0);
            playTheFinal(finalGame);
            listOfGame.removeAllGames();
        } else if (listOfGame.getSize() != 0) {
            showTheMatchups(listOfGame);
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
            keepGoingOrQuit();
            generateGames(listOfTeam, listOfGame);
        }
    }

    /*
    MODIFIES: this
    EFFECTS: The program will end if q was entered
    */
    public void keepGoingOrQuit() {
        String command = null;
        displayMenu();
        command = input.nextLine();
        command = command.toLowerCase();
        if (command.equals("q")) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
    }

    /*
    EFFECTS: displays menu of options to user
             press c to continue to next round of game
             press q to quite the scoreboard application
    */
    private void displayMenu() {
        System.out.println("\tc -> continue");
        System.out.println("\tq -> quit");
    }

    /*
    EFFECTS: show the winner of the game
             give the user some feedbacks on the exciting level of the game and report the total goals in the game
    */
    private void showWinner(int goalTeam1, int goalTeam2, Team winner) {
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
                 split the user-typed String into separated scores by ":"
    */
    private String[] parseGameResult(Game g) {
        String team1Name = g.getTeam1().getName();
        String team2Name = g.getTeam2().getName();
        System.out.println("Enter the game result for " + team1Name + " vs " + team2Name + " separated by space: ");
        String scoreGame1 = input.nextLine();
        return scoreGame1.split(" ");
    }

    //EFFECTS: show all the matchups (the team names on both sides of all the games)
    private void showTheMatchups(ListOfGame listOfGame) {
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
}


