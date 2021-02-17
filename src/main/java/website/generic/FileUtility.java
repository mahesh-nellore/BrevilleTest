package website.generic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {

  public static void main(String[] args) {
    String path = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "D365TestData.txt";
    String value = "264110";
    createFile(path);
    writeDataToFile(path, value);
    System.out.println(getTheRowsCountFromTextFile(path));
    System.out.println(readDataFromFile(path));

  }

  public static void createFile(String filePath) {
    try {
      File myObj = new File(filePath);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch(IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void writeDataToFile(String filePath, String value) {
    try {
      FileWriter myWriter = new FileWriter(filePath);
      myWriter.write(value);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch(IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static String readDataFromFile(String filePath) {
    String value = "";
    try {
      FileReader reader = new FileReader(filePath);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        value = line;
      }
      reader.close();

    } catch(IOException e) {
      e.printStackTrace();
    }
    return value;
  }

  public static int getTheRowsCountFromTextFile(String filePath) {
    int count = 0;
    try {
      FileReader reader = new FileReader(filePath);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        count++;
      }
      reader.close();

    } catch(IOException e) {
      e.printStackTrace();
    }
    return count;
  }

}