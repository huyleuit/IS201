package uit.validator;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class PromotionValidator {
    public static String validate(JTextField txtPromotionName, JTextField txtDiscount, JDateChooser txtStartDate, JDateChooser txtEndDate, JTextArea txtContent) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtPromotionName)) {
            sb.append("Tên chương trình không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtPromotionName)) {
                sb.append("Tên chương trình không được quá 255 ký tự!\n");
            }
        }

        if(Validator.isEmpty(txtDiscount)) {
            sb.append("Mức giảm giá không được để trống!\n");
        } else {
            if(Validator.isNotNumber(txtDiscount)) {
                sb.append("Mức giảm giá không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZero(txtDiscount)) {
                    sb.append("Mức giảm giá không hợp lệ!\n");
                }
            }
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
            if(!Validator.isValidEndDate(txtStartDate, txtEndDate)) {
                sb.append("Ngày kết thúc không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtContent)) {
            sb.append("Nội dung không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtContent)) {
                sb.append("Nội dung không được quá 255 ký tự!\n");
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }
}
