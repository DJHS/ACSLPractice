package practice.kenken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import static practice.kenken.KenKen.check;
import static practice.kenken.KenKen.confirmSections;
import static practice.kenken.KenKen.createArr;
import static practice.kenken.KenKen.createSections;
import static practice.kenken.KenKen.printResult;

/**
 * @author derek
 */
public class KenKenThreadedWithExecutor extends Thread {

    static ThreadPoolExecutor executor;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("/home/derek/Desktop/KenKen.txt"));
        List<Section> sections;
        int[] arr1, arr2, arr3, arr4;
        String cellNumbers;

        // 3x3 board
        sections = createSections(scan.nextLine());

        arr1 = createArr(3);
        arr2 = createArr(3);
        arr2[arr2.length - 1] = 2;
        arr3 = createArr(3);
        arr3[arr3.length - 1] = 3;

        cellNumbers = scan.nextLine();

        executor = new ScheduledThreadPoolExecutor(4);
        executor.execute(new KenKenThreadedWithExecutor(arr1, sections, cellNumbers, 3));
        executor.execute(new KenKenThreadedWithExecutor(arr2, sections, cellNumbers, 3));
        executor.execute(new KenKenThreadedWithExecutor(arr3, sections, cellNumbers, 3));

        // 4x4 board
        sections = createSections(scan.nextLine());

        arr1 = createArr(4);
        arr2 = createArr(4);
        arr2[arr2.length - 1] = 2;
        arr3 = createArr(4);
        arr3[arr3.length - 1] = 3;
        arr4 = createArr(4);
        arr4[arr4.length - 1] = 4;

        cellNumbers = scan.nextLine();

        executor = new ScheduledThreadPoolExecutor(4);
        executor.execute(new KenKenThreadedWithExecutor(arr1, sections, cellNumbers, 4));
        executor.execute(new KenKenThreadedWithExecutor(arr2, sections, cellNumbers, 4));
        executor.execute(new KenKenThreadedWithExecutor(arr3, sections, cellNumbers, 4));
        executor.execute(new KenKenThreadedWithExecutor(arr4, sections, cellNumbers, 4));

    }

    private int[] arr;
    private List<Section> sections;
    private String cellNumbers;
    private int sideLength;

    public KenKenThreadedWithExecutor(int[] arr, List<Section> sections, String cellNumbers, int sideLength) {
        this.arr = arr;
        this.sections = sections;
        this.cellNumbers = cellNumbers;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        while (!isInterrupted() && increment(arr, 0, sideLength)) {
            if (check(arr, sideLength)) {
                if (confirmSections(arr, sections)) {
                    printResult(arr, cellNumbers);
                    executor.shutdownNow();
                }
            }
        }
    }

    public static boolean increment(int[] arr, int index, int sideLength) {
        if (index < arr.length - 1) {
            if (arr[index] == sideLength) {
                arr[index] = 1;
                return increment(arr, index + 1, sideLength);
            } else {
                arr[index] = arr[index] + 1;
                return true;
            }
        } else {
            return false;
        }
    }
}
