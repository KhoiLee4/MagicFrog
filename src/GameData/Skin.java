package GameData;

public class Skin {
    private String skin;
    private String url;

    // Constructor
    public Skin(String skin, String url) {
        this.skin = skin;
        this.url = url;
    }

    // Getters and Setters
    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
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
        return "Skin [skin=" + skin + ", url=" + url + "]";
    }
}
