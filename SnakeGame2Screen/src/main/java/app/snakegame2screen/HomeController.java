package app.snakegame2screen;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeController {
    @FXML
    private Button newGameButton;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane aPane;



    Stage stage;


    public void exit(ActionEvent event)
    {
        stage= (Stage) aPane.getScene().getWindow();
        stage.close();

    }
    public void newGame(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gameScreen.css").toExternalForm());
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        gameScreenController GameScreen=fxmlLoader.getController();
        GameScreen.init();







    }
}