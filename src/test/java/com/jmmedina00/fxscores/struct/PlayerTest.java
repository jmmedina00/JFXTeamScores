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
}