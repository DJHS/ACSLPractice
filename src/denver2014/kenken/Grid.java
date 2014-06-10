package denver2014.kenken;

import java.util.ArrayList;

/**
 * @author derek
 */
public class Grid extends ArrayList<Row> {

	public Grid() {
		// TODO Auto-generated constructor stub
	}

	public int getCell(int cellNum) {
		int r = (cellNum - 1) / this.size();
		int c = (cellNum - 1) % this.size();
		return this.get(r).get(c);
	}

	public boolean isValid() {
		// for (int i = 0; i < this.size(); i++) {
		// if (!this.checkRow(i) || !this.checkCol(i)) {
		// return false;
		// }
		// }
		return true;
	}

	boolean checkRow(int r) {
		Row row = this.get(r);
		boolean result = true;
		for (int i = 0; i < row.size() - 1; i++) {
			result = result && (row.get(i) != row.get(i + 1));
		}
		return result;
	}

	boolean checkCol(int c) {
		boolean result = true;
		for (int i = 0; i < this.size() - 1; i++) {
			result = result && (this.get(i).get(c) != this.get(i + 1).get(c));
		}
		return result;
	}

}
