/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bstgame;

import java.awt.Color;
import simplegui.SimpleGUI;

/**
 *
 * @author Christian Prajzner
 */


class BST {
    
    static SimpleGUI sg = new SimpleGUI();
    final int WIDTH = sg.getWidth();
    final int HEIGHT = sg.getHeight();
    final int RADIUS = 10;
    boolean addReturn = true;
    int level = 0;
    boolean help = false;
    int xoffsetInit, yoffsetInit, xoffset, yoffset, xPos, yPos;
    BSTNode root;
    
    
    
    BST()
    {
        xPos = WIDTH/2;
        yPos = 10;
        root = null;
    }
    
    void createFromNumberSequence(int [] A){
        determineOffsets(A.length);
        for (int i = 0; i < A.length; i ++)
            if(!this.add(A[i])){ 
                System.out.println("No duplicates!"); System.exit(0);
            }
       
    }
    
    private void determineOffsets(int length){
        
        int levels = takeLogBase2(length);//this works when the tree is relatively balanced so there will be errors off the screen if it is not balanced
        xoffset = xoffsetInit = (length/8)*WIDTH/(levels);
        yoffset = yoffsetInit = HEIGHT/(levels +4);//the plus 4 takes into account unbalcned scenarios
        
        /*
        System.out.println();
        System.out.println();
        System.out.println("x offset is " + xoffsetInit + "y offset is " + yoffsetInit) ;*/
    }
    
    
    
    private static int takeLogBase2(int x){
        return (int) (Math.log10(x)/Math.log10(2));
    }
    
    void visualize(){
        if(root == null)
            return;
        else{
           preOrderTraversal();
        }  
    }
    
    private void drawNode(BSTNode cur){
        
        sg.drawFilledEllipse(cur.xPos - RADIUS, cur.yPos, 2*RADIUS, 2*RADIUS, Color.green, 1, null);
    }
    
    private void drawConnection(BSTNode cur){
        if(cur.left != null)
            sg.drawLine(cur.xPos, cur.yPos + RADIUS*2, cur.left.xPos, cur.left.yPos);
        if(cur.right != null)
            sg.drawLine(cur.xPos, cur.yPos + RADIUS*2, cur.right.xPos, cur.right.yPos);

    }
       
    
    private void preOrderTraversal(){
        if (root == null)
            return;
        else{
            if(help){
                root.visualize();
            }
            drawConnection(root);
            drawNode(root);
            preOrderTraversal(root.left); 
            preOrderTraversal(root.right);
        }
    }
    
    private void preOrderTraversal(BSTNode cur){
        if(cur == null){
            return;
        }
        else{
            if(help){
                cur.visualize();
            }
            drawConnection(cur);
            drawNode(cur);
            preOrderTraversal(cur.left);
            preOrderTraversal(cur.right);
        }
    }
            

    
    boolean add(int data){
        root = add(data, root);
        return addReturn;
    }
    
    private BSTNode add(int data, BSTNode subtree){
        if (subtree == null){
            BSTNode ans = new BSTNode(data);
            assignNodeCoordinates(ans, xPos, yPos);
            xPos = WIDTH/2;
            yPos = 0;
            xoffset = xoffsetInit;
            return ans;
            }
        else if (subtree.data == data){ //No duplicates check
            addReturn = false;
            return subtree;
        }
        else if (data > subtree.data){//move to the right and go down a level
            xoffset /= 2;
            xPos += xoffset;
            yPos += yoffset;
            subtree.right = add(data , subtree.right);
            return subtree;
        }
            
        else{//move to the left and go down a level
            xoffset /= 2;
            xPos -= xoffset;
            yPos += yoffset;
            subtree.left = add(data, subtree.left);
            return subtree;
        }
    }
    
    private void assignNodeCoordinates (BSTNode cur, int xPos, int yPos){
        cur.xPos = xPos;
        cur.yPos = yPos;
    }
    
    void playGame(int [] A){
        
        sg.print("To begin press START.  When a number appears click on the spot"
                + "in the binary tree where it belongs");
        sg.labelButton1("START");
        sg.waitForButton1();
        sg.labelButton1("");
        int i = 0;
        for(; i < A.length; i++){
            sg.print("" + A[i]);
            
            int [] pos = sg.waitForMouseClick();
            System.out.println(pos [0] + "   " + pos [1]);
            int ans = retrieveNodeValue(pos[0], pos[1]);
            System.out.println(ans);
            if(ans != A[i]){//game over
                sg.print("Game over");
                break;
                }
            //traverse until we get to the node we want
            //check to see if is good 
            //if it is good, visualize that node
            //else break the loop and visualize all nodes
            }
        
        if (i == A.length){
            sg.print("you won!");
        }
        else // we want to visualize the whole tree if we lose
        {   
            help = true;
            preOrderTraversal();
        }
        
        }
    
    
    
    
    int retrieveNodeValue(int x, int y){
        
        
            
        
        if (root == null){
            return 0;
        }
        
        else if (root.isInside(x, y)){
            root.visualize();
            return root.data;
        }
        /*
        else if (!root.isInside(x, y) && x < root.xPos) // go left
        {
            return retrieveNodeValue(x, y, root.left);// || retrieveNodeValue(x, y, root.right);
        }
        else //go right
            return retrieveNodeValue(x, y, root.right);*/
        else{
            int ans = retrieveNodeValue(x, y, root.left);
            if(ans != 0) return ans;
            ans = retrieveNodeValue(x, y, root.right);
            return ans;
        }
            
    }
    
    private int retrieveNodeValue(int x, int y, BSTNode cur){
        if(x == 0 && y == 0){
            cur.visualize();
            return 2;
        }
        if(cur == null){
            return 0;
        }
//        if (cur.right == null && cur.left == null && !cur.isInside(x, y)){
//            return 0;
//        }
        else if(cur.isInside(x, y)){

            cur.visualize();
            return cur.data;
        }
        /*
        else if (!cur.isInside(x, y) && x < cur.xPos) //go to the left
        {
            return retrieveNodeValue(x, y, cur.left);
        }
        else//go right
            return retrieveNodeValue(x, y, cur.right);
            */
        else {
            
            int ans = retrieveNodeValue(x, y, cur.left);
            if(ans != 0) return ans;
            ans = retrieveNodeValue(x, y, cur.right);
            return ans;
            
        }  
    }
    
    
    

}
