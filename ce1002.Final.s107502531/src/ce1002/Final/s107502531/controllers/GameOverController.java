package ce1002.Final.s107502531.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ce1002.Final.s107502531.Final;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameOverController implements Initializable {
	@FXML
	private ImageView _fire, _role1, _role2, _role3, _role4, _role5;
	@FXML
	private Label _claim;
	private MediaPlayer win;

	public void onPlayAgainPressed() throws IOException {
		Final.mainStage.setScene(Final.mainScene);
		Final.mainStage.setFullScreen(true);
	}

	public void onExitPressed() {
		Final.mainStage.close();
	}

	public void onPlayAgainPressedEnter(KeyEvent e) throws IOException { // press enter to play again
		if (e.getCode() == KeyCode.ENTER)
			onPlayAgainPressed();
	}

	public void onExitPressedEnter(KeyEvent e) throws IOException { // press enter to exit
		if (e.getCode() == KeyCode.ENTER)
			onExitPressed();
	}

	public void initialize(URL arg0, ResourceBundle arg1) { // initialize
		if (PlayController.winner == 1 || BossController.bossWinner == 1) {
			Media media = new Media(getClass().getResource("../resource/win.mp3").toString());
			win = new MediaPlayer(media);
			win.play();
			if (BossController.bossWinner == 1)
				_claim.setText("You     Win");
			if (RoleController._1PVisible == 0) {
				_role1.setVisible(true);
				_role1.setLayoutX(130);
				_role1.setLayoutY(610);
			} else if (RoleController._1PVisible == 1) {
				_role2.setVisible(true);
				_role2.setLayoutX(130);
				_role2.setLayoutY(610);
			} else if (RoleController._1PVisible == 2) {
				_role3.setVisible(true);
				_role3.setLayoutX(130);
				_role3.setLayoutY(610);
			} else if (RoleController._1PVisible == 3) {
				_role4.setVisible(true);
				_role4.setLayoutX(130);
				_role4.setLayoutY(610);
			} else if (RoleController._1PVisible == 4) {
				_role5.setVisible(true);
				_role5.setLayoutX(1190);
				_role5.setLayoutY(610);
			}
		} else if (PlayController.winner == 2 || BossController.bossWinner == 2) {
			Media media = new Media(getClass().getResource("../resource/win.mp3").toString());
			win = new MediaPlayer(media);
			win.play();
			if (PlayController.winner == 2)
				_claim.setText("2P     Winner");
			if (BossController.bossWinner == 2)
				_claim.setText("You     Win");
			if (RoleController._2PVisible == 0) {
				_role1.setVisible(true);
				_role1.setLayoutX(1190);
				_role1.setLayoutY(610);
			} else if (RoleController._2PVisible == 1) {
				_role2.setVisible(true);
				_role2.setLayoutX(1190);
				_role2.setLayoutY(610);
			} else if (RoleController._2PVisible == 2) {
				_role3.setVisible(true);
				_role3.setLayoutX(1190);
				_role3.setLayoutY(610);
			} else if (RoleController._2PVisible == 3) {
				_role4.setVisible(true);
				_role4.setLayoutX(1190);
				_role4.setLayoutY(610);
			} else if (RoleController._2PVisible == 4) {
				_role5.setVisible(true);
				_role5.setLayoutX(1190);
				_role5.setLayoutY(610);
			}
		} else if (PlayController.winner == 3 || BossController.bossWinner == 3) {
			Media media = new Media(getClass().getResource("../resource/win.mp3").toString());
			win = new MediaPlayer(media);
			win.play();
			if (PlayController.winner == 3)
				_claim.setText("Tie     Game");
			if (BossController.bossWinner == 3)
				_claim.setText("You     Win");
			if (RoleController._1PVisible == 0) {
				_role1.setVisible(true);
				_role1.setLayoutX(130);
				_role1.setLayoutY(610);
			} else if (RoleController._1PVisible == 1) {
				_role2.setVisible(true);
				_role2.setLayoutX(130);
				_role2.setLayoutY(610);
			} else if (RoleController._1PVisible == 2) {
				_role3.setVisible(true);
				_role3.setLayoutX(130);
				_role3.setLayoutY(610);
			} else if (RoleController._1PVisible == 3) {
				_role4.setVisible(true);
				_role4.setLayoutX(130);
				_role4.setLayoutY(610);
			} else if (RoleController._1PVisible == 4) {
				_role5.setVisible(true);
				_role5.setLayoutX(130);
				_role5.setLayoutY(610);
			}
			if (RoleController._2PVisible == 0) {
				_role1.setVisible(true);
				_role1.setLayoutX(1190);
				_role1.setLayoutY(610);
			} else if (RoleController._2PVisible == 1) {
				_role2.setVisible(true);
				_role2.setLayoutX(1190);
				_role2.setLayoutY(610);
			} else if (RoleController._2PVisible == 2) {
				_role3.setVisible(true);
				_role3.setLayoutX(1190);
				_role3.setLayoutY(610);
			} else if (RoleController._2PVisible == 3) {
				_role4.setVisible(true);
				_role4.setLayoutX(1190);
				_role4.setLayoutY(610);
			} else if (RoleController._2PVisible == 4) {
				_role5.setVisible(true);
				_role5.setLayoutX(1190);
				_role5.setLayoutY(610);
			}
		} else if (BossController.bossWinner == 4) {
			Media media = new Media(getClass().getResource("../resource/lose.mp3").toString());
			win = new MediaPlayer(media);
			win.play();
			_claim.setText("You     Lose");
			_fire.setImage(new Image(getClass().getResource("../resource/boss_win.jpg").toString()));
		}
	}

}
