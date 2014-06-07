package model;

import java.util.List;

/**
 * IO <interface>
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public interface IO {
	
	/**
	 * read in lexicon
	 * @return list of string
	 */
	List<String> readInLexicon();
	
	/**
	 * new user lexicon
	 * @param username name of user
	 */
	void newUserLexicon(String username);
	
	/**
	 * read in words
	 * @param username name of user
	 */
	List<Word> readInWords(String username);
	
	/**
	 * write lexicon
	 * @param lexicon target lexicon
	 * @param username username
	 */
	void writeLexicon(MyLexicon lexicon, String username);
	
	
	public List<Word> readInWords(String username, String lexiconName);
	public List<String> readInLexicon(String username);

}
