package practice.hexfractions.derek;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author derek
 */
public class HexFractionsFullArbitrarySolution {

    static final int MAX_COUNT = 20;
    static final BigDecimal SIXTEEN = new BigDecimal(16);
    static final Map<Integer, Character> HEXVALS = new HashMap<>();

    static {
        for (int i = 0; i <= 9; i++) {
            HEXVALS.put(i, (char) ('0' + i));
        }
        for (int i = 10; i <= 15; i++) {
            HEXVALS.put(i, (char) ('A' + i - 10));
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hex Fractions: A fully arbitrary-precision solution");
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            System.out.println(new HexFractionsFullArbitrarySolution(new BigDecimal(scan.nextLine())));
        }
    }

    List<Character> rep;
    
    public HexFractionsFullArbitrarySolution(BigDecimal dec) {
        rep = new ArrayList<>();
        rep.add('.');
        BigInteger wholePart;
        BigDecimal fracPart = dec;
        int count = 0;
        while((fracPart.compareTo(BigDecimal.ZERO) != 0) && count < MAX_COUNT){
            dec = fracPart.multiply(SIXTEEN);
            wholePart = dec.toBigInteger();
            fracPart = dec.subtract(new BigDecimal(wholePart));
            rep.add(HEXVALS.get(wholePart.intValue()));
            count++; 
        }
        
        int repeatingIndex = getRepeatingIndex();
        if (repeatingIndex == -1) {
            truncate(11);
        } else {
            truncate(repeatingIndex);
        }
    }

    private void truncate(int startIndex) {
        while (startIndex < rep.size() && startIndex != -1) {
            rep.remove(startIndex);
        }
    }

    private int getRepeatingIndex() {
        int dotIndex = rep.indexOf('.');
        int chop = dotIndex + 1;
        int end = (rep.size() - chop) / 2 + 1 + dotIndex;
        for (int i = dotIndex + 1; i <= end; i++) {
            if (isRepeating(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isRepeating(int indexUnderQuestion) {
        for (int i = 0; i < indexUnderQuestion; i++) {
            if (rep.get(i).equals(rep.get(indexUnderQuestion))) {
                if (isRepeating(i, indexUnderQuestion)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRepeating(int targetIndex, int indexUnderQuestion) {
        for (int i = 0; indexUnderQuestion + i < rep.size(); i++) {
            if (!rep.get(targetIndex + i).equals(rep.get(indexUnderQuestion + i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Character c : rep) {
            builder.append(c);
        }
        return builder.toString();
    }

}
