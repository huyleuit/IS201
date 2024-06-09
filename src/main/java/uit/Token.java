package uit;

public class Token {
    private static String token;
    private static int empId;

    Token() {
        token = null;
        empId = -1;
    }

    public static String getToken() {
        return token;
    }

    public static int getEmpId() {
        return empId;
    }

    public static void setToken(String token) {
        Token.token = token;
    }

    public static void setEmpId(int empId) {
        Token.empId = empId;
    }
}
