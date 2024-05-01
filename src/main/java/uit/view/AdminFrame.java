/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.employee.Employee;
import uit.controllers.LoginController;

/**
 *
 * @author quochuy
 */
public class AdminFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public AdminFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void showListEmp() {
        menuListEmpActionPerformed(null);
    }

    public void showEditEmpPane(Employee employee) {
        AddEmpPane pane = new AddEmpPane(this, employee);
        tpnBoard.addTab("Chỉnh sửa nhân viên", pane);
        tpnBoard.setSelectedComponent(pane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        tbrEmpManagement = new javax.swing.JButton();
        tbrListEmp = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        tbrCloseCurrentTab = new javax.swing.JButton();
        tbrExitProgram = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        tpnBoard = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuEmpManagement = new javax.swing.JMenu();
        menuAddEmp = new javax.swing.JMenuItem();
        menuListEmp = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý siêu thị");
        setSize(new java.awt.Dimension(1080, 768));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        tbrEmpManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/employee_32.png"))); // NOI18N
        tbrEmpManagement.setText("Quản lý nhân viên");
        tbrEmpManagement.setFocusable(false);
        tbrEmpManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbrEmpManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbrEmpManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbrEmpManagementActionPerformed(evt);
            }
        });
        jToolBar1.add(tbrEmpManagement);

        tbrListEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/list_32.png"))); // NOI18N
        tbrListEmp.setText("Danh sách nhân viên");
        tbrListEmp.setFocusable(false);
        tbrListEmp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbrListEmp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbrListEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbrListEmpActionPerformed(evt);
            }
        });
        jToolBar1.add(tbrListEmp);
        jToolBar1.add(jSeparator2);

        tbrCloseCurrentTab.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbrCloseCurrentTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/close_32.png"))); // NOI18N
        tbrCloseCurrentTab.setText("Đóng tab hiện tại");
        tbrCloseCurrentTab.setFocusable(false);
        tbrCloseCurrentTab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbrCloseCurrentTab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbrCloseCurrentTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbrCloseCurrentTabActionPerformed(evt);
            }
        });
        jToolBar1.add(tbrCloseCurrentTab);

        tbrExitProgram.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbrExitProgram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/exit_32.png"))); // NOI18N
        tbrExitProgram.setText("Thoát chương trình");
        tbrExitProgram.setFocusable(false);
        tbrExitProgram.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbrExitProgram.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbrExitProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbrExitProgramActionPerformed(evt);
            }
        });
        jToolBar1.add(tbrExitProgram);
        jToolBar1.add(jSeparator3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1068, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        tpnBoard.addTab("Home", jPanel1);

        jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        menuFile.setText("File");
        menuFile.add(jSeparator1);

        menuExit.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        menuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/exit_16.png"))); // NOI18N
        menuExit.setText("Thoát");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        menuFile.add(menuExit);

        jMenuBar1.add(menuFile);

        menuView.setText("View");

        menuEmpManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/employee_16.png"))); // NOI18N
        menuEmpManagement.setText("Nhân viên");

        menuAddEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/add_emp_16.png"))); // NOI18N
        menuAddEmp.setText("Quản lý nhân viên");
        menuAddEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddEmpActionPerformed(evt);
            }
        });
        menuEmpManagement.add(menuAddEmp);

        menuListEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/list_16.png"))); // NOI18N
        menuListEmp.setText("Danh sách nhân viên");
        menuListEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListEmpActionPerformed(evt);
            }
        });
        menuEmpManagement.add(menuListEmp);

        menuView.add(menuEmpManagement);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/account_16.png"))); // NOI18N
        jMenu1.setText("Quản lý tài khoản");
        menuView.add(jMenu1);

        jMenuBar1.add(menuView);

        menuHelp.setText("Help");

        jMenuItem4.setText("Về chúng tôi");
        menuHelp.add(jMenuItem4);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpnBoard)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnBoard)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbrExitProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbrExitProgramActionPerformed
        System.exit(0);
    }//GEN-LAST:event_tbrExitProgramActionPerformed

    private void tbrCloseCurrentTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbrCloseCurrentTabActionPerformed
        int selectedIndex = tpnBoard.getSelectedIndex();
        if(selectedIndex > 0) {
            tpnBoard.remove(selectedIndex);
        }
    }//GEN-LAST:event_tbrCloseCurrentTabActionPerformed

    private void menuAddEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddEmpActionPerformed
        AddEmpPane pane = new AddEmpPane(this);
        tpnBoard.addTab("Quản lý nhân viên", pane);
        tpnBoard.setSelectedComponent(pane);
    }//GEN-LAST:event_menuAddEmpActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuListEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListEmpActionPerformed
        ListEmpPane pane = new ListEmpPane(this);
        tpnBoard.addTab("Danh sách nhân viên", pane);
        tpnBoard.setSelectedComponent(pane);
    }//GEN-LAST:event_menuListEmpActionPerformed

    private void tbrEmpManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbrEmpManagementActionPerformed
        menuAddEmpActionPerformed(evt);
    }//GEN-LAST:event_tbrEmpManagementActionPerformed

    private void tbrListEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbrListEmpActionPerformed
        menuListEmpActionPerformed(evt);
    }//GEN-LAST:event_tbrListEmpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
//                new LoginController(new LoginFrame());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem menuAddEmp;
    private javax.swing.JMenu menuEmpManagement;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuListEmp;
    private javax.swing.JMenu menuView;
    private javax.swing.JButton tbrCloseCurrentTab;
    private javax.swing.JButton tbrEmpManagement;
    private javax.swing.JButton tbrExitProgram;
    private javax.swing.JButton tbrListEmp;
    private javax.swing.JTabbedPane tpnBoard;
    // End of variables declaration//GEN-END:variables
}