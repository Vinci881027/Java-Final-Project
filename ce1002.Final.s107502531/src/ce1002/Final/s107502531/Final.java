/*
 * Final Project
 * Course: ce1002
 * Name: ÁÂ¤å´Ñ
 * Student ID: 107502531
 */
package ce1002.Final.s107502531;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Final extends Application {
	public static Stage mainStage;
	public static Scene mainScene;

	@Override
	public void start(Stage mainStage) throws IOException {
		Final.mainStage = mainStage;
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("views/MainWindow.fxml"));
		Parent main = loadder.load();
		mainScene = new Scene(main);
		mainStage.setFullScreen(true);
		mainStage.setTitle("Äúµ§¤p·s");
		mainStage.setScene(mainScene);
		Final.mainStage.setResizable(false);
		mainStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
