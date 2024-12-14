public abstract class user {
    private final String username;
    private final String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayRole(); // Abstract method for polymorphism
}