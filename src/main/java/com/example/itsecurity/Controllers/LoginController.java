package com.example.itsecurity.Controllers;

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

}
