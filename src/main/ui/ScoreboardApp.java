package ui;

import model.Game;
import model.ListOfGame;
import model.ListOfTeam;
import model.Team;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

//todo:增加抽签语句
//todo: what if the input is invalid?
//todo: extract the methods whenever there is a domain shift
// 补完 Team里的methods(optiinal)
// todo: Unit Tests
// todo: revise the checkstyles
// todo parameterization
public class ScoreboardApp {
    private Scanner input;

    public ScoreboardApp() {
        runScoreboard();
    }

    private void runScoreboard() {
        input = new Scanner((System.in));
        System.out.println("Enter the team names in the knockout stage of 2022 World Cup separated by space: ");
        String teamNames = input.nextLine();
        List<String> splited = Arrays.asList(teamNames.split(" "));
        ListOfTeam listOfTeam = new ListOfTeam();
        listOfTeam.setListOfTeams(splited.stream().map(Team::new).collect(Collectors.toList()));
        ListOfGame games = new ListOfGame();
        Collections.shuffle(listOfTeam.getListOfTeams());
        generateGameSchedule(listOfTeam, games);
    }

    public void generateGameSchedule(ListOfTeam l1, ListOfGame l2) {
        l2.addGame(new Game((l1.getTeam(0)), (l1.getTeam(1))));
        l1.removeTeam(0);
        l1.removeTeam(0);
        if (!l1.isEmpty()) {
            generateGameSchedule(l1, l2);
        }
        oneRound(l2, l1);
    }


    public void oneRound(ListOfGame games, ListOfTeam listOfTeam) {
        if (games.isFinalGame()) {
            Game finalGame = games.getGame(0);
            theFinal(finalGame);
            games.removeAllGames();
        } else if (games.getSize() != 0) {
            System.out.println("The next round of games are: ");
            for (int k = 0; k < games.getSize(); k++) {
                System.out.println(games.getGame(k).getTeam1().getName() + " vs " + games.getGame(k).getTeam2().getName());
            }
            for (Game g : games.getListOfGame()) {
                System.out.println("Enter the game result for " + g.getTeam1().getName() + " vs " + g.getTeam2().getName() + ": ");
                String scoreGame1 = input.nextLine();
                String[] splited1 = scoreGame1.split(":");
                int goalTeam1 = Integer.parseInt(splited1[0]);
                int goalTeam2 = Integer.parseInt(splited1[1]);
                g.getTeam1().setGoals(goalTeam1);
                g.getTeam2().setGoals(goalTeam2);
                Team winner = g.decideWinner();
                if (goalTeam1 != goalTeam2) {
                    System.out.println(winner.getName() + " wins the game");
                    feedbackForTheGame(goalTeam1, goalTeam2);
                } else {
                    System.out.println("The result is a draw and " + winner.getName() + " wins the penalty shootout");
                    System.out.println("What a close game！");
                }
                listOfTeam.addTeam(winner);
            }
            games.removeAllGames();
            generateGameSchedule(listOfTeam, games);
        }
    }

    public void theFinal(Game g) {
        System.out.println("the final game is between " + g.getTeam1().getName() + " and " + g.getTeam2().getName());
        System.out.println("Enter the game result for " + g.getTeam1().getName() + " vs " + g.getTeam2().getName() + ": ");
        String scoreFinal = input.nextLine();
        String[] splited1 = scoreFinal.split(":");
        int goalTeam1 = Integer.parseInt(splited1[0]);
        int goalTeam2 = Integer.parseInt(splited1[1]);
        g.getTeam1().setGoals(goalTeam1);
        g.getTeam2().setGoals(goalTeam2);
        Team champion = g.decideWinner();
        System.out.println("The winner of 2022 World Cup is " + champion.getName());
        System.out.println("Congratulations to " + champion.getName() + "!");
    }

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


