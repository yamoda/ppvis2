package table.view.dialog;

import javax.swing.*;
import java.awt.*;

public class RemoveEntryDialog {
    private JFrame deleteEntryWindow;

    private JTextField fullNameField;
    private JTextField assignmentsLeftField;

    private JTextField programmingLanguagesField;
    private JTextField assignmentsAmountField;

    private JButton deleteButton;
    private JLabel removalInfo;

    public RemoveEntryDialog() {
        deleteEntryWindow = new JFrame();
        constructUI();
    }

    private void constructUI() {
        GridLayout layoutUI = new GridLayout(0, 1);
        Font mainFont = new Font("Times Roman", Font.PLAIN,23);
        JPanel panelUI = new JPanel(layoutUI);

        JLabel fullNameLabel = new JLabel("ФИО: ");
        JLabel assignmentAmountFieldLabel = new JLabel("Общее количество работ: ");
        JLabel programmingLanguagesFieldLabel = new JLabel("Язык программирования: ");
        JLabel assignmentsLeftFieldLabel = new JLabel("Количество оставшихся заданий: ");

        fullNameField = new JTextField();
        fullNameField.setFont(mainFont);
        fullNameField.setPreferredSize(new Dimension(100, 50));

        programmingLanguagesField = new JTextField();
        programmingLanguagesField.setFont(mainFont);

        assignmentsAmountField = new JTextField();
        assignmentsAmountField.setFont(mainFont);

        assignmentsLeftField = new JTextField();
        assignmentsLeftField.setFont(mainFont);

        JPanel fullNamePanel = new JPanel(new GridLayout(1, 2));
        fullNamePanel.add(fullNameLabel);
        fullNamePanel.add(fullNameField);
        panelUI.add(fullNamePanel);

        JPanel programmingBoxLabel = new JPanel(new GridLayout(1, 2));
        programmingBoxLabel.add(programmingLanguagesFieldLabel);
        programmingBoxLabel.add(programmingLanguagesField);
        panelUI.add(programmingBoxLabel);

        JPanel assignPanel = new JPanel(new GridLayout(1, 2));
        assignPanel.add(assignmentAmountFieldLabel);
        assignPanel.add(assignmentsAmountField);
        panelUI.add(assignPanel);

        JPanel assignmentsLeftPanel = new JPanel(new GridLayout(1, 2));
        assignmentsLeftPanel.add(assignmentsLeftFieldLabel);
        assignmentsLeftPanel.add(assignmentsLeftField);
        panelUI.add(assignmentsLeftPanel);

        deleteButton = new JButton("Удалить");
        panelUI.add(deleteButton);

        removalInfo = new JLabel("");
        removalInfo.setFont(new Font("Times Roman", Font.PLAIN,30));

        deleteEntryWindow.add(panelUI, BorderLayout.NORTH);
        deleteEntryWindow.add(removalInfo, BorderLayout.CENTER);
    }

    public JFrame getFrame() { return deleteEntryWindow; }

    public JLabel getRemovalInfoLabel() { return removalInfo; }

    public JTextField getFullNameField () { return fullNameField; }

    public JTextField getAssignmentsLeftField() { return assignmentsLeftField; }

    public JButton getDeleteButton() { return deleteButton; }

    public JTextField getProgrammingLanguagesField() { return programmingLanguagesField; }

    public JTextField getAssignmentsAmountField() { return assignmentsAmountField; }
}
