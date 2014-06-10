package practice.ternaryboolean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


public class TernaryBoolean {
	
	ArrayList<Gate> gates, secondrow, three;
	
	class Gate {
		String[] validStrings;
		String type;
		boolean truth;
		
		private Gate(String t){
			type = t.substring(0,1);
			if(type.equals("A")){
				validStrings = new String[1];
				validStrings[0] = "000";
			}
			if(type.equals("B")){
				validStrings = new String[4];
				validStrings[0] = "000";
				validStrings[1] = "100";
				validStrings[2] = "010";
				validStrings[3] = "001";
			}
			if(type.equals("C")){
				validStrings = new String[1];
				validStrings[0] = "111";
			}
			if(type.equals("D")){
				validStrings = new String[7];
				validStrings[0] = "000";
				validStrings[1] = "100";
				validStrings[2] = "010";
				validStrings[3] = "001";
				validStrings[4] = "011";
				validStrings[5] = "110";
				validStrings[6] = "101";
			}
			truth = output(TernaryBoolean.octal(Integer.parseInt(t.substring(1))));
		}
		
		private boolean output(String input){
			for(String s: validStrings){
				if(s.equals(input)){
					return true;
				}
			}
			return false;
		}
		
		
	}
	
	private static String[] evaluateInput(String input){
		return input.replaceAll("^[,\\s]+", "").split("[,\\s]+");
	}
	
	public TernaryBoolean(){
		gates = new ArrayList<Gate>();
		secondrow = new ArrayList<Gate>();
		three = new ArrayList<Gate>();
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Input input XD");
		TernaryBoolean b = new TernaryBoolean();
		String[] lines = new String[5];
		for(int i=0;i<5;i++){
			lines[i] = in.next();
		}
		for(String t: lines){
			b.handleSingleLine(t);
		}
		in.close();
	}
	
	protected void handleSingleLine(String line){
		String[] inputs = evaluateInput(line);
		int gateDepth = 1;
		HashSet<Character> gates2 = new HashSet<Character>();
		int numOfGates = Integer.parseInt(inputs[0]);
		if(numOfGates == 3){
			gates.add(createGate(inputs[1]));
			gates.add(createGate(inputs[3]));
			gates.add(createGate(inputs[5]));
		}
		else if(numOfGates == 4){
			gates.add(createGate(inputs[1]));
			gates.add(createGate(inputs[3]));
			gates.add(createGate(inputs[5]));
			gates.add(createGate(inputs[7]));
		}
		for(int k=2;k<inputs.length;k+=2){
			char[] chars = inputs[k].toCharArray();
			for(char c: chars){
				gates2.add(c);
			}
		}
		if(gates2.size()==3){
			gateDepth=2;
		}
		while(gateDepth>0){
			createGateLayer(gates2, inputs);
			gateDepth--;
			
		}
		
		if(three.isEmpty()){
			for(Gate g: gates){
				System.out.print(g.truth ? "1":"0");
			}
			System.out.println("");
			System.out.println(secondrow.get(0).truth ? "1": "0");
		}
		else{
			for(Gate g: secondrow){
				System.out.print(g.truth ? "1":"0");
			}
			System.out.println("");
			System.out.println(three.get(0).truth ? "1": "0");
		}
	}
	
	private void createGateLayer(HashSet<Character> GateValues, String[] lines){
		if(secondrow.size() == 0){
			for(char x: GateValues){
				String tempoct = "";
				for(int i=2;i<lines.length;i+=2){
					if(lines[i].contains(Character.toString(x))){
						boolean t = gates.get((i-1)/2).truth;
						tempoct += t ? "1":"0";
					}
				}
				String t2 = x + Integer.toString(Integer.parseInt(tempoct, 2));
				secondrow.add(createGate(t2));
			}
		}
		else{
			String tempoct = "";
			for(Gate g: secondrow){
				tempoct += g.truth ? "1":"0";
			}
			String t2 = lines[lines.length-1] + Integer.toString(Integer.parseInt(tempoct, 2));
			three.add(createGate(t2));
		}
	}

	
	
	private Gate createGate(String input){
		return new Gate(input);
	}
	
	private static String octal(int x){
		String[] lewls = {"000","001", "010", "011", "100", "101","110", "111"};
		return lewls[x];
	}

}
