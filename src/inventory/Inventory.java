/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.sql.*;
import javax.swing.JTable;

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
    
    String user;
    String password;
    String databaseName;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    int result;
    
    
    public MysqlCon(String user, String password, String databaseName) {  
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }
    
    public int getTableCount () {
        int count = 0;
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
                   count++;
               }
            connection.close();
        }catch(Exception e){ System.out.println(e);
        
        }
        return count;
    }
    
    public String[] retrieveTableNames() {
        String[] data = new String[0];
        int count = 0;
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
                   count++;
               }
               data = new String[count];
               resultSet.first();
               for (int i = 0; i<count; i++){
                   data[i] = resultSet.getString(1);
                   resultSet.next();
               }
            connection.close();
        }catch(Exception e){ System.out.println(e);
        
        }
        return data;
    }
    
    public String[] retrieveColumnnNames(String tableName) {
        String[] data = new String[0];
        int count = 0;
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
               String q = "SELECT COLUMN_NAME\n"+"FROM INFORMATION_SCHEMA.COLUMNS\n"+"WHERE TABLE_NAME = '"+tableName+"'\n"+"ORDER BY ORDINAL_POSITION";
               resultSet = statement.executeQuery(q);
               while(resultSet.next())
               {
                   System.out.println(resultSet.getString(1));
                   count++;
               }
               data = new String[count];
               resultSet.first();
               for (int i = 0; i<count; i++){
                   data[i] = resultSet.getString(1);
                   resultSet.next();
               }
            connection.close();
        }catch(Exception e){ System.out.println(e);
        
        }
        return data;
    }
    
    public String[][] retrieveTableConfig(String tableName) {
        String[][] data = new String[0][5];
        int count = 0;
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
               String q = "desc "+tableName;
               resultSet = statement.executeQuery(q);
               while(resultSet.next())
               {
                   System.out.println(resultSet.getString(1));
                   count++;
               }
               data = new String[count][6];
               resultSet.first();
               for (int i = 0; i<count; i++){
                   data[i][0] = resultSet.getString(1);
                   data[i][1] = resultSet.getString(2);
                   data[i][2] = resultSet.getString(3);
                   data[i][3] = resultSet.getString(4);
                   data[i][4] = resultSet.getString(5);
                   data[i][5] = resultSet.getString(6);
                   resultSet.next();
               }
            connection.close();
        }catch(Exception e){ System.out.println(e);
        
        }
        return data;
    }
    
    public Object[][] retrieveTableData(String tableName, String[][] tableConfig, String[] colNames) {
        Object[][] data = new Object[0][0];
        int countColumns = 0;
        int countRows = 0;
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
               String q = "SELECT * FROM "+ tableName;
               resultSet = statement.executeQuery(q);
               while(resultSet.next())
               {
                   System.out.println(resultSet.getString(1));
                   countRows++;
               }
               System.out.println("Counting done"+countRows);
               data = new Object[countRows][colNames.length];
               resultSet = statement.executeQuery(q);
               System.out.println("Now Here");
               int i=0;
                while (resultSet.next()) {
                    System.out.println("First loop");
                    for (int j=0; j<colNames.length; j++){
                        System.out.println("Second Loop");
                            if (tableConfig[j][1].contains("int")) {
                                data[i][j]=resultSet.getInt(j+1);
                                System.out.println("First if");
                            }
                            else if (tableConfig[j][1].contains("varchar")) {
                                data[i][j]=resultSet.getString(j+1);
                                System.out.println("Second if");
                            }
                    }
                    System.out.println("Done with loop");
                    i++;
                }
               System.out.println("even Here");
               for (i = 0; i<countRows; i++){
                   for (int j=1; j<=colNames.length; j++) {
                       System.out.println("Data " + data[i][j-1].toString());
                   }
               }
               System.out.println("Everything");
            connection.close();
        }catch(Exception e){ System.out.println(e);
        }
        return data;
    }
    
    public void addRecord(int Cust_ID, String Cust_Name, String Cust_Address, String Cust_Phone, String Cust_Email) {
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
               System.out.println(Cust_Name);
               statement = connection.createStatement();
               String q;
               if (Cust_ID == 0) {
                   q = "insert into Customers(Cust_Name, Cust_Address, Cust_Phone, Cust_Email) values('"+Cust_Name+"', '"+Cust_Address+"', '"+Cust_Phone+"', '"+Cust_Email+"')";
               }
               else {
                   q = "insert into Customers(Cust_ID, Cust_Name, Cust_Address, Cust_Phone, Cust_Email) values("+Integer.toString(Cust_ID)+", '"+Cust_Name+"', '"+Cust_Address+"', '"+Cust_Phone+"', '"+Cust_Email+"')";
               }
               result = statement.executeUpdate(q);
            connection.close();
        }catch(Exception e){ System.out.println(e);
        System.out.println("Done");
        }
    }
    
    public void delRecord(String recordNumber) {
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
               String q;
               statement = connection.createStatement();
               //if (tablename == "Customers"){
                q = "delete from Customers where Cust_ID = "+recordNumber+";";
        //}
               //else {
                 //      q = "delete from "+tablename+" where Prod_ID = "+recordNumber+";";
                      // }
               result = statement.executeUpdate(q);
            connection.close();
        }catch(Exception e){ System.out.println(e);
        System.out.println("Done");
        }
    }
    
    public void executeConsoleQuery(String q) {
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
               statement.execute(q);
            connection.close();
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
        System.out.println("Done");
        }
    }
    
}
