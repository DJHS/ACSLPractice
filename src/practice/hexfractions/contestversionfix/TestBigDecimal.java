package practice.hexfractions.contestversionfix;

import java.math.BigDecimal;
import java.util.Scanner;

public class TestBigDecimal {

    public static double sixTo = 1.0 / Math.pow(16, 20);
    public static double SIGMA = 1e-15;
    public static final BigDecimal SIXTEEN = new BigDecimal(16);
    public static final BigDecimal INV_SIXTEEN = (BigDecimal.ONE.divide(SIXTEEN)).subtract(new BigDecimal(SIGMA));

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("BigDecimal Test");
        for (int i = 0; i < 10; i++) {
            int numDigits = 1;
            int count = 0;
            String digits = ".";
            BigDecimal numToHex = new BigDecimal(scan.nextLine());
            BigDecimal valToSubtract = INV_SIXTEEN;
            double numToHexDouble;
            while ((numToHexDouble = numToHex.abs().doubleValue()) > SIGMA /*sixTo*/) {
                if (numToHexDouble > valToSubtract.doubleValue() && Math.abs(numToHexDouble - valToSubtract.doubleValue()) > SIGMA) {
                    numToHex = numToHex.subtract(valToSubtract);
                    count++;
                } else {
                    System.out.println("count = " + count);
                    digits += toHex(Integer.toString(count));
                    count = 0;
                    numDigits = 0;
                    valToSubtract = valToSubtract.multiply(INV_SIXTEEN);
                }

            }

            //digits= digits.substring(0,15);
            //if(doesRepeat(digits, 1)){
            //digits=reduceRepeat(digits);
            //}
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
