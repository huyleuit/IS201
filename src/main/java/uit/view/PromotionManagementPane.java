/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.promotion.Promotion;
import com.uitprojects.is210.promotion.PromotionApiHelper;
import com.uitprojects.is210.promotion.PromotionWrapper;
import com.uitprojects.is210.shipment.ShipmentApiHelper;
import com.uitprojects.is210.shipment.ShipmentWrapper;
import uit.Util.MessageBox;
import uit.validator.PromotionValidator;
import uit.validator.Validator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;
import static uit.Util.CalendarToString.calendarToString;
import static uit.Util.DateFormat.vietnameseDateFormat;

/**
 *
 * @author quochuy
 */
public class PromotionManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form PromotionManagementPane
     */
    public PromotionManagementPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        loadTable();
        changeButtonState(true, false, false, false);
        changeInputState(false);
        changeFieldState(false);
    }

    private void changeButtonState(boolean add, boolean save, boolean edit, boolean update) {
        btnAdd.setEnabled(add);
        btnSave.setEnabled(save);
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
    }

    private void changeInputState(boolean state) {
        txtPromotionId.setEnabled(state);
        txtPromotionName.setEnabled(state);
        txtDiscount.setEnabled(state);
        txtStartDate.setEnabled(state);
        txtEndDate.setEnabled(state);
        txtContent.setEnabled(state);
    }

    private void changeFieldState(boolean state) {
        txtPromotionId.setEditable(state);
        txtPromotionName.setEditable(state);
        txtDiscount.setEditable(state);
        txtStartDate.setEnabled(state);
        txtEndDate.setEnabled(state);
        txtContent.setEditable(state);
    }

    private void clearInput() {
        txtPromotionId.setText("");
        txtPromotionName.setText("");
        txtDiscount.setText("");
        txtStartDate.setDate(null);
        txtEndDate.setDate(null);
        txtContent.setText("");
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã chương trình", "Tên chương trình", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Ngày tạo", "Sửa đổi lần cuối"
        });
    }

    private void loadTable() {
        List<Promotion> promotionList = getPromotionList();
        model.setRowCount(0);
        for(Promotion promotion : promotionList) {
            model.addRow(new Object[] {
                    promotion.getPro_id(),
                    promotion.getPro_name(),
                    promotion.getDiscount(),
                    vietnameseDateFormat(promotion.getStart_date()),
                    vietnameseDateFormat(promotion.getEnd_date()),
                    promotion.getContent(),
                    calendarToString(promotion.getCreate_date()),
                    calendarToString(promotion.getLast_modified_date())
            });
        }
    }

    private List<Promotion> getPromotionList() {
        List<Promotion> promotionList = new ArrayList<>();
        try {
            PromotionApiHelper promotionApiHelper = new PromotionApiHelper(getToken());
            PromotionWrapper promotionWrapper = promotionApiHelper.read();
            promotionList = promotionWrapper.data;

            tablePromotionList.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promotionList;
    }

    private void create(Promotion promotion) {
        try {
            PromotionApiHelper promotionApiHelper = new PromotionApiHelper(getToken());
            promotionApiHelper.create(promotion);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Thêm chương trình khuyến mãi thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("pro_name already exists")) {
                MessageBox.showErrorMessage(adminFrame, "Tên chương trình khuyến mãi đã tồn tại");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    private void update(Promotion promotion) {
        try {
            PromotionApiHelper promotionApiHelper = new PromotionApiHelper(getToken());
            promotionApiHelper.update(promotion);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Cập nhật chương trình khuyến mãi thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("pro_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã chương trình khuyến mãi không tồn tại");
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

        ppmTableListPromotion = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ppmDelete = new javax.swing.JMenuItem();
        labelState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelPromotionId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelDiscount = new javax.swing.JLabel();
        txtPromotionId = new javax.swing.JTextField();
        txtPromotionName = new javax.swing.JTextField();
        txtDiscount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        labelStartDate = new javax.swing.JLabel();
        labelEndDate = new javax.swing.JLabel();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        txtEndDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePromotionList = new javax.swing.JTable();

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmTableListPromotion.add(ppmRefresh);
        ppmTableListPromotion.add(jSeparator3);

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmTableListPromotion.add(ppmDelete);

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý chương trình khuyến mãi");

        labelPromotionId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelPromotionId.setText("Mã chương trình:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên chương trình:");

        labelDiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDiscount.setText("Giảm giá:");

        txtPromotionId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPromotionName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nội dung:");

        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        labelStartDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelStartDate.setText("Ngày bắt đầu:");

        labelEndDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelEndDate.setText("Ngày kết thúc:");

        txtStartDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEndDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
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
                .addGap(21, 21, 21)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablePromotionList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablePromotionList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chương trình", "Tên chương trình", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Ngày tạo", "Sửa đổi lần cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePromotionList.setComponentPopupMenu(ppmTableListPromotion);
        tablePromotionList.setRowHeight(30);
        tablePromotionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePromotionListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablePromotionList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPromotionId)
                            .addComponent(jLabel2)
                            .addComponent(labelStartDate)
                            .addComponent(jLabel3))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtPromotionName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPromotionId, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDiscount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelEndDate)
                                .addGap(18, 18, 18)
                                .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                            .addComponent(labelPromotionId)
                            .addComponent(txtPromotionId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtPromotionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDiscount))
                                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelStartDate)
                                        .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelEndDate))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(labelState))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelState, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        labelState.setText("Thêm chương trình khuyến mãi");
        clearInput();
        changeButtonState(false, true, true, false);
        changeInputState(true);
        changeFieldState(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String valid = PromotionValidator.validate(txtPromotionName, txtDiscount, txtStartDate, txtEndDate, txtContent);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Promotion promotion = new Promotion(txtPromotionName.getText(), Double.parseDouble(txtDiscount.getText()), txtContent.getText(), txtStartDate.getDate(), txtEndDate.getDate());
            create(promotion);
            changeButtonState(true, false, false, false);
            clearInput();
            changeInputState(false);
            labelState.setText("Quản lý chương trình khuyến mãi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txtPromotionId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng nhập mã chương trình khuyến mãi");
            return;
        } else {
            if(Validator.isNotNumber(txtPromotionId) || Validator.isNotBiggerThanZero(txtPromotionId)) {
                MessageBox.showErrorMessage(adminFrame, "Mã chương trình khuyến mãi không hợp lệ");
                return;
            }
        }
        labelState.setText("Chỉnh sửa chương trình khuyến mãi");
        changeButtonState(true, false, false, true);
        changeInputState(true);
        changeFieldState(true);
        txtPromotionId.setEditable(false);
        txtPromotionName.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = PromotionValidator.validate(txtPromotionName, txtDiscount, txtStartDate, txtEndDate, txtContent);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Promotion promotion = new Promotion(txtPromotionName.getText(), Double.parseDouble(txtDiscount.getText()), txtContent.getText(), txtStartDate.getDate(), txtEndDate.getDate());
            promotion.setPro_id(Integer.parseInt(txtPromotionId.getText()));
            update(promotion);
            changeButtonState(true, false, false, false);
            changeInputState(false);
            clearInput();
            labelState.setText("Quản lý chương trình khuyến mãi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tablePromotionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePromotionListMouseClicked
        int row = tablePromotionList.getSelectedRow();
        TableModel model = tablePromotionList.getModel();
        txtPromotionId.setText(model.getValueAt(row, 0).toString());
        txtPromotionName.setText(model.getValueAt(row, 1).toString());
        txtDiscount.setText(model.getValueAt(row, 2).toString());
        txtStartDate.setDate(getPromotionList().get(row).getStart_date());
        txtEndDate.setDate(getPromotionList().get(row).getEnd_date());
        txtContent.setText(model.getValueAt(row, 5).toString());
        changeButtonState(true, false, true, false);
        changeInputState(true);
        changeFieldState(false);
    }//GEN-LAST:event_tablePromotionListMouseClicked

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            int row = tablePromotionList.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn chương trình khuyến mãi cần xóa");
                return;
            } else {
                if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa chương trình khuyến mãi này không?") == 0) {
                    int promotionId = Integer.parseInt(tablePromotionList.getValueAt(row, 0).toString());
                    PromotionApiHelper promotionApiHelper = new PromotionApiHelper(getToken());
                    promotionApiHelper.delete(promotionId);
                    loadTable();
                    MessageBox.showInfoMessage(adminFrame, "Xóa chương trình khuyến mãi thành công");
                    clearInput();
                    changeButtonState(true, false, false, false);
                    changeInputState(false);
                    labelState.setText("Quản lý chương trình khuyến mãi");
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("pro_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã chương trình khuyến mãi không tồn tại");
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel labelDiscount;
    private javax.swing.JLabel labelEndDate;
    private javax.swing.JLabel labelPromotionId;
    private javax.swing.JLabel labelStartDate;
    private javax.swing.JLabel labelState;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JPopupMenu ppmTableListPromotion;
    private javax.swing.JTable tablePromotionList;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtDiscount;
    private com.toedter.calendar.JDateChooser txtEndDate;
    private javax.swing.JTextField txtPromotionId;
    private javax.swing.JTextField txtPromotionName;
    private com.toedter.calendar.JDateChooser txtStartDate;
    // End of variables declaration//GEN-END:variables
}
