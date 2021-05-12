package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	if (word == null) throw new NullPointerException("DictionaryLL.addWord() NullPointerException");
    	
    	word = word.toLowerCase();
    	if (isWord(word)) return false;
    	dict.add(word);
    	return true;
    }

    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	if (s == null) throw new NullPointerException("DictionaryLL.isWord() NullPointerException");
    	
    	s = s.toLowerCase();
    	if (dict.contains(s)) return true;
        return false;
    }

    public String toString() {
    	String st = "";
    	for (String s : dict) {
    		st += s;
    		st = st + "\n";
    	}
    	
		return st;
    }
    
}
