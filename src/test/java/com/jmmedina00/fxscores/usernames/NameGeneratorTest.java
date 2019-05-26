package com.jmmedina00.fxscores.usernames;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameGeneratorTest {
	@Test
	public void test() {
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
}
