package denver2014.lorenz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LorenzEncoder {

	public HashMap<Character, String> charbits;
	public HashMap<String, Character> bitchars;
	public ArrayList<Character> keyone;
	public ArrayList<Character> keytwo;

	public LorenzEncoder() {
		this.charbits = new HashMap<Character, String>();
		this.bitchars = new HashMap<String, Character>();
		this.keyone = new ArrayList<Character>();
		this.keytwo = new ArrayList<Character>();
		this.charbits.put('A', "11000");
		this.charbits.put('B', "10011");
		this.charbits.put('C', "01110");
		this.charbits.put('D', "10010");
		this.charbits.put('E', "10000");
		this.charbits.put('F', "10110");
		this.charbits.put('G', "01011");
		this.charbits.put('H', "00101");
		this.charbits.put('I', "01100");
		this.charbits.put('J', "11010");
		this.charbits.put('K', "11110");
		this.charbits.put('L', "01001");
		this.charbits.put('M', "00111");
		this.charbits.put('N', "00110");
		this.charbits.put('O', "00011");
		this.charbits.put('P', "01101");
		this.charbits.put('Q', "11101");
		this.charbits.put('R', "01010");
		this.charbits.put('S', "10100");
		this.charbits.put('T', "00001");
		this.charbits.put('U', "11100");
		this.charbits.put('V', "01111");
		this.charbits.put('W', "11001");
		this.charbits.put('X', "10111");
		this.charbits.put('Y', "10101");
		this.charbits.put('Z', "10001");
		this.charbits.put('+', "11011");
		this.charbits.put('/', "00000");
		this.charbits.put('9', "00100");
		this.charbits.put('8', "11111");
		this.charbits.put('4', "01000");
		this.charbits.put('3', "00010");
		for (char c : this.charbits.keySet()) {
			this.bitchars.put(this.charbits.get(c), c);
		}
		this.keyone.add('A');
		this.keyone.add('B');
		this.keyone.add('C');
		this.keyone.add('D');
		this.keyone.add('E');
		this.keyone.add('F');
		this.keyone.add('G');
		this.keyone.add('H');
		this.keyone.add('I');
		this.keyone.add('J');
		this.keyone.add('K');
		this.keyone.add('L');
		this.keyone.add('M');
		this.keyone.add('N');
		this.keytwo.add('A');
		this.keytwo.add('A');
		this.keytwo.add('B');
		this.keytwo.add('B');
	}

	public static void main(String[] args) {
		System.out.println("Enter inputs, one at a time please");
		Scanner in = new Scanner(System.in);
		LorenzEncoder le = new LorenzEncoder();
		for(int i=0;i<5;i++){
			System.out.println("Next line!");
			String nextLine = in.nextLine();
			String[] res = le.readInput(nextLine);
			int one = Integer.parseInt(res[0]);
			int two = Integer.parseInt(res[1]);
			String orig = res[2].toUpperCase();
			System.out.println(le.encode(one, two, orig));
		}
		for (int k = 0; k < 5; k++) {
			System.out.println("Next line!");
			String nextLine = in.nextLine();
			String[] res = le.readInput(nextLine);
			int one = Integer.parseInt(res[0]);
			int two = Integer.parseInt(res[1]);
			String orig = res[2].toUpperCase();
			System.out.println(le.decode(one, two, orig));
		}
		in.close();

	}

	public String[] readInput(String in) {
		return in.split("\\s*,\\s*");
	}

	public String xor(String one, String two) {
		String out = "";
		char[] ones = one.toCharArray();
		char[] twos = two.toCharArray();
		for (int i = 0; i < ones.length; i++) {
			if (ones[i] == twos[i]) {
				out += "0";
			} else {
				out += "1";
			}
		}
		return out;
	}

	public String xor(List<String> bits) {
		String out = "";
		if (bits.size() > 2) {
			out = this.xor(bits.get(0), this.xor(bits.subList(1, bits.size())));
		} else if (bits.size() == 2) {
			out = this.xor(bits.get(0), bits.get(1));
		} else {
			out = bits.get(0);
		}
		return out;
	}

	public String encode(int startone, int starttwo, String string){
		String encoded = "";
		int one = (startone-1)%14;;
		int two = (starttwo-1)%4;
		char[] chars = string.toCharArray();
		for(int i=0;i<chars.length;i++){
			List<String> xors = new ArrayList<String>();
			xors.add(this.charbits.get(chars[i]));
			xors.add(this.charbits.get(this.keyone.get(one)));
			xors.add(this.charbits.get(this.keytwo.get(two)));
			one++;
			two++;
			one = one%14;
			two = two%4;
			String out = this.xor(xors);
			encoded += this.bitchars.get(out);
		}
		return encoded;
	}

	public String decode(int startone, int starttwo, String string) {
		String decoded = "";
		int one = (startone - 1) % 14;
		int two = (starttwo - 1) % 4;
		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			HashMap<Character, Character> particularmap = new HashMap<Character, Character>();
			for (char c : this.charbits.keySet()) {
				List<String> xors = new ArrayList<String>();
				xors.add(this.charbits.get(c));
				xors.add(this.charbits.get(this.keyone.get(one)));
				xors.add(this.charbits.get(this.keytwo.get(two)));
				char dec = this.bitchars.get(this.xor(xors));
				particularmap.put(dec, c);
			}
			one++;
			two++;
			one = one % 14;
			two = two % 4;
			decoded += particularmap.get(chars[i]);

		}
		return decoded;
	}

}
