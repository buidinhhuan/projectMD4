package project.dto;

public class UserLogin {
    private  int id;
    private String username;
    private String password;
     private  int cartId;

    public UserLogin() {
    }

    public UserLogin(int id, String username, String password, int cartId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
