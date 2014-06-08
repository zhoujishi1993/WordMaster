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
	 * write lexicon
	 * @param lexicon target lexicon
	 * @param username username
	 */
	void writeLexicon(MyLexicon lexicon, String username);
	
	/**
	 * readInWords
	 * @param username name of username
	 * @param lexicon lexicon
	 * @return words in the lexicon
	 */
	List<Word> readInWords(String username, String lexicon);

}
