package ui;

import model.ListOfGame;
import model.ListOfTeam;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class testGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/scoreboard.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private JTextArea textAreaRight;
    private JTextArea textAreaLeft;
    private ListOfTeam listOfTeam;
    private ListOfGame listOfGame;
    private JButton buttonAdd;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonClear;
    private JPanel paneBottom;
    private JWindow window;

    public testGUI() {
        super("Scoreboard");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        window = new JWindow();
        window.getContentPane().add(new JLabel("", new ImageIcon(new URL("http://docs.oracle.com/javase/tutorial/uiswing/examples/misc/SplashDemoProject/src/misc/images/splash.gif")), SwingConstants.CENTER));
        window.setBounds(500, 150, 300, 200);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        JFrame frame = new JFrame();
        frame.add(new JLabel("Welcome"));
        frame.setVisible(true);
        frame.setSize(300,100);
        window.dispose();
/*        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(500, 300);
        setBounds(ss.width / 2 - frameSize.width / 2,
                ss.height / 2 - frameSize.height / 2,
                frameSize.width, frameSize.height);
        setVisible(true);*/
        setSize(650, 450);
        setResizable(false);
        JLabel labelRight = new JLabel("Matchups");
        JLabel labelLeft = new JLabel("Candidate teams");
        labelLeft.setVisible(true);
        labelRight.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(500, 500));
        //((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        //setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
        //JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        //setLayout(new FlowLayout());
        buttonAdd = new JButton("Add");
        buttonSave = new JButton("Save");
        buttonLoad = new JButton("Load");
        buttonClear = new JButton("Clear");
        buttonAdd.setActionCommand("myButton1");
        buttonSave.setActionCommand("myButton2");
        buttonLoad.setActionCommand("myButton3");
        buttonClear.setActionCommand("myButton4");
        listOfTeam = new ListOfTeam();

        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        getContentPane().add(splitPaneVertical);
        //JLabel labelRight = new JLabel("Matchups: ");
        //labelTop.setSize(new Dimension(200, 200));
        labelRight.setVisible(true);
        JPanel paneTopLeft = new JPanel();
        textAreaLeft = new JTextArea(5,10);
        textAreaLeft.setVisible(false);
        textAreaRight = new JTextArea(5,10);
        textAreaRight.setVisible(false);
        //textArea.setLocation(100,445);
        paneBottom = new JPanel();
        //paneBottom.add(textArea);
        //paneBottomLeft.setSize(new Dimension(1000, 1000));
        JPanel paneTopRight = new JPanel();
        paneTopRight.setVisible(true);
        paneBottom.setVisible(true);
        //paneBottomLeft.setSize(new Dimension(300, 100));
        paneBottom.add(buttonAdd);
        paneBottom.add(buttonClear);
        paneBottom.add(buttonSave);
        paneBottom.add(buttonLoad);
        paneTopLeft.add(textAreaLeft);
        paneTopLeft.add(labelLeft);
        paneTopRight.setVisible(true);
        paneTopRight.add(labelRight);
        paneTopRight.add(textAreaRight);
        splitPaneVertical.setTopComponent(splitPaneHorizontal);
        splitPaneHorizontal.setLeftComponent(paneTopLeft);
        splitPaneHorizontal.setRightComponent(paneTopRight);
        splitPaneVertical.setBottomComponent(paneBottom);
        splitPaneHorizontal.setDividerLocation(315);
        splitPaneVertical.setDividerLocation(295);

/*        add(buttonStart);
        textArea.setVisible(true);
        add(textArea);*/
        //pack();
        //getContentPane().add(this, BorderLayout.CENTER);
        buttonAdd.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonClear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton1")) {
            String teamNames = (String) JOptionPane.showInputDialog(
                    this,
                    "Input the candidate teams：",
                    "Scoreboard GUI",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null
            );
            ScoreboardApp scoreboardApp = new ScoreboardApp(listOfTeam);
            StringBuilder valueLeft = new StringBuilder();
            StringBuilder valueRight = new StringBuilder();
            //textArea = new JTextArea(5,20);
            textAreaLeft.setVisible(true);
            textAreaRight.setVisible(true);
            //textArea.setColumns(12);
            //textArea.setLineWrap(true);
            listOfTeam.addListOfTeams(Arrays.asList(teamNames.split(" ")).stream().map(Team::new).collect(Collectors.toList()));
            for (int i = 0; i < listOfTeam.getListOfTeams().size(); i++) {
                if (i % 2 == 0) {
                    valueLeft.append(listOfTeam.getTeam(i).getName() + " ");
                } else {

                    valueLeft.append(" " + listOfTeam.getTeam(i).getName() + "\r\n");
                }
                valueLeft.toString();
                textAreaLeft.setText(valueLeft.toString());
            }
            //listOfTeam.addListOfTeams(Arrays.asList(teamNames.split(" ")).stream().map(Team::new).collect(Collectors.toList()));
  /*              if (teamNames != null && teamNames.length() > 0) {
                    label.setText("选择了:" + teamNames);
                } else {
                    label.setText("未选择！");
                }*/
           // ListOfTeam listOfTeamCopy = listOfTeam;
            scoreboardApp.drawCeremony(listOfTeam);
/*            scoreboardApp.generateGamesGui();
            scoreboardApp.showTheMatchups();*/
            for (int i = 0; i < listOfTeam.getListOfTeams().size(); i++) {
                if (i % 2 == 0) {
                    valueRight.append(listOfTeam.getTeam(i).getName() + " vs");
                } else {

                    valueRight.append(" " + listOfTeam.getTeam(i).getName() + "\r\n");
                }
                valueRight.toString();
                textAreaRight.setText(valueRight.toString());
            }
            //textArea.setVisible(true);

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
                StringBuilder valueLeft = new StringBuilder();
                StringBuilder valueRight = new StringBuilder();
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
                //System.out.println("Loaded " + listOfTeam + " from " + JSON_STORE);

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
            } else {
                buttonAdd.addActionListener(this);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        testGUI scoreBoardGui = new testGUI();
        scoreBoardGui.setVisible(true);
        scoreBoardGui.setLocationRelativeTo(null);

    }
}





