package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		//word = word.toLowerCase();
		TrieNode curr = root;
		
		char[] wordArray = word.toCharArray();									// create a char array of the word
		
		for (int i = 0; i < wordArray.length; i++) {
			
			if (curr.getChild(wordArray[i]) != null) {
				curr = curr.getChild(wordArray[i]);
			}
			else {
				curr = curr.insert(wordArray[i]);
			}
			
			if (i == wordArray.length-1) {
				if (curr.endsWord()) return false;
				else 
				{
					curr.setText(word);
					curr.setEndsWord(true);
					size++;
				}
			}
		}
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		boolean allCaps, firstCap;										// boolean flags to keep track if first letter/all letters are upper case
		allCaps = firstCap = false;
		
		if (s.equals(s.toUpperCase())) allCaps = true;					// if all the characters in a word are capital, set all caps to true
		else if (Character.isUpperCase(s.charAt(0)) && 					// if only first char is capital, set firstCap to true
				s.substring(1).equals(s.substring(1).toLowerCase()))
		{
			firstCap = true;
		}
		
		
		
		if (matchCase(s)) return true;									// if the word is in dictionary, return
		
		if (firstCap) {													// if the word is not in dict, and first word is upper case, convert to lower case and return
			//if (words.contains(s)) return true;						
			//else return words.contains(s.toLowerCase());
			return matchCase(s.toLowerCase());							// to account for words like Hello, 
		}
		if (allCaps) {													// if all the letter are uppercase, 
			
			if (matchCase(s.toLowerCase())) return true;				// return true if word exists in lower case, to accomodate for HELLO
			else {
				return matchCase(s.substring(0,1) +  s.substring(1).toLowerCase());		// convert the first letter to uppercase and return
			}
		}		
		return false;
		
		
	}
	
	// HELPER METHOD TO PERFORM BREADTH FIRST SEARCH ON MATCH CASE
	private boolean matchCase(String s) {
		TrieNode curr = root;

		for (int i = 0; i < s.length(); i++) {
			
			if (curr.getChild(s.charAt(i)) != null)
			{
				curr = curr.getChild(s.charAt(i));
			} else 
			{
				return false;
			}
			
			if (i == s.length() - 1)
			{
				if (curr.endsWord())
				{
					return true;
				}
			}
		}
		
		return false;
		
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 //prefix = prefix.toLowerCase();
    	 List<String> prediction = new ArrayList<String>();				// to return predictions
    	 int ref = getCase(prefix);
    	 
    	 
    	 TrieNode stem = getStem(prefix.toLowerCase());								// using helper method to find the stem for the prefix
    	 if (stem == null) {											// if no stem found, return an empty List
    		 return prediction;
    	 }
    	     	 
    	 prediction = bfSearch(stem, numCompletions);					// using helper method to perform Breadth First Search on Trie Tree
    	 
    	 //if (ref == 1) {
    		 for (int i = 0; i < prediction.size(); i++) {
    			 String temp = prediction.get(i);
    			 if (ref == 1) {
    			 temp = temp.toUpperCase();
    			 prediction.set(i, temp);
    			 }
    			 if (ref == 0) {
    				 temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
    				 prediction.set(i, temp);
    			 }
    			 if (ref == -1) {
    				 temp = temp.toLowerCase();
    				 prediction.set(i, temp);
    			 }
    		 }
    	 //}
         return prediction;												// return the completed List
     }
     
     // PRIVATE HELPER METHOD FOR PREDICT COMPLETIONS TO FIND THE CASE
     private int getCase(String toCheck) {
    	 

 		if (toCheck.equals(toCheck.toUpperCase())) return 1;					// return 1 if all upper case
 		else if (Character.isUpperCase(toCheck.charAt(0)) && 					// return 0 if first letter is upper case
 				toCheck.substring(1).equals(toCheck.substring(1).toLowerCase()))
 		{
 			return 0;
 		}
 		return -1;																// return -1 otherwise
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	// do a level order Traversal
 	private List<String> bfSearch(TrieNode stem, int numCompletions){				// performs breadth first search on the Trie Tree
 		
   	 Queue<TrieNode> q = new LinkedList<TrieNode>();								// initialise a queue for FIFO
	 List<String> prediction = new ArrayList<String>();
	 q.add(stem);																	// start the breadth first search from the stem node
	 
	 while (!q.isEmpty()) {					// while there are still nodes to visit
		 TrieNode curr = q.remove();		// remove the node from queue to visit
		 if (curr.endsWord()) {				// if the node ends with word, add the word to predictions
			 prediction.add(curr.getText());
		 }
		 
		 if (curr != null) {								// if the node is not null, get all the children and add to queue
			 List<TrieNode> children = curr.getChildren();
			 for (TrieNode thisNode : children) {			// loop through all the children to add in the queue
				 if (thisNode != null)
				 {
					 q.add(thisNode);
				 }
			 }
		 }
		 
		 if (prediction.size() == numCompletions)			// return when predictions are complete
		 {
			 return prediction;
		 }
	 }
	 return prediction;

 	}
 	
 	private TrieNode getStem(String prefix) {

 		TrieNode stem = root;

 		for (int i = 0; i<prefix.length(); i++) {			// loop through the prefix to find the stem node in Trie

 			if (stem.getChild(prefix.charAt(i)) != null)	// while there are still child nodes
 			{
 				stem= stem.getChild(prefix.charAt(i));		// update stem node
 			} else 
 			{
 				return null;							// if stem not found, return null
 			}
 		}
 		
 		return stem;

 	}
 	
 	public static void main(String[] args) {
 		
 		AutoCompleteMatchCase test = new AutoCompleteMatchCase();
 		if(test.addWord("Hello")) System.out.println("first statement");
 		if (test.addWord("HELLO")) System.out.println("second statement");
 		if (test.addWord("HELlo")) System.out.println("third statement");
 		if(test.isWord("Hello")) System.out.println("is word 1");
 		if(test.isWord("HELLO")) System.out.println("is word 2");
 		if(test.isWord("hello")) System.out.println("is word 3");
 	}

	
}
