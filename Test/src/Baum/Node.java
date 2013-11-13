package Baum;

import java.util.ArrayList;

public class Node<T> {
	
	T p;
	@SuppressWarnings("rawtypes")
	ArrayList<Node> children = new ArrayList<Node>();
	
	Node(T p){
		this.p = p;
		children.clear();
	}
	
}
