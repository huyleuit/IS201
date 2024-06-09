package uit.validator;

import javax.swing.*;

public class BillDetailValidator {
    public static String validate(JTextField txtProductId, JTextField txtShipmentId, JTextField txtQuantity) {
        StringBuilder sb = new StringBuilder();

        if(Validator.isEmpty(txtProductId)) {
            sb.append("Mã sản phẩm không được để trống!\n");
        } else if(Validator.isNotBiggerThanZero(txtProductId)) {
            sb.append("Mã sản phẩm không hợp lệ!\n");
        } else {
            if(Validator.isNotNumber(txtProductId)) {
                sb.append("Mã sản phẩm không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtShipmentId)) {
            sb.append("Mã lô hàng không được để trống!\n");
        } else if(Validator.isNotBiggerThanZero(txtShipmentId)) {
            sb.append("Mã lô hàng không hợp lệ!\n");
        } else {
            if(Validator.isNotNumber(txtShipmentId)) {
                sb.append("Mã lô hàng không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtQuantity)) {
            sb.append("Số lượng không được để trống!\n");
        } else if(Validator.isNotBiggerThanZero(txtQuantity)) {
            sb.append("Số lượng không hợp lệ!\n");
        } else {
            if(Validator.isNotNumber(txtQuantity)) {
                sb.append("Số lượng không hợp lệ!\n");
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }
}
