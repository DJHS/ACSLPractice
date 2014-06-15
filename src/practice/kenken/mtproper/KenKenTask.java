package practice.kenken.mtproper;

import java.util.ArrayList;
import java.util.List;

/**
 * KenKen implementation, properly multi-threaded with Concurrency and Executor
 * @author derek
 */
public class KenKenTask implements Runnable{
    
    private long value;
    private final long targetValue;
    private final int sideLength;
    private int[] arr;
    private List<Section> sections;
    private DropSite<int[]> dropSite;
    
    public KenKenTask(long initialValue, long finalValue, List<Section> sectionList, int sideLengthOfBoard, DropSite<int[]> dropSite){
        value = initialValue;
        targetValue = finalValue;
        sideLength = sideLengthOfBoard;
        arr = new int[sideLength * sideLength];
        long c = initialValue;
        for(int i=0; i<arr.length; i++){
            arr[i] = (int) (c % sideLength);
            c = c / sideLength;
        }
        sections = new ArrayList<>();
        for(Section section : sectionList){
            sections.add(section.clone());
        }
        this.dropSite = dropSite;
    }
    
    @Override
    public void run() {
        try {
            while (value <= targetValue) {
                stopIfInterrupted();
                
                if (confirm()) {
                    dropSite.set(arr);
                    return;
                }
                increment(0);
                value++;
            }
        } catch (InterruptedException interruptedException) {
            // We have been interrupted!!
            return;
        }
    }
    
    private void increment(int index) {
        if (index < arr.length) {
            if (arr[index] == sideLength - 1) {
                arr[index] = 0;
                increment(index + 1);
            } else {
                arr[index] = arr[index] + 1;
            }
        }
    }
    
    private boolean confirm() throws InterruptedException{
        return confirmBoardValidity() && confirmSections();
    }
    
    private boolean confirmSections() throws InterruptedException{
        stopIfInterrupted();
        
        for (Section section : sections) {
            if (!section.checkAgainst(arr)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean confirmBoardValidity() throws InterruptedException{
        int[] checkArray;

        // Check rows
        for (int i = 0; i < arr.length; i += sideLength) {
            checkArray = new int[sideLength];
            for (int j = i; j < i + sideLength; j++) {
                checkArray[arr[j]] = checkArray[arr[j]] + 1;
            }
            if (!check(checkArray)) {
                return false;
            }
        }

        stopIfInterrupted();
        
        //Check columns
        for (int i = 0; i < sideLength; i++) {
            checkArray = new int[sideLength];
            for (int j = i; j < arr.length; j += sideLength) {
                checkArray[arr[j]] = checkArray[arr[j]] + 1;
            }
            if (!check(checkArray)) {
                return false;
            }
        }

        return true;
    }

    private static boolean check(int[] checkArray) {
        for (int i : checkArray) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private static void stopIfInterrupted() throws InterruptedException{
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
}
