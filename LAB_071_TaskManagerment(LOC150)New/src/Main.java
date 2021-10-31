
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "List.dat";
        // loop until user want to exit program
        while (true) {
            //display menu
            Manager.displayMenu();
            // choice option
            int choice = GetValue.getIntNumberInRange(1, 4, "Your choice");
            switch (choice) {
                case 1:
                    // add task
                    Manager.addTask(fileName);
                    break;
                case 2:
                    // delete task
                    Manager.deleteTask(fileName);
                    break;
                case 3:
                    // display task
                    Manager.displayTask(fileName);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

}
