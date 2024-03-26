package zombie;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private Scanner scan = new Scanner(System.in);
	private Random random = new Random();

	private Game() {

	}

	private static Game instance = new Game();

	public static Game getInstance() {
		return instance;
	}

	private int sel;
	private boolean isBattle;
	private Hero hero = new Hero(200, 0, 20);
	private Boss boss = new Boss(300, 30, 30);

	private boolean isRun() {
		return hero.getPos() == 10 || hero.getIsDead() || sel == 2 ? false : true;
	}

	public void run() {
		while (isRun()) {

		}
	}
}
