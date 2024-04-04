package CLI;

public class User {
    private String username;
    private String password;

    private boolean firstPurchase;

    public User(String username, String password,boolean firstPurchase ) throws RuntimeException{
        this.username = username;
        this.password = password;
        this.firstPurchase=firstPurchase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String newPassword, String oldPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }

    public boolean isFirstPurchase() {
        return this.firstPurchase;
    }

    public void setFirstPurchase(boolean firstPurchase) {
       this.firstPurchase=firstPurchase;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstPurchase=" + firstPurchase +
                '}';
    }
}
