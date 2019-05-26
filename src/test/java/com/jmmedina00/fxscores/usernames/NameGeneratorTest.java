package com.jmmedina00.fxscores.usernames;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class NameGeneratorTest {
	/*
	@Test
	public void test() {
		//Original test used to check WordNet library was working
		NameGenerator ng = null;
		try {
			ng = new NameGenerator();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(ng.getRandomWord(POS.ADJECTIVE));
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
	}
	*/

	@Test
	public void uniqueNamesTest() {
		//This test was made before adding the stylizers. It should still work
		try {
			NameGenerator ng = new NameGenerator();
			for (int x = 0; x < 150; x++) {
				int uniqueNames = (int) (Math.random() * 200);
				/* A TreeSet doesn't add and object to itself if it finds
				   that object "is already in there" */
				SortedSet<String> names = new TreeSet<>();

				for (int y = 0; y < uniqueNames; y++) {
					String name = ng.createUserName();
					System.out.println(name);
					names.add(name);
				}

				/* This assertion tries to check all added names were unique */
				assertEquals(uniqueNames, names.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
