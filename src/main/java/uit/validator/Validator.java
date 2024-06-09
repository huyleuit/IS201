package uit.validator;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class Validator {
    public static boolean isEmpty(JComponent component) {
        if(component instanceof JTextField) {
            if(((JTextField) component).getText().isEmpty()) {
                return true;
            }
        }
        if(component instanceof JTextArea) {
            if(((JTextArea) component).getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(JTextField txt) {
        String email = txt.getText();
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isNotNumber(JTextField txt) {
        try {
            Integer.parseInt(txt.getText());
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotDoubleNumber(JTextField txt) {
        try {
            Double.parseDouble(txt.getText());
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

    public static boolean isNotBiggerThanZeroDouble(JTextField txt) {
        try {
            double number = Double.parseDouble(txt.getText());
            return number <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isVietnamesePhoneNumber(JTextField txt) {
        String phone = txt.getText();
        return phone.matches("0[0-9]{9}");
    }

    public static boolean isValidBirthday(JDateChooser txtBirthday) {
        if (txtBirthday.getDate() == null) {
            return false;
        }

        LocalDate selectedDate = txtBirthday.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return !selectedDate.isAfter(currentDate);
    }

    public static boolean isValidStartDate(JDateChooser txtStartDate) {
        if (txtStartDate.getDate() == null) {
            return false;
        }

        LocalDate selectedDate = txtStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return !selectedDate.isBefore(currentDate);
    }

    public static boolean isValidEndDate(JDateChooser txtStartDate, JDateChooser txtEndDate) {
        if (txtEndDate.getDate() == null) {
            return false;
        }

        LocalDate startDate = txtStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = txtEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return !endDate.isBefore(startDate);
    }

    public static boolean isOver255Characters(JComponent component) {
        if(component instanceof JTextField) {
            if(((JTextField) component).getText().length() > 255) {
                return true;
            }
        }
        if(component instanceof JTextArea) {
            return ((JTextArea) component).getText().length() > 255;
        }
        return false;
    }
}
