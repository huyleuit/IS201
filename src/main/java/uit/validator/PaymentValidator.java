package uit.validator;

import javax.swing.*;

public class PaymentValidator {
    public static String validate(JTextField txtDiscount, JTextField txtReceived) {
        StringBuilder sb = new StringBuilder();

        if(!txtDiscount.getText().isEmpty()) {
            if(Validator.isNotNumber(txtDiscount)) {
                sb.append("Giá trị chiết khấu không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZero(txtDiscount)) {
                    sb.append("Giá trị chiết khấu không hợp lệ!\n");
                }
            }
        }

        if(Validator.isEmpty(txtReceived)) {
            sb.append("Số tiền nhận không được để trống!\n");
        } else {
            if(Validator.isNotNumber(txtReceived)) {
                sb.append("Số tiền nhận không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZero(txtReceived)) {
                    sb.append("Số tiền nhận không hợp lệ!\n");
                }
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }
}
