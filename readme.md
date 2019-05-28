# JFXTeamScores

Personal Java project that aims both practising JavaFX table handling and
trying JSefa library. It also happens to use extJWNL, a library for using
WordNet dictionaries, as a part of CSV random generation.

The current design of the UI consists of two tables, one for players, who
would have earned a score in a not specific game, and another one for the
teams players are part of:
* The ***team table*** would show the **team's name**, the **number of players**
it has (due to the randomness of the CSV generation, teams aren't likely
to have the same number of players), their **average score**, and 
their **total score**.

* The ***player table*** would show **each player's name**, its ***global* 
position**, obtained from combining all the players of all
teams, and its **individual score**.

The UI also has a button to show all players in the player table, in
which case the **global position** would be switched to **the team's name that
player is in**. For loading both files, the player CSV file (in which
each line would be `player name;team number;score`), and the team name
file (with one name per line), a button for loading files and a text
field for showing the absolute path of each selected file is provide.
Along with that are including the file loader button and the random
generation button, which would also open the randomly created file.

## Classes

For the internal data structure, there are two classes: **Player** and 
**Team**:

### Player (implements Comparable\<Player\>)

This class contains the three expected attributes for a player: its 
**name**, its **team's number** and its **score**. Because of the use
of JSefa library, this class had to have all getters and setters and no
constructor. It also has the following methods:

* int compareTo(Player player) /* Players are ordered by score reversely,
and then alphabetically */
* int getGlobalPosition(List<Team> teamList) /* Gets the global position
column value, by finding itself in the SortedSet created by 
combining all players in the teams */
 
### Team (extends TreeSet\<Player> implements Comparable\<Team\>)
 
As having the players ordered by the criteria specified in their class'
compareTo method would be the most convenient thing for both showing
team players and all players, and since a Team class would behave
exactly like a SortedSet\<Player\>, it was decided to make this class
a TreeSet's subclass. This way, no methods dealing with adding and 
removing players are needed to be implemented, the list of implemented
methods being reduced to:
 
* int getTotalScore() /* Iterates itself to get the sum of all players
scores */
* double getAverageScore() /* As dividing an integer (for example,
the total score) is likely to return a decimal number, 
it is preferred to return it as is, since there are no constraints on
how large a number can be for the UI */
* int compareTo(Team team) /* This methods only compares teams by score
(reversely), since no name is expected for more than one team */

---

For the name generation feature, the WordNet library comes into play.
It's used to grab a random adjective and a random noun and give them
a funky styling in order to try to create believable usernames. All of 
this is accomplished by used the following two classes:

### NameStylizer (interface)

This interface is only used for being able to store all tweaking 
algorithms as objects, it only has one method:

* String stylize(String[] tokens) /* It would take the array of tokens
and merge them together in an arbitrary way */
 
### NameGenerator

It takes care of getting the needed words and selecting a random
algorithm (in an array of NameStylizer objects )for generating an
username (it sometimes includes a birth year, too) in the method
**createUserName()**. As one of its attributes, the WordNet
dictionary, could throw an exception, an instance of this
class is required to operate.

## Problems

* It was first thought to use [SpinXO](http://spinxo.com/) 
for handling the username generation. However, it always returns the same
usernames inside the document the website generated, plus finding the
way to make its username generation script work separately from the website is almost impossible,
since the data it generates seem to be sent corrupted or encrypted if
the request isn't made correctly. That's the reason that first idea
was dismissed.

* JavaFX, when run from Maven in Java 11, requires some configuration
it's only been possible to provide by using a modular configuration,
which requires a module-info.java file in the source root directory.
A way of making WordNet 3.1 data library work while using that is
yet to be found, which is the reason its related classes have been
successfully tested with JUnit 4.12, yet they can't be used in
the final application. An
[issue has been posted in extJWNL's repo](https://github.com/extjwnl/extjwnl/issues/29)
in order to find some help solving this problem.