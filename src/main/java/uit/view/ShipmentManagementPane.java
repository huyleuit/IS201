/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.shipment.Shipment;
import com.uitprojects.is210.shipment.ShipmentApiHelper;
import com.uitprojects.is210.shipment.ShipmentWrapper;
import uit.Util.MessageBox;
import uit.Util.NumberFormat;
import uit.validator.ShipmentVadidator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;
import static uit.Util.CalendarToString.calendarToString;
import static uit.Util.DateFormat.vietnameseDateFormat;

/**
 *
 * @author huyle
 */
public class ShipmentManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form ShipmentManagementPane
     */
    public ShipmentManagementPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        loadTable();
        changeButtonState(true, false, false, false);
        changeInputState(false);
        changeTextFieldState(false);
    }

    private void changeButtonState(boolean add, boolean save, boolean edit, boolean update) {
        btnAdd.setEnabled(add);
        btnSave.setEnabled(save);
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
    }

    private void changeInputState(boolean state) {
        txtShipmentId.setEnabled(state);
        txtShipmentName.setEnabled(state);
        txtManufactureDate.setEnabled(state);
        txtExpirationDate.setEnabled(state);
        txtGoodId.setEnabled(state);
        txtQuantity.setEnabled(state);
        txtPrice.setEnabled(state);
        txtDescription.setEnabled(state);
    }

    private void changeTextFieldState(boolean state) {
        txtShipmentId.setEditable(state);
        txtShipmentName.setEditable(state);
        txtGoodId.setEditable(state);
        txtQuantity.setEditable(state);
        txtPrice.setEditable(state);
        txtDescription.setEditable(state);
    }

    private void clearInput() {
        txtShipmentId.setText("");
        txtShipmentName.setText("");
        txtManufactureDate.setDate(null);
        txtExpirationDate.setDate(null);
        txtGoodId.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã lô hàng", "Tên lô hàng", "Ngày sản xuất", "Ngày hết hạn", "Mã hàng hóa", "Số lượng", "Giá", "Mô tả", "Ngày tạo", "Sửa đổi lần cuối"
        });
    }

    private void loadTable() {
        List<Shipment> shipmentList = getShipmentList();
        model.setRowCount(0);
        for (Shipment shipment : shipmentList) {
            model.addRow(new Object[] {
                    shipment.getShipment_id(),
                    shipment.getShipment_name(),
                    vietnameseDateFormat(shipment.getManufacture_date()),
                    vietnameseDateFormat(shipment.getExpiration_date()),
                    shipment.getGood_id(),
                    shipment.getQuantity(),
                    NumberFormat.doubleFormat(shipment.getPrice()),
                    shipment.getDescription(),
                    calendarToString(shipment.getCreate_date()),
                    calendarToString(shipment.getLast_modified_date())
            });
        }
    }

    private List<Shipment> getShipmentList() {
        List<Shipment> shipmentList= new ArrayList<>();
        try {
            ShipmentApiHelper shipmentApiHelper = new ShipmentApiHelper(getToken());
            ShipmentWrapper shipmentWrapper = shipmentApiHelper.read();
            shipmentList = shipmentWrapper.data;

            tableShipmentList.setModel(model);
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(this, "Bạn không có quyền truy cập vào chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false);
                clearInput();
            } else {
                MessageBox.showErrorMessage(this, "Có lỗi xảy ra, vui lòng thử lại!\n" + e.getMessage());
            }
            e.printStackTrace();
        }
        return shipmentList;
    }

    private void createShipment(Shipment shipment) {
        try {
            ShipmentApiHelper shipmentApiHelper = new ShipmentApiHelper(getToken());
            shipmentApiHelper.create(shipment);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Thêm lô hàng thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false);
                clearInput();
            } else if(e.getMessage().contains("shipment_id already exists")) {
                MessageBox.showErrorMessage(adminFrame, "Mã lô hàng đã tồn tại");
            } else if(e.getMessage().contains("500")) {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n Mã lỗi: 500");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    private void updateShipment(Shipment shipment) {
        try {
            ShipmentApiHelper shipmentApiHelper = new ShipmentApiHelper(getToken());
            shipmentApiHelper.update(shipment);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Cập nhật lô hàng thành công");
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false);
                clearInput();
            } else if(e.getMessage().contains("Goods ID not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã hàng hóa không tồn tại");
            } else if(e.getMessage().contains("shipment_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã lô hàng không tồn tại");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
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

        ppmListShipment = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ppmDelete = new javax.swing.JMenuItem();
        labelState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelShipmentId = new javax.swing.JLabel();
        txtShipmentId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtShipmentName = new javax.swing.JTextField();
        labelManufactureDate = new javax.swing.JLabel();
        txtManufactureDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtExpirationDate = new com.toedter.calendar.JDateChooser();
        labelGoodId = new javax.swing.JLabel();
        txtGoodId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableShipmentList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.setToolTipText("");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmListShipment.add(ppmRefresh);
        ppmListShipment.add(jSeparator3);

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmListShipment.add(ppmDelete);

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý lô hàng");

        labelShipmentId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelShipmentId.setText("Mã lô hàng:");

        txtShipmentId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên lô hàng:");

        txtShipmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelManufactureDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelManufactureDate.setText("Ngày sản xuất:");

        txtManufactureDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ngày hết hạn:");

        txtExpirationDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelGoodId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelGoodId.setText("Mã hàng hóa:");

        txtGoodId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số lượng:");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Giá:");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );

        tableShipmentList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableShipmentList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lô hàng", "Tên lô hàng", "Ngày sản xuất", "Ngày hết hạn", "Mã sản phẩm", "Số lượng", "Giá", "Ngày tạo", "Sửa đổi lần cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableShipmentList.setComponentPopupMenu(ppmListShipment);
        tableShipmentList.setRowHeight(30);
        tableShipmentList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShipmentListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableShipmentList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mô tả:");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelManufactureDate)
                                .addGap(18, 18, 18)
                                .addComponent(txtManufactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelShipmentId)
                                    .addComponent(labelGoodId)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtShipmentId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(txtGoodId)
                                            .addComponent(txtPrice))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtShipmentName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(txtQuantity)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelShipmentId)
                                    .addComponent(txtShipmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtShipmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelManufactureDate)
                                        .addComponent(txtManufactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtExpirationDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGoodId)
                            .addComponent(txtGoodId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                        .addGap(27, 27, 27)
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
        clearInput();
        labelState.setText("Thêm lô hàng");
        changeButtonState(false, true, false, false);
        changeInputState(true);
        changeTextFieldState(true);
        txtShipmentId.setEnabled(false);
        txtShipmentName.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
         String valid = ShipmentVadidator.validate(txtShipmentName, txtManufactureDate, txtExpirationDate, txtGoodId, txtQuantity, txtPrice, txtDescription);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Shipment shipment = new Shipment(txtShipmentName.getText(), txtDescription.getText(), Integer.parseInt(txtGoodId.getText()), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()), txtManufactureDate.getDate(), txtExpirationDate.getDate());
            createShipment(shipment);
            changeButtonState(true, false, false, false);
            clearInput();
            changeInputState(false);
            labelState.setText("Quản lý lô hàng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txtShipmentId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn lô hàng cần chỉnh sửa");
            return;
        } else {
            String validId = ShipmentVadidator.validateId(txtShipmentId);
            if(validId != null) {
                MessageBox.showErrorMessage(adminFrame, validId);
                return;
            }
        }
        labelState.setText("Chỉnh sửa lô hàng");
        changeButtonState(true, false, false, true);
        changeInputState(true);
        changeTextFieldState(true);
        txtShipmentId.setEditable(false);
        txtShipmentName.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = ShipmentVadidator.validate(txtShipmentName, txtManufactureDate, txtExpirationDate, txtGoodId, txtQuantity, txtPrice, txtDescription);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Shipment shipment = new Shipment(txtShipmentName.getText(), txtDescription.getText(), Integer.parseInt(txtGoodId.getText()), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()), txtManufactureDate.getDate(), txtExpirationDate.getDate());
            shipment.setShipment_id(Integer.parseInt(txtShipmentId.getText()));
            updateShipment(shipment);
            changeButtonState(true, false, false, false);
            changeInputState(false);
            clearInput();
            labelState.setText("Quản lý lô hàng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableShipmentListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShipmentListMouseClicked
        int row = tableShipmentList.getSelectedRow();
        TableModel model = tableShipmentList.getModel();
        txtShipmentId.setText(model.getValueAt(row, 0).toString());
        txtShipmentName.setText(model.getValueAt(row, 1).toString());
        txtManufactureDate.setDate(getShipmentList().get(row).getManufacture_date());
        txtExpirationDate.setDate(getShipmentList().get(row).getExpiration_date());
        txtGoodId.setText(model.getValueAt(row, 4).toString());
        txtQuantity.setText(model.getValueAt(row, 5).toString());
        txtPrice.setText(model.getValueAt(row, 6).toString());
        txtDescription.setText(getShipmentList().get(row).getDescription());
        changeButtonState(true, false, true, false);
        changeInputState(true);
        changeTextFieldState(false);
    }//GEN-LAST:event_tableShipmentListMouseClicked

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            int row = tableShipmentList.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn lô hàng cần xóa");
                return;
            } else {
                if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa lô hàng này không?") == 0) {
                    int shipmentId = Integer.parseInt(tableShipmentList.getValueAt(row, 0).toString());
                    ShipmentApiHelper shipmentApiHelper = new ShipmentApiHelper(getToken());
                    shipmentApiHelper.delete(shipmentId);
                    loadTable();
                    MessageBox.showInfoMessage(adminFrame, "Xóa lô hàng thành công");
                    clearInput();
                    changeButtonState(true, false, false, false);
                    changeInputState(false);
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("Permission Deny")) {
                MessageBox.showErrorMessage(adminFrame, "Bạn không có quyền thực hiện chức năng này!");
                changeButtonState(false, false, false, false);
                changeInputState(false);
                clearInput();
            } else if(e.getMessage().contains("shipment_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã lô hàng không tồn tại");
            } else if(e.getMessage().contains("Can't not delete because of child record found")) {
                MessageBox.showErrorMessage(adminFrame, "Không thể xóa lô hàng này vì có hàng hóa liên quan");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_ppmDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel labelGoodId;
    private javax.swing.JLabel labelManufactureDate;
    private javax.swing.JLabel labelShipmentId;
    private javax.swing.JLabel labelState;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JPopupMenu ppmListShipment;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JTable tableShipmentList;
    private javax.swing.JTextArea txtDescription;
    private com.toedter.calendar.JDateChooser txtExpirationDate;
    private javax.swing.JTextField txtGoodId;
    private com.toedter.calendar.JDateChooser txtManufactureDate;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtShipmentId;
    private javax.swing.JTextField txtShipmentName;
    // End of variables declaration//GEN-END:variables
}
