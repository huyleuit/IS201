/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uit.view;

import com.uitprojects.is210.membership.Membership;
import com.uitprojects.is210.membership.MembershipApiHelper;
import com.uitprojects.is210.membership.MembershipWrapper;
import uit.Util.MessageBox;
import uit.validator.MembershipValidator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static uit.Token.getToken;
import static uit.Util.CalendarToString.calendarToString;
import static uit.Util.DateFormat.vietnameseDateFormat;
import static uit.Util.DateFormat.vietnameseDateFormatWithoutTime;

/**
 *
 * @author quochuy
 */
public class MembershipManagementPane extends javax.swing.JPanel {
    private AdminFrame adminFrame;
    private DefaultTableModel model;

    /**
     * Creates new form MembershipManagementPane
     */
    public MembershipManagementPane(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        initComponents();
        initTable();
        loadTable();
        changeButtonState(true, false, false, false);
        changeInputState(false);
        changeFieldState(false);
    }

    private void changeButtonState(boolean add, boolean save, boolean edit, boolean update) {
        btnSave.setEnabled(save);
        btnAdd.setEnabled(add);
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
    }

    private void changeInputState(boolean state) {
        txtCardId.setEnabled(state);
        txtMemberName.setEnabled(state);
        txtBirthday.setEnabled(state);
        txtPhone.setEnabled(state);
        txtEmail.setEnabled(state);
        txtCardPoint.setEnabled(state);
        txtCardRank.setEnabled(state);
        txtStartDate.setEnabled(state);
        txtEndDate.setEnabled(state);
        txtStatus.setEnabled(state);
    }

    private void changeSecondInputState(boolean state) {
        txtCardPoint.setEnabled(state);
        txtCardRank.setEnabled(state);
        txtStartDate.setEnabled(state);
        txtEndDate.setEnabled(state);
        txtStatus.setEnabled(state);
    }

    private void changeFieldState(boolean state) {
        txtCardId.setEditable(state);
        txtMemberName.setEditable(state);
        txtBirthday.setEnabled(state);
        txtPhone.setEditable(state);
        txtEmail.setEditable(state);
        txtCardPoint.setEditable(state);
        txtCardRank.setEditable(state);
        txtStartDate.setEnabled(state);
        txtEndDate.setEnabled(state);
        txtStatus.setEnabled(state);
    }

    private void clearInput() {
        txtCardId.setText("");
        txtMemberName.setText("");
        txtBirthday.setDate(null);
        txtPhone.setText("");
        txtEmail.setText("");
        txtCardPoint.setText("");
        txtCardRank.setText("");
        txtStartDate.setDate(null);
        txtEndDate.setDate(null);
        txtStatus.setSelectedIndex(0);
    }

    private void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[] {
                "Mã thẻ thành viên", "Tên thành viên", "Ngày sinh", "Số điện thoại", "Email", "Điểm tích lũy", "Hạng thẻ", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
        });
    }

    private void loadTable() {
        List<Membership> membershipList = getMembershipList();
        model.setRowCount(0);
        for (Membership membership : membershipList) {
            model.addRow(new Object[] {
                    membership.getCard_id(),
                    membership.getMember_name(),
                    vietnameseDateFormatWithoutTime(membership.getDate_of_birth()),
                    membership.getPhone(),
                    membership.getEmail(),
                    membership.getCard_point(),
                    membership.getCard_rank(),
                    vietnameseDateFormat(membership.getStart_date()),
                    vietnameseDateFormat(membership.getEnd_date()),
                    getVietnameseStatus(membership.getStatus())
            });
        }
    }

    private List<Membership> getMembershipList() {
        List<Membership> membershipList = new ArrayList<>();
        try {
            MembershipApiHelper membershipApiHelper = new MembershipApiHelper(getToken());
            MembershipWrapper membershipWrapper = membershipApiHelper.read();
            membershipList = membershipWrapper.data;

            tableListMembership.setModel(model);
        } catch (Exception e) {
            MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
        }
        return membershipList;
    }

    private String getVietnameseStatus(String status) {
        switch (status) {
            case "Active":
                return "Đang hoạt động";
            case "Inactive":
                return "Không hoạt động";
            case "Expired":
                return "Hết hạn";
            default:
                return "Không hoạt động";
        }
    }

    private String getComboBoxStatus() {
        switch (txtStatus.getSelectedIndex()) {
            case 0:
                return "Active";
            case 1:
                return "Inactive";
            case 2:
                return "Expired";
            default:
                return "Inactive";
        }
    }

    private void createMembership(Membership membership) {
        try {
            MembershipApiHelper membershipApiHelper = new MembershipApiHelper(getToken());
            Membership newMembership = membershipApiHelper.create(membership);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Thêm thẻ thành viên thành công!");
        } catch (Exception e) {
            if(e.getMessage().contains("Email already exists")) {
                MessageBox.showErrorMessage(adminFrame, "Email đã tồn tại!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    private void updateMembership(Membership membership) {
        try {
            MembershipApiHelper membershipApiHelper = new MembershipApiHelper(getToken());
            Membership updatedMembership = membershipApiHelper.update(membership);
            loadTable();
            MessageBox.showInfoMessage(adminFrame, "Cập nhật thẻ thành viên thành công!");
        } catch (Exception e) {
            if(e.getMessage().contains("card_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã thẻ thành viên không tồn tại!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
            }
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

        ppmTableListMembership = new javax.swing.JPopupMenu();
        ppmRefresh = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ppmDelete = new javax.swing.JMenuItem();
        labelState = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCardId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMemberName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBirthday = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCardPoint = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCardRank = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtEndDate = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListMembership = new javax.swing.JTable();

        ppmRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh_20.png"))); // NOI18N
        ppmRefresh.setText("Làm mới");
        ppmRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmRefreshActionPerformed(evt);
            }
        });
        ppmTableListMembership.add(ppmRefresh);
        ppmTableListMembership.add(jSeparator3);

        ppmDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_20.png"))); // NOI18N
        ppmDelete.setText("Xóa");
        ppmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppmDeleteActionPerformed(evt);
            }
        });
        ppmTableListMembership.add(ppmDelete);

        setPreferredSize(new java.awt.Dimension(900, 600));

        labelState.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelState.setText("Quản lý thẻ thành viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã thẻ thành viên:");

        txtCardId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên thành viên:");

        txtMemberName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ngày sinh:");

        txtBirthday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Điểm tích luỹ:");

        txtCardPoint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Hạng thẻ:");

        txtCardRank.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ngày bắt đầu:");

        txtStartDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Ngày kết thúc:");

        txtEndDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Trạng thái:");

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Không hoạt động", "Hết hạn" }));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/new_20.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableListMembership.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableListMembership.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thẻ thành viên", "Tên thành viên", "Ngày sinh", "Số điện thoại", "Email", "Điểm tích lũy", "Hạng thẻ", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListMembership.setComponentPopupMenu(ppmTableListMembership);
        tableListMembership.setRowHeight(30);
        tableListMembership.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMembershipMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableListMembership);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtCardPoint, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCardId, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMemberName, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCardRank, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCardId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBirthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtMemberName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtCardPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCardRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        clearInput();
        labelState.setText("Thêm thẻ thành viên");
        changeButtonState(false, true, false, false);
        changeInputState(true);
        changeFieldState(true);
        changeSecondInputState(false);
        txtCardId.setEnabled(false);
        txtMemberName.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String valid = MembershipValidator.validateCreate(txtMemberName, txtBirthday, txtPhone, txtEmail);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Membership membership = new Membership(txtMemberName.getText(), txtBirthday.getDate(), txtPhone.getText(), txtEmail.getText());
            createMembership(membership);
            changeButtonState(true, false, false, false);
            changeInputState(false);
            clearInput();
            labelState.setText("Quản lý thẻ thành viên");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(txtCardId.getText().isEmpty()) {
            MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn thẻ thành viên cần chỉnh sửa!");
            return;
        } else {
            String validId = MembershipValidator.validateId(txtCardId);
            if(validId != null) {
                MessageBox.showErrorMessage(adminFrame, validId);
                return;
            }
        }
        labelState.setText("Chỉnh sửa thẻ thành viên");
        changeButtonState(false, false, false, true);
        changeInputState(true);
        changeFieldState(true);
        txtCardId.setEditable(false);
        txtMemberName.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String valid = MembershipValidator.validateUpdate(txtMemberName, txtBirthday, txtPhone, txtEmail, txtCardPoint, txtCardRank, txtStartDate, txtEndDate);
            if(valid != null) {
                MessageBox.showErrorMessage(adminFrame, valid);
                return;
            }

            Membership membership = new Membership(txtMemberName.getText(), txtBirthday.getDate(), txtPhone.getText(), txtEmail.getText());
            membership.setCard_id(Integer.parseInt(txtCardId.getText()));
            membership.setCard_point(Integer.parseInt(txtCardPoint.getText()));
            membership.setCard_rank(txtCardRank.getText());
            membership.setStart_date(txtStartDate.getDate());
            membership.setEnd_date(txtEndDate.getDate());
            membership.setStatus(getComboBoxStatus());
            updateMembership(membership);
            changeButtonState(true, false, false, false);
            changeInputState(false);
            clearInput();
            labelState.setText("Quản lý thẻ thành viên");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableListMembershipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMembershipMouseClicked
        int row = tableListMembership.getSelectedRow();
        TableModel model = tableListMembership.getModel();
        txtCardId.setText(model.getValueAt(row, 0).toString());
        txtMemberName.setText(model.getValueAt(row, 1).toString());
        txtBirthday.setDate(getMembershipList().get(row).getDate_of_birth());
        txtPhone.setText(model.getValueAt(row, 3).toString());
        txtEmail.setText(model.getValueAt(row, 4).toString());
        txtCardPoint.setText(model.getValueAt(row, 5).toString());
        txtCardRank.setText(model.getValueAt(row, 6).toString());
        txtStartDate.setDate(getMembershipList().get(row).getStart_date());
        txtEndDate.setDate(getMembershipList().get(row).getEnd_date());
        switch (model.getValueAt(row, 9).toString()) {
            case "Đang hoạt động":
                txtStatus.setSelectedIndex(0);
                break;
            case "Không hoạt động":
                txtStatus.setSelectedIndex(1);
                break;
            case "Hết hạn":
                txtStatus.setSelectedIndex(2);
                break;
            default:
                txtStatus.setSelectedIndex(1);
                break;
        }
        changeButtonState(true, false, true, false);
        changeInputState(true);
        changeFieldState(false);
    }//GEN-LAST:event_tableListMembershipMouseClicked

    private void ppmRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmRefreshActionPerformed
        loadTable();
    }//GEN-LAST:event_ppmRefreshActionPerformed

    private void ppmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppmDeleteActionPerformed
        try {
            int row = tableListMembership.getSelectedRow();
            if(row == -1) {
                MessageBox.showErrorMessage(adminFrame, "Vui lòng chọn thẻ thành viên cần xóa!");
                return;
            } else {
                if(MessageBox.showConfirmMessage(adminFrame, "Bạn có chắc chắn muốn xóa thẻ thành viên này không?") == 0) {
                    int cardId = Integer.parseInt(tableListMembership.getValueAt(row, 0).toString());
                    MembershipApiHelper membershipApiHelper = new MembershipApiHelper(getToken());
                    membershipApiHelper.delete(cardId);
                    loadTable();
                    MessageBox.showInfoMessage(adminFrame, "Xóa thẻ thành viên thành công!");
                    clearInput();
                    changeButtonState(true, false, false, false);
                    changeInputState(false);
                    changeFieldState(false);
                    labelState.setText("Quản lý thẻ thành viên");
                }
            }
        } catch (Exception e) {
            if(e.getMessage().contains("card_id not found")) {
                MessageBox.showErrorMessage(adminFrame, "Mã thẻ thành viên không tồn tại!");
            } else {
                MessageBox.showErrorMessage(adminFrame, "Có lỗi xảy ra, vui lòng thử lại\n" + e.getMessage());
            }
        }
    }//GEN-LAST:event_ppmDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelState;
    private javax.swing.JMenuItem ppmDelete;
    private javax.swing.JMenuItem ppmRefresh;
    private javax.swing.JPopupMenu ppmTableListMembership;
    private javax.swing.JTable tableListMembership;
    private com.toedter.calendar.JDateChooser txtBirthday;
    private javax.swing.JTextField txtCardId;
    private javax.swing.JTextField txtCardPoint;
    private javax.swing.JTextField txtCardRank;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtEndDate;
    private javax.swing.JTextField txtMemberName;
    private javax.swing.JTextField txtPhone;
    private com.toedter.calendar.JDateChooser txtStartDate;
    private javax.swing.JComboBox<String> txtStatus;
    // End of variables declaration//GEN-END:variables
}
