package uit;

import uit.controllers.LoginController;
import uit.view.loginFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new LoginController(new loginFrame());
            }
        });
    }
}