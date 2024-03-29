/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author MarcosPauloRMM
 */
public class Question {
 
   private String description;
   private String answer;
   private String answerwrong1;
   private String answerwrong2;
   private String answerwrong3;

   
   //CRIANDO METODO PARA PEGAR QEUSTÔES
   public static ArrayList<Question> getQuestions() throws Exception{
        ArrayList<Question> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM questions");
        while(rs.next()){
            list.add(new Question(
                    rs.getString("description"), 
                    rs.getString("answer"), 
                    rs.getString("answerwrong1"), 
                    rs.getString("answerwrong2"), 
                    rs.getString("answerwrong3")));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
   
   
   
   
    public Question(String description, String answer, String answerwrong1, String answerwrong2, String answerwrong3) {
        this.description = description;
        this.answer = answer;
        this.answerwrong1 = answerwrong1;
        this.answerwrong2 = answerwrong2;
        this.answerwrong3 = answerwrong3;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerwrong1() {
        return answerwrong1;
    }

    public void setAnswerwrong1(String answerwrong1) {
        this.answerwrong1 = answerwrong1;
    }

    public String getAnswerwrong2() {
        return answerwrong2;
    }

    public void setAnswerwrong2(String answerwrong2) {
        this.answerwrong2 = answerwrong2;
    }

    public String getAnswerwrong3() {
        return answerwrong3;
    }

    public void setAnswerwrong3(String answerwrong3) {
        this.answerwrong3 = answerwrong3;
    }
    
    }



   