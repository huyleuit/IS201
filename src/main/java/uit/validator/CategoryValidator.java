package uit.validator;

import javax.swing.*;

public class CategoryValidator {
    public static String validate(JTextField txtCategoryName, JTextArea txtDescription) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtCategoryName)) {
            sb.append("Tên danh mục không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtCategoryName)) {
                sb.append("Tên danh mục không được quá 255 ký tự!\n");
            }
        }

        if(Validator.isEmpty(txtDescription)) {
            sb.append("Mô tả không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtDescription)) {
                sb.append("Mô tả không được quá 255 ký tự!\n");
            }
        }

        return sb.toString();
    }

    public static String validateId(JTextField txtCategoryId) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtCategoryId)) {
            sb.append("Mã danh mục không được để trống!\n");
        } else {
            if(Validator.isNotNumber(txtCategoryId)) {
                sb.append("Mã danh mục không hợp lệ!\n");
                sb.append("Mã danh mục không hợp lệ!\n");
            } else {
                if(Validator.isNotBiggerThanZero(txtCategoryId)) {
                    sb.append("Mã danh mục không hợp lệ!\n");
                }
            }
        }
        return sb.isEmpty()? null : sb.toString();
    }
}
