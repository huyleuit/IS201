package uit.validator;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class ShipmentVadidator {
    public static String validate(JTextField txtShipmentName, JDateChooser txtManufactureDate, JDateChooser txtExpirationDate, JTextField txtGoodId, JTextField txtQuantity, JTextField txtPrice, JTextArea txtDescription) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtShipmentName)) {
            sb.append("Tên lô hàng không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtShipmentName)) {
                sb.append("Tên lô hàng không được quá 255 ký tự!\n");
            }
        }

        if(Validator.isEmpty(txtGoodId)) {
            sb.append("Mã hàng hóa không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtGoodId)) {
                sb.append("Mã hàng hóa không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtGoodId)) {
                    sb.append("Mã hàng hóa không hợp lệ!\n");
                }
            }
        }

        if(Validator.isEmpty(txtQuantity)) {
            sb.append("Số lượng không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtQuantity)) {
                sb.append("Số lượng không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtQuantity)) {
                    sb.append("Số lượng không hợp lệ!\n");
                }
            }
        }

        if(Validator.isEmpty(txtPrice)) {
            sb.append("Giá không được để trống!\n");
        } else {
            if(Validator.isNotDoubleNumber(txtPrice)) {
                sb.append("Giá không hợp lệ!\n");
            } else {
                if(!Validator.isValidPrice(txtPrice)) {
                    sb.append("Giá không hợp lệ!\n");
                }
            }
        }

        if(Validator.isEmpty(txtManufactureDate)) {
            sb.append("Ngày sản xuất không được để trống!\n");
        } else {
            if(!Validator.isValidManufactureDate(txtManufactureDate)) {
                sb.append("Ngày sản xuất không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtExpirationDate)) {
            sb.append("Ngày hết hạn không được để trống!\n");
        } else {
            if(!Validator.isValidEndDate(txtManufactureDate, txtExpirationDate)) {
                sb.append("Ngày hết hạn không hợp lệ!\n");
            }
        }

        if(Validator.isEmpty(txtDescription)) {
            sb.append("Mô tả không được để trống!\n");
        } else {
            if(Validator.isOver255Characters(txtDescription)) {
                sb.append("Mô tả không được quá 255 ký tự!\n");
            }
        }

        return sb.isEmpty()? null : sb.toString();
    }

    public static String validateId(JTextField txtShipmentId) {
        StringBuilder sb = new StringBuilder();
        if(Validator.isEmpty(txtShipmentId)) {
            sb.append("Mã lô hàng không được để trống!\n");
        } else {
            if(Validator.isNotBiggerThanZero(txtShipmentId)) {
                sb.append("Mã lô hàng không hợp lệ!\n");
            } else {
                if(Validator.isNotNumber(txtShipmentId)) {
                    sb.append("Mã lô hàng không hợp lệ!\n");
                }
            }
        }
        return sb.isEmpty()? null : sb.toString();
    }
}
