
import java.util.LinkedList;
import java.util.Queue;

public class BTNode<E> {
	private E data; 
	private BTNode<E> left; 
	private BTNode<E> right;
	private BTNode<E> parent;
	
	public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight){
		parent = null;
		data = initialData;
		left = initialLeft; 
		right =initialRight;
	}
	public void setParent( BTNode<E> p){
		parent = p;
	}
	
	public void setData(E newData){
		data = newData;
	}
	
	public void setLeft(BTNode<E> newLeft){
		left = newLeft;
	}
	
	public void setRight(BTNode<E> newRight){
		right = newRight;
	}
	
	public BTNode<E> getLeft(){ return left; }
	public BTNode<E> getRight() {return right;}
	public E getData() { return data;}
	
	public E getLeftMostData(){
		if(left == null) return data; 
		else return left.getLeftMostData();
	}
	
	public E getRightMostData(){
		if(right == null) return data;
		else return right.getRightMostData();
	}
	
	public void inOrderPrint(){
		if(left!=null)
			left.inOrderPrint();
		System.out.print(data+",");
		if(right!=null)	
			right.inOrderPrint();	
	}
	
	public void preOrderPrint(){
		System.out.print(data+",");
		if(left!=null)
			left.inOrderPrint();
		if(right!=null)	
			right.inOrderPrint();	
	}
	
	public void postOrderPrint(){
		if(left!=null)
			left.inOrderPrint();
		if(right!=null)	
			right.inOrderPrint();	
		System.out.print(data+",");
	}
	
	
	
	public BTNode<E> getParent(){
		return parent; 
	}
	
	public BTNode<E> getRoot(){
		if(parent !=null)
			return parent.getRoot();
		else
			return this;
	}

	public void levelOrderPrint(){ //Breadth First Traversal
		Queue<BTNode> q = new LinkedList<BTNode>();
		q.add(this);
		while(!q.isEmpty()){
			BTNode temp =(BTNode)q.remove();
			System.out.println(temp.getData());
			if(temp.getLeft()!=null)
				q.add(temp.getLeft());
			if(temp.getRight()!=null)
				q.add(temp.getRight());
		}
	}
	
	public void print(int depth){
		Queue<BTNode> q = new LinkedList<BTNode>();
		Queue<BTNode> tq = new LinkedList<BTNode>();
		q.add(this);
		int level=1;
		boolean flag =false;
		while(level <= depth){
			int begin = depth - level;
			
			while(!q.isEmpty()){
				BTNode temp =(BTNode)q.remove();
				String offset = new String();
				if (!flag) {
					offset = getSpace(begin);
					flag = false;
				}
				else offset = "   ";
				if(temp.getData()!=null) 
					System.out.print( offset+ temp.getData());
				else
					System.out.print( offset+ "-");
				
				if(temp.getLeft()!=null) tq.add(temp.getLeft());
				if(temp.getLeft()==null) tq.add(new BTNode<E>(null, null, null));
				
				if(temp.getRight()!=null) tq.add(temp.getRight());
				if(temp.getRight()==null) tq.add(new BTNode<E>(null, null, null));
				
				if(temp.getLeft()==null && temp.getRight()==null) {
					begin++;
					flag=true;
				}
			}
			while(!tq.isEmpty()) q.add(tq.remove());
			System.out.println();
			level++;
		}
	}
	
	
	private String getSpace(int depth){
		String space = new String(); 
		for(int i=1 ; i<=depth ; i++)
			space += "      ";
		return space;
	}

	
	public BTNode<E> removeLeftmostNode(){
		if(left==null) return right;
		else{
			left = left.removeLeftmostNode();
			return this;
		}
	}
	
	public BTNode<E> removeRightmostNode(){
		if(right == null)
			return left;
		else{
			right = right.removeRightmostNode();
			return this;
		}
	}
	
	public static <E> BTNode<E> treeCopy(BTNode<E> source){
		BTNode<E> leftCopy, rightCopy;
		if(source==null) return null;
		else{
			leftCopy = treeCopy(source.left);
			rightCopy = treeCopy(source.right);
			return new BTNode<E>(source.data, leftCopy, rightCopy);
		}
	}
	
	public static <E> long treeSize(BTNode<E> root){
		if(root == null) return 0;
		else
			return (1+treeSize(root.left) + treeSize(root.right));
	}
	
	public boolean isLeaf(){
		return (left==null && right ==null);
	}
	/**
	 * This method returns the total number of leaves in the tree
	 * @param root
	 * @return numberOfLeaves
	 */
	public static <E> int countLeaves(BTNode<E> root) {
		
		if (root == null || root.getLeft() == null && root.getLeft() == null) return 0;
		else
			return (1 + countLeaves(root.left) + countLeaves(root.right));
	}
	/**
	 * This method returns the total number of non-leaves in the tree
	 * @param root
	 * @return numberOfNonLeaves
	 */
	public static <E> int countNonLeaves(BTNode<E> root) {
		return (int)treeSize(root) - countLeaves(root);
	}
	
	public static <E> E findParentNode(BTNode<E> root){
		if(root != null){
			return root.getParent().getData();
		}
		else
			 return (findParentNode(root.left));
	}
	
	/*
	public boolean search(E element){
		BTNode<E> current  =this;
		while(current!=null){
			if( element < current.getData()){
				current = current.getLeft();
			}
			else if(element > current.getData()){
				current = current.getRight();
			}
			else return true;
		}
		return false;
	}
	
	public boolean insert(E element){
		BTNode<E> parent = null;
		BTNode<E> current = this;
		while(current!=null){
			if (element < current.getData()){
				parent = current;
				current = current.getLeft();
			}
			else if (element > current.getData()){
				parent = current;
				current = current.getRight();
			}
			else return true;
		}
		if(element<parent.getData())
			parent.setLeft(new BTNode<BTNode.E>(element, null, null));
		else 
			parent.setRight(new BTNode<BTNode.E>(element, null, null));	
		return true;
	}
	
	
	public boolean delete(E element){
		BTNode<E> parent = null; 
		BTNode<E> current = this;
		
		while(current!=null){
			if(element < current.getData()){
				parent = current;
				current = current.getLeft();
			}
			else if (element > current.getData()){
				parent = current;
				current = current.getRight();
			}
			else break;
			
		}
		if(current.getLeft() == null){
			if(current.getRight()!=null)
				if (element<parent.getData())
					parent.left = current.getData();
				else
					parent.right = current.getRight();
		}
		else{
			BTNode<E> parentOfRightMost = current;
			BTNode<E> rightMost = current.getLeft();
			
			while(rightMost.getRight()!= null){
				parentOfRightMost = rightMost;
				rightMost = rightMost.getRight();
			}
			current.setData(rightMost.getData());
			if(parentOfRightMost.getRight()==rightMost)
				parentOfRightMost.right = rightMost.getLeft();
			else 
				parentOfRightMost.left = rightMost.getLeft();
		}
	}
	*/
	
	public void addNode(BTNode<E> newNode){ //BFS add
		Queue<BTNode> q = new LinkedList<BTNode>();
		q.add(this);
		while(!q.isEmpty()){
			BTNode temp =(BTNode)q.remove();
			if(temp.getLeft()==null || temp.getRight()==null){
				if(temp.getLeft()==null) {
					temp.setLeft(newNode);
					return;
				}
				else {
					temp.setRight(newNode);
					return;
				}
			}
				
				
			if(temp.getLeft()!=null)
				q.add(temp.getLeft());
			if(temp.getRight()!=null)
				q.add(temp.getRight());
		}
	}
	
	public boolean find(E data){ //BFS search
		Queue<BTNode<E>> q = new LinkedList<BTNode<E>>(); 
		q.add(this); 
		while(!q.isEmpty()){
			BTNode<E> temp = q.remove(); 
			if(temp.getData().equals(data))
				return true;
			if (temp.getLeft()!=null)
				q.add(temp.getLeft());
			if(temp.getRight()!=null)
				q.add(temp.getRight());	
		}
		return false;
	}
	
	public boolean isEmpty(){
		if(this.getData() == null) return true;
		else return false;
	}
	
	public int getHeight(){
		BTNode<E> node = getRoot(); 
		int h = 0;
		if(node !=null && node.getData()!=null) h++;
		while(!node.isLeaf()){
			if(node.getLeft()!=null){
				h++;
				node = node.getLeft(); 
			}
			else if(node.getRight()!=null){
				h++;
				node = node.getRight();
			}
		}
		return h;
	}
	
	public void insertNode(BTNode<E> newNode){
		if(isLeaf()){ //leaf node
			if( ((Integer) data).compareTo( ((Integer)newNode.getData())) > 0){
				//insert at left
				left = newNode; 
				left.setParent(this);
			}
			else {
				right =newNode;
				right.setParent(this);
			}
		}
		else{
			if( ((Integer) data).compareTo( ((Integer)newNode.getData())) > 0){
				if(this.getLeft()==null) {
					left = newNode; 
					left.setParent(this);
				}
				else left.insertNode(newNode);
			}
			else if ( ((Integer) data).compareTo( ((Integer)newNode.getData())) <= 0){
				if(this.getRight()==null) {
					right = newNode; 
					right.setParent(this);
				}
				else right.insertNode(newNode);
			}
		}
	}
	
	
	public boolean search(E element){
		if(element.equals(data)) return true;
		else if(compare(element) < 1) 
			return left.search(element);
		else if(compare(element) > 1)
			return right.search(element);
		else return false;
	}
	
	public boolean isRoot(){
		return ((parent==null)?true :false);
	}
	
	public boolean delete(E element , boolean duplicate){
		if(isEmpty()) return false;
		
		if(compare(element)==0 && !duplicate) {
			//delete this node
			if(isLeaf()) {
				if(isRoot()) {
					data = null; return true; 
				}
				
				//just remove the node
				if(parent.getLeft()!=null && compare(parent.getLeft().getData())==0) {
					parent.setLeft(null);
					return true;
				}
				else if(parent.getRight()!=null && compare(parent.getRight().getData())==0) {
					parent.setRight(null);
					return true;
				}
				
			}
			
			//remove a node with one child
			else if(hasOnlyLeftChild()){
				if(parent == null) {
					data = left.getData(); 
					BTNode<E> l = left.getLeft();
					BTNode<E> r = left.getRight();
					left = l; 
					if(left!=null) left.setParent(this);
					right = r;
					if(right!=null) right.setParent(this);
					return true;
				}
				
				left.setParent(parent);
				if(parent.getLeft()!=null && compare(parent.getLeft().getData())==0) 
					parent.setLeft(left);
				else if (parent.getRight()!=null && compare(parent.getRight().getData())==0)
					parent.setRight(left);
				return true;
			}
			else if(hasOnlyRightChild()){
				if(parent == null) {
					data = right.getData(); 
					BTNode<E> l = right.getLeft();
					BTNode<E> r = right.getRight();
					left = l; 
					if(left!=null) left.setParent(this);
					right = r;
					if(right!=null) right.setParent(this);
					return true;
				}
				
				right.setParent(parent);
				if(parent.getLeft()!=null && compare(parent.getLeft().getData())==0) 
					parent.setLeft(right);
				else if (parent.getRight()!=null && compare(parent.getRight().getData())==0)
					parent.setRight(right);
				return true;
			}
			
			else{
			//remove a node with 2 children
			BTNode<E> node = findMin(right);
			data = node.getData(); // copy the value of minimum node in the right subtree
			//right.getParent().setData(node.getData());
			//remove the minimum node
			BTNode<E> root = getRoot();
			return root.delete(node.getData() , true);
			}
		}
		
		
		else if(compare(element)==0 && duplicate)
			duplicate = false;	
		if(compare(element) < 0 && left!=null) 
			return left.delete(element , duplicate);
		else if(compare(element) >= 0 && right != null)
			return right.delete(element,duplicate);
		else return false;
	}
	
	public boolean hasOnlyLeftChild(){
		if(getRight()==null && getLeft()!=null) return true;
		else return false;
	}
	
	public boolean hasOnlyRightChild(){
		if(getRight()!=null && getLeft()==null) return true;
		else return false;
	}

	public BTNode<E> findMin(BTNode<E> root) {
		//return the leftMostNode
		if(root.getLeft()!=null) return findMin(root.getLeft());
		else return root;
	}
		
	public int compare(E element){
		if(this.data instanceof Integer){ //Integer type
			Integer thisData = (Integer)data;
			Integer elementData= (Integer) element;
			if(thisData == elementData) return 0;
			else if(elementData < thisData) return -1;
			else return 1;
		}
		
		else if(this.data instanceof Double){ //Double type
			Double thisData = (Double)data;
			Double elementData= (Double) element;
			if(thisData == elementData) return 0;
			else if(elementData < thisData) return -1;
			else return 1;
		}
		
		else if(this.data instanceof Float){ //Float Type
			Float thisData = (Float)data;
			Float elementData= (Float) element;
			if(thisData == elementData) return 0;
			else if(elementData < thisData) return -1;
			else return 1;
		}
		
		else if(this.data instanceof String){ //String type
			String thisData = (String)data;
			String elementData= (String) element;
			if(thisData.compareTo(elementData)==0) return 0;
			else if(thisData.compareTo(elementData) < 0) return -1;
			else return 1;
		}
		
		else{
			System.err.print("[Error_"+this.getClass().getName()+ "]: This type is not supported.");
			return -2;
		}
		
	}
	
}
