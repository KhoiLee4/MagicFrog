package Model;

public class SkinsOfUser {
    private String username;
    private String skin;
    private int state;

    // Constructors
    public SkinsOfUser() {
        super();
    }

    public SkinsOfUser(String username, String skin, int state) {
        this.username = username;
        this.skin = skin;
        this.state = state;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SkinsOfUser{" +
                "username='" + username + '\'' +
                ", skin='" + skin + '\'' +
                ", state=" + state +
                '}';
    }
}
