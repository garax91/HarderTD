package towerDefense;

import java.util.ArrayList;


public class Tree<T> {

	public static ArrayList<Integer> indexWeg = new ArrayList<Integer>();
	Node<T> root;
	private int idCounter;
	private T p;
	
	Tree(T p){
		idCounter = 0;
		this.p = p;
		root = new Node<T>(p, idCounter);
	}
	
	public Node<T> find(T p){
		
		if(root ==  null){
			return null;
		}
		
		indexWeg.clear();
		
		return find(p, root);
	}
	
	@SuppressWarnings("unchecked")
	private Node<T> find(T p, Node<T> node){
		
		//System.out.println(node.p+ "  &&");
		
		if(node.p.equals(p)){
			return node;
		}
		
		for(int i= 0; i < node.children.size(); i++){
			Node<T> kn = find(p, node.children.get(i));
			if(kn != null){
				indexWeg.add(0, i);		//füge den neuen index VORNE in die liste ein (index 0)
				return kn;
			}
		}
		return null;		
	}
	
	public Node<T> findID(int id){
		
		if(root ==  null){
			return null;
		}
		
		//schnellsterWeg.clear();
		
		return findID(id, root);
	}
	
	@SuppressWarnings("unchecked")
	private Node<T> findID(int id, Node<T> node){
		
		//System.out.println(node.p+ "  &&");
		
		if(node.id == id){
			return node;
		}
		
		for(int i= 0; i < node.children.size(); i++){
			Node<T> kn = findID(id, node.children.get(i));
			if(kn != null){
				indexWeg.add(0, i);		//füge den neuen index VORNE in die liste ein (index 0)
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
		
		idCounter++;
		motherNode.children.add(new Node<T>(p, idCounter));
	}
	
	public void clearTree(){
		idCounter = 0;
		root = new Node<T>(p, idCounter);
//		root.children.clear();
	}
	
	public int getIdCounter(){
		return idCounter;
	}
	
}
