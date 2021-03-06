package com.jmmedina00.fxscores.struct;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class for teams. As it would have a SortedSet as an attribute, it was preferred to
 * make this class extend TreeSet.
 */
public class Team extends TreeSet<Player> implements Comparable<Team> {
	private String name;

	/**
	 * Default constructor for a team. Teams are only getting their names
	 * via a TXT file, not even a CSV.
	 *
	 * @param name The name the team is going to have. It cannot be modified later.
	 */
	public Team(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public int getTotalScore() {
		int total = 0;

		//Interesting! You can make a set iterate itself within a method.
		for (Player player : this) {
			total += player.getScore();
		}

		return total;
	}

	public double getAverageScore() {
		double average = (double) getTotalScore() / (double) size();
		return average;
	}

	/**
	 * Teams are compared only by score reversely, two teams with the same name
	 * aren't expected.
	 *
	 * @param team Another team to be compared.
	 * @return The teams' score difference multiplied by -1
	 */
	@Override
	public int compareTo(Team team) {
		return -(getTotalScore() - team.getTotalScore());
	}
}
