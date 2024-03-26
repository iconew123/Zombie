package zombie;

public class Game {
	private Game() {
		
	}
	
	private static Game instance = new Game();
	public static Game getInstance() {
		return instance;
	}
	
	public void run() {
		
	}
}
