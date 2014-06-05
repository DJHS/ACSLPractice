package denver2014.lorenz;

import java.util.Scanner;

public class LorenzDecoder {

	public LorenzEncoder coding;

	public LorenzDecoder() {
		this.coding = new LorenzEncoder();
	}

	public static void main(String[] args) {
		System.out.println("Enter input, one line at a time please.");
		Scanner in = new Scanner(System.in);
		LorenzDecoder ld = new LorenzDecoder();
		for (int i = 0; i < 5; i++) {
			System.out.println("Next line!");
			String next = in.nextLine();
			String[] nexts = next.split("\\s*,\\s*");
			int start = Integer.parseInt(nexts[1]);
			String orig = nexts[0].toUpperCase();
			String dz = ld.deltaZ(orig);
			String[] ks = ld.deltaKs(orig.length());
			String[] ans = ld.decoded(orig, dz, ks, start);
			System.out.println(ans[1]);
			System.out.println(ans[0]);
		}
		in.close();

	}


	public String deltaZ(String z) {
		String deltaZ = "";
		char[] chars = z.toCharArray();
		for (int i = 0; i < chars.length - 1; i++) {
			String bitone = this.coding.charbits.get(chars[i]);
			String bittwo = this.coding.charbits.get(chars[i + 1]);
			String f = this.coding.xor(bitone, bittwo);
			deltaZ += this.coding.bitchars.get(f);
		}
		return deltaZ;
	}

	public String[] deltaKs(int length) {
		String[] ks = new String[14];
		for (int i = 0; i < ks.length; i++) {
			String k = "";
			int position = i % 14;
			for (int j = 0; j < length; j++) {
				k += this.coding.keyone.get(position);
				position++;
				position = position % 14;
			}
			ks[i] = this.deltaZ(k);
		}
		return ks;
	}

	public String[] decoded(String z, String deltaz, String[] deltaks, int starttwo) {
		int length = z.length();
		int pos = -1;
		int finalpos = -1;
		int totalnumslash = 0;
		for(int i=0;i<deltaks.length;i++){
			String xored = this.xorString(deltaz, deltaks[i]);
			int numslash = this.getnumslash(xored);
			if(numslash>totalnumslash){
				pos = i;
				totalnumslash = numslash;
			}
		}
		finalpos = pos + 1;
		String k = "";
		pos = pos % 14;
		for (int j = 0; j < length; j++) {
			k += this.coding.keyone.get(pos);
			pos++;
			pos = pos % 14;
		}
		String k2 = "";
		int pos2 = (starttwo - 1) % 4;
		for (int j = 0; j < length; j++) {
			k2 += this.coding.keytwo.get(pos2);
			pos2++;
			pos2 = pos2 % 4;
		}
		String xor1 = this.xorString(z, k);
		String xor2 = this.xorString(xor1, k2);
		String[] out = { xor2, Integer.toString(finalpos) };
		return out;
	}

	public int getnumslash(String orig){
		char[] chars = orig.toCharArray();
		int n = 0;
		for(char c: chars){
			if(c == '/'){
				n++;
			}
		}
		return n;
	}

	public String xorString(String one, String two) {
		String out = "";
		char[] ones = one.toCharArray();
		char[] twos = two.toCharArray();
		for (int i = 0; i < ones.length; i++) {
			String x = this.coding.xor(this.coding.charbits.get(ones[i]),
					this.coding.charbits.get(twos[i]));
			out += this.coding.bitchars.get(x);
		}
		return out;
	}

}
