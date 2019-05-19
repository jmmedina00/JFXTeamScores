package com.jmmedina00.fxscores.struct;

/**
 * Class for Players in each team. As teams will be placed in a list,
 * each player gets their team no. as an attribute, in order to be able to find it quicker.
 *
 * This class cannot have a custom constructor since it's going to be
 * used in conjunction with JSefa.
 */
public class Player implements Comparable<Player> {
	private String name;
	private int team, score;

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
