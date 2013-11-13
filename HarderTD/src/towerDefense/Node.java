package towerDefense;

import java.util.ArrayList;

public class Node<T> {
	
	T p;
	final int id;
	@SuppressWarnings("rawtypes")
	ArrayList<Node> children = new ArrayList<Node>();
	
	Node(T p, int id){
		this.p = p;
		this.id = id;
		children.clear();
	}
	
}
