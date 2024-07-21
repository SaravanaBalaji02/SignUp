package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyWebAppDB", "root", "password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            int i = ps.executeUpdate();
            if (i > 0) {
                PrintWriter out = response.getWriter();
                out.println("You are successfully registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
