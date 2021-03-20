package table.controller;

import table.model.Student;
import table.model.StudentModel;

import table.model.parsers.DOMWriter;
import table.model.parsers.SAXReader;

import table.view.MainApplicationView;
import table.view.dialog.RemoveEntryDialog;
import table.view.dialog.NewEntryDialog;
import table.view.dialog.SearchEntryDialog;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MainApplicationController {
    private StudentModel model;
    private MainApplicationView mainView;
    private TableController mainTableController;

    public MainApplicationController(StudentModel model, MainApplicationView view) {
        this.model = model;
        this.mainView = view;

        final int windowWidth = 1200;
        final int windowHeight = 700;

        this.mainView.getFrame().setTitle("Assignments table");
        this.mainView.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainView.getFrame().setSize(windowWidth, windowHeight);
        this.mainView.getFrame().setVisible(true);

        mainTableController = new TableController(view.getTableView(), model, 10);
        initController();
    }

    private void initController() {
        mainView.getSaveButton().addActionListener(e -> save());
        mainView.getOpenButton().addActionListener(e -> load());

        mainView.getNewEntryButton().addActionListener(e -> initEntryDialog());
        mainView.getSearchButton().addActionListener(e -> initSearchDialog());
        mainView.getDeleteButton().addActionListener(e -> initRemoveDialog());

        mainView.getTableView().getFirstPageButton().addActionListener(e -> mainTableController.toFirstPage());
        mainView.getTableView().getLastPageButton().addActionListener(e -> mainTableController.toLastPage());
        mainView.getTableView().getNextPageButton().addActionListener(e -> mainTableController.nextPage());
        mainView.getTableView().getPrevPageButton().addActionListener(e -> mainTableController.prevPage());

        mainView.getSaveItem().addActionListener(e -> save());
        mainView.getOpenItem().addActionListener(e -> load());

        mainView.getNewEntryItem().addActionListener(e -> initEntryDialog());
        mainView.getSearchItem().addActionListener(e -> initSearchDialog());
        mainView.getDeleteItem().addActionListener(e -> initRemoveDialog());
    }

    private void load() {
        JFrame dialogFrame = null;
        FileDialog fileDialog = new FileDialog(dialogFrame, "open", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String parsePath = fileDialog.getDirectory() + fileDialog.getFile();

        SAXReader xmlDocumentReader = new SAXReader();
        ArrayList<Student> documentEntries = xmlDocumentReader.readAndParse(parsePath);

        model.setTableArray(documentEntries);
        mainTableController.updateModel(documentEntries);
        mainTableController.updateTableView();
    }

    private void save() {
        JFrame dialogFrame = null;
        FileDialog fileDialog = new FileDialog(dialogFrame, "save", FileDialog.SAVE);

        fileDialog.setFile("Untitled.xml");
        fileDialog.setVisible(true);

        String writePath = fileDialog.getDirectory() + fileDialog.getFile();

        DOMWriter xmlModelWriter = new DOMWriter("Entries", "Entry");
        ArrayList<Student> entriesArray = model.getTableArray();
        xmlModelWriter.parseAndWrite(entriesArray, writePath);
    }


    private void initEntryDialog() {
        final int windowWidth = 500;
        final int windowHeight = 400;

        var newEntryDialog = new NewEntryDialog();

        newEntryDialog.getFrame().setTitle("New entry");
        newEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        newEntryDialog.getFrame().setVisible(true);

        newEntryDialog.getInputButton().addActionListener(clickEvent -> {
            boolean correctData = true;

            String fullName = newEntryDialog.getFullNameField().getText();
            String programmingLanguage = newEntryDialog.getProgrammingLanguageField().getText();

            int year=-1, groupId=-1, assignmentsAmount=-1, passedAmount=-1;
            try {
                year = Integer.parseInt(newEntryDialog.getYearField().getText());
                groupId = Integer.parseInt(newEntryDialog.getGroupIdField().getText());
                assignmentsAmount = Integer.parseInt(newEntryDialog.getAssignmentAmountField().getText());
                passedAmount = Integer.parseInt(newEntryDialog.getPassedAmountField().getText());
            }
            catch (NumberFormatException numberFormatException) {
                correctData = false;
                JOptionPane.showMessageDialog(null, "Поля должны быть заполнены корректно");
            }

            if(correctData) {
                ArrayList<Student> entriesArray = model.getTableArray();

                Student newEntry = new Student(fullName, year, groupId, assignmentsAmount, passedAmount, programmingLanguage);
                entriesArray.add(newEntry);

                model.setTableArray(entriesArray);
                mainTableController.updateModel(model.getTableArray());
                mainTableController.updateTableView();
                newEntryDialog.getFrame().dispose();
            }
        });
    }

    private void initSearchDialog() {
        final int windowWidth = 850;
        final int windowHeight = 400;

        var searchEntryDialog = new SearchEntryDialog();

        searchEntryDialog.getFrame().setTitle("Search");
        searchEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        searchEntryDialog.getFrame().setVisible(true);

        searchEntryDialog.getAssignmentsAmountBox().setModel(new DefaultComboBoxModel<>(findUniqueAssignmentsAmount()));
        searchEntryDialog.getProgrammingLanguagesBox().setModel(new DefaultComboBoxModel<>(findUniqueLanguages()));
        searchEntryDialog.getSearchButton().addActionListener(clickEvent -> {
            String fullName = searchEntryDialog.getFullNameField().getText();
            String assignmentsLeftText = searchEntryDialog.getAssignmentsLeftField().getText();

            JComboBox<String> progLangBox = searchEntryDialog.getProgrammingLanguagesBox();
            String selectedLanguage = progLangBox.getItemAt(progLangBox.getSelectedIndex());

            JComboBox<Integer> allAssignmentsBox = searchEntryDialog.getAssignmentsAmountBox();
            Integer assignmentsAmount = allAssignmentsBox.getItemAt(allAssignmentsBox.getSelectedIndex());

            int assignmentsLeft = 0;
            boolean correctInput = true;

            if (assignmentsLeftText.equals("")) assignmentsLeft=-1;
            else assignmentsLeft = Integer.parseInt(assignmentsLeftText);

            if (correctInput) {
                ArrayList<Student> entriesArray = find(fullName, selectedLanguage, assignmentsAmount, assignmentsLeft);
                TableController matchesTableController = new TableController(searchEntryDialog.getTableView(), new StudentModel(), 3);
                matchesTableController.updateModel(entriesArray);
                matchesTableController.updateTableView();

                searchEntryDialog.getTableView().getFirstPageButton().addActionListener(e -> matchesTableController.toFirstPage());
                searchEntryDialog.getTableView().getLastPageButton().addActionListener(e -> matchesTableController.toLastPage());
                searchEntryDialog.getTableView().getNextPageButton().addActionListener(e -> matchesTableController.nextPage());
                searchEntryDialog.getTableView().getPrevPageButton().addActionListener(e -> matchesTableController.prevPage());
            }
        });
    }

    private void initRemoveDialog() {
        final int windowWidth = 850;
        final int windowHeight = 400;

        var removeEntryDialog = new RemoveEntryDialog();

        removeEntryDialog.getFrame().setTitle("Search");
        removeEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        removeEntryDialog.getFrame().setVisible(true);

        removeEntryDialog.getDeleteButton().addActionListener(clickEvent -> {
            String fullName = removeEntryDialog.getFullNameField().getText();
            String progLang = removeEntryDialog.getProgrammingLanguagesField().getText();

            int assignmentsAmount=0, assignmentsLeft=0;
            boolean correctInput = true;

            try {
                assignmentsAmount = Integer.parseInt(removeEntryDialog.getAssignmentsAmountField().getText());
                assignmentsLeft = Integer.parseInt(removeEntryDialog.getAssignmentsLeftField().getText());
            }
            catch (NumberFormatException numberFormatException) {
                correctInput = false;
                JOptionPane.showMessageDialog(null, "Поля должны быть заполнены корректно");
            }

            if (correctInput) {
                ArrayList<Student> entriesArray = model.getTableArray();
                ArrayList<Student> toRemove = find(fullName, progLang, assignmentsAmount, assignmentsLeft);

                entriesArray.removeAll(toRemove);
                model.setTableArray(entriesArray);

                mainTableController.updateModel(entriesArray);
                mainTableController.updateTableView();

                if(toRemove.size() == 0) removeEntryDialog.getRemovalInfoLabel().setText("Ничего не было удалено");
                else removeEntryDialog.getRemovalInfoLabel().setText("Было удалено записей: "+toRemove.size());
            }
        });
    }

    private ArrayList<Student> find(String fullName, String progLang, int assignmentsAmount, int assignmentsLeft) {
        ArrayList<Student> entriesArray = model.getTableArray();
        ArrayList<Student> matchesArray = new ArrayList<>();

        String[] searchedStrings = {fullName, progLang};
        Integer[] searchedParameters = {assignmentsAmount, assignmentsLeft};

//        entriesArray.stream()
//                .filter(entry-> {entry.getFullName().equals(fullName) || entry.getFullName().equals("")})
//                .collect(Collectors.toList());

        for (Student currentEntry : entriesArray) {
            boolean isEquals = true;
            String[] entryStrings = { currentEntry.getFullName(), currentEntry.getProgrammingLanguage() };
            Integer[] entryParameters = {currentEntry.getAssignmentsAmount(), currentEntry.getAssignmentsAmount() - currentEntry.getPassedAmount() };

            for (int i=0; i<searchedStrings.length; i++)
                if (!(searchedStrings[i].equals(entryStrings[i])) && !(searchedStrings[i].equals(""))) isEquals=false;

            for (int i=0; i<searchedParameters.length; i++)
                if (!searchedParameters[i].equals(entryParameters[i]) && !(searchedParameters[i].equals(-1))) isEquals=false;

            if (isEquals) matchesArray.add(currentEntry);
        }

        return matchesArray;
    }

    private String[] findUniqueLanguages() {
        ArrayList<Student> entriesArray = model.getTableArray();

        HashSet<String> uniqueLanguages = new HashSet<>();
        uniqueLanguages.add("");
        for (Student currentEntry : entriesArray) uniqueLanguages.add(currentEntry.getProgrammingLanguage());

        String[] uniqueLanguagesArray = new String[uniqueLanguages.size()];
        return uniqueLanguages.toArray(uniqueLanguagesArray);
    }

    private Integer[] findUniqueAssignmentsAmount() {
        ArrayList<Student> entriesArray = model.getTableArray();

        HashSet<Integer> uniqueAssignmentsAmount = new HashSet<>();
        uniqueAssignmentsAmount.add(-1);
        for (Student currentEntry : entriesArray) uniqueAssignmentsAmount.add(currentEntry.getAssignmentsAmount());

        Integer[] uniqueLanguagesArray = new Integer[uniqueAssignmentsAmount.size()];
        return uniqueAssignmentsAmount.toArray(uniqueLanguagesArray);
    }
}
