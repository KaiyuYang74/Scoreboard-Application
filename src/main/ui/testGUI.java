package ui;

import model.ListOfTeam;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class testGUI {
    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Scoreboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Start");
        final JTextArea textArea = new JTextArea();
        textArea.setVisible(false);

        JList list;
        DefaultListModel listModel;
        ListOfTeam listOfTeam = new ListOfTeam();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamNames = (String) JOptionPane.showInputDialog(
                        frame,
                        "Input the candidate teams：",
                        "Scoreboard GUI",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                ScoreboardApp scoreboardApp = new ScoreboardApp(listOfTeam);
                StringBuilder value = new StringBuilder();
                textArea.setVisible(true);
                textArea.setColumns(6);
                textArea.setLineWrap(true);
                listOfTeam.addListOfTeams(Arrays.asList(teamNames.split(" ")).stream().map(Team::new).collect(Collectors.toList()));
  /*              if (teamNames != null && teamNames.length() > 0) {
                    label.setText("选择了:" + teamNames);
                } else {
                    label.setText("未选择！");
                }*/
                scoreboardApp.drawCeremony(listOfTeam);
                for (int i = 0; i < listOfTeam.getListOfTeams().size(); i++) {
                    if (i % 2 == 0) {
                        value.append(listOfTeam.getTeam(i).getName() + " vs");
                    } else {

                        value.append(" " + listOfTeam.getTeam(i).getName() + "\r\n");
                    }
                    value.toString();
                    textArea.setText(value.toString());


                }


            }
        });

        panel.add(button);
        panel.add(textArea);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}




