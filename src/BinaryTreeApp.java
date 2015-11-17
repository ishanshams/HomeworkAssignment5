
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ishan
 */
public class BinaryTreeApp {
    public static void main(String[] args) {
        BTNode<Integer> tree = new BTNode<Integer>(null, null, null);
		
			if(tree.getData()==null) tree.setData(47);
                        
						tree.addNode(new BTNode<Integer>(47, null, null));
                        tree.addNode(new BTNode<Integer>(77, null, null));
                        tree.addNode(new BTNode<Integer>(11, null, null));
                        tree.addNode(new BTNode<Integer>(43, null, null));
                        tree.addNode(new BTNode<Integer>(65, null, null));
                        tree.addNode(new BTNode<Integer>(93, null, null));
                        tree.addNode(new BTNode<Integer>(7, null, null));
                        tree.addNode(new BTNode<Integer>(17, null, null));
                        tree.addNode(new BTNode<Integer>(31, null, null));
                        tree.addNode(new BTNode<Integer>(44, null, null));
                        tree.addNode(new BTNode<Integer>(68, null, null));
                        
                        
		
		tree.print(tree.getHeight());
                System.out.println("\n"+ tree.getRoot().getData() + " is a root node");
                System.out.println("\n"+ tree.getRoot().getData() + " is a root node");
                System.out.println("\n"+ BTNode.countLeaves(tree) + " number of leaves");
                System.out.println("\n"+ BTNode.countNonLeaves(tree) + " number of non-leaves");
    }
}
