/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexfractions;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author derek
 */
public class Main {

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File(args[0]));
        while(scan.hasNextLine()){
            System.out.println(new Hexadecimal(new BigDecimal(Double.parseDouble(scan.nextLine()))));
        }
    }
}
