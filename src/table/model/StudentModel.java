package table.model;

import java.util.ArrayList;

public class StudentModel {
    ArrayList<Student> tableArray;

    public StudentModel() { tableArray = new ArrayList<Student>(0); }

    public ArrayList<Student> getTableArray() { return tableArray; }

    public void setTableArray(ArrayList<Student> tableArray) { this.tableArray = tableArray; }


    public int size() { return tableArray.size(); }
}
