# Top-DownRBtrees
A Demonstration of storing an English sentence in  a red-black tree using top-down insertion approach and inserting a word into it using this strategy. 

## Rbtree.java file 

This is the main file consisting of driver code and other functionalities described in the problem statement.

### Methods and their Description -

1. **treenode(String data)** : constructor to create and initialize a new node with the different parameters 
                               describing the Rbtree node. The parameters are data ,color and children - Left 
                               and Right.
 
2. **insert(treenode root,String data)** : method to insert a node into the Rbtree using top down insertion 
                                           approach.It accepts two parameters as the root node of the tree and a 
                                           string data which is a word of an English Sentence. It returns the 
                                           root of the tree.

3. **singlerotate(treenode root,int dir)** : method to rotate the child node once around the parent and recolor 
                                             them after performing rotation to maintain the properties of an  
                                             Rbtree.It accepts two parameters as the parent node and an integer   
                                             variable dir to specify the direction of rotation depending on the 
                                             left or right child of a node. It returns a pointer to the new node 
                                             after rotation.
 
4. **doublerotate(treenode root,int dir)** : method to rotate the child node twice , first around parent and 
                                             then along grand-parent.calls singlerotate twice to perform double 
                                             rotation . It returns pointer to the new node after rotation. 

5. **isred(treenode root)** : method to check if a node's color is red or not. It returns a boolean value True 
                              if red otherwise False.

6. **heightT(treenode root)** : To calculate the height of the Rbtree. It accepts one parameter as the root node 
                                of the tree and returns back an integer value as the height of the tree.

7. **printlevel(treenode root,int i)** : Method to print nodes at each level in the level-order traversal of the 
                                       tree. It accepts two parameters as the rootnode of the tree and an 
                                       integer variable.

8. **levelorder(treenode root)** :  method to iterate over the height of the tree and call printlevel to show 
                                    the level-order traversal of the tree.
 
## Wire Frame Diagram

The detailed wireframe diagram can be found [here](https://wireframe.cc/pro/pp/4ca6899c4232403) or in TOP-DOWN-RED-BLACK-TREES.pdf.

## sampleinput.txt 
This file contains some sample inputs to test the working of the program.

