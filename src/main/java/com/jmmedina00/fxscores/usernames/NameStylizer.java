package com.jmmedina00.fxscores.usernames;

/**
 * The only function of a NameStylizer object is to
 * take an array of String tokens and combine them using a series of rules
 */
public interface NameStylizer {
	/**
	 * Combines the tokens into a regular username
	 *
	 * @param tokens Original string divided from spaces, hyphens, etc.
	 * @return The final username
	 */
	String stylize(String[] tokens);
}
