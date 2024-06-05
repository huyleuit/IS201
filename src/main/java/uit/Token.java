package uit;

public class Token {
    private static String token;

    Token() {
        token = null;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Token.token = token;
    }
}
