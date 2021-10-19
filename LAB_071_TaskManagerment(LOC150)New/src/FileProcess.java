
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class FileProcess {

//    public static ArrayList<Task> readListTask(String fileName) {
//        boolean fileExisted = Validation.checkFileExisted(fileName);
//        if (fileExisted == false) {
//            System.out.println("File not existed in system!\n");
//            writeListTask(new ArrayList<Task>(), fileName);
//        }
//        try {
//            FileInputStream fileInput = new FileInputStream(fileName);
//            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
//            ArrayList<Task> list = (ArrayList<Task>) inputStream.readObject();
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void writeListTask(ArrayList<Task> list, String fileName) {
//        FileOutputStream fileOutput = null;
//        try {
//            fileOutput = new FileOutputStream(fileName);
//            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
//            for (Task task : list) {
//                outputStream.writeObject(task);
//            }
//        } catch (Exception e) {
//             e.printStackTrace();
//        } finally {
//            try {
//                fileOutput.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static List<Task> readListTask(String fileName) {
        FileInputStream fileInput = null;
        boolean fileExisted = Validation.checkFileExisted(fileName);
        if (fileExisted == false) {
            System.out.println("File not existed in system!\n");
            // create new file
            writeListTask(new ArrayList<Task>(), fileName);
        }
        try {
            fileInput = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            List<Task> listAccount = (List<Task>) inputStream.readObject();
            return listAccount;
        } catch (Exception ex) {
        } finally {
            try {
                fileInput.close();
            } catch (Exception e) {
            }
        }
        return new ArrayList<>();
    }

    public static void writeListTask(ArrayList<Task> list, String fileName) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutput.close();
            } catch (IOException ex) {
            }
        }

    }
}
