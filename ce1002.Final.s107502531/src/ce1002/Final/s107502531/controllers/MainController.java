package ce1002.Final.s107502531.controllers;

import java.io.IOException;

import ce1002.Final.s107502531.Final;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {
	public static int gameMode = 0;

	public void onKOPressed() throws IOException {
		gameMode = 1;
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/RoleWindow.fxml"));
		Parent Role = loadder.load();
		Scene RoleScene = new Scene(Role);
		RoleController RoleCtrl = loadder.getController();
		RoleScene.setOnKeyPressed(RoleCtrl.onKeyPressed);
		Final.mainStage.setScene(RoleScene);
		Final.mainStage.setFullScreen(true);
	}

	public void on60SecPressed() throws IOException {
		gameMode = 2;
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/RoleWindow.fxml"));
		Parent Role = loadder.load();
		Scene RoleScene = new Scene(Role);
		RoleController RoleCtrl = loadder.getController();
		RoleScene.setOnKeyPressed(RoleCtrl.onKeyPressed);
		Final.mainStage.setScene(RoleScene);
		Final.mainStage.setFullScreen(true);
	}

	public void onBossPressed() throws IOException {
		gameMode = 3;
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/RoleWindow.fxml"));
		Parent Role = loadder.load();
		Scene RoleScene = new Scene(Role);
		RoleController RoleCtrl = loadder.getController();
		RoleScene.setOnKeyPressed(RoleCtrl.onKeyPressed);
		Final.mainStage.setScene(RoleScene);
		Final.mainStage.setFullScreen(true);
	}

	public void onExitPressed() {
		Final.mainStage.close();
	}

	public void onKOPressedEnter(KeyEvent e) throws IOException { // press enter to start
		if (e.getCode() == KeyCode.ENTER)
			onKOPressed();
	}

	public void on60SecPressedEnter(KeyEvent e) throws IOException { // press enter to start
		if (e.getCode() == KeyCode.ENTER)
			on60SecPressed();
	}

	public void onBossPressedEnter(KeyEvent e) throws IOException { // press enter to start
		if (e.getCode() == KeyCode.ENTER)
			onBossPressed();
	}

	public void onExitPressedEnter(KeyEvent e) throws IOException { // press enter to exit
		if (e.getCode() == KeyCode.ENTER)
			onExitPressed();
	}
}
