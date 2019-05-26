package com.jmmedina00.fxscores.struct;

import net.sf.jsefa.csv.annotation.CsvField;

/**
 * Class for Players in each team. As teams will be placed in a list,
 * each player gets their team no. as an attribute, in order to be able to find it quicker.
 *
 * This class cannot have a custom constructor since it's going to be
 * used in conjunction with JSefa.
 */
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
}
