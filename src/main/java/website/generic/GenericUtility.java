package website.generic;

import java.util.Random;

public class GenericUtility {

  public static String generateRandomPhoneNumber() {
    String phonenumber = "5";
    Random random = new Random();
    phonenumber += random.nextInt(1000000000);
    if (phonenumber.length() != 10) phonenumber += phonenumber.concat("0");
    return phonenumber;
  }

  public static void main(String[] args) {
    String str = generateRandomPhoneNumber();
    System.out.println(str);

  }
}