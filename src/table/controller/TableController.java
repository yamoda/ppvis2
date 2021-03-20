package table.controller;

import table.model.Student;
import table.model.StudentModel;
import table.view.tableview.TableView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TableController {
    private TableView controlledTableView;
    private StudentModel controlledModel;

    private int rowsOnPage;
    private int currentPage = 1;

    public TableController(TableView controlledTableView, StudentModel controlledModel, int rowsOnPage) {
        this.controlledTableView = controlledTableView;
        this.controlledModel = controlledModel;
        this.rowsOnPage = rowsOnPage;
    }

    public void toFirstPage() {
        currentPage = 1;
        updateTableView();
    }

    public void toLastPage() {
        currentPage = controlledModel.size() / rowsOnPage + 1;
        updateTableView();
    }

    public void nextPage() {
        if (currentPage <= controlledModel.size() / rowsOnPage) {
            currentPage++;
            updateTableView();
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            updateTableView();
        }
    }

    private void clearTableView() {
        DefaultTableModel tableViewModel = (DefaultTableModel) controlledTableView.getTable().getModel();
        int rowsOnScreen = tableViewModel.getRowCount();

        for(int rowIdx=rowsOnScreen-1; rowIdx>=0; rowIdx--) tableViewModel.removeRow(rowIdx);
    }

    public void updateTableView() {
        clearTableView();
        DefaultTableModel tableViewModel = (DefaultTableModel) controlledTableView.getTable().getModel();

        int startIdx = (currentPage-1)*rowsOnPage;
        int endIdx = currentPage*rowsOnPage;

        ArrayList<Student> tablesArray = controlledModel.getTableArray();
        int entriesOnPage = 0;
        for (int entryIdx=startIdx; entryIdx<endIdx; entryIdx++) {
            if (entryIdx >= tablesArray.size()) break;

            Student currentEntry = tablesArray.get(entryIdx);
            Object[] newRowToDisplay = {
                    currentEntry.getFullName(),
                    currentEntry.getYear(),
                    currentEntry.getGroupId(),
                    currentEntry.getAssignmentsAmount(),
                    currentEntry.getPassedAmount(),
                    currentEntry.getProgrammingLanguage(),
            };

            tableViewModel.addRow(newRowToDisplay);
            entriesOnPage++;
        }
        updateTableStatus(entriesOnPage);
    }

    private void updateTableStatus(int entriesOnPage) {
        int pagesAmount = controlledModel.size() / rowsOnPage + 1;
        controlledTableView.getTableStatus().setText("" +
            "Номер страницы: " + currentPage + "   " +
            "Всего страниц: " + pagesAmount + "   " +
            "Записей на странице: " + entriesOnPage + "   " +
            "Всего записей: " + controlledModel.size()
        );
    }

    public void updateModel(ArrayList<Student> newTableArray) { controlledModel.setTableArray(newTableArray); }
}
