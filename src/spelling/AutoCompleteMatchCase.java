package spelling;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
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
		if (word == null) throw new NullPointerException();
		
		word = word.toLowerCase();
		boolean noSuchWord = false;
		TrieNode next = root;
		
		// loop each char of the word
		for (Character c : word.toCharArray()) {
			// Check if the node is a null, 
			if (next.getChild(c) == null) {
				// if it is a null, insert(c) with its TrieNode, 
				next.insert(c);
				next = next.getChild(c);
				noSuchWord = true;
			} else { 
				// if it is not a null, visit c
				next = next.getChild(c);
			}
		}
		next.setEndsWord(true);
		if (noSuchWord == true) return true;
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size() 
	{
		sizeNode(root);
		return size;
	}
	
	private void sizeNode(TrieNode curr) 
	{
	    //TODO: Implement this method
		//loop each character linked to a trieNode;
		for (Character c : curr.getValidNextCharacters()) {
			//if this character not linked to a trieNode, then do nothing;
			if (curr.getChild(c) == null) 
				{
				return;
			} else { //else, visit this trieNode and repeat the steps above
				sizeNode(curr.getChild(c));
			}
		}
		//check if a word; if so, do size++;
		if (curr.ifEndsWord()) {
			size++;
		}
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if (s == "") return false;
		if (getNodeOfString(s) == null) return false;
		return true;
	}
	
	private TrieNode getNodeOfString(String s) 
	{
	    // TODO: Implement this method
		s = s.toLowerCase();
		TrieNode next = root;
		
		// loop each char of the word
		for (Character c : s.toCharArray()) {
			// Check if the node is a null, 
			if (next.getChild(c) == null) {
				// if it is a null, return false, 
				return null;
			} else { 
				// if it is not a null, visit c
				next = next.getChild(c);
			}
		}
		
		return next;
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
     // completions = smallDict.predictCompletions("", 0);
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
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
    	 // TODO: Implement this method
    	 LinkedList<TrieNode> nodeList = new LinkedList<TrieNode>();
    	 List<String> wordList = new LinkedList<String>();
    	 int count = 0;
    	 
    	 //get to the node that has the prefix
    	 TrieNode curr = getNodeOfString(prefix);
    	 //if there is no such prefix, add "" into list and return the list
    	 if (curr == null) {
    		 return wordList;
    	 }
    	 
    	 //add this node to nodeList
    	 nodeList.add(curr);
    	 while (count < numCompletions) {
	    	 //if this is a word, add it to wordList and count++
    		 if (curr.ifEndsWord()) {
    			 wordList.add(curr.getText());
    			 count++;
    		 }
	    	 //add all its children to nodeList
    		 for (Character c : curr.getValidNextCharacters()) {
    			 nodeList.add(curr.getChild(c));
    		 }
	    	 //remove the first element in nodeList
    		 nodeList.remove();
	    	 //visit the first element in nodeList
    		 try {
    			 curr = nodeList.getFirst();
    		 } catch (NoSuchElementException e) {
    			 break;
    		 }
    	 }
    	 
         return wordList;
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
 	
}