package uit.controllers;

import uit.view.loginFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uitprojects.is210.login.LoginWrapper;
import com.uitprojects.is210.login.ApiLoginHelper;


public class LoginController {
    private loginFrame loginWindow;

    public LoginController(loginFrame loginWindow) {
        this.loginWindow = loginWindow;
        loginWindow.setVisible(true);
        loginWindow.setActionListenerBtnLogin(new btnLogin());
    }

    public void getDataLogin() {
        try {
            loginWindow.setErrorLabel("");
            loginWindow.setUsernameError("");
            loginWindow.setPasswordError("");
            String username = loginWindow.getInputUsername();
            if(username.isEmpty()) {
                loginWindow.setUsernameError("Tên đăng nhập không được trống!");
                throw new IllegalArgumentException("Username cannot be empty!");
            }
            String password = loginWindow.getInputPassword();
            if(password.isEmpty()) {
                loginWindow.setPasswordError("Mật khẩu không được trống!");
                throw new IllegalArgumentException("Password cannot be empty!");
            }
            ApiLoginHelper apiLoginHelper = new ApiLoginHelper(username, password);
            LoginWrapper loginWrapper = apiLoginHelper.login();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            loginWindow.setErrorLabel("Tài khoản hoặc mật khẩu không chính xác!");
        }
    }

    class btnLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getDataLogin();
        }
    }
}
