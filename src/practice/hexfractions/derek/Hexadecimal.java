package practice.hexfractions.derek;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.*;
/**
 * @author derek
 * @deprecated
 */
@Deprecated /* See HexFractionsFullArbitrarySolution for a fully 
               arbitrary-precision solution to Hex Fractions, this solution
               depends on a SIGMA of 1e-20 and can only maintain 13 precise 
               digits due to inherrent conversion to double. The aforementioned
               solution does not, and is instead fully arbitrary. */
public class Hexadecimal {

    static final int MAX_PRECISE_LENGTH = 13;
    static final int MAX_COUNT = 20;
    static final double SIGMA = 1e-20;
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

    List<Character> rep;

    public Hexadecimal(List<Character> characterListRepresentation) {
        rep = characterListRepresentation;
        truncateIfNotPrecise();
        correctIfRepeatsElseTruncate();
    }

    public Hexadecimal(BigDecimal dec) {
        rep = new ArrayList<>();
        int fullWhole = dec.toBigInteger().intValue();
        int quotient = fullWhole;
        int remainder;
        while (quotient > 0) {
            remainder = quotient % 16;
            rep.add(HEXVALS.get(remainder));
            quotient = quotient / 16;
        }
        rep.add('.');

        BigInteger wholePart;
        BigDecimal fracPart = dec.subtract(new BigDecimal(fullWhole));
        BigDecimal multiplied;
        int count = 0;
        while ((fracPart.abs().doubleValue() > SIGMA) && count < MAX_COUNT) {
            multiplied = fracPart.multiply(SIXTEEN);
            wholePart = multiplied.toBigInteger();
            rep.add(HEXVALS.get(wholePart.intValue()));
            fracPart = multiplied.subtract(new BigDecimal(wholePart));
            count++;
        }
        truncateIfNotPrecise();
        correctIfRepeatsElseTruncate();
    }

    void correctIfRepeatsElseTruncate() {
        int dotIndex = rep.indexOf('.');
        int repeatingIndex = getRepeatingIndex();
        if (repeatingIndex == -1) {
            subList(dotIndex + 1 + 10);
        } else {
            subList(repeatingIndex);
        }
    }

    void truncateIfNotPrecise() {
        int dotIndex = rep.indexOf('.');
        if (rep.size() - dotIndex - 1 > MAX_PRECISE_LENGTH) {
            subList(dotIndex + 1 + MAX_PRECISE_LENGTH);
        }
    }

    void subList(int repeatingIndex) {
        while (repeatingIndex < rep.size() && repeatingIndex != -1) {
            rep.remove(repeatingIndex);
        }
    }

    int getRepeatingIndex() {
        int repeatingIndex = -1;
        int dotIndex = rep.indexOf('.');
        int chop = dotIndex + 1;
        int end = (rep.size() - chop) / 2 + 1 + dotIndex;
        for (int i = dotIndex + 1; i <= end; i++) {
            if (isRepeating(i)) {
                repeatingIndex = i;
                break;
            }
        }
        return repeatingIndex;
    }

    boolean isRepeating(int indexUnderQuestion) {
        for (int i = 0; i < indexUnderQuestion; i++) {
            if (rep.get(i).equals(rep.get(indexUnderQuestion))) {
                if (isRepeating(i, indexUnderQuestion)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isRepeating(int targetIndex, int indexUnderQuestion) {
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
