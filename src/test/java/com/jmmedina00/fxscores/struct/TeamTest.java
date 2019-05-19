package com.jmmedina00.fxscores.struct;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

	@Test
	public void calculationTest() {
		for (int i = 0; i < 50; i++) {
			int players = 100, score = 0;
			Team team = new Team("RandomTeam");

			for (int x = 0; x < players; x++) {
				int playerScore = (int) (Math.random() * 350);
				score += playerScore;

				Player player = new Player();
				player.setScore(playerScore);

				/* TreeSet doesn't add objects which are the same.
				   This name randomizing avoids that behaviour from triggering.
				*/
				player.setName(String.valueOf(Math.random() * Double.MAX_VALUE));
				team.add(player);
			}

			assertEquals(score, team.getTotalScore());
			assertEquals((double) score / (double) players,
					team.getAverageScore(), 0.001);
		}
	}
}