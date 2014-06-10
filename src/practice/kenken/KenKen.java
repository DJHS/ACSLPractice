package practice.kenken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KenKen {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(args[0]));
        List<Section> sections;
        int[] arr;
        
        // 3x3 board
        sections = createSections(scan.nextLine());      
        arr = createArr(3);
        while(increment(arr, 0, 3)){
            if(check(arr,3)){
                if(confirmSections(arr,sections)){
                    break;
                }
            }
        }
        printResult(arr, scan.nextLine());
        
        // 4x4 board
        sections = createSections(scan.nextLine());     
        arr = createArr(4);
        while (increment(arr, 0, 4)) {
            if (check(arr, 4)) {
                if (confirmSections(arr, sections)) {
                    break;
                }
            }
        }
        printResult(arr, scan.nextLine());
    }

    public static void printResult(int[] arr, String cellNumbers){
        Scanner scan = new Scanner(cellNumbers);
        scan.useDelimiter("\\s*,\\s*");
        while(scan.hasNextInt()){
            System.out.println(arr[scan.nextInt() - 1]);
        }
    }
    
    public static int[] createArr(int sideLength){
        int[] result = new int[sideLength * sideLength];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        return result;
    }
    
    public static List<Section> createSections(String s){
        Scanner scan = new Scanner(s);
        scan.useDelimiter("\\s*,\\s*");
        List<Section> result = new ArrayList<>();
        Section section = new Section();
        String c;
        int target, sum;
        scan.next(); //Get rid of first number
        while(scan.hasNext()){
            c = scan.next();
            if(c.matches("\\d+")){
                target = Integer.parseInt(c);
                section.addTarget(target);
            }else{
                sum = Integer.parseInt(c.substring(0,c.length()-1));
                section.setSum(sum);
                result.add(section);
                section = new Section();
            }
        }
        return result;
    }
    
    public static boolean increment(int[] arr, int index, int sideLength) {
        if (index < arr.length) {
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

    public static boolean check(int[] arr, int sideLength) {
        int[] checkArray;
        
        // Check rows
        for (int i = 0; i < arr.length; i += sideLength) {
            checkArray = new int[sideLength];
            for (int j = i; j < i + sideLength; j++) {
                checkArray[arr[j] - 1] = checkArray[arr[j] - 1] + 1;
            }
            if (!check(checkArray)) {
                return false;
            }
        }
        
        //Check columns
        for (int i = 0; i < sideLength; i++) {
            checkArray = new int[sideLength];
            for (int j = i; j < arr.length; j += sideLength) {
                checkArray[arr[j] - 1] = checkArray[arr[j] - 1] + 1;
            }
            if (!check(checkArray)) {
                return false;
            }
        }

        return true;
    }

    public static boolean check(int[] checkArray) {
        for (int i : checkArray) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean confirmSections(int[] arr, List<Section> sections) {
        for (Section section : sections) {
            if (!section.checkAgainst(arr)) {
                return false;
            }
        }
        return true;
    }
}
