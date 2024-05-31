package GameData;

public class Detail {
    public String username;
    private int money;
    private int maxScore;
    private String skins;  
    private String items; 
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
        this.skins = "0 0 0 0";
        this.items = "0 0 0 0";
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

    public String getSkins() {
        return skins;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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

    // Methods to add skins and items
    public int[] Items () {
    	int[] result = new int[4];
    	
    	String[] itemArray = items.split(" ");
    	for (int i = 0; i < 4; i++) {
    		result[i] = Integer.parseInt(itemArray[i]);
    	}
    	
    	return result;
    }

    public boolean[] Skins () {
    	 boolean[] result = new boolean[4];
         
         String[] itemArray = items.split(" ");
         for (int i = 0; i < 4; i++) {
             int value = Integer.parseInt(itemArray[i]);
             result[i] = (value != 0); // Chuyển đổi số nguyên thành boolean
         }
         
         return result;
    }
    
    @Override
    public String toString() {
        return "Detail{" +
                "username='" + username + '\'' +
                ", money=" + money +
                ", maxScore=" + maxScore +
                ", skins='" + skins + '\'' +
                ", items='" + items + '\'' +
                ", gameMusic=" + gameMusic +
                ", soundEffect=" + soundEffect +
                '}';
    }
}
