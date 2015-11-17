
import javax.swing.JOptionPane;

public class TestBTNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode<Integer> tree = new BTNode<Integer>(null, null, null);
		while(JOptionPane.showConfirmDialog(null, "add more?")==JOptionPane.YES_OPTION){
			Integer data = Integer.parseInt(JOptionPane.showInputDialog("enter data"));
			if(tree.getData()==null) tree.setData(data);
			//else tree.addNode(new BTNode<Integer>(data, null, null));
			else tree.insertNode(new BTNode<Integer>(data, null, null));
		}
		
		tree.print(tree.getHeight());
//		System.out.println("find 10: " +tree.find(10));
//		System.out.println("find 20: " +tree.find(20));
		while(JOptionPane.showConfirmDialog(null, "delete more?")==JOptionPane.YES_OPTION){
			Integer element = Integer.parseInt(JOptionPane.showInputDialog("enter a value to delete"));
			if(element == 6){
				System.out.println();
			}
			if(!tree.delete(element, false)) JOptionPane.showMessageDialog(null, "Error!");
			else JOptionPane.showMessageDialog(null, "delete was successful.");
			System.out.println("Next----\n");
			int h=tree.getHeight();
			System.out.println("height: " +h);
			tree.print(h);
		}
	}

}
