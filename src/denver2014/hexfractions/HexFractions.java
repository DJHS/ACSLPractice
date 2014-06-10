package denver2014.hexfractions;

import java.text.NumberFormat;


public class HexFractions {
	public static double sixTo =Math.pow(16, -20);
	public static void main(String[] args){
		int count = 0;
		String digits = ".";
		double numToHex = .5;
		double valToSubtract = 1.0/16;
		while(numToHex > sixTo){
			if(numToHex > valToSubtract){
				numToHex -= valToSubtract;
				count++;
			}else{
				System.out.println(numToHex);
				digits += toHex(Integer.toString(count));
				count=0;
				valToSubtract *= (1.0/16);
			}
	}
	
		if(digits.length()>10){
			digits= digits.substring(0,11);
		}
		
		System.out.println(digits);
	}

	public static String toHex(String bin){
	 String finalString= bin;
	 finalString = finalString.replaceAll("10","A");
	 finalString = finalString.replaceAll("11","B");
	 finalString = finalString.replaceAll("12","C");
	 finalString = finalString.replaceAll("13","D");
	 finalString = finalString.replaceAll("14","E");
	 finalString = finalString.replaceAll("15","F");
	 return finalString;
	}
}