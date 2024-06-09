package uit.validator;

import javax.swing.*;

public class EmpValidator {
    public static String validate(JTextField txtEmpId, JTextField txtUsername, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JTextField txtSalary, JTextField txtKPI) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtEmpId)) {
            sb.append("Mã nhân viên không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtEmpId)) {
                sb.append("Mã nhân viên không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtEmpId)) {
                    sb.append("Mã nhân viên không hợp lệ!\n");
                }
            }
        }



        if(Validator.isEmpty(txtUsername)) {
            sb.append("Tên đăng nhập không được để trống!\n");
        }

        if(Validator.isEmpty(txtName)) {
            sb.append("Tên nhân viên không được để trống!\n");
        }

        if(Validator.isEmpty(txtAddress)) {
            sb.append("Địa chỉ không được để trống!\n");
        }

        if(Validator.isEmpty(txtPhone)) {
            sb.append("Số điện thoại không được để trống!\n");
        }

        if(Validator.isNotNumber(txtPhone)) {
            sb.append("Số điện thoại không hợp lệ!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtPhone)) {
                sb.append("Số điện thoại không hợp lệ!\n");
            } else {
                if(!Validator.isVietnamesePhoneNumber(txtPhone)) {
                    sb.append("Số điện thoại không hợp lệ!\n");
                }
            }
        }

        if(!txtSalary.getText().isEmpty()) {
            if(Validator.isNotDoubleNumber(txtSalary)) {
                sb.append("Mức lương không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZeroDouble(txtSalary)) {
                    sb.append("Mức lương không hợp lệ!\n");
                }
            }
        }

        if(!txtKPI.getText().isEmpty()) {
            if(Validator.isNotNumber(txtKPI)) {
                sb.append("KPI không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZero(txtKPI)) {
                    sb.append("KPI không hợp lệ!\n");
                }
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }
}
