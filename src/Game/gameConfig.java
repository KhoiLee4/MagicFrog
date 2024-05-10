package Game;

public interface gameConfig {
	// Kích thước khung hình
	int screenWidth = 1050;
	int screenHeight = 1000;

	// Tốc độ chuyển động của các đối tượng
	float speedPlank = 0.5f;
	float speedCar = 0.45f;
	float speedFrog = 0.5f;
	float speedMap = speedFrog;

	// Trạng thái âm thanh
	boolean Music = false;
	boolean Sound = false;
	
	// Tốc độ giảm của năng lượng
	float energyReduction = 0.001f;

}

// chiều rộng, chiều cao 
// ếch 72, 72
// map 945, auto
// xe 197, 135
// ván auto, 975
// butto 465 540/700, 900 840
// 

// Việc cần làm:
// chỉnh hitbox