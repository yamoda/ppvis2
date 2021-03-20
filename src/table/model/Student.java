package table.model;

public class Student {
    private String fullName;
    private String programmingLanguage;

    private int year;
    private int groupId;
    private int assignmentsAmount;
    private int passedAmount;

    public Student() {}

    public Student(String fullName, int year, int groupId, int assignmentsAmount, int passedAmount, String programmingLanguage) {
        this.fullName = fullName;
        this.year = year;
        this.groupId = groupId;
        this.assignmentsAmount = assignmentsAmount;
        this.passedAmount = passedAmount;
        this.programmingLanguage = programmingLanguage;
    }

    public boolean equals(Student other) {
        return  this.fullName.equals(other.fullName) && this.year == other.year &&
                this.groupId == other.groupId && this.assignmentsAmount == other.assignmentsAmount &&
                this.passedAmount == other.passedAmount && this.programmingLanguage.equals(other.programmingLanguage);
    }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setProgrammingLanguage(String programmingLanguage) { this.programmingLanguage = programmingLanguage; }

    public void setYear(int year) { this.year = year; }

    public void setGroupId(int groupId) { this.groupId = groupId; }

    public void setAssignmentsAmount(int assignmentsAmount) { this.assignmentsAmount = assignmentsAmount; }

    public void setPassedAmount(int passedAmount) { this.passedAmount = passedAmount; }

    public String getFullName() { return fullName; }

    public String getProgrammingLanguage() { return programmingLanguage; }

    public int getYear() { return year; }

    public int getGroupId() { return groupId; }

    public int getAssignmentsAmount() { return assignmentsAmount; }

    public int getPassedAmount() { return passedAmount; }
}
