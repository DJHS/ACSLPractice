package practice.hexfractions.contestversionfix;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @author derek
 */
public class TestWithAlgAdjustment {

    public static int MAX_DIGITS = 15;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int count = 0;
            String digits = ".";
            double numToHex = scan.nextDouble();
            double test;
            double valToSubtract = 1.0 / 16;
            while (digits.length() < MAX_DIGITS + 1) {
                test = numToHex - valToSubtract;
                if(test < 0){
                    digits += toHex(Integer.toString(count));
                    count = 0;
                    valToSubtract *= (1.0 / 16);
                }else if(test == 0){
                    digits += toHex(Integer.toString(++count));
                    break;
                }else{
                    count++;
                    numToHex = test;
                }
            }

            if (digits.length() > 10) {
                digits = digits.substring(0, 11);
            }

            System.out.println(digits);
        }
    }

    public static String toHex(String bin) {
        String finalString = bin;
        finalString = finalString.replaceAll("10", "A");
        finalString = finalString.replaceAll("11", "B");
        finalString = finalString.replaceAll("12", "C");
        finalString = finalString.replaceAll("13", "D");
        finalString = finalString.replaceAll("14", "E");
        finalString = finalString.replaceAll("15", "F");
        return finalString;
    }
}
