package uit.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uitprojects.is210.login.ApiLoginHelper;
import com.uitprojects.is210.login.LoginWrapper;
import uit.model.User;
import uit.view.LoginFrame;

import static uit.Token.getToken;
import static uit.Token.setToken;


public class LoginController {
    private LoginFrame loginDialog;
    private User user;

    public LoginController(LoginFrame loginDialog) {
        user = new User();
        this.loginDialog = loginDialog;
        loginDialog.setVisible(true);
        loginDialog.setAlwaysOnTop(true);
        loginDialog.setActionListenerBtnLogin(new btnLogin());
    }

    public void getDataLogin() {
        try {
            loginDialog.setErrorLabel("");
            loginDialog.setUsernameError("");
            loginDialog.setPasswordError("");
            String username = loginDialog.getInputUsername();
            if(username.isEmpty()) {
                loginDialog.setUsernameError("Tên đăng nhập không được trống!");
                throw new IllegalArgumentException("Username cannot be empty!");
            }
            String password = loginDialog.getInputPassword();
            if(password.isEmpty()) {
                loginDialog.setPasswordError("Mật khẩu không được trống!");
                throw new IllegalArgumentException("Password cannot be empty!");
            }
            ApiLoginHelper apiLoginHelper = new ApiLoginHelper(username, password);
            setToken(apiLoginHelper.getToken());
            if(getToken() != null) {
                loginDialog.dispose();
            }
//            LoginWrapper loginWrapper = apiLoginHelper.login();
//            user.setToken(loginWrapper.getToken());
//            if(user.getToken() != null) {
//                loginDialog.dispose();
//            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            loginDialog.setErrorLabel("Tài khoản hoặc mật khẩu không chính xác!");
        }
    }

    public class btnLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getDataLogin();
        }
    }
}
