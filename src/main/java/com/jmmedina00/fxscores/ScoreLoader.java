package com.jmmedina00.fxscores;

import com.jmmedina00.fxscores.struct.Player;
import com.jmmedina00.fxscores.struct.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller class for the main form.
 */
public class ScoreLoader {
	@FXML
	private Button btnShowAll, btnLoadPlayers, btnLoadTeamNames, btnLoadFiles,
	btnGenerate;

	@FXML
	private TextField txtPathPlayers, txtPathTeamNames;

	@FXML
	private TableView<Team> tableTeams;

	@FXML
	private TableView<Player> tablePlayers;

	@FXML
	private TableColumn<Team, String> tcTeamName, tcTeamPlayers, tcTeamAverage,
	tcTeamScore;

	@FXML
	private TableColumn<Player, String> tcPlayerName, tcGlobalPosOrTeam, tcPlayerScore;
}
