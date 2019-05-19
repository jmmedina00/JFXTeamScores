package com.jmmedina00.fxscores.struct;

import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class PlayerTest {
	@Test
	public void playerSortingTest() {
		//Automated tests are best done thousands of times, aren't they?
		for (int i = 0; i < 100; i++) {
			SortedSet<Player> sortedSet = new TreeSet<>();

			for (int x = 0; x < 25; x++) {
			/* Getting a name by using the alphabet and character codes
			   Example: 0 -> A, 1 -> B, 2 -> C, ...
			   Used in order to test the sorting by name as well.
			*/
				String name = String.valueOf((char) 65 + x);
				int score = (int) ((Math.random() * 300) + 1);

				Player player = new Player();
				player.setName(name);
				player.setScore(score);
				sortedSet.add(player);
			}

			Player previous = null;
			for (Player player : sortedSet) {
				if (previous != null) {
					//Testing sorting by score reversely
					assertTrue(previous.getScore() >= player.getScore());

					if (previous.getScore() == player.getScore()) {
						//No players with the same name are expected here.
						assertTrue(previous.getName().compareTo(player.getName()) < 0);
					}

					previous = player;
				} else {
					previous = player;
				}
			}
		}

	}
}