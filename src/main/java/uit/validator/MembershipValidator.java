package uit.validator;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class MembershipValidator {
    public static String validate(JTextField txtName, JDateChooser txtBirthday, JTextField txtPhone, JTextField txtEmail, JTextField txtCardPoint, JTextField txtCardRank, JDateChooser txtStartDate, JDateChooser txtEndDate) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtName)) {
            sb.append("Tên không được để trống!\n");
        }

        if(Validator.isEmpty(txtBirthday)) {
            sb.append("Ngày sinh không được để trống!\n");
        } else {
            if(!Validator.isValidBirthday(txtBirthday)) {
                sb.append("Ngày sinh không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtPhone)) {
            sb.append("Số điện thoại không được để trống!\n");
        } else if(Validator.isNotNumber(txtPhone)) {
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

        if(Validator.isEmpty(txtEmail)) {
            sb.append("Email không được để trống!\n");
        } else {
            if(!Validator.isValidEmail(txtEmail)) {
                sb.append("Email không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtCardPoint)) {
            sb.append("Điểm thẻ không được để trống!\n");
        } else if(Validator.isNotNumber(txtCardPoint)) {
            sb.append("Điểm thẻ không hợp lệ!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtCardPoint)) {
                sb.append("Điểm thẻ không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtCardRank)) {
            sb.append("Hạng thẻ không được để trống!\n");
        }

        if(Validator.isEmpty(txtStartDate)) {
            sb.append("Ngày bắt đầu không được để trống!\n");
        } else {
            if(!Validator.isValidStartDate(txtStartDate)) {
                sb.append("Ngày bắt đầu không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtEndDate)) {
            sb.append("Ngày kết thúc không được để trống!\n");
        } else {
            if (!Validator.isValidEndDate(txtStartDate, txtEndDate)) {
                sb.append("Ngày kết thúc không hợp lệ!\n");
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }
}
