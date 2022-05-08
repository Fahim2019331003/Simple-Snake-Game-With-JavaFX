module app.snakegame2screen {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.snakegame2screen to javafx.fxml;
    exports app.snakegame2screen;
}