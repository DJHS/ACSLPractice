package practice.kenken;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import static practice.kenken.KenKen.check;
import static practice.kenken.KenKen.confirmSections;
import static practice.kenken.KenKen.createArr;
import static practice.kenken.KenKen.createSections;
import static practice.kenken.KenKen.increment;
import static practice.kenken.KenKen.printResult;

/**
 * @author derek
 */
public class KenKenMultiThreaded extends Thread {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File(args[0]));
        List<Section> sections;
        int[] arr;

        // 3x3 board
        sections = createSections(scan.nextLine());
        arr = createArr(3);
        while (increment(arr, 0, 3)) {
            if (check(arr, 3)) {
                if (confirmSections(arr, sections)) {
                    break;
                }
            }
        }
        printResult(arr, scan.nextLine());

        // 4x4 board
        sections = createSections(scan.nextLine());
        int[] arr1 = createArr(4);
        int[] arr2 = createArr(4);
        arr2[arr2.length - 1] = 2;
        int[] arr3 = createArr(4);
        arr3[arr3.length - 1] = 3;
        int[] arr4 = createArr(4);
        arr4[arr4.length - 1] = 4;
        String cellNumbers = scan.nextLine();
        
        new KenKenMultiThreaded(arr1, sections, cellNumbers).start();
        new KenKenMultiThreaded(arr2, sections, cellNumbers).start();
        new KenKenMultiThreaded(arr3, sections, cellNumbers).start();
        new KenKenMultiThreaded(arr4, sections, cellNumbers).start();

    }
    
    private int[] arr;
    private List<Section> sections;
    private String cellNumbers;
    public KenKenMultiThreaded(int[] arr, List<Section> sections, String cellNumbers){
        this.arr = arr;
        this.sections = sections;
        this.cellNumbers = cellNumbers;
    }
    @Override public void run(){
        while (increment(arr, 0, 4)) {
            if (check(arr, 4)) {
                if (confirmSections(arr, sections)) {
                    printResult(arr, cellNumbers);
                    System.exit(0);
                }
            }
        }
    }
}
