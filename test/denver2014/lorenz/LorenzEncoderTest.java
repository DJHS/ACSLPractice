package denver2014.lorenz;

import org.junit.Test;

/**
 * @author freddy
 */
public class LorenzEncoderTest {

	@Test
	public void test() {
		String out = "RCN";
		int one = 1;
		int two = 4;
		LorenzEncoder n = new LorenzEncoder();
		System.out.println(n.decode(one, two, out));
	}

}
