package Model;

public class ItemsOfUser extends Account{

    private String item;
    private int quantity;

    // Constructors
    public ItemsOfUser() {
        super();
    }
    
    public ItemsOfUser(String username, String item, int quantity) {
        this.username = username;
        this.item = item;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemOfUser{" +
                "username='" + username + '\'' +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
