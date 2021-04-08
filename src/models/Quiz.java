package models;

import java.sql.*;

public class Quiz {
    private Integer quizId;
    private String title;

    public static class metaData{
        public static final String TABLE_NAME="Quiz";
        public static final String QUIZ_ID="id";
        public static final String TITLE="title";

    }

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz() {
    }

    public Integer getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", title='" + title + '\'' +
                '}';
    }

    public static void createTable() throws ClassNotFoundException, SQLException {
        String raw="CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50) );";
        String query=String.format(raw,metaData.TABLE_NAME,metaData.QUIZ_ID,metaData.TITLE);
        String connectionUrl="jdbc:sqlite:Quiz.db";
        Class.forName("org.sqlite.JDBC");
        Connection connection= DriverManager.getConnection(connectionUrl);
        PreparedStatement ps= connection.prepareStatement(query);
        boolean b=ps.execute();
        System.out.println(b);
        connection.close();
    }

    public int save() throws ClassNotFoundException, SQLException {
        String raw="Insert into %s (%s) values (?) ";
        String query=String.format(raw,metaData.TABLE_NAME,metaData.TITLE);
        String connectionUrl="jdbc:sqlite:Quiz.db";
        Class.forName("org.sqlite.JDBC");
        Connection connection= DriverManager.getConnection(connectionUrl);
        PreparedStatement ps= connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,this.title);

        int i=ps.executeUpdate();
        ResultSet keys=ps.getGeneratedKeys();
        if(keys.next()){
            connection.close();
            return keys.getInt(1);
        }
        connection.close();
        return -1;

    }
}
