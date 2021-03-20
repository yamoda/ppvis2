import table.controller.MainApplicationController;
import table.view.MainApplicationView;
import table.model.StudentModel;

public class Main {
    public static void main(String[] args) {
       StudentModel applicationModel = new StudentModel();
       MainApplicationView applicationView = new MainApplicationView();
       MainApplicationController applicationController = new MainApplicationController(applicationModel, applicationView);
    }
}
