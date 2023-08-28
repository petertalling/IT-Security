package com.example.itsecurity.Controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


@Controller
@RequestMapping("/loginView")
public class LoginController {

    @RequestMapping("/login")
    public String addByForm() {
        return "login";
    }


    //DETTA ÄR DEN SÅRBARA KODEN
    /*
    @PostMapping("/verify")
    public String verifyUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) throws SQLException, IOException {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/application.properties"));
        Connection con = DriverManager.getConnection(
                prop.getProperty("connectionString"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password"));

        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM users WHERE username = '" + username + "' and password = '" + password + "'";
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            model.addAttribute("message", "Login successful.");
            con.close();
            return "successful-login";
        } else {
            model.addAttribute("message", "Login failed.");
            con.close();
            return "unsuccessful-login";
        }

    }

    //TILLÄGG PREPARED STATEMENTS FÖR SKYDD MOT INJEKTIONSATTACKER
    @PostMapping("/verify")
    public String verifyUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) throws SQLException, IOException {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/application.properties"));
        Connection con = DriverManager.getConnection(
                prop.getProperty("connectionString"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password"));

        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet res = pstmt.executeQuery();
        if (res.next()) {
            model.addAttribute("message", "Login successful.");
            con.close();
            return "successful-login";
        } else {
            model.addAttribute("message", "Login failed.");
            con.close();
            return "unsuccessful-login";
        }

    }

     */
    @PostMapping("/verify")
    public String verifyUser(@RequestParam("username") String inputUsername, @RequestParam("password") String inputPassword, Model model) throws SQLException, IOException {
        String hashedPwd = "";
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/application.properties"));
        Connection con = DriverManager.getConnection(
                prop.getProperty("connectionString"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password"));

        PreparedStatement pstmt = con.prepareStatement("SELECT password FROM users WHERE username = ?");
        pstmt.setString(1, inputUsername);
        ResultSet res = pstmt.executeQuery();
        if (res.next()) {
            hashedPwd = res.getString(1);
            System.out.println(hashedPwd);
        }

        if (BCrypt.checkpw(inputPassword, hashedPwd)) {
            model.addAttribute("message", "Login successful.");
            con.close();
            return "result-login";

        } else {
            model.addAttribute("message", "Login failed.");
            con.close();
            return "result-login";
        }

    }


}
