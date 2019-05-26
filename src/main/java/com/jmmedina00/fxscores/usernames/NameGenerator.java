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
	private NameStylizer stylizers[] = {
			new NameStylizer() {
				@Override
				/* This one capitalises every single token */
				public String stylize(String[] tokens) {
					String username = "";

					for (String singleToken : tokens) {
						if (singleToken.length() > 1) {
							String transformedToken = singleToken.substring(0, 1).toUpperCase() +
									singleToken.substring(1);
							username += transformedToken;
						}
					}

					return username;
				}
			},
			new NameStylizer() {
				@Override
				/* This one splits the group of tokens in two with an underscore,
				   and the first letter of each group might come out capitalised
				   Example: "adj ective composed noun" could become
				   "Adjective_Composednoun_" or "adjective_composednoun_"
				*/
				public String stylize(String[] tokens) {
					String username = "";
					int capitalise = (int) (Math.random() * 2);
					int half = tokens.length / 2;

					for (int x = 0; x < tokens.length; x++) {
						if (x == 0 || x == half) {
							username += "_";
							if (capitalise == 1 && tokens[x].length() > 1) {
								username += tokens[x].substring(0, 1).toUpperCase() +
										tokens[x].substring(1);
							} else {
								username += tokens[x];
							}
						} else {
							username += tokens[x];
						}
					}

					return username.replaceFirst("^_", "") + "_";
				}
			},
			new NameStylizer() {
				@Override
				/* This one combines all tokens, capitalising the first and
				   and last letter of the resulting string, and replacing several
				   letters.
				*/
				public String stylize(String[] tokens) {
					String old = "aeiots", replacing = "431075",
							username = "";
					for (String singleToken : tokens) {
						username += singleToken;
					}

					username = username.substring(0, 1).toUpperCase()
							+ username.substring(1, username.length() - 1).toLowerCase()
							+ username.substring(username.length() - 1).toUpperCase();

					for (int x = 0; x < old.length(); x++) {
						username = username.replace(
								old.charAt(x),
								replacing.charAt(x)
						);
					}

					return username + "_"; //The underscore is for the possible year
				}
			},
			new NameStylizer() {
				@Override
				/* This one puts every even character (starting to count from 0)
				   in uppercase and puts the rest in lower case.
				*/
				public String stylize(String[] tokens) {
					String combined = "", username = "";
					for (String singleToken : tokens) {
						combined += singleToken;
					}

					for (int x = 0; x < combined.length(); x++) {
						if (x % 2 == 0) {
							username += Character.toUpperCase(combined.charAt(x));
						} else {
							username += Character.toLowerCase(combined.charAt(x));
						}
					}

					return username;
				}
			}
	};

	public NameGenerator() throws JWNLException {
		dictionary = Dictionary.getDefaultResourceInstance();
	}

	public String createUserName() throws JWNLException {
		IndexWord adjective = dictionary.getRandomIndexWord(POS.ADJECTIVE),
				noun = dictionary.getRandomIndexWord(POS.NOUN);
		String complete = adjective.getLemma() + " " + noun.getLemma();
		String tokens[] = complete.split("[ '\\-]");

		String stylized = stylizers[(int) (Math.random() * stylizers.length)]
				.stylize(tokens);
		int addYear = (int) (Math.random() * 2);

		if (addYear == 1) {
			//The added year (if any) oscillates between 1990 and 2005
			stylized += String.valueOf((int) ((Math.random() * 16) + 1990));
		} else {
			/* Some stylizers put an underscore for the year
			   When there's no year, that underscore is useless,
			   so it has to go */
			stylized = stylized.replaceFirst("_$", "");
		}
		return stylized;
	}
}
