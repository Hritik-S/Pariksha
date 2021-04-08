package start;

import models.Quiz;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Quiz quiz=new Quiz("Java and C++");
        System.out.println(quiz.save());
    }
}
