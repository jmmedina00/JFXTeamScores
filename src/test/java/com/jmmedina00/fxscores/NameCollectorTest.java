package com.jmmedina00.fxscores;

import org.junit.Test;

import static org.junit.Assert.*;

public class NameCollectorTest {

	@Test
	public void test() {
		try {
			System.out.println(NameCollector.getNames());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}