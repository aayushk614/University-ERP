/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import DAO.SubjectDAO;
import DTO.SubjectDTO;

import javax.swing.*;
import java.sql.SQLException;

public class Subject extends javax.swing.JPanel {

    /**
     * Creates new form Subject Page
     */
    
    SubjectDTO subjectDTO;
    String username = null;
    String supplier = null;
    int userID;
    Dashboard dashboard;
    
    
    public Subject() {
    }
    
    public Subject(String username, Dashboard dashboard){
        initComponents();
        this.username = username;
        this.dashboard = dashboard;
//        loadComboBox();
        loadDataSet();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        entryPanel = new javax.swing.JPanel();
        addSuppButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        deptcode = new javax.swing.JTextField();
        coursecode = new javax.swing.JTextField();
        coursename = new javax.swing.JTextField();
        abbrevation = new javax.swing.JTextField();
        instructor = new javax.swing.JTextField();
        level = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        credit = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        subjectTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Kohinoor Bangla", 1, 24)); // NOI18N
        jLabel1.setText("SUBJECTS");

        entryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter Subject Details"));

        addSuppButton.setText("Click to add a New Proffessor");
        addSuppButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSuppButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuppButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Dept Code");

        jLabel3.setText("Course Code");

        jLabel5.setText("Course Name");

        jLabel6.setText("Abbrevation");

        jLabel7.setText("Instructor");

        jLabel8.setText("Level");

        addButton.setText("Add");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel10.setText("Credits");

        javax.swing.GroupLayout entryPanelLayout = new javax.swing.GroupLayout(entryPanel);
        entryPanel.setLayout(entryPanelLayout);
        entryPanelLayout.setHorizontalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addSuppButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deptcode))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coursecode))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(level))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coursename))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(abbrevation))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(instructor))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(credit)))
                .addContainerGap())
        );
        entryPanelLayout.setVerticalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(addSuppButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deptcode, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coursecode, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coursename, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abbrevation, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instructor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subjectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        subjectTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subjectTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subjectTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(subjectTable);

        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Kohinoor Bangla", 2, 13)); // NOI18N
        jLabel9.setText("Search:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(entryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    //static String productName;
    private void subjectTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectTableMouseClicked
        int row = subjectTable.getSelectedRow();
        int col = subjectTable.getColumnCount();

        Object[] data = new Object[col];
        for (int i=0; i<col; i++)
            data[i] = subjectTable.getValueAt(row, i);

        deptcode.setText(data[0].toString());
        coursecode.setText(data[1].toString());
        coursename.setText(data[2].toString());
        abbrevation.setText(data[3].toString());
        instructor.setText(data[4].toString());
        level.setText(data[5].toString());
        credit.setText(data[6].toString());

        
       // productName = data[1].toString();

    }//GEN-LAST:event_subjectTableMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        loadDataSet();
//        loadComboBox();
        clearButtonActionPerformed(evt);  
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        loadSearchData(searchText.getText());
    }//GEN-LAST:event_searchTextKeyReleased

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        deptcode.setText("");
        coursecode.setText("");
        coursename.setText("");
        abbrevation.setText("");
        instructor.setText("");
        level.setText("");
        credit.setText("");
        searchText.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (subjectTable.getSelectedRow()<0)
        JOptionPane.showMessageDialog(null, "Please select product from the table.");
        else {
            int opt = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this subject?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION) {
                new SubjectDAO().deleteSubjectDAO(
                    (String) subjectTable.getValueAt(
                        subjectTable.getSelectedRow(),1));
                loadDataSet();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (subjectTable.getSelectedRow()<0)
        JOptionPane.showMessageDialog(null, "Please select subject from the table.");
        else{
            subjectDTO = new SubjectDTO();
            if (coursecode.getText().equals("") || abbrevation.getText().equals("")
                || instructor.getText().equals("") || level.getText().equals("") || coursename.getText().equals("") || deptcode.getText().equals("")||credit.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Please enter all the required details.");
            else {
                subjectDTO.setDepartmentCode(deptcode.getText());
                subjectDTO.setCourseCode(coursecode.getText());
                subjectDTO.setCourseName((coursename.getText()));
                subjectDTO.setAbbreviation((abbrevation.getText()));
                subjectDTO.setInstructor((instructor.getText()));
                subjectDTO.setStudentLevel(level.getText());
                subjectDTO.setCredits(Integer.parseInt(credit.getText()));
                
                System.out.println("Instructor: " + subjectDTO.getInstructor());

                new SubjectDAO().editSubjectDAO(subjectDTO);
            }
            loadDataSet();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        subjectDTO = new SubjectDTO();
        if (coursecode.getText().equals("") || abbrevation.getText().equals("")
                || instructor.getText().equals("") || level.getText().equals("") || coursename.getText().equals("") || deptcode.getText().equals("")||credit.getText().equals(""))
        JOptionPane.showMessageDialog(null, "Please enter all the required details.");
        else {
            subjectDTO.setDepartmentCode(deptcode.getText());
            subjectDTO.setCourseCode(coursecode.getText());
            subjectDTO.setCourseName((coursename.getText()));
            subjectDTO.setAbbreviation((abbrevation.getText()));
            subjectDTO.setInstructor((instructor.getText()));
            subjectDTO.setStudentLevel(level.getText());
            subjectDTO.setCredits(Integer.parseInt(credit.getText()));
            new SubjectDAO().addSubjectDAO(subjectDTO);
            loadDataSet();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void addSuppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuppButtonActionPerformed
        dashboard.addSuppPage();
    }//GEN-LAST:event_addSuppButtonActionPerformed

    // Method to update combo box containing supplier names
//    public void loadComboBox() {
//        try {
//            SubjectDAO supplierDAO = new SubjectDAO();
//            suppCombo.setModel(supplierDAO.setComboItems(supplierDAO.getQueryResult()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // Method to load data into table
    public void loadDataSet() {
        try {
            SubjectDAO subjectDTO = new SubjectDAO();
            subjectTable.setModel(subjectDTO.buildTableModel(subjectDTO.getQueryResult()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Method to display search result in table
    public void loadSearchData(String text) {
        try {
             SubjectDAO subjectDTO = new SubjectDAO();
            subjectTable.setModel(subjectDTO.buildTableModel(subjectDTO.getSubjectSearch(text)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abbrevation;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addSuppButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField coursecode;
    private javax.swing.JTextField coursename;
    private javax.swing.JTextField credit;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField deptcode;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JTextField instructor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField level;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchText;
    private javax.swing.JTable subjectTable;
    // End of variables declaration//GEN-END:variables
}
