package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.net.URL;
import java.util.ResourceBundle;

public class addQuizFXMLController implements Initializable {
    @FXML
    private JFXTextField quizTitle;
    @FXML
    private Button saveQuizTitleButton;
    @FXML
    private JFXTextArea question;
    @FXML
    private JFXTextField option1;
    @FXML
    private JFXTextField option2;
    @FXML
    private JFXTextField option3;
    @FXML
    private JFXTextField option4;
    @FXML
    private JFXRadioButton option1radio;
    @FXML
    private JFXRadioButton option2radio;
    @FXML
    private JFXRadioButton option3radio;
    @FXML
    private JFXRadioButton option4radio;
    @FXML
    private JFXButton addNextQuestion;
    @FXML
    private JFXButton submitQuiz;

    private ToggleGroup radioGroup;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioButtonSetup();
    }

    private void radioButtonSetup(){
        radioGroup=new ToggleGroup();
        option1radio.setToggleGroup(radioGroup);
        option2radio.setToggleGroup(radioGroup);
        option3radio.setToggleGroup(radioGroup);
        option4radio.setToggleGroup(radioGroup);
    }
    @FXML
    private void setQuizTitle(ActionEvent actionEvent) {
        String title=quizTitle.getText();
        if(title.trim().isEmpty()){
            Notifications notify= Notifications.create();
            notify.text("Enter valid quiz title");
            notify.title("Quiz Title");
            notify.showError();
            notify.position(Pos.TOP_CENTER);
        }
        else{
            quizTitle.setEditable(false);
        }
    }

    private boolean validateFields(){
        String qu=this.question.getText();
        String op1=this.option1.getText();
        String op2=this.option2.getText();
        String op3=this.option3.getText();
        String op4=this.option4.getText();
        Toggle obj=radioGroup.getSelectedToggle();
        if(qu.trim().isEmpty() || op1.trim().isEmpty() || op2.trim().isEmpty() || op3.trim().isEmpty() || op4.trim().isEmpty()){
            Notifications.create().darkStyle().title("Question").text("Enter valid input").position(Pos.CENTER).showError();
            return false;
        }

        else
        {
            if(obj==null){
                Notifications.create().darkStyle().text("Select Right Answer").position(Pos.CENTER).showError();
                return false;
            }
            else{
                return true;
            }

        }
    }
    @FXML
    private void addNextQuestion(ActionEvent actionEvent) {
        boolean valid=validateFields();
        if(valid){

        }
    }
    @FXML
    private void submitQuiz(ActionEvent actionEvent) {
    }
}
