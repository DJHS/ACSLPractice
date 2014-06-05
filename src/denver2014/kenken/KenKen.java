package denver2014.kenken;


import java.io.IOException;
import java.util.Scanner;

public class KenKen {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		Trial trial3 = new Trial(3, scan.nextLine().substring(3));
		System.out.println();
		trial3.printGivenCells(scan.nextLine());
		Trial trial4 = new Trial(4, scan.nextLine().substring(3));
		System.out.println();
		trial4.printGivenCells(scan.nextLine());
	}

}
