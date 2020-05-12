package ce1002.Final.s107502531.controllers;

import java.io.IOException;

import ce1002.Final.s107502531.Final;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoleController {
	@FXML
	private Label _1P, _2P;
	private MediaPlayer select1P;
	private MediaPlayer select2P;
	private boolean lock_1 = false, lock_2 = false;
	public static int _1PVisible = 0, _2PVisible = 0;

	public void onMenuPressed() throws IOException {
		Final.mainStage.setScene(Final.mainScene);
		Final.mainStage.setFullScreen(true);
	}

	public EventHandler<KeyEvent> onKeyPressed = (e) -> { // keyboard press
		if ((e.getCode() == KeyCode.A || e.getCode() == KeyCode.D) && lock_1 == false) { // 1P choose role
			int x = GridPane.getColumnIndex(_1P);
			if (e.getCode() == KeyCode.A)
				x -= 1;
			if (e.getCode() == KeyCode.D)
				x += 1;
			x = Math.max(Math.min(x, 4), 0);
			GridPane.setColumnIndex(_1P, x);
		}
		if ((e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) && lock_2 == false) { // 2P choose role
			int y = GridPane.getColumnIndex(_2P);
			if (e.getCode() == KeyCode.LEFT)
				y -= 1;
			if (e.getCode() == KeyCode.RIGHT)
				y += 1;
			y = Math.max(Math.min(y, 4), 0);
			GridPane.setColumnIndex(_2P, y);
		}
		if (e.getCode() == KeyCode.J) { // 1P lock
			if (GridPane.getColumnIndex(_1P) != GridPane.getColumnIndex(_2P)) {
				Media media = new Media(getClass().getResource("../resource/select.mp3").toString());
				select1P = new MediaPlayer(media);
				select1P.play();
				_1P.setUnderline(true);
				lock_1 = true;
			}
		}
		if (e.getCode() == KeyCode.NUMPAD1) { // 2P lock
			if (GridPane.getColumnIndex(_1P) != GridPane.getColumnIndex(_2P)) {
				Media media = new Media(getClass().getResource("../resource/select.mp3").toString());
				select2P = new MediaPlayer(media);
				select2P.play();
				_2P.setUnderline(true);
				lock_2 = true;
			}
		}
		if (lock_1 == true && lock_2 == true) { // enter the play scene
			try {
				_1PVisible = GridPane.getColumnIndex(_1P);
				_2PVisible = GridPane.getColumnIndex(_2P);
				enterGame();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};

	public void enterGame() throws IOException { // enter the play scene
		if (MainController.gameMode == 1 || MainController.gameMode == 2) {
			FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/PlayWindow.fxml"));
			Parent Play = loadder.load();
			Scene PlayScene = new Scene(Play);
			PlayController PlayCtrl = loadder.getController();
			PlayScene.setOnKeyPressed(PlayCtrl.OnKeyPressed);
			PlayScene.setOnKeyReleased(PlayCtrl.OnKeyReleased);
			Final.mainStage.setScene(PlayScene);
			Final.mainStage.setFullScreen(true);
		} else if (MainController.gameMode == 3) {
			FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/BossWindow.fxml"));
			Parent Boss = loadder.load();
			Scene BossScene = new Scene(Boss);
			BossController BossCtrl = loadder.getController();
			BossScene.setOnKeyPressed(BossCtrl.OnKeyPressed);
			BossScene.setOnKeyReleased(BossCtrl.OnKeyReleased);
			Final.mainStage.setScene(BossScene);
			Final.mainStage.setFullScreen(true);
		}
	}
}
