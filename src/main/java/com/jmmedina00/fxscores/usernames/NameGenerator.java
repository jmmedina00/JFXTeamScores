package com.jmmedina00.fxscores.usernames;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

/**
 * Generates random usernames by using the default WordNet dictionary, mixing
 * adjectives and nouns and replacing certain characters. It requires an instance
 * to be made since it needs to get the default dictionary.
 */
public class NameGenerator {
	private Dictionary dictionary;

	public NameGenerator() throws JWNLException {
		dictionary = Dictionary.getDefaultResourceInstance();
	}

	public String getRandomWord(POS pos) throws JWNLException {
		IndexWord indexWord = dictionary.getRandomIndexWord(pos);
		return indexWord.getLemma();
	}
}
