/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.sql.*;

/**
 *
 * @author DWave
 */
public class Inventory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new MainFrame().setVisible(true);
    }
    
}


class MysqlCon{ 
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    public MysqlCon(String user, String password, String databaseName) {  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,user,password);  
//here sonoo is database name, root is username and password
//            DatabaseMetaData meta = con.getMetaData();
//            ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
//            System.out.println("One way of Listing Tables");
//            while (rs1.next())
//            {
//            System.out.println(rs1.getString("TABLE_NAME"));
//            }
               statement = connection.createStatement();
               String q = "show tables";
               resultSet = statement.executeQuery(q);
               while(resultSet.next())
               {
                   System.out.println(resultSet.getString(1));
               }
            connection.close();  
        }catch(Exception e){ System.out.println(e);
        
    }  
}
}
