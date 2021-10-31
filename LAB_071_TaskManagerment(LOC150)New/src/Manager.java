
import java.io.File;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class Manager {

    public static void displayMenu() {
        System.out.println("========= Task program =========");
        System.out.println("  1.  Add Task");
        System.out.println("  2.  Delete Task");
        System.out.println("  3.  Display Task");
        System.out.println("  4.  Exit");
    }

    public static void addTask(String fileName) {
        ArrayList<Task> list = new ArrayList<>();
        // read data from file
        list.addAll(FileProcess.readListTask(fileName));
        System.out.println("------------Add Task---------------");
        int id = GetValue.getId(list);
        String name = GetValue.getInputString("Requirement Name");
        String taskType = GetValue.getTaskType();
        String date = GetValue.getDate();
        if (date == null) { // out of time working
            return;
        }
        double from = GetValue.getStartTime(date);
        double to = GetValue.getEndTime(from);
        String assignee = GetValue.getInputString("Assignee");
        // check task was duplicated
        boolean taskDuplicated = Validation.checkTaskDuplicate(list, date, from, to, assignee);
        if (taskDuplicated == true) {
            System.out.println("This task was duplicated time!\n");
            return;
        }
        String reveiwer = GetValue.getInputString("Reveiwer");
        list.add(new Task(id, name, taskType, date, from, to, assignee, reveiwer));
        // write data to file
        FileProcess.writeListTask(list, fileName);
        System.out.println("Successfully!\n");
    }

    public static void deleteTask(String fileName) {
        ArrayList<Task> list = new ArrayList<>();
        // check file existed
        boolean fileExisted = Validation.checkFileExisted(fileName);
        if (fileExisted == false) {
            System.out.println("File not existed in system!\n");
            return;
        }
        // read data from file
        list.addAll(FileProcess.readListTask(fileName));
        System.out.println("------------Delete Task---------------");
        // get id to delete task
        int id = GetValue.getInputPositiveInt("ID");
        // check have a task have same id in list
        boolean taskExisted = Validation.checkTaskExistedById(list, id);
        if (taskExisted == false) {
            System.out.println("Task with this id not contain in system!\n");
        } else {
            // delete task
            // traverse all element in list to delete obj have same id to remove
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    list.remove(i);
                    System.out.println("Successfully!\n");
                    break;
                }
            }
        }
        // write data to file
        FileProcess.writeListTask(new ArrayList<Task>(list), fileName);
    }

    public static void displayTask(String fileName) {
        ArrayList<Task> list = new ArrayList<>();
        boolean fileExisted = Validation.checkFileExisted(fileName);
        if (fileExisted == false) {
            System.out.println("File not existed in system!\n");
            return;
        }
        // read data from file
        list.addAll(FileProcess.readListTask(fileName));
        if (list.size() == 0) { // check empty
            System.out.println("System is empty!\n");
            return;
        }
        System.out.println("----------------------------------------- Task ----"
                + "-------------------------------------");
        String format = String.format("%-5s%-20s%-15s%-15s%-10s%-15s%-15s", "ID",
                "Name", "Task Type", "Date", "Time", "Assignee", "Reveiwer");
        System.out.println(format);
        // traverse all element of list to get data of task
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("");
    }

}
