package uit.controllers;

import uit.view.loginFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.uitprojects.is210.login.LoginWrapper;
import com.uitprojects.is210.login.ApiLoginHelper;


public class LoginController {
    private loginFrame loginWindow;

    public LoginController(loginFrame loginWindow) {
        this.loginWindow = loginWindow;
        loginWindow.setVisible(true);
        loginWindow.setActionListenerBtnLogin(new btnLogin());
        loginWindow.setActionListenerForEnterKey(new EnterLogin());
    }

    public void getDataLogin() {
        try {
            String username = loginWindow.getInputUsername();
            String password = loginWindow.getInputPassword();
            ApiLoginHelper apiLoginHelper = new ApiLoginHelper(username, password);
            LoginWrapper loginWrapper = apiLoginHelper.login();
        } catch (Exception e) {
            loginWindow.setErrorLabel("Tài khoản hoặc mật khẩu không chính xác!");
        }
    }

    class btnLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getDataLogin();
        }
    }

    class EnterLogin implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if ( e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                getDataLogin();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
