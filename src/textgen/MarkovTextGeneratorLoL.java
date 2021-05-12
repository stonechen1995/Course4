package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

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
	
	//train indicator
	boolean trained;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
		trained = false;
	}

	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) throws NullPointerException
	{
		// TODO: Implement this method
		if (trained == true) {
			System.out.println("generator already trained, train failed");
		}
		
		trained = true;
		
		List<String> words = getTokens("[a-zA-Z.']+", sourceText);
		//exception handling
		if (sourceText.equals(null)) throw new NullPointerException();
		if (sourceText.equals("")) return;
		//set "starter" to be the first word in the text  
		starter = words.get(0);
		//set "prevWord" to be starter
		String prevWord = starter;
		//for each word "w" in the source text starting at the second word
		//check to see if "prevWord" is already a node in the list
		for (int i = 1; i < words.size(); i++) {
			String w = words.get(i);
			//System.out.println(w);
			boolean haveWord = false;
			for (ListNode ln : wordList) {
				if (prevWord.equals(ln.getWord())) { //if "prevWord" is a node in the list
					//add "w" as a nextWord to the "prevWord" node
					ln.addNextWord(w);
					haveWord = true;
					break;
				} 
			}
			if (haveWord == false) { //else add a node to the list with "prevWord" as the node's word
				//add "w" as a nextWord to the "prevWord" node
				ListNode newLN = new ListNode(prevWord);
				wordList.add(newLN);
				newLN.addNextWord(w);
			}
			//index moving forward
			prevWord = w;
		}

		//add starter to be a next word for the last word in the source text.
		String lastWord = words.get(words.size()-1);
		boolean haveWord = false;
		for (ListNode ln : wordList) {
			if (lastWord.equals(ln.getWord())) {
				ln.addNextWord(starter);
				haveWord = true;
				break;
			}					
		}
		if (!haveWord) {
			ListNode lastNode = new ListNode(lastWord);
			wordList.add(lastNode);
			lastNode.addNextWord(starter);
		}
	}

	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) throws NullPointerException {
		// TODO: Implement this method
		//set "output" to be ""
		String output = "";
		
		//handling exceptions
		if (trained == false) {
			System.out.println("no train before generating text");
			return output;
		}
		if (numWords < 0) throw new IndexOutOfBoundsException();
		if (wordList.size() == 0) return "";
		
		// if numWords = 0, then output should be empty String
		if (numWords == 0) return output;
		//set "currWord" to be the starter word
		String currWord = starter;
		//set "output" to be 
		//add "currWord" to output
		output += currWord;
		//while you need more words
		while(numWords-1 > 0) {
			//find the "node" corresponding to "currWord" in the list
			ListNode currNode = null;
			for (ListNode ln : wordList) {
				if (ln.getWord().equals(currWord)) {
					currNode = ln;
					break;
				}
			}
			//select a random word "w" from the "wordList" for "node"
			String w = currNode.getRandomNextWord(rnGenerator);
			//add "w" to the "output"
			output += " ";
			output += w;
			//set "currWord" to be "w" 
			currWord = w;
			//increment number of words added to the list
			numWords--;
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
		trained = false;
		starter = "";
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.


	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		System.out.println("");
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		
		System.out.println("");
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
		// This method should select a random word from the "wordList" for "node"
		int index1 = generator.nextInt(nextWords.size());
		return nextWords.get(index1);
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


