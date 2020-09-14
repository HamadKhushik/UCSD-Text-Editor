/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;


/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSetMatchCase implements Dictionary 
{

    private HashSet<String> words;
	
	public DictionaryHashSetMatchCase()
	{
	    words = new HashSet<String>();
	}
	
    /** Add this word to the dictionary.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
	@Override
	public boolean addWord(String word) 
	{
		//return words.add(word.toLowerCase());
//		boolean firstCap = false;
//		boolean allCaps = false;
//		if (word.equals(word.toUpperCase())) allCaps = true;					// if all the characters in a word are capital, set all caps to true
//		else if ((word.charAt(0) == Character.toUpperCase(word.charAt(0))) && 	// if only first char is capital, set firstCap to true
//				word.substring(1).equals(word.substring(1).toLowerCase()))
//		{
//			firstCap = true;
//		}
//		else 																	// if neither first char nor all chars are upper case,  
//		{																		// convert the word to lower case to avoid mis-spelling due to case sensitivity
//			word = word.toLowerCase();
//		}
//		return words.add(word);
		return words.add(word);
	}

	/** Return the number of words in the dictionary */
    @Override
	public int size()
	{
    	 return words.size();
	}
	
	/** Is this a word according to this dictionary? */
    @Override
	public boolean isWord(String s) {
    	//return words.contains(s.toLowerCase());
    	boolean firstCap , allCaps;
    	firstCap = allCaps = false;
		if (s.equals(s.toUpperCase())) allCaps = true;					// if all the characters in a word are capital, set all caps to true
		else if (Character.isUpperCase(s.charAt(0)) && 	// if only first char is capital, set firstCap to true
				s.substring(1).equals(s.substring(1).toLowerCase()))
		{
			firstCap = true;
		}
		
		
		
		if (words.contains(s)) return true;								// if the word is in dictionary, return
		
		if (firstCap) {													// if the word is not in dict, and first word is upper case, convert to lower case and return
			//if (words.contains(s)) return true;						
			//else return words.contains(s.toLowerCase());
			return words.contains(s.toLowerCase());						// to account for words like Hello, 
		}
		if (allCaps) {													// if all the letter are uppercase, 
			
			if (words.contains(s.toLowerCase())) return true;			// return true if word exists in lower case, to accomodate for HELLO
			else {
				return words.contains(s.substring(0,1) +  s.substring(1).toLowerCase());		// convert the first letter to uppercase and return
			}
		}
    	return words.contains(s);
	}
	
    
    
    
    
    
    
    public static void main(String[] args) {
    	DictionaryHashSetMatchCase test = new DictionaryHashSetMatchCase();
    	test.addWord("Christine");
    	if (test.isWord("christine")) System.out.println("IS WORD");
    	
    	String te = "Christine";
    	
    	System.out.println(te.substring(0,1).toUpperCase() + te.substring(1));
    }
   
}
