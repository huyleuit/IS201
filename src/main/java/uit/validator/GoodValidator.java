package uit.validator;

import javax.swing.*;

public class GoodValidator {
    public static String validate(JTextField txtGoodsName, JTextField txtCategoryId) {
        StringBuilder sb = new StringBuilder();

        if(Validator.isEmpty(txtGoodsName)) {
            sb.append("Tên hàng hóa không được để trống!\n");
        } else if(Validator.isOver255Characters(txtGoodsName)) {
            sb.append("Tên hàng hóa không được quá 255 ký tự!\n");
        }

        if(Validator.isEmpty(txtCategoryId)) {
            sb.append("Mã danh mục hàng hóa không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtCategoryId)) {
                sb.append("Mã danh mục hàng hóa không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtCategoryId)) {
                    sb.append("Mã danh mục hàng hóa không hợp lệ!\n");
                }
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }

    public static String validateId(JTextField txtGoodsId) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtGoodsId)) {
            sb.append("Mã hàng hóa không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtGoodsId)) {
                sb.append("Mã hàng hóa không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtGoodsId)) {
                    sb.append("Mã hàng hóa không hợp lệ!\n");
                }
            }
        }
        return sb.isEmpty()? null : sb.toString();
    }
}
