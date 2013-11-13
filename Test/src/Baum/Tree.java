package Baum;

import java.util.ArrayList;


public class Tree<T> {

	public ArrayList<Integer> schnellsterWeg = new ArrayList<Integer>();
	Node<T> root;
	
	Tree(T p){
		root = new Node<T>(p);
	}
	
	public Node<T> find(T p){
		
		if(root ==  null){
			return null;
		}
		
		schnellsterWeg.clear();
		
		return find(p, root);
	}
	
	@SuppressWarnings("unchecked")
	private Node<T> find(T p, Node<T> node){
		
		System.out.println(node.p+ "  &&");
		
		if(node.p.equals(p)){
			return node;
		}
		
		for(int i= 0; i < node.children.size(); i++){
			Node<T> kn = find(p, node.children.get(i));
			if(kn != null){
				schnellsterWeg.add(0, i);		//füge den neuen index VORNE in die liste ein (index 0)
				return kn;
			}
		}
		return null;		
	}
	
	public void insert(Node<T> motherNode, T p){
		
		if(motherNode == null){
			System.out.println("FEHLER: Tree.insert: kein Baum vorhanden");
			return;
		}
		if(find(p) != null){
			System.out.println("FEHLER: Tree.insert:  p = \""+p+"\" schon im Baum vorhanden");
			return;
		}
		
		motherNode.children.add(new Node<T>(p));
	}
	
	
	
}
