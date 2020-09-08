package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	private String preWord;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		
		String[] wordsInText = sourceText.split(" +");  // to train
		
		if (wordsInText.length <= 1) {
			return;
		}
		
		starter = wordsInText[0];
		preWord = starter;
		String w;  // to store the current word
		int index;  // to keep track the nodes
		boolean ifExists = false;  // to keep track if the node exists
		
		for (int i = 1; i < wordsInText.length; i++) {
			
			w = wordsInText[i];
			ifExists = false;	// set it to false for next node

			
			for (ListNode thisNode : wordList) {		// check if the node already exists

				if (thisNode.getWord().equals(preWord))
				{ 
					index = wordList.indexOf(thisNode);		// find the node
					wordList.get(index).addNextWord(w); 		//add next word
					preWord = w; 								// update preWord
					ifExists = true;							// set it to true so that a new node is not created in next statement
					break; 										// break out of loop. work is done. no need to continue the loop
				} 
			} 
			
			if (!ifExists) {		// if the node does not exist
				ListNode n = new ListNode(preWord);		// create a new node
				n.addNextWord(w); 		// add next word
				wordList.add(n);		// add node to wordList
				preWord = w;		 	// update preWord
				
			}
			
			// taking care of the last word of text
			if (i == (wordsInText.length - 1)) {
				ListNode last = new ListNode(wordsInText[i]);		// last word in text always follows the first word in text
				last.addNextWord(wordsInText[0]);
				wordList.add(last);
			}
			
		}
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		
		String currWord = starter;
		String output = "";
		String w= "";
		output += currWord;
		int i = 0;		// increment variable
		
		if (wordList.isEmpty()) {
			return "";
		}
		if (numWords == 0) {
			//throw new NullPointerException("no words requested");		// grader get confused with exceptions
			return "";
		}
		
		while (i < numWords - 1) {		// loop through numWords-1 because first word is already assigned as starter
			
			for (ListNode thisNode : wordList) {		// find the node
				
				if (thisNode.getWord().equals(currWord)) {
					w = thisNode.getRandomNextWord(rnGenerator);
					output += " " + w;					// add output
					currWord = w;						// update currWord
					break;
				}
			}
			i++;		// update increment variable
		}
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		
		wordList.clear();		// reset the wordList
		starter = "";			// reset the starter

		if (sourceText.isEmpty()) {
			return;
		}
		
		
		String[] text = sourceText.split(" +");
		starter = text[0];
		preWord = starter;
		int index;
		String w;
		
		
		for (int i = 1; i < text.length; i++) {
			w = text[i];
			index = getNode(preWord);		// find the node index with helper method getNode()

			if (index >= 0) {				// if node exists
				wordList.get(index).addNextWord(w);		//add next word
			}

			else if (index < 0) {			// if node does not exist, create a node and add next word
				ListNode n = new ListNode(preWord);
				n.addNextWord(w);
				wordList.add(n);
			}
			preWord = w;					// update preWord

			// taking care of the last word of text
			if (i == (text.length - 1)) {
				ListNode last = new ListNode(text[i]);
				last.addNextWord(text[0]);
				wordList.add(last);
			}
		}
	}
	
	// TODO: Add any private helper methods you need here.
	
	private int getNode(String word) {				// private helper method to find the node in wordList and return the node index
		
		for (ListNode thisNode : wordList) {		// loop through wordList
			
			if (thisNode.getWord().equals(word)) {			// check if the word is same as the 'word' passed in as parameter
				return wordList.indexOf(thisNode);			// if yes, return the index of this node
			}
			
			
		}
		return -1;											// if node is not found, return -1
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println("Re train");
		gen.retrain(textString);
		System.out.println(gen);
		System.out.println("generate 0 words");
		System.out.println(gen.generateText(0));
		System.out.println("Hello");
		
		String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
        gen.retrain(input);
        String res = gen.generateText(500);
        String[] res2 = res.split(" ");
        System.out.println(res2.length);
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		int size = nextWords.size();
		int rand = generator.nextInt(size);
	    return nextWords.get(rand);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


