package practice.kenken;

import java.util.Arrays;
import static practice.kenken.KenKen.increment;

/**
 * @author derek
 */
public class IncrementTest {
    public static void main(String[] args) {
        int[] test = new int[9];
        int count = 0;
        while (increment(test, 0, 3)) {
            System.out.println(Arrays.toString(test));
            count++;
        }
        System.out.println("count = " + count);
    }
}
