/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.employee.*;
import uit.Util.MessageBox;
import uit.Util.NumberFormat;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;
import static uit.Util.CalendarToString.calendarToString;

/**
 *
 * @author quochuy
 */
public class ListEmpPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form ListEmpPane
     */
    public ListEmpPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        loadTable();
    }


    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
            "Mã nhân viên", "Tên đăng nhập", "Tên nhân viên", "Mã chức vụ", "Giới tính", "Địa chỉ", "Số điện thoại", "Mức lương", "KPI", "Ngày tạo", "Sửa đổi lần cuối"
        });
    }

    private void loadTable() {
        List<Employee> empList = getEmpList();
        model.setRowCount(0);
        for (Employee emp : empList) {
            model.addRow(new Object[] {
                emp.getEmp_id(), emp.getUsername(), emp.getEmp_name(), emp.getRole_id(), emp.getGender(), emp.getAddress(), emp.getPhone(), NumberFormat.doubleFormat(emp.getSalary()), emp.getKpi(), calendarToString(emp.getCreate_date()), calendarToString(emp.getLast_modified_date())
            });
        }
    }

    private List<Employee> getEmpList() {
        List<Employee> empList = new ArrayList<>();
        try {
            ApiEmpHelper apiEmpHelper = new ApiEmpHelper(getToken());
            EmployeeWrapper employeeWrapper = apiEmpHelper.getEmp();
            empList = employeeWrapper.data;

            tableListEmp.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ppmEmpList = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        ppmEditEmp = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ppmDeleteEmp = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListEmp = new javax.swing.JTable();

        ppmEmpList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmEmpList.add(ppmRefresh);
        ppmEmpList.add(jSeparator1);

        ppmEditEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmEditEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/edit_20.png"))); // NOI18N
        ppmEditEmp.setText("Chỉnh sửa");
        ppmEditEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmEditEmpActionPerformed(evt);
            }
        });
        ppmEmpList.add(ppmEditEmp);
        ppmEmpList.add(jSeparator2);

        ppmDeleteEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDeleteEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDeleteEmp.setText("Xóa");
        ppmDeleteEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteEmpActionPerformed(evt);
            }
        });
        ppmEmpList.add(ppmDeleteEmp);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Danh sách nhân viên");

        tableListEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableListEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên đăng nhập", "Tên nhân viên", "Chức vụ", "Giới tính", "Địa chỉ", "Số điện thoại", "Mức lương", "KPI", "Ngày tạo", "Ngày sửa cuối cùng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListEmp.setComponentPopupMenu(ppmEmpList);
        tableListEmp.setRowHeight(30);
        jScrollPane1.setViewportView(tableListEmp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ppmEditEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmEditEmpActionPerformed
        int row = tableListEmp.getSelectedRow();
        if(row == -1) {
            MessageBox.showErrorMessage(this, "Vui lòng chọn nhân viên cần sửa!");
        } else {
            Employee emp = getEmpList().get(row);
            if(emp != null) {
                adminFrame.showEditEmpPane(emp);
            } else {
                MessageBox.showErrorMessage(this, "Không tìm thấy nhân viên cần sửa!");
            }
        }
    }//GEN-LAST:event_ppmEditEmpActionPerformed

    private void ppmDeleteEmpActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ppmDeleteEmpActionPerformed
        try {
            int row = tableListEmp.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(this, "Vui lòng chọn nhân viên cần xóa!");
            } else {
                if (MessageBox.showConfirmMessage(this, "Bạn có chắc chắn muốn xóa nhân viên này?") == 0) {
                    Employee emp = getEmpList().get(row);
                    if (emp != null) {
                        ApiEmpHelper apiEmpHelper = new ApiEmpHelper(getToken());
                        apiEmpHelper.delete(emp.getEmp_id());
                        MessageBox.showInfoMessage(this, "Xóa nhân viên thành công!");
                        loadTable();
                    } else {
                        MessageBox.showErrorMessage(this, "Không tìm thấy nhân viên cần xóa!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showErrorMessage(this, "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }//GEN-LAST:event_ppmDeleteEmpActionPerformed

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem ppmDeleteEmp;
    private javax.swing.JMenuItem ppmEditEmp;
    private javax.swing.JPopupMenu ppmEmpList;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JTable tableListEmp;
    // End of variables declaration//GEN-END:variables
}
