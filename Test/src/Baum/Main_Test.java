package Baum;

public class Main_Test {
	public static void main(String[] args) {
		
		Tree<Integer> firstTree = new Tree<Integer>(5);
		
		firstTree.insert(firstTree.find(5), 7);
		firstTree.insert(firstTree.find(7), 17);
		firstTree.insert(firstTree.find(5), 8);
		firstTree.insert(firstTree.find(5), 10);
		firstTree.insert(firstTree.find(8), 1);
		firstTree.insert(firstTree.find(1), 4);
		firstTree.insert(firstTree.find(1), 3);
		firstTree.insert(firstTree.find(1), 20);
		firstTree.insert(firstTree.find(20), 27);
		firstTree.insert(firstTree.find(20), 28);
		
		firstTree.insert(firstTree.find(4), 31);
		firstTree.insert(firstTree.find(4), 32);
		firstTree.insert(firstTree.find(4), 33);
		firstTree.insert(firstTree.find(31), 44);
		firstTree.insert(firstTree.find(31), 45);
		firstTree.insert(firstTree.find(31), 46);
		
		firstTree.insert(firstTree.find(44), 61);
		firstTree.insert(firstTree.find(44), 62);
		firstTree.insert(firstTree.find(44), 63);
		firstTree.insert(firstTree.find(44), 66);
		firstTree.insert(firstTree.find(63), 88);
		firstTree.insert(firstTree.find(63), 89);
		firstTree.insert(firstTree.find(88), 90);
		
		firstTree.insert(firstTree.find(61), 100);
		firstTree.insert(firstTree.find(61), 101);
		firstTree.insert(firstTree.find(101), 102);
		firstTree.insert(firstTree.find(102), 103);
		firstTree.insert(firstTree.find(100), 105);
		
		firstTree.insert(firstTree.find(27), 201);
		firstTree.insert(firstTree.find(27), 202);
		firstTree.insert(firstTree.find(202), 203);
		firstTree.insert(firstTree.find(28), 204);
		firstTree.insert(firstTree.find(204), 205);
		firstTree.insert(firstTree.find(205), 206);
		firstTree.insert(firstTree.find(205), 207);
		firstTree.insert(firstTree.find(205), 208);
		firstTree.insert(firstTree.find(205), 209);
		firstTree.insert(firstTree.find(207), 210);
		
		System.out.println("=======================================================");
		
		System.out.println("// "+firstTree.find(10).p+" //");
//		System.out.println("// "+firstTree.find(7).p+" //");
//		System.out.println("// "+firstTree.find(10).p+" //");
//		
//		System.out.println("(((((((((");
//		
//		System.out.println("// "+firstTree.find(-3)+" //");
//		
//		firstTree.insert(firstTree.find(207), 33);
//		
//		System.out.println("// "+firstTree.find(66).p+" //");
		
		System.out.println(firstTree.schnellsterWeg);
		
	}

}
