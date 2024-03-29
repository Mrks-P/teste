/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author MarcosPauloRMM
 */
public class User {
    private String login;
    private String name;
    
    
    //CRIANDO METODO PARA PEGAR USUARIOS
    public static ArrayList<User> getUsers() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while(rs.next()){
            list.add(new User(
                    rs.getString("login"), 
                    rs.getString("name")));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static User getUser(String login, String password) throws Exception{
        User user = null;
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT * FROM users WHERE login=? AND password_hash=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new User(
                    rs.getString("login"), 
                    rs.getString("name"));
        }else{
            
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }
    
    //CRIANDO METODO PARA CRIAR USUARIOS
    public static void addUser(String login, String name,String password) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO users(login, name, password_hash) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, login);
        stmt.setString(2, name);
        stmt.setLong(3, password.hashCode());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}