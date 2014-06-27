package model;

import java.io.Serializable;
import java.util.List;

/**
 * Lexicon
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class Lexicon implements Serializable {
	
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
		String str = "";
		str += "<?xml version='1.0' encoding='UTF-8'?><words>";
		for(Word w: this.words) 
			str += w;
		str += "</words>";
		return str;
	}
	
}
