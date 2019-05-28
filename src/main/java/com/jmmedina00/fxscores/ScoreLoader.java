package com.jmmedina00.fxscores;

import com.jmmedina00.fxscores.struct.Player;
import com.jmmedina00.fxscores.struct.Team;
import com.jmmedina00.fxscores.usernames.NameGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

import net.sf.extjwnl.*;

/**
 * Controller class for the main form.
 */
public class ScoreLoader {
	private NameGenerator nameGenerator = null;
	private File playerData = null, teamNames = null;

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

	public void initialize() {
		//NameGenerator objects needed to load the default dictionary.
		try {
			nameGenerator = new NameGenerator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadPlayers() {
		teamNames = openDialog(txtPathPlayers, false);
		boolean disableButtons = (playerData == null || teamNames == null);

		btnLoadFiles.setDisable(disableButtons);
		btnGenerate.setDisable(disableButtons);
	}

	public void loadTeamNames() {
		playerData = openDialog(txtPathTeamNames, true);
		boolean disableButtons = (playerData == null || teamNames == null);

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

	/* INCOMPLETE */
	public void generate() {
		int teams = 0;
		int players = (int) ((Math.random() * 26) + 50);
		Scanner teamCounter = null;
		Writer playerDataWriter = null;
		Serializer serializer = CsvIOFactory.createFactory(Player.class).
				createSerializer();

		try {
			teamCounter = new Scanner(teamNames);
			while (teamCounter.hasNext()) {
				String notUsedTeamName = teamCounter.nextLine();
				teams++;
			}

			playerDataWriter = new FileWriter(playerData);
			serializer.open(playerDataWriter);

			for (int x = 0; x < players; x++) {
				Player player = new Player();
			}
		} catch (Exception e) {
			e.printStackTrace();
			lblStatus.setText(e.getMessage());
		} finally {
			if (teamCounter != null) {
				teamCounter.close();
			}
		}
	}
}
