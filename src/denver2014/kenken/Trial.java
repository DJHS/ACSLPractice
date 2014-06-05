package denver2014.kenken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Trial {

	Grid grid;
	List<Section> sections;

	public Trial(int len, String definition) {
		this.initializeSections(definition);
		this.initializeGrid(len);
	}

	public void printGivenCells(String cells) {
		Scanner scan = new Scanner(cells);
		scan.useDelimiter(", ");
		while (scan.hasNextInt()) {
			System.out.println(this.grid.getCell(scan.nextInt()));
		}
	}

	public static void main(String[] args) {
		Set<Row> r = generateAllPossibleRows(4);
		System.out.println(r.size());
		Set<Grid> a = generateAllPossibleGrids(4, generateAllPossibleRows(4));
		System.out.println(a.size());
		List<Section> sections = new ArrayList<>();
		// System.out.println(a);
		String[] def = "8, 1, 5, 9, 9+, 13, 1#, 2, 1#, 6, 10, 14, 9+, 3, 7, 8, 6+, 4, 2#, 12, 4#, 11, 15, 16, 8+"
				.split(", ");
		Section target = new Section();
		for (String s : def) {
			if (s.matches("\\d+")) {
				target.addCell(Integer.parseInt(s));
			} else /* if (s.matches("\\d+[\\#\\+]")) */{
				target.setSum(Integer.parseInt(s.substring(0, s.length() - 1)));
				sections.add(target);
				target = new Section();
			}
		}
		Grid grid = null;
		for (Grid gr : a) {
			if (true) {
				boolean allSectionsWork = true;
				for (Section s : sections) {
					s.setGrid(gr);
					allSectionsWork = allSectionsWork && s.worksInGrid();
				}
				if (allSectionsWork) {
					grid = gr;
				}
			}
		}
		System.out.println(sections);
		System.out.println(grid);
		System.out.println("WTF");
	}

	void initializeSections(String definition) {
		this.sections = new ArrayList<>();
		String[] def = definition.split(", ");
		Section target = new Section();
		for (String s : def) {
			if (s.matches("\\d+")) {
				target.addCell(Integer.parseInt(s));
			} else /*if (s.matches("\\d+[\\#\\+]"))*/ {
				target.setSum(Integer.parseInt(s.substring(0, s.length() - 1)));
				this.sections.add(target);
				target = new Section();
			}
		}
	}

	void initializeGrid(int len) {
		Set<Row> allPossibleRows = generateAllPossibleRows(len);
		Set<Grid> allPossibleGrids = generateAllPossibleGrids(len, allPossibleRows);
		for (Grid gr : allPossibleGrids) {
			if (true) {
				boolean allSectionsWork = true;
				for (Section s : this.sections) {
					s.setGrid(gr);
					allSectionsWork = allSectionsWork && s.worksInGrid();
				}
				if (allSectionsWork) {
					this.grid = gr;
					return;
				}
			}
		}
	}

	public static void addAllRowInsertionees(Set<Row> target, Set<Row> source,
			Integer insertionee) {
		for (Row start : source) {
			for (int i = 0; i <= start.size(); i++) {
				Row r = (Row) start.clone();
				if (!r.contains(insertionee)) {
					r.add(i, insertionee);
					target.add(r);
				}
			}
		}
	}

	public static void addAllGridInsertionees(Set<Grid> target, Set<Grid> source,
			Row insertionee, int len) {
		for (Grid start : source) {
			for (int i = 0; i <= start.size(); i++) {
				Grid g = (Grid) start.clone();
				boolean go = true;
				Outer: for (int c = 0; c < len; c++) {
					for (int r = 0; r < g.size(); r++) {
						if (g.get(r).get(c) == insertionee.get(c)) {
							go = false;
							break Outer;
						}
					}
				}
				if (go) {
					g.add(i, insertionee);
					target.add(g);
				}
			}
		}
	}

	public static Set<Row> generateAllPossibleRows(int len) {
		Set<Integer> possibilities = new HashSet<>();
		if (len == 3 || len == 4) {
			possibilities.add(1);
			possibilities.add(2);
			possibilities.add(3);
		}
		if (len == 4) {
			possibilities.add(4);
		}

		Set<Row> res = new HashSet<>();
		res.add(new Row());
		for (int j = 0; j < len; j++) {
			Set<Row> target = new HashSet<>();
			for (Integer insertionee : possibilities) {
				addAllRowInsertionees(target, res, insertionee);
			}
			res = target;
		}
		return res;
	}

	public static Set<Grid> generateAllPossibleGrids(int len, Set<Row> possibilities) {
		Set<Grid> res = new HashSet<>();
		res.add(new Grid());
		for (int j = 0; j < len; j++) {
			Set<Grid> target = new HashSet<>();
			for (Row insertionee : possibilities) {
				addAllGridInsertionees(target, res, insertionee, len);
			}
			res = target;
		}
		return res;
	}
}
