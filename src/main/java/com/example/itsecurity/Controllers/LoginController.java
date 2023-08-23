package com.example.itsecurity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;

@Controller
@RequestMapping("/loginView")
public class LoginController{
    @RequestMapping("/login")
    public String addByForm() {
        return "login";
    }


    @PostMapping("/verify")
    public String verifyUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) throws SQLException, IOException {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/application.properties"));
        Connection con = DriverManager.getConnection(
                prop.getProperty("spring.datasource.url"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password"));

        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM users WHERE username = + 'peter' + and password = '1234'";
        if(stmt.execute(sql)){
            model.addAttribute("message", "Login successful.");
            return "successful-login";
        }else{
            model.addAttribute("message", "Login failed.");
            return "unsuccessful-login";
        }

        //"SELECT * FROM users WHERE user = '" + $user + "' and password = '" + $password + "'"

    }

}
