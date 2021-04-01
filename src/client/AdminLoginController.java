package client;

import constants.adminEmailPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private PasswordField studentPassword;
    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button adminLoginButton;
    @FXML
    private TextField studentEmail;
    @FXML
    private Button studentLoginButton;

    public void loginAdmin(ActionEvent actionEvent) throws IOException {
        String email=adminEmail.getText();
        String password=adminPassword.getText();
        if(email.trim().equalsIgnoreCase(adminEmailPassword.email) && password.trim().equals(adminEmailPassword.password))
        {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/adminHomeScreenFXML.fxml"));
            Stage stage=(Stage)studentPassword.getScene().getWindow();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            System.out.println("Login Successful");
        }
        else
        {
            System.out.println("Login Failed");
        }
    }

    public void loginStudent(ActionEvent actionEvent) {
        System.out.println("AdminLoginController.loginStudent()");
    }
}
