package ui;

import model.ListOfTeam;
import model.Team;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

//Represents a matchup maker for the candidate teams in knockout stage of a football cup event
public class ScoreboardGui extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/scoreboard.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final JTextArea textAreaRight;
    private final JTextArea textAreaLeft;
    private final JButton buttonAdd;
    private final JButton buttonSave;
    private final JButton buttonLoad;
    private final JButton buttonClear;
    private final JPanel paneBottom;
    private final JPanel paneTopLeft;
    private final JSplitPane splitPaneVertical;
    private final JSplitPane splitPaneHorizontal;
    private final JLabel labelRight;
    private final JLabel labelLeft;
    private final JPanel paneTopRight;
    private ListOfTeam listOfTeam;

    //MODIFIES: this
    //EFFECTS: initialize and set up the SWING components for my GUI
    public ScoreboardGui() {
        super("Scoreboard");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        listOfTeam = new ListOfTeam();
        splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        setSplashScreen();
        labelRight = new JLabel("Matchups");
        labelLeft = new JLabel("Candidate teams");
        setFrame(labelRight, labelLeft);
        buttonAdd = new JButton("Add");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");
        buttonClear = new JButton("Clear");
        setButton();
        getContentPane().add(splitPaneVertical);
        paneTopLeft = new JPanel();
        paneBottom = new JPanel();
        textAreaLeft = new JTextArea(5, 10);
        textAreaRight = new JTextArea(5, 10);
        setTextArea();
        paneTopRight = new JPanel();
        setPaneLayout(splitPaneVertical, splitPaneHorizontal, paneBottom, paneTopLeft, paneTopRight);
    }

    //MODIFIES: this
    //EFFECTS: set up the JTextArea used to display the candidate teams(ListOfTeam) and the corresponding matchups
    private void setTextArea() {
        textAreaLeft.setVisible(false);
        textAreaRight.setVisible(false);
    }

    //MODIFIES: vertical, horizontal, bottom, topL, topR
    //EFFECTS: set up the layout of my GUI
    private void setPaneLayout(JSplitPane vertical, JSplitPane horizontal, JPanel bottom, JPanel topL, JPanel topR) {
        bottom.setVisible(true);
        bottom.add(buttonAdd);
        bottom.add(buttonClear);
        bottom.add(buttonSave);
        bottom.add(buttonLoad);
        topL.add(textAreaLeft);
        topL.add(labelLeft);
        topR.setVisible(true);
        topR.add(labelRight);
        topR.add(textAreaRight);
        vertical.setTopComponent(horizontal);
        horizontal.setLeftComponent(topL);
        horizontal.setRightComponent(topR);
        vertical.setBottomComponent(bottom);
        horizontal.setDividerLocation(315);
        vertical.setDividerLocation(295);
    }

    //MODIFIES: this, labelRight,labelLeft
    //EFFECTS: set up the size, visibility, and exit operation
    private void setFrame(JLabel labelRight, JLabel labelLeft) {
        setSize(650, 450);
        setResizable(false);
        labelLeft.setVisible(true);
        labelRight.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //EFFECTS: set up a splash screen at the start of my program
    private void setSplashScreen() {
        JWindow window;
        window = new JWindow();
        window.getContentPane().add(new JLabel(new ImageIcon("data//Splash Screen.jpg")));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.dispose();
    }

    //MODIFIES: this
    //EFFECTS: set up the button commands
    public void setButton() {
        buttonAdd.setActionCommand("myButton1");
        buttonSave.setActionCommand("myButton2");
        buttonLoad.setActionCommand("myButton3");
        buttonClear.setActionCommand("myButton4");
        buttonAdd.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonClear.addActionListener(this);
    }

    //MODIFIES: this, e
    //EFFECTS: specify the actions when the corresponding buttons are pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton1")) {
            String teamNames = (String) JOptionPane.showInputDialog(this, "Input the candidate teams：",
                    "Scoreboard GUI", JOptionPane.PLAIN_MESSAGE, null, null, null);
            ScoreboardApp scoreboardApp = new ScoreboardApp(listOfTeam);
            StringBuilder valueLeft = new StringBuilder();
            StringBuilder valueRight = new StringBuilder();
            textAreaLeft.setVisible(true);
            textAreaRight.setVisible(true);
            listOfTeam.addListOfTeams(parseteamNames(teamNames));
            printCandidateTeams(valueLeft);
            scoreboardApp.drawCeremony(listOfTeam);
            printDrawResult(valueRight);
        } else if (e.getActionCommand().equals("myButton2")) {
            saveListOfTeam();
        } else if (e.getActionCommand().equals("myButton3")) {
            loadListOfTeam();
        } else if (e.getActionCommand().equals("myButton4")) {
            textAreaRight.setText(null);
            textAreaLeft.setText(null);
            textAreaLeft.setVisible(false);
            textAreaRight.setVisible(false);
            listOfTeam = new ListOfTeam();
        }
    }

    //MODIFIES: teamNames
    //EFFECTS: split the user-typed String into separated team names by space and store them in a list
    private List<Team> parseteamNames(String teamNames) {
        return Arrays.asList(teamNames.split(" ")).stream().map(Team::new).collect(Collectors.toList());
    }

    //MODIFIES: this, valueRight
    //EFFECTS: print the draw results in the right textArea
    private void printDrawResult(StringBuilder valueRight) {
        for (int i = 0; i < listOfTeam.getListOfTeams().size(); i++) {
            if (i % 2 == 0) {
                valueRight.append(listOfTeam.getTeam(i).getName() + " vs");
            } else {

                valueRight.append(" " + listOfTeam.getTeam(i).getName() + "\r\n");
            }
            valueRight.toString();
            textAreaRight.setText(valueRight.toString());
            textAreaRight.setVisible(true);
        }
    }

    //MODIFIES: this, valueLeft
    //EFFECTS: print the candidate teams (ListOfTeam) in the left textArea
    private void printCandidateTeams(StringBuilder valueLeft) {
        for (int i = 0; i < listOfTeam.getListOfTeams().size(); i++) {
            if (i % 2 == 0) {
                valueLeft.append(listOfTeam.getTeam(i).getName() + " ");
            } else {

                valueLeft.append(" " + listOfTeam.getTeam(i).getName() + "\r\n");
            }
            valueLeft.toString();
            textAreaLeft.setText(valueLeft.toString());
            textAreaLeft.setVisible(true);
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
    //          initialize a new Scoreboard GUI if the data file is empty
    public void loadListOfTeam() {
        try {
            listOfTeam = jsonReader.readTeams();
            if (!listOfTeam.isEmpty()) {
                StringBuilder valueLeft = new StringBuilder();
                StringBuilder valueRight = new StringBuilder();
                printCandidateTeams(valueLeft);
                printDrawResult(valueRight);
            } else {
                buttonAdd.addActionListener(this);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        ScoreboardGui scoreBoardGui = new ScoreboardGui();
        scoreBoardGui.setVisible(true);
        scoreBoardGui.setLocationRelativeTo(null);
    }
}







