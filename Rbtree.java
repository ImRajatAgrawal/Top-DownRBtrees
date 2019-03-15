//To demonstrate red-black tree top down insertion approach
package rbtree;
/**
 **************************************************************************
 * Author : Rajat Agrawal
 * Statement : creating a red black tree and storing an English sentence 
 *             into it using Top down insertion approach
 * Date : 01-03-2019 .
 **************************************************************************
 **/

import static java.lang.Integer.max;
import java.util.Scanner;


class rbtreemain{
    treenode root=null;// ROOT NODE OF THE TREE
    //To calculate height of the tree
    int heightT(treenode root){
        int lefth,righth;
        
        if(root==null || (root.children==null && root.children[1]==null))
            return 0;    
        
        lefth=heightT(root.children[0]);
        righth=heightT(root.children[1]);
        
        return (max(lefth,righth)+1);   
    }
    //Check value of dir
    int check(int dir){
        return dir==0 ? 1:0;
    }
    //Print nodes at each level in level order traversal
    void printlevel(treenode root,int i){
       if(root==null)
        return;
      
       if(i==1){
           System.out.print("| "+root.data+" | "+root.color+" |");
       
           if(root.children[0]!=null)
               System.out.print(" "+root.children[0].data+" |");
        
           else
               System.out.print(" "+"N"+" |");
        
           if(root.children[1]!=null)
               System.out.print(" "+root.children[1].data+" |");
        
           else
               System.out.print(" "+"N"+" |");
        
           System.out.print("   ");
       
           return;
       }
        printlevel(root.children[0],i-1);
        printlevel(root.children[1],i-1);        
    }			
    //Method to display the level order traversal of the RB-Tree 
    void levelorder(treenode root){
        int i;
        
        for(i=1;i<heightT(root)+1;i++){
         
            System.out.println("-------------------------------------------------------------");
            printlevel(root,i);
            System.out.println("\n-------------------------------------------------------------");
            System.out.print("\n");
        }
    }
    //Method to check if a node's color is red 
    boolean isred(treenode root){
        
        return root!=null && root.color.equals("R");
    }
    //Rotate the node once
    treenode singlerotate(treenode root,int dir){
        
        treenode temp=root.children[check(dir)];
        root.children[check(dir)]=temp.children[dir];
        temp.children[dir] = root;
        root.color="R"; 
        temp.color="B";
        return temp;
    }
    //Rotate the node twice
    treenode doublerotate(treenode root,int dir){
   
        root.children[check(dir)] = singlerotate(root.children[check(dir)], check(dir));
        return singlerotate(root, dir);
    }
    //Method to insert a node into the tree using Top-Down Inserion Appproach
    treenode insert(rbtreemain tree,String data){
        if(tree.root==null){
        
            tree.root=new treenode(data);
            if(tree.root==null)
                return null;
                    
        }
        else{
                treenode temp=new treenode("");   //A temporary root
                treenode g,t;   //Grandparent and Parent 
                treenode p,q;
                int dir=0,last=0;
                t=temp;
                g=p=null;
                t.children[1]=tree.root;
                q=t.children[1];
                while(true){
            
                    if(q==null){
                        //Inserting root node
                        q=new treenode(data);
                        p.children[dir]=q;
                        
                    }
                    else if(isred(q.children[0])&& isred(q.children[1])){
                        //Recoloring if both children are red 
                        q.color="R";
                        q.children[0].color="B";
                        q.children[1].color="B";
                            
                    }
                    if(isred(q)&& isred(p)){
                        //Resolving red-red violation
                        int dir2;
                        if(t.children[1]==g)
                            dir2=1;
                        else dir2=0;
                        
                        if (q == p.children[last]) //If children and parent are left-left or right-right of grand-parent
                            t.children[dir2]=singlerotate(g, last==0?1:0);
                      
                        else    // If they are opposite childs i.e left-right ot right-left
                            t.children[dir2] =doublerotate(g, last==0?1:0);              
                    }
                    //Checking for correct position of node
                    if(q.data.equals(data))
                        break;
                    last=dir;
                    //Finding the path to traverse [Either left or right ]
                    dir = q.data.compareTo(data)<0 ? 1:0;

                    if (g != null)
                        t = g;
                    //Rearranging pointers
                    g = p;
                    p = q;
                    q = q.children[dir];
                }
                tree.root=temp.children[1];
        }
        //Assign black color to the root node
        tree.root.color="B";
        return tree.root;
    }
}
//class to represent an RB-Tree node
class treenode{
    
    String data;
    String color;
    treenode children[];
    
    public treenode(String data){
    
        this.data=data;
        this.color="R";
        children=new treenode[2];
        children[0]=null; //[0- LEFT CHILD , 1-RIGHT CHILD]
        children[1]=null;
    }
    
}
//MAIN CLASS
public class Rbtree {
    //DRIVER FUNCTION
    public static void main(String[] args) {
        
        String sentence,word,ans;
        int i;
        
        //CREATING THE STRUCTURE
        rbtreemain tree=new rbtreemain();
        
        Scanner sc=new Scanner(System.in);
        System.out.println("******************      TOP-DOWN INSERTION IN RED BLACK TREE      ***************\n");
        System.out.println("*******  TREE NODE REPRESENTATION   ***********");
        System.out.println("-------------------------------------------");
        System.out.print("| "+"DATA"+" | "+"COLOR"+" |"+" "+"LEFT CHILD"+" |"+" "+"RIGHT CHILD"+" |\n");
        System.out.println("-------------------------------------------");
        
        System.out.println("Enter an English Sentence:");
        sentence=sc.nextLine();
        
        String arr[]=sentence.split(" ");
        for(i=0;i<arr.length;i++)
            tree.root=tree.insert(tree, arr[i]); //Insert nodes into the tree
        
        System.out.print("\n");
        System.out.println("LEVEL ORDER TRAVERSAL OF TREE");
        tree.levelorder(tree.root);
        
        do{
            System.out.println("Enter a word to insert:");
            word=sc.next();
            tree.root=tree.insert(tree, word);
            System.out.println("LEVEL ORDER TRAVERSAL OF TREE");
            tree.levelorder(tree.root);
            System.out.println("Do you want to insert more words:[y or n]");
            ans=sc.next();
        }while(ans.equals("y"));
    }
    //End
}
