/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.employee.*;
import uit.Util.MessageBox;
import uit.validator.EmpValidator;
import uit.validator.Validator;

import java.util.Objects;

/**
 *
 * @author quochuy
 */
public class AddEmpPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    /**
     * Creates new form AddEmpPane
     */
    public AddEmpPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        changeButtonState(true, true, false, false);
        changeFieldState(false);
        changeInputState(false);
    }

    public AddEmpPane(AdminFrame adminFrame, Employee employee) {
        this.adminFrame = adminFrame;
        initComponents();
        changeButtonState(false, true, false, true);
        changeFieldState(true);
        changeInputState(true);
        inputEmpId.setText(String.valueOf(employee.getEmp_id()));
        inputUsername.setText(employee.getUsername());
        inputName.setText(employee.getEmp_name());
        inputRole.setText(employee.getRole());
        inputGender.setSelectedItem(employee.getGender());
        inputAddress.setText(employee.getAddress());
        inputPhone.setText(employee.getPhone());
        inputSalary.setText(String.valueOf(employee.getSalary()));
        inputKPI.setText(String.valueOf(employee.getKpi()));
    }

    private void changeButtonState(boolean add , boolean edit, boolean save, boolean update) {
        btnAdd.setEnabled(add);
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnUpdate.setEnabled(update);
    }

    private void changeFieldState(boolean state) {
        inputEmpId.setEditable(state);
        inputUsername.setEditable(state);
        inputName.setEditable(state);
        inputRole.setEditable(state);
        inputGender.setEnabled(state);
        inputAddress.setEditable(state);
        inputPhone.setEditable(state);
        inputSalary.setEditable(state);
        inputKPI.setEditable(state);
    }

    private void changeInputState(boolean state) {
        inputEmpId.setEnabled(state);
        inputUsername.setEnabled(state);
        inputName.setEnabled(state);
        inputRole.setEnabled(state);
        inputGender.setEnabled(state);
        inputAddress.setEnabled(state);
        inputPhone.setEnabled(state);
        inputSalary.setEnabled(state);
        inputKPI.setEnabled(state);
    }

    private void createEmp(Employee emp) {
        try {
            ApiEmpHelper apiEmpHelper = new ApiEmpHelper("MWQ+S348U1B6SHVYeTgzYQ==");
            apiEmpHelper.createEmp(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEmp(Employee emp) {
        try {
            ApiEmpHelper apiEmpHelper = new ApiEmpHelper("MWQ+S348U1B6SHVYeTgzYQ==");
            apiEmpHelper.updateEmp(emp);
        } catch (Exception e) {
            e.printStackTrace();
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

        labelState = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelEmpId = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelRole = new javax.swing.JLabel();
        labelGender = new javax.swing.JLabel();
        labelAddress = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        inputName = new javax.swing.JTextField();
        inputEmpId = new javax.swing.JTextField();
        inputRole = new javax.swing.JTextField();
        inputAddress = new javax.swing.JTextField();
        inputPhone = new javax.swing.JTextField();
        labelSalary = new javax.swing.JLabel();
        labelKPI = new javax.swing.JLabel();
        inputSalary = new javax.swing.JTextField();
        inputKPI = new javax.swing.JTextField();
        inputGender = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnList = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        labelUsername = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(900, 600));

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý nhân viên");

        labelEmpId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelEmpId.setText("Mã nhân viên:");

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setText("Tên nhân viên:");

        labelRole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelRole.setText("Chức vụ:");

        labelGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelGender.setText("Giới tính:");

        labelAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelAddress.setText("Địa chỉ:");

        labelPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPhone.setText("Số điện thoại:");

        inputName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        inputEmpId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputEmpId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmpIdActionPerformed(evt);
            }
        });
        inputEmpId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputEmpIdKeyTyped(evt);
            }
        });

        inputRole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputRoleActionPerformed(evt);
            }
        });

        inputAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        inputPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPhoneActionPerformed(evt);
            }
        });
        inputPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputPhoneKeyTyped(evt);
            }
        });

        labelSalary.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        labelSalary.setText("Mức lương:");

        labelKPI.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        labelKPI.setText("KPI:");

        inputSalary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSalaryActionPerformed(evt);
            }
        });
        inputSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputSalaryKeyTyped(evt);
            }
        });

        inputKPI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputKPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputKPIActionPerformed(evt);
            }
        });
        inputKPI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputKPIKeyTyped(evt);
            }
        });

        inputGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/new_20.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save_20.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/update_20.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnList.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/list_20.png"))); // NOI18N
        btnList.setText("Danh sách");
        btnList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/edit_20.png"))); // NOI18N
        btnEdit.setText("Chỉnh sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelUsername.setText("Tên đăng nhập:");

        inputUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputUsernameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRole)
                    .addComponent(labelEmpId)
                    .addComponent(labelGender)
                    .addComponent(labelAddress)
                    .addComponent(labelPhone)
                    .addComponent(labelSalary)
                    .addComponent(labelKPI)
                    .addComponent(labelUsername)
                    .addComponent(labelName))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inputAddress)
                        .addComponent(inputPhone)
                        .addComponent(inputRole)
                        .addComponent(inputName)
                        .addComponent(inputEmpId)
                        .addComponent(inputSalary)
                        .addComponent(inputGender, 0, 250, Short.MAX_VALUE)
                        .addComponent(inputUsername))
                    .addComponent(inputKPI, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmpId)
                    .addComponent(inputEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsername)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRole))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGender))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddress))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhone))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSalary))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKPI)
                    .addComponent(inputKPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelState, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(494, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelState, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inputEmpIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEmpIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmpIdActionPerformed

    private void inputRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputRoleActionPerformed

    private void inputPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPhoneActionPerformed

    private void inputSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSalaryActionPerformed

    private void inputKPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputKPIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputKPIActionPerformed

    private void inputUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputUsernameActionPerformed

    private void inputEmpIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmpIdKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            inputEmpId.setEditable(false);
        } else {
            inputEmpId.setEditable(true);
        }
    }//GEN-LAST:event_inputEmpIdKeyTyped

    private void inputPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPhoneKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            inputEmpId.setEditable(false);
        } else {
            inputEmpId.setEditable(true);
        }
    }//GEN-LAST:event_inputPhoneKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        labelState.setText("Thêm nhân viên");
        inputEmpId.setText("");
        inputUsername.setText("");
        inputName.setText("");
        inputRole.setText("");
        inputGender.setSelectedIndex(0);
        inputAddress.setText("");
        inputPhone.setText("");
        inputSalary.setText("");
        inputKPI.setText("");

        changeButtonState(false, true, true, false);
        changeFieldState(true);
        changeInputState(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        labelState.setText("Chỉnh sửa nhân viên");
        changeButtonState(true, false, false, true);
        changeFieldState(true);
        changeInputState(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String valid = EmpValidator.validate(inputEmpId, inputUsername, inputName, inputAddress, inputPhone, inputSalary, inputKPI);
            if(valid != null) {
                MessageBox.showErrorMessage(this, valid);
                return;
            }

            Employee emp = new Employee(Integer.parseInt(inputEmpId.getText()), inputUsername.getText(), inputName.getText(), inputRole.getText(), Objects.requireNonNull(inputGender.getSelectedItem()).toString(), inputAddress.getText(), inputPhone.getText(), Validator.isEmpty(inputSalary) ? 0 : Double.parseDouble(inputSalary.getText()), Validator.isEmpty(inputKPI) ? 0 : Double.parseDouble(inputKPI.getText()));
            createEmp(emp);
            MessageBox.showInfoMessage(this, "Thêm nhân viên thành công");
            changeButtonState(true, true, false, false);
            inputEmpId.setText("");
            inputUsername.setText("");
            inputName.setText("");
            inputRole.setText("");
            inputGender.setSelectedIndex(0);
            inputAddress.setText("");
            inputPhone.setText("");
            inputSalary.setText("");
            inputKPI.setText("");
            changeInputState(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = EmpValidator.validate(inputEmpId, inputUsername, inputName, inputAddress, inputPhone, inputSalary, inputKPI);
            if(valid != null) {
                MessageBox.showErrorMessage(this, valid);
                return;
            }

            Employee emp = new Employee(Integer.parseInt(inputEmpId.getText()), inputUsername.getText(), inputName.getText(), inputRole.getText(), Objects.requireNonNull(inputGender.getSelectedItem()).toString(), inputAddress.getText(), inputPhone.getText(), Validator.isEmpty(inputSalary) ? 0 : Double.parseDouble(inputSalary.getText()), Validator.isEmpty(inputKPI) ? 0 : Double.parseDouble(inputKPI.getText()));
            updateEmp(emp);
            MessageBox.showInfoMessage(this, "Cập nhật nhân viên thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListActionPerformed
        adminFrame.showListEmp();
    }//GEN-LAST:event_btnListActionPerformed

    private void inputSalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSalaryKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            inputEmpId.setEditable(false);
        } else {
            inputEmpId.setEditable(true);
        }
    }//GEN-LAST:event_inputSalaryKeyTyped

    private void inputKPIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKPIKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            inputEmpId.setEditable(false);
        } else {
            inputEmpId.setEditable(true);
        }
    }//GEN-LAST:event_inputKPIKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnList;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField inputAddress;
    private javax.swing.JTextField inputEmpId;
    private javax.swing.JComboBox<String> inputGender;
    private javax.swing.JTextField inputKPI;
    private javax.swing.JTextField inputName;
    private javax.swing.JTextField inputPhone;
    private javax.swing.JTextField inputRole;
    private javax.swing.JTextField inputSalary;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelEmpId;
    private javax.swing.JLabel labelGender;
    private javax.swing.JLabel labelKPI;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelSalary;
    private javax.swing.JLabel labelState;
    private javax.swing.JLabel labelUsername;
    // End of variables declaration//GEN-END:variables
}