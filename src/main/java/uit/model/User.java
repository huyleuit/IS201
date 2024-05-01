package uit.model;

public class User {
    private int empId;
    private String username;
    private String password;
    private String token;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int empId, String username, String password, String token) {
        this.empId = empId;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEmpId() {
        return empId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
