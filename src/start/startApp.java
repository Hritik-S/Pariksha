package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Quiz;
import models.Students;
import models.questions;

import java.sql.SQLException;

public class startApp extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        createTables();
        Parent root= FXMLLoader.load(getClass().getResource("/client/AdminLogin.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    private void createTables() throws SQLException, ClassNotFoundException {
        Quiz.createTable();
        questions.createTable();
        Students.createTable();

    }
}
