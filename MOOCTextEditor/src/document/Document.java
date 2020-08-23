package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and 
	    // EfficientDocument (module 3).
		
		int counter = 0;  // counter for syllables
		//int loopCounter = 0;
		Boolean vowelCheck  = false;
		//String previousChar = "";
		HashSet<String> set = new HashSet<String>(); 
		set.add("a"); set.add("e"); set.add("i"); set.add("o"); set.add("u"); set.add("y");
		set.add("A"); set.add("E"); set.add("I"); set.add("O"); set.add("U"); set.add("Y");
		
		/*  // for each loop
		 * for (char c : word.toCharArray()) {
		 * 
		 * String check = Character.toString(c);
		 * 
		 * if (set.contains(check) && !vowelCheck) { counter += 1; vowelCheck = true; }
		 * else if (!set.contains(check) && vowelCheck) { vowelCheck = false; }
		 * 
		 * if ((loopCounter == word.length()-1) && (c == 'e') && (counter > 1) &&
		 * (!set.contains(previousChar)) ) { counter -= 1; } loopCounter += 1;
		 * previousChar = check; } return counter;
		 */
	    
	    
	    // second approach with for loop
    	
	    char[] letters = word.toCharArray();
	    for (int i = 0; i < letters.length; i++) {
	    	
	    	String currChar = Character.toString(letters[i]);
	    	if (set.contains(currChar) && !vowelCheck) {  // check if current character is a vowel and previous character is not a vowel
				counter += 1;
				vowelCheck = true;
			} 
			else if (!set.contains(currChar) && vowelCheck) {  // check if current character is not a vowel and previous character was a vowel
				vowelCheck = false;
			}
	    	if ((i == (letters.length-1)) && (letters[i] == 'e') && (counter > 1) && (!set.contains(Character.toString(letters[i-1]))) ) { // check if last character is 'e' &&
	    		// previous character is not a vowel
				counter -= 1;
			}
	    }
	    return counter;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: You will play with this method in week 1, and 
		// then implement it in week 2
		
		double fleschScore;
		
		double ratio1 = getNumWords()/(double)getNumSentences();  // cast integer to double to get the result in double instead of integer - integer division
		double ratio2 = getNumSyllables()/(double)getNumWords();
		fleschScore = 206.835 - (1.015 * ratio1) - (84.6 * ratio2);
		
	    return fleschScore;
	}
	
	
	
}
