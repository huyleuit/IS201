/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.category.ApiCategoryHelper;
import com.uitprojects.is210.category.Category;
import com.uitprojects.is210.category.CategoryWrapper;
import uit.Util.MessageBox;
import uit.validator.CategoryValidator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;
import static uit.Util.CalendarToString.calendarToString;

/**
 *
 * @author huyle
 */
public class CategoryManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form CategoryManagementPane
     */
    public CategoryManagementPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        loadTable();
        changeButtonState(true, false, false, false);
        changeInputState(false, false, false);
        changeTextFieldState(false, false, false);
    }

    private void changeButtonState(boolean add, boolean save, boolean edit, boolean update) {
        btnAdd.setEnabled(add);
        btnSave.setEnabled(save);
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
    }

    private void changeInputState(boolean categoryId, boolean categoryName, boolean description) {
        txtCategoryId.setEnabled(categoryId);
        txtCategoryName.setEnabled(categoryName);
        txtCategoryDescription.setEnabled(description);
    }

    private void changeTextFieldState(boolean categoryId, boolean categoryName, boolean description) {
        txtCategoryId.setEditable(categoryId);
        txtCategoryName.setEditable(categoryName);
        txtCategoryDescription.setEditable(description);
    }

    private void clearInput() {
        txtCategoryId.setText("");
        txtCategoryName.setText("");
        txtCategoryDescription.setText("");
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã danh mục", "Tên danh mục", "Mô tả", "Ngày tạo", "Sửa đổi lần cuối"
        });
    }

    private void loadTable() {
        List<Category> categoryList = getCategoryList();
        model.setRowCount(0);
        for (Category category : categoryList) {
            model.addRow(new Object[] {
                    category.getCategory_id(),
                    category.getCategory_name(),
                    category.getDescription(),
                    calendarToString(category.getCreate_date()),
                    calendarToString(category.getLast_modified_date())
            });
        }
    }

    private List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try {
            ApiCategoryHelper apiCategoryHelper = new ApiCategoryHelper(getToken());
            CategoryWrapper categoryWrapper = apiCategoryHelper.read();
            categoryList = categoryWrapper.data;

            tableCategoryList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    private void createCategory(Category category) {
        try {
            ApiCategoryHelper apiCategoryHelper = new ApiCategoryHelper(getToken());
            apiCategoryHelper.create(category);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Thêm danh mục hàng hóa thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("already exists")) {
                MessageBox.showErrorMessage((adminFrame), "Danh mục hàng hóa đã tồn tại");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau");
            }
            e.printStackTrace();
        }
    }

    private void updateCategory(Category category) {
        try {
            ApiCategoryHelper apiCategoryHelper = new ApiCategoryHelper(getToken());
            apiCategoryHelper.update(category);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Cập nhật danh mục hàng hóa thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("not found")) {
                MessageBox.showErrorMessage((adminFrame), "Danh mục hàng hóa không tồn tại");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau");
            }
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

        ppmCategoryList = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ppmDelete = new javax.swing.JMenuItem();
        labelState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelCategoryId = new javax.swing.JLabel();
        labelCategoryName = new javax.swing.JLabel();
        labelDescription = new javax.swing.JLabel();
        txtCategoryId = new javax.swing.JTextField();
        txtCategoryName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCategoryDescription = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCategoryList = new javax.swing.JTable();

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmCategoryList.add(ppmRefresh);
        ppmCategoryList.add(jSeparator3);

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmCategoryList.add(ppmDelete);

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý danh mục hàng hóa");

        labelCategoryId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCategoryId.setText("Mã danh mục:");

        labelCategoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCategoryName.setText("Tên danh mục:");

        labelDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDescription.setText("Mô tả danh mục:");

        txtCategoryId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCategoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCategoryDescription.setColumns(20);
        txtCategoryDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCategoryDescription.setRows(5);
        jScrollPane1.setViewportView(txtCategoryDescription);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/new_20.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save_20.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
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

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/update_20.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableCategoryList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableCategoryList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã danh mục", "Tên danh mục", "Mô tả", "Ngày tạo", "Sửa đổi lần cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCategoryList.setComponentPopupMenu(ppmCategoryList);
        tableCategoryList.setRowHeight(30);
        tableCategoryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCategoryListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCategoryList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDescription)
                            .addComponent(labelCategoryId))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelCategoryName)
                                .addGap(18, 18, 18)
                                .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCategoryId)
                            .addComponent(txtCategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCategoryName)
                            .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescription)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(labelState)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelState, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        labelState.setText("Thêm danh mục hàng hóa");
        changeButtonState(false, true, false, false);
        changeInputState(false, true, true);
        changeTextFieldState(false, true, true);
        txtCategoryName.requestFocus();
        clearInput();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String valid = CategoryValidator.validate(txtCategoryName, txtCategoryDescription);
            if (valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Category category = new Category(txtCategoryName.getText(), txtCategoryDescription.getText());
            createCategory(category);
            changeButtonState(true, false, false, false);
            clearInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txtCategoryId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng nhập mã danh mục hàng hóa");
            return;
        } else {
            String validId = CategoryValidator.validateId(txtCategoryId);
            if (validId != null) {
                MessageBox.showErrorMessage(adminFrame, validId);
                return;
            }
        }
        labelState.setText("Chỉnh sửa danh mục hàng hóa");
        changeButtonState(true, false, false, true);
        changeInputState(true, true, true);
        changeTextFieldState(false, true, true);

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = CategoryValidator.validate(txtCategoryName, txtCategoryDescription);
            if (valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Category category = new Category(txtCategoryName.getText(), txtCategoryDescription.getText());
            category.setCategory_id(Integer.parseInt(txtCategoryId.getText()));
            updateCategory(category);
            changeButtonState(true, false, false, false);
            changeInputState(false, false, false);
            clearInput();
            labelState.setText("Quản lý danh mục hàng hóa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableCategoryListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCategoryListMouseClicked
        int row = tableCategoryList.getSelectedRow();
        TableModel model = tableCategoryList.getModel();
        txtCategoryId.setText(model.getValueAt(row, 0).toString());
        txtCategoryName.setText(model.getValueAt(row, 1).toString());
        txtCategoryDescription.setText(model.getValueAt(row, 2).toString());
        changeInputState(true, true, true);
        changeTextFieldState(false, false, false);
        changeButtonState(true, false, true, false);
    }//GEN-LAST:event_tableCategoryListMouseClicked

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            int row = tableCategoryList.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn danh mục hàng hóa cần xóa");
                return;
            } else {
                if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa danh mục hàng hóa này không?") == 0) {
                    int categoryId = Integer.parseInt(tableCategoryList.getValueAt(row, 0).toString());
                    ApiCategoryHelper apiCategoryHelper = new ApiCategoryHelper(getToken());
                    apiCategoryHelper.delete(categoryId);
                    loadTable();
                    MessageBox.showInfoMessage(adminFrame, "Xóa danh mục hàng hóa thành công");
                    clearInput();
                    changeButtonState(true, false, false, false);
                    changeInputState(false, false, false);
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("child record")) {
                MessageBox.showErrorMessage(adminFrame, "This bill has child record, can't not delete, please delete child record first");
            } else if(e.getMessage().contains("not found")) {
                MessageBox.showErrorMessage(adminFrame, "Danh mục hàng hóa không tồn tại");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau");
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_ppmDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel labelCategoryId;
    private javax.swing.JLabel labelCategoryName;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelState;
    private javax.swing.JPopupMenu ppmCategoryList;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JTable tableCategoryList;
    private javax.swing.JTextArea txtCategoryDescription;
    private javax.swing.JTextField txtCategoryId;
    private javax.swing.JTextField txtCategoryName;
    // End of variables declaration//GEN-END:variables
}
