package com.jmmedina00.fxscores.struct;

import com.jmmedina00.fxscores.usernames.NameGenerator;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

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

	@Test
	public void csvTest() {
		NameGenerator generator = null;
		try {
			generator = new NameGenerator();
			Writer writer = new StringWriter();
			ArrayList<Player> players = new ArrayList<>();

			Serializer serializer = CsvIOFactory.createFactory(Player.class)
					.createSerializer();
			serializer.open(writer);

			for (int x = 0; x < 25; x++) {
				Player player = new Player();
				player.setName(generator.createUserName());
				player.setTeam(2);
				player.setScore((int) (Math.random() * 250));

				serializer.write(player);
			}

			String result = writer.toString();
			serializer.close(true);
			Reader reader = new StringReader(result);

			Deserializer deserializer = CsvIOFactory.createFactory(Player.class)
					.createDeserializer();
			deserializer.open(reader);

			while (deserializer.hasNext()) {
				players.add(deserializer.next());
			}

			deserializer.close(true);
			assertEquals(25, players.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void globalPositionTest() {
		List<Team> teams = Arrays.asList(
				new Team("One"),
				new Team("Two"),
				new Team("Three"),
				new Team("Four")
				);
		//Players will be also stored in order to get to pick one randomly.
		ArrayList<Player> players = new ArrayList<>();

		for (int x = 30; x >= 1; x--) {
			Player player = new Player();
			player.setName(String.valueOf(Math.random() * Double.MAX_VALUE));
			player.setTeam((int) (Math.random() * 4));
			player.setScore(x);

			teams.get(player.getTeam()).add(player);
			players.add(player);
		}

		/*
		   Now, scores should be like this (reduced in order to show it more quickly):
		   1. 3
		   2. 2
		   3. 1

		   So the position they should get is: (number of players) - (number of points) + 1
		*/

		for (int x = 0; x < 100; x++) {
			int selectedIndex = (int) (Math.random() * 30);
			Player player = players.get(selectedIndex);

			assertEquals(30 - player.getScore() + 1,
					player.getGlobalPosition(teams));
		}

	}
}