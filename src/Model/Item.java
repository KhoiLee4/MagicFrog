package Model;

public class Item {
    private String item;
    private String url;

    // Constructor
    public Item(String item, String url) {
        this.item = item;
        this.url = url;
    }

    // Getters and Setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // toString method
    @Override
    public String toString() {
        return "Item [item=" + item + ", url=" + url + "]";
    }
}

