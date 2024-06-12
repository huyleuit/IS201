/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.good.Good;
import com.uitprojects.is210.good.GoodApiHelper;
import com.uitprojects.is210.good.GoodWrapper;
import uit.Util.MessageBox;
import uit.validator.GoodValidator;

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
public class GoodsManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form GoodManagementPane
     */
    public GoodsManagementPane(AdminFrame adminFrame) {
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

    private void changeInputState(boolean goodsId, boolean goodsName, boolean categoryId) {
        txtGoodsId.setEnabled(goodsId);
        txtGoodsName.setEnabled(goodsName);
        txtCategoryId.setEnabled(categoryId);
    }

    private void changeTextFieldState(boolean goodsId, boolean goodsName, boolean categoryId) {
        txtGoodsId.setEditable(goodsId);
        txtGoodsName.setEditable(goodsName);
        txtCategoryId.setEditable(categoryId);
    }

    private void clearInput() {
        txtGoodsId.setText("");
        txtGoodsName.setText("");
        txtCategoryId.setText("");
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã hàng hóa", "Tên hàng hóa", "Mã danh mục hàng hóa", "Ngày tạo", "Sửa đổi lần cuối"
        });
    }

    private void loadTable() {
        List<Good> goodsList = getGoodsList();
        model.setRowCount(0);
        for (Good good : goodsList) {
            model.addRow(new Object[] {
                    good.getGoods_id(),
                    good.getGoods_name(),
                    good.getCategory_id(),
                    calendarToString(good.getCreate_date()),
                    calendarToString(good.getLast_modified_date())
            });
        }
    }

    private List<Good> getGoodsList() {
        List<Good> goodsList = new ArrayList<>();
        try {
            GoodApiHelper goodApiHelper = new GoodApiHelper(getToken());
            GoodWrapper goodWrapper = goodApiHelper.read();
            goodsList = goodWrapper.data;

            tableGoodsList.setModel(model);
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(this, "Bạn không có quyền truy cập vào chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false, false, false);
                clearInput();
            } else {
                MessageBox.showErrorMessage(this, "Có lỗi xảy ra, vui lòng thử lại!\n" + e.getMessage());
            }
            e.printStackTrace();
        }
        return goodsList;
    }

    private void createGood(Good good) {
        try {
            GoodApiHelper goodApiHelper = new GoodApiHelper(getToken());
            goodApiHelper.create(good);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Thêm hàng hóa thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false, false, false);
                clearInput();
            } else if(e.getMessage().contains("goods_name already exists")) {
                MessageBox.showErrorMessage(adminFrame, "Tên hàng hóa đã tồn tại");
            } else if(e.getMessage().contains("500")) {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\nMã lỗi: 500");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    private void updateGood(Good good) {
        try {
            GoodApiHelper goodApiHelper = new GoodApiHelper(getToken());
            goodApiHelper.update(good);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Cập nhật hàng hóa thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false, false, false);
                clearInput();
            } else if(e.getMessage().contains("goods_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã hàng hóa không tồn tại");
            } else if(e.getMessage().contains("500")) {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\nMã lỗi: 500");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
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

        ppmTableListGoods = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ppmDelete = new javax.swing.JMenuItem();
        labelState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelGoodsId = new javax.swing.JLabel();
        txtGoodsId = new javax.swing.JTextField();
        labelGoodsName = new javax.swing.JLabel();
        txtGoodsName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGoodsList = new javax.swing.JTable();
        txtCategoryId = new javax.swing.JTextField();

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmTableListGoods.add(ppmRefresh);
        ppmTableListGoods.add(jSeparator3);

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmTableListGoods.add(ppmDelete);

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý hàng hóa");

        labelGoodsId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelGoodsId.setText("Mã hàng hóa:");

        txtGoodsId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelGoodsName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelGoodsName.setText("Tên hàng hóa:");

        txtGoodsName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã danh mục hàng hóa:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableGoodsList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableGoodsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng hóa", "Tên hàng hóa", "Mã danh mục hàng hóa", "Ngày tạo", "Sửa đổi lần cuối "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        tableGoodsList.setComponentPopupMenu(ppmTableListGoods);
        tableGoodsList.setRowHeight(30);
        tableGoodsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGoodsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableGoodsList);

        txtCategoryId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGoodsId)
                            .addComponent(labelGoodsName))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGoodsId)
                            .addComponent(txtGoodsName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtCategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                .addGap(18, 18, 18)
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
                            .addComponent(labelGoodsId)
                            .addComponent(txtGoodsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGoodsName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtCategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelGoodsName))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
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
        labelState.setText("Thêm hàng hóa");
        changeButtonState(false, true, false, false);
        changeInputState(false, true, true);
        changeTextFieldState(false, true, true);
        txtGoodsName.requestFocus();
        clearInput();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String valid = GoodValidator.validate(txtGoodsName, txtCategoryId);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Good good = new Good(txtGoodsName.getText(), Integer.parseInt(txtCategoryId.getText()));
            createGood(good);
            changeButtonState(true, false, false, false);
            changeInputState(false, false, false);
            clearInput();
            labelState.setText("Quản lý hàng hóa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txtGoodsId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn hàng hóa cần chỉnh sửa");
            return;
        } else {
            String validId = GoodValidator.validateId(txtGoodsId);
            if(validId != null) {
                MessageBox.showErrorMessage(adminFrame, validId);
                return;
            }
        }
        labelState.setText("Chỉnh sửa hàng hóa");
        changeButtonState(true, false, false, true);
        changeInputState(true, true, true);
        changeTextFieldState(false, true, true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = GoodValidator.validate(txtGoodsName, txtCategoryId);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Good good = new Good(txtGoodsName.getText(), Integer.parseInt(txtCategoryId.getText()));
            good.setGoods_id(Integer.parseInt(txtGoodsId.getText()));
            updateGood(good);
            changeButtonState(true, false, false, false);
            changeInputState(false, false, false);
            clearInput();
            labelState.setText("Quản lý hàng hóa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableGoodsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGoodsListMouseClicked
        int row = tableGoodsList.getSelectedRow();
        TableModel model = tableGoodsList.getModel();
        txtGoodsId.setText(model.getValueAt(row, 0).toString());
        txtGoodsName.setText(model.getValueAt(row, 1).toString());
        txtCategoryId.setText(model.getValueAt(row, 2).toString());
        changeButtonState(true, false, true, false);
        changeInputState(true, true, true);
        changeTextFieldState(false, false, false);
    }//GEN-LAST:event_tableGoodsListMouseClicked

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            int row = tableGoodsList.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn hàng hóa cần xóa");
                return;
            } else {
                if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa hàng hóa này không?") == 0) {
                    int goodsId = Integer.parseInt(tableGoodsList.getValueAt(row, 0).toString());
                    GoodApiHelper goodApiHelper = new GoodApiHelper(getToken());
                    goodApiHelper.delete(goodsId);
                    loadTable();
                    MessageBox.showInfoMessage(adminFrame, "Xóa hàng hóa thành công");
                    clearInput();
                    changeButtonState(true, false, false, false);
                    changeInputState(false, false, false);
                    changeTextFieldState(false, false, false);
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false, false, false);
                clearInput();
            } else if(e.getMessage().contains("goods_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã hàng hóa không tồn tại");
            } else if(e.getMessage().contains("please delete child record first")) {
                MessageBox.showErrorMessage(adminFrame, "Không thể xóa hàng hóa này, vui lòng xóa các bản ghi liên quan trước");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_ppmDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel labelGoodsId;
    private javax.swing.JLabel labelGoodsName;
    private javax.swing.JLabel labelState;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JPopupMenu ppmTableListGoods;
    private javax.swing.JTable tableGoodsList;
    private javax.swing.JTextField txtCategoryId;
    private javax.swing.JTextField txtGoodsId;
    private javax.swing.JTextField txtGoodsName;
    // End of variables declaration//GEN-END:variables
}
