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
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
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
		word = word.toLowerCase();
		char[] wordArray = word.toCharArray();
		TrieNode curr = root;
		
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
		TrieNode curr = root;
		s = s.toLowerCase();
		
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
    	 
    	 prefix = prefix.toLowerCase();
    	 List<String> prediction = new ArrayList<String>();				// to return predictions
    	 
    	 TrieNode stem = getStem(prefix);								// using helper method to find the stem for the prefix
    	 if (stem == null) {											// if no stem found, return an empty List
    		 return prediction;
    	 }
    	     	 
    	 prediction = bfSearch(stem, numCompletions);					// using helper method to perform Breadth First Search on Trie Tree
         return prediction;												// return the completed List
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
 		
 		AutoCompleteDictionaryTrie test = new AutoCompleteDictionaryTrie();
 		if(test.addWord("hello")) System.out.println("first statement");
 		if (test.addWord("Helloo")) System.out.println("second statement");
 		if(test.isWord("hell0o")) System.out.println("is word");
 	}

	
}