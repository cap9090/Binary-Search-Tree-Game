/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bstgame;


import java.util.*;
/**
 *
 * @author Christian Prajzner
 */
public class Main{

    
    public static void main(String[] args) {
        new Main();
    }
    
    Main(){
        
        //works best with 10 to 15 
        System.out.println("Enter 10 numbers, pressing enter after each. NO DUPLICATES!");
        Scanner in = new Scanner (System.in);
        
        //int [] numbers = new int[10];//decoment this and the next for loop later 
        int [] numbers = {11,1,2,3,4,5,6,7,8, 0};//,9,1234,123, 12,54,245, 24112,-3,-903};
        //for testing sake i will skip adding the numbers
        /*
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = in.nextInt();
        }*/
        
        System.out.println("Thank you");
        
        for(int i = 0 ; i<numbers.length; i++)
            System.out.print(numbers[i] + " ");
        System.out.println();
        System.out.println();
        shuffle(numbers);
        
        for(int i = 0 ; i<numbers.length; i++)
            System.out.print(numbers[i] + " ");
        //now we have the array we want
        
        BST tree = new BST();
        tree.createFromNumberSequence(numbers);
        tree.visualize();
        System.out.println();
        System.out.println(tree.root.data);
        //System.out.println(tree.root.left.data); // null pointer, we're not creating the tree right
        //System.out.println(tree.root.right.data);
        tree.playGame(numbers);
        //playGame(tree, numbers);
        
    }
    
    private void shuffle(int [] A){
        ArrayList<Integer> hi = new ArrayList();
        for (int i = 0 ; i < A.length; i++){
            hi.add(new Integer(A[i]));
        }
        
        Collections.shuffle(hi);
        
        int i = -1;
        for(Integer x: hi){
            A[++i] = x.intValue();
        }
        
    }
    
    private void playGame(BST tree, int [] A ){
        tree.playGame(A);
    }
    
    /*
    void playGame(BST tree, int [] A){
        
        BST.sg.print("To begin press START.  When a number appears click on the spot"
                + "in the binary tree where it belongs");
        tree.sg.labelButton1("START");
        for(int i = 0; i < A.length; i++){
            tree.sg.print("" + A[i]);
            tree.sg.waitForMouseClick();
        }
    }*/

}
