package uit.validator;

import javax.swing.*;

public class Validator {
    public static boolean isEmpty(JComponent component) {
        if(component instanceof JTextField) {
            if(((JTextField) component).getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNumber(JTextField txt) {
        try {
            Integer.parseInt(txt.getText());
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotBiggerThanZero(JTextField txt) {
        try {
            int number = Integer.parseInt(txt.getText());
            return number <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isVietnamesePhoneNumber(JTextField txt) {
        String phone = txt.getText();
        return phone.matches("0[0-9]{9}");
    }
}
