/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProdUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author srvmng
 */
public class DisplayImage extends HttpServlet {
    
    Statement stmt=null;
     private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Statement stmt = null;
        ResultSet rs;
        InputStream sImage;
        
        response.setContentType("image/jpeg");
         try {
            response.setCharacterEncoding("UTF-8");
            String idString = request.getParameter("prodid");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
                Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "22420Kos!!");
                properties.setProperty("useSSL", "false");
                String url="jdbc:mysql://localhost:3306/demo?zeroDateTimeBehavior=convertToNull";
                Connection con = DriverManager.getConnection(url, properties);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String strSql = "select img from products where prodid=" + Integer.parseInt(idString);
            rs = stmt.executeQuery(strSql);
            
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
               
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                }
            }
            con.commit();
            }   catch (IOException | ClassNotFoundException | SQLException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
