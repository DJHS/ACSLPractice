package hexfractions.contestversion;

import java.math.BigDecimal;
import java.util.Scanner;

public class TestFullArbitraryWithAlgAdj {

    static final BigDecimal SIXTEEN = new BigDecimal(16);
    static final BigDecimal INV_SIXTEEN = BigDecimal.ONE.divide(SIXTEEN);
    static final int MAX_DIGITS = 15;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int count = 0;
            String digits = ".";
            BigDecimal numToHex = new BigDecimal(scan.nextLine());
            BigDecimal test;
            BigDecimal valToSubtract = INV_SIXTEEN;
            while (digits.length() < MAX_DIGITS + 1) {
                test = numToHex.subtract(valToSubtract);
                int compare = test.compareTo(BigDecimal.ZERO);
                if (compare < 0) {
                    digits += toHex(Integer.toString(count));
                    count = 0;
                    valToSubtract = valToSubtract.multiply(INV_SIXTEEN);
                }else if(compare == 0){
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
