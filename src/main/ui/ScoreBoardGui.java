/*
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
import java.util.stream.Collectors;

public class ScoreBoardGui extends JFrame implements ActionListener {
    private ScoreboardApp scoreboardApp;
    private JList list;
    private DefaultListModel listModel;
    private ListOfTeam listOfTeam;

    public ScoreBoardGui() {
        super("Scoreboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        listOfTeam = new ListOfTeam();
        listModel = new DefaultListModel();
        JTextField textField = new JTextField(40);
        String teamNames = textField.getText();
        listOfTeam.addListOfTeams(Arrays.asList(teamNames.split(" ")).stream().map(Team::new).collect(Collectors.toList()));
        DefaultListModel defaultListModel1 = new DefaultListModel();
        for (Team team: listOfTeam.getListOfTeams()) {
            defaultListModel1.addElement(team);
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        setSize(650, 450);
        //setLayout(new FlowLayout());
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        getContentPane().add(splitPaneVertical);
        JLabel labelTop = new JLabel("Matchups: ");
        //labelTop.setSize(new Dimension(200, 200));
        labelTop.setVisible(true);
        JPanel paneBottomLeft = new JPanel();
        paneBottomLeft.add(textField);
        //paneBottomLeft.setSize(new Dimension(1000, 1000));
        JPanel paneBottomRight = new JPanel();
        //paneBottomLeft.setSize(new Dimension(300, 100));
        paneBottomRight.setVisible(true);
        splitPaneVertical.setTopComponent(splitPaneHorizontal);
        splitPaneHorizontal.setLeftComponent(paneBottomLeft);
        splitPaneHorizontal.setRightComponent(paneBottomRight);
        splitPaneVertical.setBottomComponent(labelTop);
        splitPaneHorizontal.setDividerLocation(315);
        splitPaneVertical.setDividerLocation(195);
    }



    public static void main(String[] args) {
        ScoreBoardGui scoreBoardGui = new ScoreBoardGui();
        scoreBoardGui.setVisible(true);
        scoreBoardGui.setLocationRelativeTo(null);
        scoreBoardGui.pack();
    }

@Override
    public void actionPerformed(ActionEvent e) {
        String result = (String)JOptionPane.showInputDialog(
                this,
                "选择一种语言：",
                "Swing文本输入框(示例)",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Java? Python?"
        );
        if(result != null && result.length() > 0){
            labelTop.setText("选择了:" + result);
        }else {
            labelTop.setText("未选择！");
        }
    }
}
*/
