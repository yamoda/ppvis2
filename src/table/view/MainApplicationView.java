package table.view;

import table.view.tableview.TableView;

import javax.swing.*;
import java.awt.*;


public class MainApplicationView {
    private JFrame mainWindow;
    private TableView mainTable;

    JMenuItem saveItem;
    JMenuItem openItem;

    JMenuItem newEntryItem;
    JMenuItem searchItem;
    JMenuItem deleteItem;

    JButton openButton;
    JButton saveButton;

    JButton newEntryButton;
    JButton searchButton;
    JButton deleteButton;

    public MainApplicationView() {
        mainWindow = new JFrame();

        createMenu();
        createToolbar();
        createTable();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");

        saveItem = new JMenuItem("Сохранить");
        openItem = new JMenuItem("Загрузить");

        fileMenu.add(saveItem);
        fileMenu.add(openItem);

        JMenu toolsMenu = new JMenu("Инструменты");

        newEntryItem = new JMenuItem("Добавить");
        searchItem = new JMenuItem("Найти");
        deleteItem = new JMenuItem("Удалить");

        toolsMenu.add(newEntryItem);
        toolsMenu.add(searchItem);
        toolsMenu.add(deleteItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        mainWindow.setJMenuBar(menuBar);
    }

    private void createToolbar() {
        JToolBar windowToolbar = new JToolBar();

        openButton = new JButton("Загрузить");
        saveButton = new JButton("Сохранить");

        newEntryButton = new JButton("Добавить");
        searchButton = new JButton("Найти");
        deleteButton = new JButton("Удалить");

        windowToolbar.add(openButton);
        windowToolbar.add(saveButton);
        windowToolbar.add(newEntryButton);
        windowToolbar.add(searchButton);
        windowToolbar.add(deleteButton);

        mainWindow.add(windowToolbar, BorderLayout.NORTH);
    }

    public void createTable() {
        mainTable = new TableView();
        mainWindow.add(mainTable);
    }

    public JFrame getFrame() { return mainWindow; }

    public TableView getTableView() { return mainTable; }

    public JMenuItem getSaveItem() { return saveItem; }

    public JMenuItem getOpenItem() { return openItem; }

    public JMenuItem getNewEntryItem() { return newEntryItem; }

    public JMenuItem getSearchItem() { return searchItem; }

    public JMenuItem getDeleteItem() { return deleteItem; }

    public JButton getOpenButton() { return openButton; }

    public JButton getSaveButton() { return saveButton; }

    public JButton getNewEntryButton() { return newEntryButton; }

    public JButton getSearchButton() { return searchButton; }

    public JButton getDeleteButton() { return deleteButton; }
}