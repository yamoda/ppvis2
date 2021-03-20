package table.view.dialog;

import javax.swing.*;
import java.awt.*;

public class NewEntryDialog {
    private JFrame newEntryWindow;

    private JTextField fullNameField;
    private JTextField programmingLanguageField;

    private JTextField yearField;
    private JTextField groupIdField;
    private JTextField assignmentAmountField;
    private JTextField passedAmountField;

    private JButton inputButton;

    public NewEntryDialog() {
        newEntryWindow = new JFrame();
        constructUI();
    }

    private void constructUI() {
        GridLayout layoutUI = new GridLayout(0, 1);
        Font mainFont = new Font("Times Roman", Font.PLAIN,23);
        JPanel panelUI = new JPanel(layoutUI);

        JLabel fullNameLabel = new JLabel("ФИО: ");
        JLabel yearFieldLabel = new JLabel("Курс: ");
        JLabel groupIdFieldLabel = new JLabel("Номер группы: ");
        JLabel assignmentAmountFieldLabel = new JLabel("Общее количество работ: ");
        JLabel passedAmountFieldLabel = new JLabel("Количество сданных работ: ");
        JLabel programmingLanguageFieldLabel = new JLabel("Язык программирования: ");

        fullNameField = new JTextField();
        fullNameField.setFont(mainFont);

        yearField = new JTextField();
        yearField.setFont(mainFont);

        groupIdField = new JTextField();
        groupIdField.setFont(mainFont);

        assignmentAmountField = new JTextField();
        assignmentAmountField.setFont(mainFont);

        passedAmountField = new JTextField();
        passedAmountField.setFont(mainFont);

        programmingLanguageField = new JTextField();
        programmingLanguageField.setFont(mainFont);

        JPanel fullNamePanel = new JPanel(new GridLayout(1, 2));
        fullNamePanel.add(fullNameLabel);
        fullNamePanel.add(fullNameField);
        panelUI.add(fullNamePanel);

        JPanel yearPanel = new JPanel(new GridLayout(1, 2));
        yearPanel.add(yearFieldLabel);
        yearPanel.add(yearField);
        panelUI.add(yearPanel);

        JPanel groupIdPanel = new JPanel(new GridLayout(1, 2));
        groupIdPanel.add(groupIdFieldLabel);
        groupIdPanel.add(groupIdField);
        panelUI.add(groupIdPanel);

        JPanel assignmentAmountPanel = new JPanel(new GridLayout(1, 2));
        assignmentAmountPanel.add(assignmentAmountFieldLabel);
        assignmentAmountPanel.add(assignmentAmountField);
        panelUI.add(assignmentAmountPanel);

        JPanel passedAmountPanel = new JPanel(new GridLayout(1, 2));
        passedAmountPanel.add(passedAmountFieldLabel);
        passedAmountPanel.add(passedAmountField);
        panelUI.add(passedAmountPanel);

        JPanel programmingLanguagePanel = new JPanel(new GridLayout(1, 2));
        programmingLanguagePanel.add(programmingLanguageFieldLabel);
        programmingLanguagePanel.add(programmingLanguageField);
        panelUI.add(programmingLanguagePanel);

        inputButton = new JButton("Ввести");
        panelUI.add(inputButton, 6);

        newEntryWindow.add(panelUI);
    }

    public JFrame getFrame() { return newEntryWindow; }

    public JTextField getFullNameField() { return fullNameField; }

    public JTextField getYearField() { return yearField; }

    public JTextField getGroupIdField() { return groupIdField; }

    public JTextField getAssignmentAmountField() { return assignmentAmountField; }

    public JTextField getPassedAmountField() { return passedAmountField; }

    public JTextField getProgrammingLanguageField() { return programmingLanguageField; }

    public JButton getInputButton() { return inputButton; }
}
