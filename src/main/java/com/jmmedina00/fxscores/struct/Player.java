package com.jmmedina00.fxscores.struct;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class for Players in each team. As teams will be placed in a list,
 * each player gets their team no. as an attribute, in order to be able to find it quicker.
 *
 * This class cannot have a custom constructor since it's going to be
 * used in conjunction with JSefa.
 */
@CsvDataType()
public class Player implements Comparable<Player> {
	@CsvField(pos = 1)
	private String name;

	@CsvField(pos = 2)
	private int team;

	@CsvField(pos = 3)
	private int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Players are to be ordered descendingly by score (from most points to least).
	 * If two players get exactly the same score, they are ordered by name.
	 *
	 * @param player
	 * @return
	 */
	@Override
	public int compareTo(Player player) {
		int comparison = -(score - player.getScore()); //Reversing score order

		if (comparison == 0) {
			comparison = name.compareTo(player.getName());
		}

		return comparison;
	}

	/**
	 * Finds itself in a generated list of all players of all given teams,
	 * then returns its position in it (starting by 1).
	 *
	 * @param teamList A list with all the teams
	 * @return Its position between all the players, 0 if it couldn't find itself.
	 */
	public int getGlobalPosition(List<Team> teamList) {
		SortedSet<Player> allPlayers = new TreeSet<>();
		for (Team team : teamList) {
			allPlayers.addAll(team);
		}

		int position = -1;
		if (allPlayers.contains(this)) {
			boolean searching = true;
			Iterator<Player> iterator = allPlayers.iterator();

			while (iterator.hasNext() && searching) {
				Player player = iterator.next();
				searching = (compareTo(player) != 0);
				position++;
			}
		}

		return position + 1;
	}
}
