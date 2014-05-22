package tonyryan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cells {
	public static String x, y, z;

	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		// for (int i = 0; i < 13; i++) {
		// if (i > 4) {
		// switch (i) {
		// case 0:
		// x = scan.nextLine();
		// break;
		// case 1:
		// y = scan.nextLine();
		// break;
		// case 2:
		// z = scan.nextLine();
		// break;
		// }
		// } else {
		// // run methods
		// start(scan.nextLine());
		// }
		// }

		x = "ABCDEFGH";
		y = "AAGGHDFE";
		z = "GHAFFEDB";

	}

	public static void start(String s) {
		String[] split = s.split(" ");
		if (split[1].contains("Z") || split[1].contains("X")
				|| split[1].contains("Y")) {
			switch (split[0]) {
			case "DIV":
				System.out.println(DIV(split));
				break;
			case "ALI":
				System.out.println(ALI(split));
				break;
			case "UNI":
				System.out.println(UNI(split));
				break;
			case "INT":
				System.out.println(INT(split));
				break;
			default:
				addSubWOShit(split);
				break;
			}
		} else {
			if (split[0].contains("UNI") || split[0].contains("INT"))
				UI(split);
			else {

			}

		}

	}

	public static void UI(String[] split) {
		String firstOp, secondOp;
		switch (split[0]) {
		case "DIV":
			firstOp = DIV(split);
			break;
		case "ALI":
			System.out.println(ALI(split));
			break;
		case "UNI":
			System.out.println(UNI(split));
			break;
		case "INT":
			System.out.println(INT(split));
			break;
		default:
			break;
		}
		
		String[] fuck = recompileWO(split);
		String first1, second1;
		String[] first1Arr = new String[2];
		String[] second1Arr = new String[2];
		first1Arr[0] = fuck[0];
		first1Arr[1] = fuck[1];
		first1 = UNI(first1Arr);
		second1Arr[0] = fuck[3];
		second1Arr[1] = fuck[4];
		second1 = UNI(second1Arr);
	}

	public static String[] recompileWO(String[] split) {
		ArrayList<String> b4 = new ArrayList<String>();
		for (int i = 1; i < split.length; i++) {
			b4.add(split[0]);
		}
		String[] asdf = new String[b4.size()];
		for (int i = 0; i < b4.size(); i++) {
			asdf[i] = b4.get(i);
		}
		return asdf;
	}

	public static void addSubWOShit(String[] s) {
		if (s[0].contains("ADD"))
			System.out.println(ADD(s, Integer.parseInt(s[0].substring(3, 4))));
		else
			System.out.println(SUB(s, Integer.parseInt(s[0].substring(3, 4))));
	}

	public static String ADD(String[] s, int n) {
		String first = null, firstDig, secondDig;
		switch (s[1]) {
		case "X":
			first = x;
			break;
		case "Y":
			first = y;
			break;
		case "Z":
			first = z;
			break;
		}
		firstDig = first.substring(0, n);
		secondDig = first.substring(n, 8 - n);
		return firstDig + firstDig + secondDig;
	}

	public static String SUB(String[] s, int n) {
		String first = null, firstDig, secondDig;
		switch (s[1]) {
		case "X":
			first = x;
			break;
		case "Y":
			first = y;
			break;
		case "Z":
			first = z;
			break;
		}
		firstDig = first.substring(8 - n, 8);
		secondDig = first.substring(n, 8 - n);
		return secondDig + firstDig + firstDig;
	}

	public static String DIV(String[] s) {
		String f = null, l = null;
		switch (s[1]) {
		case "X":
			f = x.substring(0, 4);
			l = x.substring(4, x.length());
			break;
		case "Y":
			f = y.substring(0, 4);
			l = y.substring(4, y.length());
			break;
		case "Z":
			f = z.substring(0, 4);
			l = z.substring(4, z.length());
			break;
		}
		return (f + f) + " and " + (l + l);
	}

	public static String ALI(String[] s) {
		ArrayList<String> letters = new ArrayList<String>();
		String[] chars;
		switch (s[1]) {
		case "X":
			chars = x.split("");
			for (int i = 0; i < chars.length; i++) {
				letters.add(chars[i]);
			}
			break;
		case "Y":
			chars = y.split("");
			for (int i = 0; i < chars.length; i++) {
				letters.add(chars[i]);
			}
			break;
		case "Z":
			chars = z.split("");
			for (int i = 0; i < chars.length; i++) {
				letters.add(chars[i]);
			}
			break;

		}

		Collections.sort(letters);
		Collections.reverse(letters);
		String returnS = "";
		for (int i = 0; i < letters.size(); i++) {
			returnS += letters.get(i);

		}
		return returnS;
		// System.out.println();
	}

	public static String UNI(String[] s) {
		String first = null, second = null;
		String[] firstL, secondL;
		ArrayList<String> firstArr = new ArrayList<String>();
		ArrayList<String> secondArr = new ArrayList<String>();
		switch (s[1]) {
		case "X":
			first = x.substring(4, x.length());
			break;
		case "Y":
			first = y.substring(4, y.length());
			break;
		case "Z":
			first = z.substring(4, z.length());
			break;
		}
		switch (s[2]) {
		case "X":
			second = x.substring(0, 4);
			break;
		case "Y":
			second = y.substring(0, 4);
			break;
		case "Z":
			second = z.substring(0, 4);
			break;
		}
		firstL = first.split("");
		secondL = second.split("");

		for (int i = 0; i < firstL.length; i++) {
			firstArr.add(firstL[i]);
			secondArr.add(secondL[i]);
		}
		first = "";
		second = "";
		Collections.sort(firstArr);
		Collections.sort(secondArr);
		for (int i = 0; i < secondL.length; i++) {
			first += firstArr.get(i);
			second += secondArr.get(i);
		}
		return first + second;
	}

	public static String INT(String[] s) {
		String first = null, second = null;
		String[] firstL, secondL;
		ArrayList<String> firstArr = new ArrayList<String>();
		ArrayList<String> secondArr = new ArrayList<String>();
		switch (s[1]) {
		case "X":
			first = x.substring(0, 2) + x.substring(6, x.length());
			break;
		case "Y":
			first = y.substring(0, 2) + y.substring(6, y.length());
			break;
		case "Z":
			first = z.substring(0, 2) + z.substring(6, z.length());
			break;
		}
		switch (s[2]) {
		case "X":
			second = x.substring(0, 2) + x.substring(6, x.length());
			break;
		case "Y":
			second = y.substring(0, 2) + y.substring(6, y.length());
			break;
		case "Z":
			second = z.substring(0, 2) + z.substring(6, z.length());
			break;
		}
		firstL = first.split("");
		secondL = second.split("");

		for (int i = 0; i < firstL.length; i++) {
			firstArr.add(firstL[i]);
			secondArr.add(secondL[i]);
		}
		first = "";
		second = "";
		Collections.sort(firstArr);
		Collections.sort(secondArr);
		for (int i = 0; i < secondL.length; i++) {
			first += firstArr.get(i);
			second += secondArr.get(i);
		}
		return first + second;
	}

}
