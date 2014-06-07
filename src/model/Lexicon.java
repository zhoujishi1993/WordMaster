package model;

import java.util.Iterator;
import java.util.List;

/**
 * Lexicon
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class Lexicon {
	
	protected List<Word> words;
	protected String name;
	
	/**
	 * constructor
	 * @param words
	 * 			words in this lexicon
	 * @param name
	 * 			name of this lexicon
	 */
	public Lexicon(List<Word> words, String name) {
		super();
		this.words = words;
		this.name = name;
	}
	
	/**
	 * get name
	 * @return name of this lexicon
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "lexicon [words=" + words + ", name=" + name + "]";
	}

	/**
	 * get next Chinese
	 * <p>if a word is picked, that means it is to be recited,
	 * 	so set its recited true
	 * </p>
	 * @return next word to be recited
	 * 			if null, that means no word needs to be recited	
	 */
	public Word getNext() {
		//TODO
		Iterator<Word> it = this.words.iterator();
		Word ret;
		while(it.hasNext()) {
			ret = it.next();
			if(!ret.isRecited()) {
				ret.setRecited(true);
				return ret;
			}
		}
		return null;
	}
	

}
