package ui;

import model.Game;
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
        List<Team> group = splited.stream().map(Team::new).collect(Collectors.toList());
        List<Game> games = new ArrayList<>();
        Collections.shuffle(group);
        generateGameSchedule(group, games);
    }

    public void generateGameSchedule(List<Team> l1, List<Game> l2) {
        l2.add(new Game((l1.get(0)), (l1.get(1))));
        l1.remove(0);
        l1.remove(0);
        if (l1 != null && !l1.isEmpty()) {
            generateGameSchedule(l1, l2);
        }
        oneRound(l2, l1);
    }


    public void oneRound(List<Game> games, List<Team> group) {
        if (games.size() == 1) {
            Game finalGame = games.get(0);
            theFinal(finalGame);
            games.removeAll(games);
        } else if (games.size() != 0) {
            System.out.println("The next round of games are: ");
            for (int k = 0; k < games.size(); k++) {
                System.out.println(games.get(k).getTeam1().getName() + " vs " + games.get(k).getTeam2().getName());
            }
            for (Game g : games) {
                System.out.println("Enter the game result for " + g.getTeam1().getName() + " vs " + g.getTeam2().getName() + ": ");
                String scoreGame1 = input.nextLine();
                String[] splited1 = scoreGame1.split(":");
                int goalTeam1 = Integer.parseInt(splited1[0]);
                int goalTeam2 = Integer.parseInt(splited1[1]);
                g.setTeam1Goals(goalTeam1);
                g.setTeam2Goals(goalTeam2);
                Team winner = g.decideWinner();
                feedbackForTheGame(goalTeam1, goalTeam2);
                group.add(winner);
            }
            games.removeAll(games);
            generateGameSchedule(group, games);
        }
    }

    public void theFinal(Game g) {
        System.out.println("the final game is between " + g.getTeam1().getName() + " and " + g.getTeam2().getName());
        System.out.println("Enter the game result for " + g.getTeam1().getName() + " vs " + g.getTeam2().getName() + ": ");
        String scoreFinal = input.nextLine();
        String[] splited1 = scoreFinal.split(":");
        int goalTeam1 = Integer.parseInt(splited1[0]);
        int goalTeam2 = Integer.parseInt(splited1[1]);
        g.setTeam1Goals(goalTeam1);
        g.setTeam2Goals(goalTeam2);
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


