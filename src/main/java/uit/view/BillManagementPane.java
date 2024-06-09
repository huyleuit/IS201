/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.bill.Bill;
import com.uitprojects.is210.bill.BillApiHelper;
import com.uitprojects.is210.bill_detail.BillDetail;
import com.uitprojects.is210.bill_detail.BillDetailApiHelper;
import com.uitprojects.is210.bill_detail.BillDetailWrapper;
import com.uitprojects.is210.payment.Payment;
import com.uitprojects.is210.payment.PaymentApiHelper;
import uit.Util.MessageBox;
import uit.Util.NumberFormat;
import uit.validator.BillDetailValidator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;

/**
 *
 * @author quochuy
 */
public class BillManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;
    BillDetailWrapper billDetailWrapper = new BillDetailWrapper();

    /**
     * Creates new form AddBillPane
     */
    public BillManagementPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        tableListProduct.setModel(model);
        defaultButtonState();
    }

    public BillManagementPane(AdminFrame adminFrame, Bill bill) {
        this.adminFrame = adminFrame;
        billDetailWrapper.data = new ArrayList<>(getBillDetailList(bill.getBill_id()));
        initComponents();
        txtBillId.setText(String.valueOf(bill.getBill_id()));
        txtBillId.setEditable(false);
        initTable();
        loadTable(bill.getBill_id());
        getBillPayment(bill.getBill_id());
        if(txtPaymentStatus.getSelectedIndex() == 1 || txtPaymentStatus.getSelectedIndex() == 2) {
            changeButtonState(true, false, false, false, false, false);
            changeFieldState(false, false, false, false, false, false, false, false);
        } else {
            changeButtonState(true, true, false, false,  true, true);
            changeFieldState(true, true, true, true, true, true, true, true);
        }
    }

    private void defaultButtonState() {
        changeButtonState(true, false, false, false,  false, false);
        changeFieldState(false, false, false, false, false, false, false, false);
        txtBillId.setEditable(false);
    }

    private void changeButtonState(boolean addBill, boolean deleteBill, boolean addBillDetail, boolean saveBillDetail, boolean updateBillDetail, boolean updatePayment) {
        btnAddBill.setEnabled(addBill);
        btnDeleteBill.setEnabled(deleteBill);
        btnAddBillProduct.setEnabled(addBillDetail);
        btnSaveBillDetail.setEnabled(saveBillDetail);
        btnUpdateBillProduct.setEnabled(updateBillDetail);
    }

    private void changeFieldState(boolean billId, boolean productId, boolean shipmentId, boolean quantity, boolean proId, boolean paymentMethod, boolean received, boolean paymentStatus) {
        txtBillId.setEnabled(billId);
        txtProductId.setEnabled(productId);
        txtShipmentId.setEnabled(shipmentId);
        txtQuantity.setEnabled(quantity);
        txtProId.setEnabled(proId);
        txtPaymentMethod.setEnabled(paymentMethod);
        txtReceived.setEnabled(received);
        txtPaymentStatus.setEnabled(paymentStatus);
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã sản phẩm", "Mã lô hàng", "Số lượng", "Giá", "Thành tiền"
        });
    }

    private void loadTable(int billId) {
        List<BillDetail> list = getBillDetailList(billId);
        model.setRowCount(0);
        for (BillDetail billDetail : list) {
            model.addRow(new Object[] {
                    billDetail.getGoods_id(), billDetail.getShipment_id(), billDetail.getQuantity(), NumberFormat.intFormat(billDetail.getUnit_price()), NumberFormat.intFormat(billDetail.getTotal())
            });
        }
    }

    private ArrayList<BillDetail> getBillDetailList(int billId) {
        ArrayList<BillDetail> billDetailList = new ArrayList<>();
        try {
            BillDetailApiHelper billDetailApiHelper = new BillDetailApiHelper(getToken());
            BillDetailWrapper billDetailWrapper = billDetailApiHelper.getBillDetail(billId);
            billDetailList = billDetailWrapper.data;

            tableListProduct.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billDetailList;
    }
    
    private void getBillPayment(int billId) {
        try {
            PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
            Payment payment = paymentApiHelper.getpayment(billId);
            labelTotal.setText(NumberFormat.intFormat(payment.getTotal()) + " VNĐ");
            txtProId.setText(String.valueOf(payment.getPro_id()));
            txtDiscount.setText(NumberFormat.intFormat(payment.getDiscounts()));
            txtPaymentMethod.setText(payment.getPayment_method());
            txtReceived.setText(NumberFormat.intFormat(payment.getReceived()));
            txtChange.setText(NumberFormat.intFormat(payment.getChange()));
            getPaymentStatus(payment.getPayment_status());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPaymentStatus(String status) {
        switch (status) {
            case "Processing":
                txtPaymentStatus.setSelectedIndex(0);
                break;
            case "Success":
                txtPaymentStatus.setSelectedIndex(1);
                break;
            case "Failed":
                txtPaymentStatus.setSelectedIndex(2);
                break;
        }
    }

    private String getPaymentStatus() {
        switch (txtPaymentStatus.getSelectedIndex()) {
            case 0:
                return "Processing";
            case 1:
                return "Success";
            case 2:
                return "Failed";
        }
        return null;
    }

    private void clearInput() {
        txtBillId.setText("");
        txtProductId.setText("");
        txtShipmentId.setText("");
        txtQuantity.setText("");
        txtDiscount.setText("0");
        txtPaymentMethod.setText("");
        txtReceived.setText("");
        txtChange.setText("0");
        txtPaymentStatus.setSelectedIndex(0);
    }

    private void addBillDetail(List<BillDetail> billDetailList) {
        try {
            BillDetailWrapper billDetailWrapper = new BillDetailWrapper();
            billDetailWrapper.data.addAll(billDetailList);
            BillDetailApiHelper billDetailApiHelper = new BillDetailApiHelper(getToken());
            billDetailApiHelper.addGoods(Integer.parseInt(txtBillId.getText()), billDetailWrapper);
            MessageBox.showInfoMessage(adminFrame, "Lưu hoá đơn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    private void loadTotal(int billId) {
        try {
            PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
            Payment payment = paymentApiHelper.getpayment(billId);
            labelTotal.setText(NumberFormat.intFormat(payment.getTotal()) + " VNĐ");
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

        ppmGoodList = new javax.swing.JPopupMenu();
        ppmDelete = new javax.swing.JMenuItem();
        labelBillManagement = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelBillId = new javax.swing.JLabel();
        txtBillId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListProduct = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        labelTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtReceived = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtChange = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPaymentStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtShipmentId = new javax.swing.JTextField();
        txtPaymentMethod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAddBill = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnAddBillProduct = new javax.swing.JButton();
        btnUpdateBillProduct = new javax.swing.JButton();
        btnDeleteBill = new javax.swing.JButton();
        btnSaveBillDetail = new javax.swing.JButton();

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmGoodList.add(ppmDelete);

        setPreferredSize(new java.awt.Dimension(900, 600));

        labelBillManagement.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelBillManagement.setText("Quản lý hoá đơn");

        labelBillId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelBillId.setText("Mã hoá đơn:");

        txtBillId.setEditable(false);
        txtBillId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tableListProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableListProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        tableListProduct.setComponentPopupMenu(ppmGoodList);
        tableListProduct.setRowHeight(30);
        tableListProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableListProduct);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm:");

        txtProductId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng:");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("1,000,000,000.00 VNĐ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 542, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotal)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Chiết khấu:");

        txtDiscount.setEditable(false);
        txtDiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscount.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Phương thức thanh toán:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tiền nhận được:");

        txtReceived.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtReceived.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtReceived.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReceivedKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReceivedKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tiền thừa:");

        txtChange.setEditable(false);
        txtChange.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtChange.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtChange.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Trạng thái thanh toán:");

        txtPaymentStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPaymentStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang thanh toán", "Thanh toán thành công", "Thanh toán thất bại" }));
        txtPaymentStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtPaymentStatusItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("VNĐ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Mã lô hàng:");

        txtShipmentId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPaymentMethod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPaymentMethod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPaymentMethodKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mã chương trình khuyến mãi:");

        txtProId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProIdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProIdKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("VNĐ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("VNĐ");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAddBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/new_20.png"))); // NOI18N
        btnAddBill.setText("Tạo hoá đơn mới");
        btnAddBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBillActionPerformed(evt);
            }
        });

        btnAddBillProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddBillProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/new_20.png"))); // NOI18N
        btnAddBillProduct.setText("Thêm sản phẩm");
        btnAddBillProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBillProductActionPerformed(evt);
            }
        });

        btnUpdateBillProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdateBillProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/update_20.png"))); // NOI18N
        btnUpdateBillProduct.setText("Cập nhật sản phẩm");
        btnUpdateBillProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBillProductActionPerformed(evt);
            }
        });

        btnDeleteBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDeleteBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        btnDeleteBill.setText("Xoá hoá đơn");
        btnDeleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBillActionPerformed(evt);
            }
        });

        btnSaveBillDetail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSaveBillDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save_20.png"))); // NOI18N
        btnSaveBillDetail.setText("Lưu");
        btnSaveBillDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBillDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(btnAddBillProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateBillProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(btnDeleteBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveBillDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddBill, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteBill, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddBillProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveBillDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateBillProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtChange)
                                    .addComponent(txtReceived, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtProId, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1))
                                    .addComponent(txtPaymentStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelBillId)
                                .addGap(27, 27, 27)
                                .addComponent(txtBillId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(322, 322, 322)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33)
                                .addComponent(txtShipmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBillId)
                    .addComponent(txtBillId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(txtShipmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtProId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtReceived, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelBillManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBillManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBillActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnAddBillActionPerformed
        try {
            clearInput();
            BillApiHelper billApiHelper = new BillApiHelper(getToken());
            Bill bill = billApiHelper.createBill();
            txtBillId.setText(String.valueOf(bill.getBill_id()));
            labelBillManagement.setText("Thêm hoá đơn");
            changeButtonState(false, true, true, false, false, false);
            changeFieldState(true, true, true, true, false, false, false, false);
            txtBillId.setEditable(false);
            model.setRowCount(0);
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
        }

    }//GEN-LAST:event_btnAddBillActionPerformed

    private void btnDeleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBillActionPerformed
        if(txtBillId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn hóa đơn cần xóa!");
        } else {
            if (MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa hoá đơn này?") == 0) {
                try {
                    BillApiHelper billApiHelper = new BillApiHelper(getToken());
                    billApiHelper.delete(Integer.parseInt(txtBillId.getText()));
                    MessageBox.showInfoMessage(adminFrame, "Xóa hoá đơn thành công!");
                    clearInput();
                    defaultButtonState();
                    labelBillManagement.setText("Quản lý hoá đơn");
                    model.setRowCount(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
                    clearInput();
                }
            }
        }
    }//GEN-LAST:event_btnDeleteBillActionPerformed

    private void btnAddBillProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBillProductActionPerformed
        try {
            String valid = BillDetailValidator.validate(txtProductId, txtShipmentId, txtQuantity);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }
            BillDetail billDetail = new BillDetail(Integer.parseInt(txtProductId.getText()), Integer.parseInt(txtShipmentId.getText()), Integer.parseInt(txtQuantity.getText()));
            billDetailWrapper.data.add(billDetail);
            model.addRow(new Object[] {
                    Integer.parseInt(txtProductId.getText()), Integer.parseInt(txtShipmentId.getText()), Integer.parseInt(txtQuantity.getText())
            });
            MessageBox.showInfoMessage(adminFrame, "Thêm sản phẩm thành công!");

            changeButtonState(false, true, true, true, false, false);
            txtProductId.setText("");
            txtShipmentId.setText("");
            txtQuantity.setText("");
            loadTotal(Integer.parseInt(txtBillId.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddBillProductActionPerformed

    private void btnUpdateBillProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBillProductActionPerformed
        try {
            String valid = BillDetailValidator.validate(txtProductId, txtShipmentId, txtQuantity);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }
            BillDetailApiHelper billDetailApiHelper = new BillDetailApiHelper(getToken());
            BillDetail billDetail = billDetailApiHelper.updateGoodsQuantity(Integer.parseInt(txtBillId.getText()), Integer.parseInt(txtProductId.getText()), Integer.parseInt(txtQuantity.getText()));
            loadTable(Integer.parseInt(txtBillId.getText()));
            MessageBox.showInfoMessage(adminFrame, "Cập nhật sản phẩm thành công!");

            changeButtonState(false, true, true, false, true, true);
            txtProductId.setText("");
            txtShipmentId.setText("");
            txtQuantity.setText("");
            loadTotal(Integer.parseInt(txtBillId.getText()));
        } catch (Exception e) {
            if(e.getMessage().contains("Payment is already done")) {
                MessageBox.showErrorMessage(adminFrame, "Không thể cập nhật sản phẩm cho hoá đơn đã thanh toán!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateBillProductActionPerformed

    private void tableListProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListProductMouseClicked
        int row = tableListProduct.getSelectedRow();
        TableModel model = tableListProduct.getModel();
        txtProductId.setText(model.getValueAt(row, 0).toString());
        txtShipmentId.setText(model.getValueAt(row, 1).toString());
        txtQuantity.setText(model.getValueAt(row, 2).toString());
        if(model.getValueAt(row, 3).toString().isEmpty() || model.getValueAt(row, 4).toString().isEmpty()) {
            changeButtonState(false, true, true, false, false, true);
            changeFieldState(true, true, true, true, false, false, false, false);
        } else {
            changeButtonState(true, true, true, false, true, true);
            changeFieldState(true, true, true, true, true, true, true, true);
        }
        txtBillId.setEditable(false);
    }//GEN-LAST:event_tableListProductMouseClicked

    private void btnSaveBillDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBillDetailActionPerformed
        try {
            addBillDetail(billDetailWrapper.data);
            txtProductId.setText("");
            txtShipmentId.setText("");
            txtQuantity.setText("");
            defaultButtonState();
            loadTable(Integer.parseInt(txtBillId.getText()));
            loadTotal(Integer.parseInt(txtBillId.getText()));
            changeButtonState(false, true, false,false, true, true);
            changeFieldState(true, true, true, true, true, true, true, true);
            txtBillId.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveBillDetailActionPerformed

    private void txtProIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProIdKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            txtProId.setEditable(false);
        } else {
            txtProId.setEditable(true);
        }
    }//GEN-LAST:event_txtProIdKeyTyped

    private void txtReceivedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceivedKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            txtReceived.setEditable(false);
        } else {
            txtReceived.setEditable(true);
        }
    }//GEN-LAST:event_txtReceivedKeyTyped

    private void txtProIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProIdKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if(txtProId.getText().isEmpty()) {
                    MessageBox.showErrorMessage(adminFrame, "Vui lòng nhập mã chương trình khuyến mãi!");
                    return;
                }
                PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
                Payment payment = paymentApiHelper.updatePaymentPro_id(Integer.parseInt(txtBillId.getText()), Integer.parseInt(txtProId.getText()));
                txtDiscount.setText(NumberFormat.intFormat(payment.getDiscounts()));
                MessageBox.showInfoMessage(adminFrame, "Thêm chương trình khuyến mãi thành công!");
            } catch (Exception e) {
                if(e.getMessage().contains("payment_id not found")) {
                    MessageBox.showErrorMessage(adminFrame, "Không tìm thấy hoá đơn!");
                } else if(e.getMessage().contains("Promotion ID not found")) {
                    MessageBox.showErrorMessage(adminFrame, "Không tìm thấy chương trình khuyến mãi!");
                } else if(e.getMessage().contains("Payment already have promotion")) {
                    MessageBox.showErrorMessage(adminFrame, "Hoá đơn đã có chương trình khuyến mãi!");
                } else {
                    MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!\n" + e.getMessage());
                }
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtProIdKeyPressed

    private void txtReceivedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceivedKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if(txtReceived.getText().isEmpty()) {
                    MessageBox.showErrorMessage(adminFrame, "Vui lòng nhập số tiền nhận được!");
                    return;
                }
                PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
                Payment payment = paymentApiHelper.updatePaymentReceive(Integer.parseInt(txtBillId.getText()), Integer.parseInt(txtReceived.getText()));
                txtChange.setText(NumberFormat.intFormat(payment.getChange()));
                txtReceived.setText(NumberFormat.intFormat(payment.getReceived()));
                MessageBox.showInfoMessage(adminFrame, "Cập nhật tiền nhận thành công!");
            } catch (Exception e) {
                if(e.getMessage().contains("payment_id not found")) {
                    MessageBox.showErrorMessage(adminFrame, "Không tìm thấy hoá đơn!");
                } else if(e.getMessage().contains("Received less than actual payment")) {
                    MessageBox.showErrorMessage(adminFrame, "Số tiền nhận được nhỏ hơn số tiền cần thanh toán!");
                } else {
                    MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!\n" + e.getMessage());
                }
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtReceivedKeyPressed

    private void txtPaymentMethodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaymentMethodKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
                Payment payment = paymentApiHelper.updatePaymentMethod(Integer.parseInt(txtBillId.getText()), txtPaymentMethod.getText());
                MessageBox.showInfoMessage(adminFrame, "Cập nhật phương thức thanh toán thành công!");
            } catch (Exception e) {
                if(e.getMessage().contains("payment_id not found")) {
                    MessageBox.showErrorMessage(adminFrame, "Không tìm thấy hoá đơn!");
                } else {
                    MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
                }
            }
        }
    }//GEN-LAST:event_txtPaymentMethodKeyPressed

    private void txtPaymentStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtPaymentStatusItemStateChanged
        try {
            if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn thay đổi trạng thái thanh toán?") == 0) {
                PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
                Payment payment = paymentApiHelper.updatePaymentStatus(Integer.parseInt(txtBillId.getText()), getPaymentStatus());
                MessageBox.showInfoMessage(adminFrame, "Cập nhật trạng thái thanh toán thành công!");
                if(txtPaymentStatus.getSelectedIndex() == 1) {
                    changeButtonState(true, false, false, false, false, false);
                    changeFieldState(false, false, false, false, false, false, false, false);
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("payment_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Không tìm thấy hoá đơn!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!\n" + e.getMessage());
            }
        }
    }//GEN-LAST:event_txtPaymentStatusItemStateChanged

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa sản phẩm này?") == 0) {
                int row = tableListProduct.getSelectedRow();
                int productId = Integer.parseInt(tableListProduct.getModel().getValueAt(row, 0).toString());
                if(getBillDetailList(Integer.parseInt(txtBillId.getText())).size() == 0) {
                    billDetailWrapper.data.removeIf(billDetail -> billDetail.getGoods_id() == productId);
                    model.removeRow(row);
                } else {
                    BillDetailApiHelper billDetailApiHelper = new BillDetailApiHelper(getToken());
                    boolean result = billDetailApiHelper.removeGoods(Integer.parseInt(txtBillId.getText()), productId);
                    if(result) {
                        loadTable(Integer.parseInt(txtBillId.getText()));
                    }
                }
                MessageBox.showInfoMessage(adminFrame, "Xóa sản phẩm thành công!");
                loadTotal(Integer.parseInt(txtBillId.getText()));
                PaymentApiHelper paymentApiHelper = new PaymentApiHelper(getToken());
                Payment payment = paymentApiHelper.getpayment(Integer.parseInt(txtBillId.getText()));
                txtDiscount.setText(NumberFormat.intFormat(payment.getDiscounts()));
                txtReceived.setText(NumberFormat.intFormat(payment.getReceived()));
                txtChange.setText(NumberFormat.intFormat(payment.getChange()));
            }
        } catch (Exception e) {
            if(e.getMessage().contains("not found")) {
                MessageBox.showErrorMessage(adminFrame, "Không tìm thấy sản phẩm!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại!");
            }
            e.printStackTrace();
        }
    }//GEN-LAST:event_ppmDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBill;
    private javax.swing.JButton btnAddBillProduct;
    private javax.swing.JButton btnDeleteBill;
    private javax.swing.JButton btnSaveBillDetail;
    private javax.swing.JButton btnUpdateBillProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelBillId;
    private javax.swing.JLabel labelBillManagement;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JPopupMenu ppmGoodList;
    private javax.swing.JTable tableListProduct;
    private javax.swing.JTextField txtBillId;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtPaymentMethod;
    private javax.swing.JComboBox<String> txtPaymentStatus;
    private javax.swing.JTextField txtProId;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtReceived;
    private javax.swing.JTextField txtShipmentId;
    // End of variables declaration//GEN-END:variables
}
