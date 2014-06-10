package practice.hexfractions.contestversionfix;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author derek
 */
public class TestWithSigmaAdjustment {

    public static double sixTo = 1.0 / Math.pow(16, 20);
    public static double SIGMA = 1e-15;
    public static double INV_SIXTEEN_ADJUSTED = (1.0 / 16.0 - SIGMA);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int numDigits = 1;
            int count = 0;
            String digits = ".";
            double numToHex = scan.nextDouble();;
            double valToSubtract = INV_SIXTEEN_ADJUSTED;
            while (numToHex > SIGMA) {
                if (numToHex > valToSubtract) {
                    numToHex -= valToSubtract;
                    count++;
                } else {
                    digits += toHex(Integer.toString(count));
                    count = 0;
                    numDigits = 0;
                    /* Note adjustment not necessary
                       on multiply, as error propogation is MUCH less 
                       (why Derek's soludion does not have this issue) */
                    valToSubtract *= 1.0 / 16.0; 
                }

            }
            if(digits.length() > 12){
                digits = digits.substring(0,digits.length()-1);
            }
            if(digits.matches("\\.[0-9A-F]+[0]+")){
                String digitsReversed = new StringBuilder(digits).reverse().toString();
                digits = new StringBuilder(digitsReversed.replaceFirst("[0]+", "")).reverse().toString();
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
