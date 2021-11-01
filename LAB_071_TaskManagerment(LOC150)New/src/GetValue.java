
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class GetValue {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntNumberInRange(int from, int to, String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            result = getInputPositiveInt(msg);
            if (result < from || result > to) {
                System.err.println("Invalid of " + msg + "!Please enter a number from "
                        + from + " to " + to + "!");
                continue;
            } else {
                return result;
            }
        }
    }

    public static String getInputString(String msg) {
        String result = null;
        // loop until user input correct
        while (true) {
            System.out.print(msg + ": ");
            result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Invalid of " + msg + ", " + msg + " must not be empty!");
            } else {
                return result.trim();
            }
        }
    }

    public static int getInputPositiveInt(String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            try {
                String input = getInputString(msg);
                result = Integer.parseInt(input);
                if (result < 0) {
                    System.err.println("Invalid of " + msg + ", " + msg
                            + " must be positive number!");
                    continue;
                }
                return result;
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + ", " + msg
                        + " must be a number!");
            }
        }
    }

//    public static int getId(ArrayList<Task> list) {
//        int id = 0;
//        if (list.size() == 0) {
//            return 1;
//        }
//        Task lastTask = list.get(list.size() - 1);
//        id = lastTask.getId() + 1;
//        return id;
//    }
    
  

    public static String getTaskType() {
        // get number tasktypeId
        int taskTypeId = 0;
        // loop until user input taskTypeId correct
        while (true) {
            taskTypeId = getInputPositiveInt("Task Type");
            if (taskTypeId <= 0 || taskTypeId > 4) {
                System.err.println("Invalid of task type ID, task type ID must be in range 1 - 4!");
                continue;
            } else {
                break;
            }
        }
        // return string with task type number
        switch (taskTypeId) {
            case 1:
                return "Code";
            case 2:
                return "Test";
            case 3:
                return "Design";
            case 4:
                return "Review";
        }
        return null;
    }

    public static String getDate() {
        Date dateTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(dateTime);
        // loop until user input correct
        while (true) {
            // input date
            String date = getInputString("Date");
            // check valid format date
            boolean validDateFormat = Validation.checkStringValidDateFormat(date);
            // check date exited
            boolean dateExited = Validation.checkDateExited(date);
            // check date less than current date
            boolean dateLessThanCurrentDate = Validation.checkDate_1LessThanDate_2(date, currentDate);
            // check date eqaual with current date
            boolean dateEqualCurrentDate = Validation.checkDateEqualCurrentDate(date);

            if (dateEqualCurrentDate == true) {
                // check today can add more task (out of time working: 17)
                double currentTime = getCurrentTime();
                if (currentTime > 17) {
                    System.out.println("Today can not add more task(Out of working time)!\n");
                    return null;
                }
            }
            if (validDateFormat == true && dateExited == true && dateLessThanCurrentDate == false) {
                return date;
            } else if (validDateFormat == false) {
                System.err.println("Invalid of date, date must be follow format dd-MM-yyyy!");
                continue;
            } else if (dateExited == false) {
                System.err.println("Invalid of date, date must be exited!");
                continue;
            } else if (dateLessThanCurrentDate == true) {
                System.err.println("Invalid of date, date must be equal or greater"
                        + " current date " + currentDate + "!");
                continue;
            }
        }
    }



    public static double getStartTime(String date) {
        double startTime = 0;
        double currentTime = getCurrentTime();
        // loop until user input correct
        while (true) {
            // input start time in range 8 - 17
            startTime = getDoubleNumberInRange(8, 17, "From");
            // check time divisible by 0.5
            if (startTime % 0.5 != 0) {
                System.err.println("Invalid of From, From must be "
                        + "divisible by 0.5!");
                continue;
            }
            // date equal currenDate -> check start time greater current time
            boolean dateEqualCurrenTime = Validation.checkDateEqualCurrentDate(date);
            if (dateEqualCurrenTime == true) {
                if (startTime < currentTime) {
                    System.err.println("Invalid of From, From must be "
                            + "equal or greater than current time " + currentTime + "!");
                    continue;
                }
            }
            return startTime;
        }
    }

    public static double getEndTime(double startTime) {
        double endTime = 0;
        // loop until user input correct
        while (true) {
            // input end time in range (start time +0.5) - 17.5
            endTime = getDoubleNumberInRange((startTime + 0.5), 17.5, "To");
            // check time divisible by 0.5
            if (endTime % 0.5 != 0) {
                System.err.println("Invalid of To, To must be divisible by 0.5!");
                continue;
            }
//            // check end time greater start time
//            if (endTime <= startTime) {
//                System.err.println("Invalid of end time, end time must be greater than start time!");
//                continue;
//            }
            return endTime;
        }

    }
    
     public static double getCurrentTime() {
        Date date = new Date();
        DateFormat dateFormatHour = new SimpleDateFormat("kk");
        DateFormat dateFormatMinute = new SimpleDateFormat("mm");
        String stringCurrentHour = dateFormatHour.format(date);
        String stringCurrentMinute = dateFormatMinute.format(date);
        double time = 0;
        int hour = Integer.parseInt(stringCurrentHour);
        int minute = Integer.parseInt(stringCurrentMinute);
        if (minute == 0) {
            time = hour;
        } else if (minute <= 30) {
            time = hour + 0.5;
        } else {
            time = hour + 1;
        }
        return time;
    }

    private static double getDoubleNumberInRange(double from, double to, String msg) {
        double result = 0;
        // loop until user input correct
        while (true) {
            try {
                System.out.print(msg + ": ");
                String temp = scanner.nextLine().trim();
                result = Double.parseDouble(temp);
                if (result < from || result > to) {
                    System.err.println("Invalid of " + msg + ", " + msg
                            + " must be in range " + from + " - " + to + "!");
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be a number!");
            }
        }
    }

   

}
