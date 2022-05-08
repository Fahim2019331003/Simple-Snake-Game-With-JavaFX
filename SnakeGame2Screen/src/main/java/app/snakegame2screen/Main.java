package app.snakegame2screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            scene.getStylesheets().add(getClass().getResource("homeScreen.css").toExternalForm());
            stage.setScene(scene);

            stage.initStyle(StageStyle.UTILITY);
            stage.setOnCloseRequest((event) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.show();
            Stage primaryStage=stage;

            primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
                System.out.println("Key pressed: " + event.toString());

                System.out.println(event.getCode().getCode());
            });


        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}