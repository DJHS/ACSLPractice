package denver2014.kenken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author derek
 */
public class Section {
	Grid grid;
	int sum = -1;
	List<Integer> cells;

	public Section() {
		this.cells = new ArrayList<Integer>();
	}

	public void setGrid(Grid gr) {
		this.grid = gr;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void addCell(int cellNum) {
		this.cells.add(cellNum);
	}

	public boolean worksInGrid() {
		if (this.grid instanceof Grid) {
			int s = 0;
			for (Integer cell : this.cells) {
				s += this.grid.getCell(cell);
			}
			return (s == this.sum);
		} else {
			return false;
		}
	}

}
