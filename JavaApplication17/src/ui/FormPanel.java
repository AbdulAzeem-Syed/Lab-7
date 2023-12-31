/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Customer;
import util.DatabaseConnector;

/**
 *
 * @author Abdul
 */
public class FormPanel extends javax.swing.JPanel {

    /**
     * Creates new form FormPanel
     */
    private JPanel bottomPanel;
    public FormPanel(JPanel bottomPanel) {
        initComponents();
        this.bottomPanel = bottomPanel;
    }
    
    public int tryParseInt(String value) 
    {
        try 
        {
            return Integer.parseInt(value);
        } 
        catch (NumberFormatException e) 
        {
            return -1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderRadioGroup = new javax.swing.ButtonGroup();
        mainTitle = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        itemCountLabel = new javax.swing.JLabel();
        itemCountText = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();

        mainTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        mainTitle.setText("Customer Order Track");

        nameLabel.setText("Name");

        itemCountLabel.setText("ItemCount");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemCountLabel)
                            .addComponent(nameLabel))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(itemCountText, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(mainTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(submitButton)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(mainTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemCountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemCountLabel))
                .addGap(86, 86, 86)
                .addComponent(submitButton)
                .addContainerGap(300, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        boolean hasError = false;
        StringBuilder errorFields = new StringBuilder();
        Customer customer = new Customer();
        
        if(nameText.getText().isEmpty())
        {
            errorFields.append("First name is empty. \n");
            hasError = true;
        }
        if(tryParseInt(itemCountText.getText()) == -1)
        {
            errorFields.append("Age is invalid. \n");
            hasError = true;
        }
        

        if(hasError)
        {
            String errorMessage = "Invalid input, below are the invalid fields:\n" + errorFields;
            JOptionPane.showMessageDialog(this, errorMessage, "Error", HEIGHT);
        }
        else
        {
            try
            {
                customer.setName(nameText.getText());
                customer.setItemCount(tryParseInt(itemCountText.getText()));

                DatabaseConnector.addCustomer(customer);

                //ViewPanel newViewPanel = new ViewPanel(patient);
                //bottomPanel.add(newViewPanel);
                //CardLayout layout = (CardLayout) bottomPanel.getLayout();
                //layout.next(bottomPanel);

                String outputMessage = "Save successfull";
                JOptionPane.showMessageDialog(this, outputMessage, "Customer information", HEIGHT);
                cleanup();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", HEIGHT);
            }
        }
    }//GEN-LAST:event_submitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup genderRadioGroup;
    private javax.swing.JLabel itemCountLabel;
    private javax.swing.JTextField itemCountText;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
    public void cleanup()
    {
        nameText.setText("");
        itemCountText.setText("");
    }
}
