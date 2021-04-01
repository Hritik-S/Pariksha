package controllers;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminHomeScreenFXMLController implements Initializable {
    @FXML
    private JFXTabPane adminTabPane;
    @FXML
    private Tab addQuizTab;
    @FXML
    private Tab addStudentTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Parent node= FXMLLoader.load(getClass().getResource("/fxml/addQuizFXML.fxml"));
            addQuizTab.setContent(node);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
