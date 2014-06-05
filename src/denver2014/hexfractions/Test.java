	import java.text.NumberFormat;
import java.util.Scanner;
public class Test {


		public static double sixTo =1.0/Math.pow(16, 20);
		public static void main(String[] args){
			Scanner scan = new Scanner(System.in);
			for(int i =0; i<10; i++){
			int numDigits =1;
			int count = 0;
			String digits = ".";
			double numToHex =  scan.nextDouble();;
			double valToSubtract = 1.0/16;
			while(numToHex > sixTo){
				if(numToHex > valToSubtract){
					numToHex -= valToSubtract;
					count++;
				}else{
					digits += toHex(Integer.toString(count));
					count=0;
					numDigits=0;
					valToSubtract *= (1.0/16);
				}
				
				}
			
			//digits= digits.substring(0,15);
			//if(doesRepeat(digits, 1)){
				//digits=reduceRepeat(digits);
			//}
			
			if(digits.length()>10){
				digits= digits.substring(0,11);
			}
			
			System.out.println(digits);
			}
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
