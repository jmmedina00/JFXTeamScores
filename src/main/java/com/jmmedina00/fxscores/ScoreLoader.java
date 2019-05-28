package com.jmmedina00.fxscores;

import com.jmmedina00.fxscores.struct.Player;
import com.jmmedina00.fxscores.struct.Team;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Controller class for the main form.
 */
public class ScoreLoader {
	private File playerData = null, teamsNames = null;

	@FXML
	private Button btnShowAll, btnLoadPlayers, btnLoadTeamNames, btnLoadFiles,
	btnGenerate;

	@FXML
	private TextField txtPathPlayers, txtPathTeamNames;

	@FXML
	private Label lblStatus;

	@FXML
	private TableView<Team> tableTeams;

	@FXML
	private TableView<Player> tablePlayers;

	@FXML
	private TableColumn<Team, String> tcTeamName, tcTeamPlayers, tcTeamAverage,
	tcTeamScore;

	@FXML
	private TableColumn<Player, String> tcPlayerName, tcGlobalPosOrTeam, tcPlayerScore;

	public void loadPlayers() {
		teamsNames = openDialog(txtPathPlayers, false);
		boolean disableButtons = (playerData == null || teamsNames == null);

		btnLoadFiles.setDisable(disableButtons);
		btnGenerate.setDisable(disableButtons);
	}

	public void loadTeamNames() {
		playerData = openDialog(txtPathTeamNames, true);
		boolean disableButtons = (playerData == null || teamsNames == null);

		btnLoadFiles.setDisable(disableButtons);
		btnGenerate.setDisable(disableButtons);
	}

	private File openDialog(TextField pathTarget, boolean nonExistent) {
		File selected = null;
		FileChooser fileChooser = new FileChooser();
		Window window = pathTarget.getScene().getWindow();

		if (nonExistent) {
			fileChooser.setTitle("Open file...");
			selected = fileChooser.showOpenDialog(window);
		} else {
			fileChooser.setTitle("Open or save file...");
			selected = fileChooser.showSaveDialog(window);
		}

		if (selected != null) {
			pathTarget.setText(selected.getAbsolutePath());
		} else {
			pathTarget.setText("");
		}

		return selected;
	}
}
