package util;

import map.SolMap;

public class ParserTest {
	public static void ParseTest(SolMap m) {
//		Example 1
//		m.set(5, 3);
//		m.initial(2, 2);
//		m.addBox(3, 2);
//		m.addGoal(4, 2);
//		m.addWall(1, 1);
//		m.addWall(1, 2);
//		m.addWall(1, 3);
//		m.addWall(2, 1);
//		m.addWall(2, 3);
//		m.addWall(3, 1);
//		m.addWall(3, 3);
//		m.addWall(4, 1);
//		m.addWall(4, 3);
//		m.addWall(5, 1);
//		m.addWall(5, 2);
//		m.addWall(5, 3);
		
		m.set(8, 8);
		m.initial(5, 5);
		m.addBox(4, 4);
		m.addBox(4, 6);
		m.addBox(5, 4);
		m.addBox(6, 5);
		m.addGoal(2, 4);
		m.addGoal(4, 7);
		m.addGoal(5, 2);
		m.addGoal(7, 5);
		
		m.addWall(1, 3);
		m.addWall(1, 4);
		m.addWall(1, 5);
		
		m.addWall(2, 3);
		m.addWall(2, 5);
		
		m.addWall(3, 3);
		m.addWall(3, 5);
		m.addWall(3, 6);
		m.addWall(3, 7);
		m.addWall(3, 8);
		
		m.addWall(4, 1);
		m.addWall(4, 2);
		m.addWall(4, 3);
		m.addWall(4, 8);
		
		m.addWall(5, 1);
		m.addWall(5, 6);
		m.addWall(5, 7);
		m.addWall(5, 8);
		
		m.addWall(6, 1);
		m.addWall(6, 2);
		m.addWall(6, 3);
		m.addWall(6, 4);
		m.addWall(6, 6);
		
		m.addWall(7, 4);
		m.addWall(7, 6);
		
		m.addWall(8, 4);
		m.addWall(8, 5);
		m.addWall(8, 6);
	}
}
