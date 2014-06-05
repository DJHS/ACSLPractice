package practice.hexfractions.derek;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
//        Scanner scan = new Scanner(new File(args[0]));
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            System.out.println(new Hexadecimal(new BigDecimal(scan.nextLine())));
        }
    }
}