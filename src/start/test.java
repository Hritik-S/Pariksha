package start;

import models.Students;

import java.sql.SQLException;

public class test {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        //Students.createTable();
        new Students("hritik","singhal","8439511004",'M',"hsinghal800@gmail.com","12345").save();
    }
}
