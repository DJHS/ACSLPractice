package practice.kenken.mtproper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * KenKen implementation, properly multi-threaded with Concurrency and Executor
 * Notes: Adjust final variable NUM_THREADS to the number of threads to use
 * @author derek
 */

/*
 *        === Results ===
 * 6/14/2014 1:07 derek's laptop (4 threads) (note 100% cpu)
 * 6/15/2014 1:06 derek's laptop (4 threads) (note 100% cpu, ~25% each thread, 3.34 load)
 * 6/15/2014 0:49 derek's laptop (2 threads) (note 50% cpu, ~25% each thread)
 * 6/15/2014 0:51 derek's laptop (8 threads) (note 100% cpu, ~13% each thread)
 * 6/15/2014 0:24 derek's laptop (16 threads) (note 100% cpu ~6% each thread, 4.21 load)
 * 6/15/2014 0:48 derek's laptop (32 threads) (note 100% cpu, ~3% each thread, 6.94 load)
 *        ===============
 */

public class KenKenMtProper {
    
    //ADJUST TO OPTIMAL NUMBER OF THREADS TO UTILIZE!!
    static final int NUM_THREADS = 4;
    
    static final int[] SIDE_LENGTHS = new int[]{3,4};
    
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File(args[0]));
        
        long maxValue;
        List<Section> sections;
        ExecutorService executor;
        DropSite<int[]> dropSite;
        int[] resultArr;
        
        for(int sideLength : SIDE_LENGTHS){
            maxValue = (long) Math.pow(sideLength, Math.pow(sideLength, 2)) - 1;
            sections = createSections(scan.nextLine());
            executor = new ThreadPoolExecutor(NUM_THREADS, NUM_THREADS, 5, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            dropSite = new DropSite<>();
            long chop = maxValue / NUM_THREADS;
            long start = 0;
            for (int i = 0; i < NUM_THREADS - 1; i++) {
                executor.execute(new KenKenTask(start, start + chop, sections, sideLength, dropSite));
                start = start + chop + 1;
            }
            executor.execute(new KenKenTask(start, maxValue, sections, sideLength, dropSite));
            
            resultArr = dropSite.get(); //Blocks/Waits until one of the threads finds it
            executor.shutdownNow(); //Stop all other threads
            printResult(resultArr, scan.nextLine());
        }
        
    }
    
    private static List<Section> createSections(String s) {
        Scanner scan = new Scanner(s);
        scan.useDelimiter("\\s*,\\s*");
        List<Section> result = new ArrayList<>();
        Section section = new Section();
        String c;
        int target, sum;
        scan.next(); //Get rid of first number
        while (scan.hasNext()) {
            c = scan.next();
            if (c.matches("\\d+")) {
                target = Integer.parseInt(c);
                section.addTarget(target);
            } else {
                sum = Integer.parseInt(c.substring(0, c.length() - 1));
                section.setSum(sum);
                result.add(section);
                section = new Section();
            }
        }
        return result;
    }

    private static void printResult(int[] arr, String cellNumbers) {
        Scanner scan = new Scanner(cellNumbers);
        scan.useDelimiter("\\s*,\\s*");
        while (scan.hasNextInt()) {
            System.out.println(arr[scan.nextInt() - 1] + 1);
        }
    }

}
