package controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import models.Students;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminStudentTab implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private VBox formContainer;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField mobileNumber;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private Button saveButton;
    @FXML
    private TableView studentTable;
    @FXML
    private TableColumn studentIdColumn;
    @FXML
    private TableColumn firstNameColumn;
    @FXML
    private TableColumn lastNameColumn;
    @FXML
    private TableColumn mobileNumberColumn;
    @FXML
    private TableColumn genderColumn;

    // non fxml variables
    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAll();
        radioButtonSetup();
    }

    private void radioButtonSetup(){
        this.male.setSelected(true);
        this.male.setToggleGroup(toggleGroup);
        this.female.setToggleGroup(toggleGroup);
    }

    private void initAll(){
        toggleGroup=new ToggleGroup();

    }

    private void resetForm(){
        this.password.clear();
        this.email.clear();
        this.firstName.clear();
        this.lastName.clear();
        this.mobileNumber.clear();
    }

    public void saveStudent(ActionEvent actionEvent) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:[a-zA-Z]{2}|aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)$");

        System.out.println("Save Student Button clicked !!!");
        String firstName=this.firstName.getText().trim();
        String lastName=this.lastName.getText().trim();
        String mobileNumber=this.mobileNumber.getText().trim();
        String email=this.email.getText().trim();
        String password=this.password.getText().trim();
        Character gen='M';
        JFXRadioButton gender=(JFXRadioButton) toggleGroup.getSelectedToggle();
        String message="null";
        if(firstName.length()<1){
            message="Please enter first name";
        }
        else if(lastName.length()<1){
            message="please Enter last Name";
        }
        else if(!p.matcher(email).matches()){
            message="Enter valid Email";
        }
        else if(password.length()<6){
            message="Password must be more than 6 char long";
        }
        else if(mobileNumber.length()<10){
            message="Please enter valid mobile number";
        }
        if(message!="null"){
            Notifications.create().title("Fill Student Form correctly !!").text(message).showError();
            return ;
        }

        // save code here if not error
        if(gender!=null)
        {
            if(gender==female){
                gen='F';
            }
        }
        Students s = new Students(
                firstName ,
                lastName ,
                mobileNumber ,
                gen ,
                email ,
                password
        );

        s=s.save();
        if(s!=null){
            Notifications.create().text("Student registered").title("Success").showInformation();
            this.resetForm();
        }
        else
        {
            Notifications.create().text("Student registered failed").title("Failure").showError();
        }
    }
}
