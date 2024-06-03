package GameData;

public class Detail {
    public String username;
    private int money;
    private int maxScore;
    private boolean gameMusic;
    private boolean soundEffect;

    public Detail() {
        super();
    }

    // Constructor
    public Detail(String username) {
        this.username = username;
        this.money = 0;
        this.maxScore = 0;
        this.gameMusic = true;
        this.soundEffect = true;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public boolean isGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(boolean gameMusic) {
        this.gameMusic = gameMusic;
    }

    public boolean isSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(boolean soundEffect) {
        this.soundEffect = soundEffect;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "username='" + username + '\'' +
                ", money=" + money +
                ", maxScore=" + maxScore +
                ", gameMusic=" + gameMusic +
                ", soundEffect=" + soundEffect +
                '}';
    }
}
