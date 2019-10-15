/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import javax.swing.JTable;

/**
 *
 * @author DWave
 */
class JTableExamples extends JTable {
    JTable j;
  
    // Constructor 
    JTable JTableExamples() 
    {
        
        try {
        // Data to be displayed in the JTable 
        String[][] data = { 
            { "Kundan Kumar Jha", "4031", "CSE" }, 
            { "Anand Jha", "6014", "IT" } 
        }; 
  
        // Column Names 
        String[] columnNames = { "Name", "Roll Number", "Department" }; 
  
        // Initializing the JTable 
        j = new JTable(data, columnNames); 
        j.setBounds(30, 40, 200, 300);
        }catch (Exception e) { System.out.println(e); }
        return j;
    } 
}


//
//jScrollPane1 = new javax.swing.JScrollPane();
//jScrollPane1.setViewportView(jTable1);
//jTabbedPane1.addTab("tab1", jScrollPane1);