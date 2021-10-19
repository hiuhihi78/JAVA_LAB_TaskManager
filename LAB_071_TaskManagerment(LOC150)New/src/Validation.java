
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class Validation {

    public static boolean checkStringValidDateFormat(String string) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkDateExited(String string) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkDate_1LessThanDate_2(String date_1, String date_2) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date firstDate;
        Date secondDate;
        try {
            firstDate = dateFormat.parse(date_1);
            secondDate = dateFormat.parse(date_2);
            if (firstDate.compareTo(secondDate) < 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkDateEqualCurrentDate(String string) {
        Date dateTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateString = dateFormat.format(dateTime);
        Date currentDate;
        Date date;
        try {
            currentDate = dateFormat.parse(currentDateString);
            date = dateFormat.parse(string);
            if (currentDate.equals(date)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean checkTaskExistedById(ArrayList<Task> list, int id) {
        // traverse all element of list to check obj have same id
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkTaskDuplicate(ArrayList<Task> list, String date, double from, double to, String assignee) {
        boolean taskExisted = Validation.checkTaskExistedByDateAssignee(list, date, assignee);
        if (taskExisted == true) {
            // travase all element of list to check task duplicate
            for (Task task : list) {
                if (task.getAssignee().equalsIgnoreCase(assignee)
                        && task.getDate().equalsIgnoreCase(date)) {
                    boolean fromDuplicate = checkANumberInRange(from, task.getFrom(), task.getTo());
                    boolean toDuplicate = checkANumberInRange(to, task.getFrom(), task.getTo());
                    boolean fromTaskDuplicate = checkANumberInRange(task.getFrom(), from, to);
                    boolean toTaskDuplicate = checkANumberInRange(task.getTo(), from, to);
                    if (fromDuplicate == false && toDuplicate == false
                            && fromTaskDuplicate == false && toTaskDuplicate == false) {
                        return false;
                    }
                    if (fromDuplicate == true || toDuplicate == true
                            || fromTaskDuplicate == true || toTaskDuplicate == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkTaskExistedByDateAssignee(ArrayList<Task> list, String date, String assignee) {
        for (Task task : list) {
            if (task.getAssignee().equalsIgnoreCase(assignee)
                    && task.getDate().equalsIgnoreCase(date)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkANumberInRange(double number, double from, double to) {
        if (number > from && number < to) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDate_1EqualDate_2(String date_1, String date_2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fristDate, secondDate;
        try {
            fristDate = dateFormat.parse(date_1);
            secondDate = dateFormat.parse(date_2);
            if (fristDate.equals(secondDate)) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean checkFileExisted(String fileName) {
        FileReader file = null;
        try {
            file = new FileReader(fileName);
            file.close();
            return true;
        } catch (Exception e) {
            try {
                file.close();
            } catch (Exception ex) {
            }
            return false;
        }
//        try {
//            FileReader fileReader = new FileReader(fileName);
//            return true;
//        } catch (Exception e) {
//            System.out.println("File not existed in system!\n");
//            return false;
//        }
    }

}
