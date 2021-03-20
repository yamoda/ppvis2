package table.view.dialog;

import table.view.tableview.TableView;

import javax.swing.*;
import java.awt.*;

public class SearchEntryDialog {
    private JFrame searchEntryWindow;
    private TableView matchesTable;

    private JTextField fullNameField;
    private JTextField assignmentsLeftField;

    private JComboBox<String> programmingLanguagesBox;
    private JComboBox<Integer> assignmentsAmountBox;

    private JButton searchButton;

    public SearchEntryDialog() {
        searchEntryWindow = new JFrame();
        constructUI();
    }

    private void constructUI() {
        GridLayout layoutUI = new GridLayout(0, 1);
        Font mainFont = new Font("Times Roman", Font.PLAIN,23);
        JPanel panelUI = new JPanel(layoutUI);

        JLabel fullNameLabel = new JLabel("ФИО: ");
        JLabel assignmentAmountBoxLabel = new JLabel("Общее количество работ: ");
        JLabel programmingLanguagesBoxLabel = new JLabel("Язык программирования: ");
        JLabel assignmentsLeftFieldLabel = new JLabel("Количество оставшихся заданий: ");

        fullNameField = new JTextField();
        fullNameField.setFont(mainFont);

        assignmentsLeftField = new JTextField();
        assignmentsLeftField.setFont(mainFont);

        programmingLanguagesBox = new JComboBox<String>();
        assignmentsAmountBox = new JComboBox<Integer>();

        JPanel fullNamePanel = new JPanel(new GridLayout(1, 2));
        fullNamePanel.add(fullNameLabel);
        fullNamePanel.add(fullNameField);
        panelUI.add(fullNamePanel);

        JPanel programmingBoxLabel = new JPanel(new GridLayout(1, 2));
        programmingBoxLabel.add(programmingLanguagesBoxLabel);
        programmingBoxLabel.add(programmingLanguagesBox);
        panelUI.add(programmingBoxLabel);

        JPanel assignPanel = new JPanel(new GridLayout(1, 2));
        assignPanel.add(assignmentAmountBoxLabel);
        assignPanel.add(assignmentsAmountBox);
        panelUI.add(assignPanel);

        JPanel assignmentsLeftPanel = new JPanel(new GridLayout(1, 2));
        assignmentsLeftPanel.add(assignmentsLeftFieldLabel);
        assignmentsLeftPanel.add(assignmentsLeftField);
        panelUI.add(assignmentsLeftPanel);

        searchButton = new JButton("Найти");
        panelUI.add(searchButton);

        matchesTable = new TableView();

        searchEntryWindow.add(panelUI, BorderLayout.NORTH);
        searchEntryWindow.add(matchesTable, BorderLayout.CENTER);
    }

    public JFrame getFrame() { return searchEntryWindow;}

    public TableView getTableView() { return matchesTable; }

    public JTextField getFullNameField () { return fullNameField; }

    public JTextField getAssignmentsLeftField() { return assignmentsLeftField; }

    public JButton getSearchButton() { return searchButton; }

    public JComboBox<String> getProgrammingLanguagesBox() { return programmingLanguagesBox; }

    public JComboBox<Integer> getAssignmentsAmountBox() { return assignmentsAmountBox; }
}
