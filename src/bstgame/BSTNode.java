/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bstgame;

/**
 *
 * @author Christian Prajzner
 */

class BSTNode {
    BSTNode right, left;
    int xPos, yPos, data;
    public final int RADIUS = 10;
    
    public BSTNode(int data){
        right = null;
        left = null;
        this.data = data;
    }
    
    void visualize(){
        BST.sg.drawText(""  + this.data, xPos, yPos);
    }
    
    
    boolean isInside(){
        return true;
    }
    
    boolean isInside(int x, int y){
        return ((x <= this.xPos + RADIUS) && (x >= this.xPos - RADIUS) && (y >= this.yPos) && (y <= this.yPos + 2*RADIUS));
    }
            

    
}
