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
import models.Quiz;
import models.questions;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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

    //my local variables

    private Quiz quiz=null;
    private ArrayList<models.questions> questions=new ArrayList<>();
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
            this.quiz=new Quiz(title);
        }
    }

    private boolean validateFields(){

        if(quiz==null){
            Notifications.create().darkStyle().title("Quiz").text("Enter Quiz Title and press OK").position(Pos.CENTER).showError();
            return false;
        }

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
        addQuestion();
    }

    private boolean addQuestion(){
        boolean valid=validateFields();
        models.questions question=new questions();
        if(valid){



            question.setOption1(option1.getText().trim());
            question.setOption2(option2.getText().trim());
            question.setOption3(option3.getText().trim());
            question.setOption4(option4.getText().trim());

            Toggle obj2=radioGroup.getSelectedToggle();
            String ans=null;
            if(obj2==option1radio){
                ans=option1.getText().trim();
            }
            else if(obj2==option2radio){
                ans=option2.getText().trim();
            }
            else if(obj2==option3radio){
                ans=option3.getText().trim();
            }
            else
            {
                ans=option4.getText().trim();
            }

            question.setAnswer(ans);
            question.setQuestion(this.question.getText().trim());

            this.question.clear();
            option1.clear();
            option2.clear();
            option3.clear();option4.clear();

            questions.add(question);
            question.setQuiz(quiz);

            System.out.println("Save Question......");
            System.out.println(questions);
            System.out.println(quiz);


        }
        return valid;
    }

    @FXML
    private void submitQuiz(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean flag=addQuestion();
        if(flag){
            flag= quiz.save(questions);
            if(flag){
                Notifications.create().darkStyle().text("Quiz Successfully saved").position(Pos.CENTER).showInformation();
            }
            else{
                //error
                Notifications.create().darkStyle().title("FAILED").text("Can't Saved Quiz....check all entries carefully").position(Pos.CENTER).showError();
            }
        }

    }
}
