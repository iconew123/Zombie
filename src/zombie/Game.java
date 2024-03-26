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
	private Hero hero = new Hero(300, 1, 20);
	private Boss boss = new Boss(350, 30, 30);
	private Zombie zombie;

	private boolean isRun() {
		return hero.getPos() == 11 || hero.getIsDead() || sel == 2 ? false : true;
	}

	private int inputNumber(String text) {
		System.out.print(text);
		int number = -1;
		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("숫자만 입력하세요.");
		}

		return number;
	}

	private void printNextAction() {
		System.out.println("[1] 이동하기");
		System.out.println("[2] 게임종료");
		System.out.printf("현재위치 : %d\n", hero.getPos());
	}

	private void mainChoice(int sel) {
		if (sel == 1) {
			if (hero.getPos() < 10)
				zombie = randomCreateNormalZombie();
			hero.setPos();
		} else if (sel == 2)
			System.out.println("게임종료");
		else
			System.err.println("없는 기능입니다.");
	}

	private Zombie randomCreateNormalZombie() {
		Zombie tmp = null;
		boolean create = random.nextInt(3) < 1 ? true : false;
		if (create) {
			// 생성된 좀비의 체력은 70~100사이 최대공격력은 7~10사이로 설정
			int hp = random.nextInt(31) + 70;
			int max = random.nextInt(4) + 7;
			tmp = new Zombie(hp, max);
			isBattle = true;
		}

		return tmp;
	}

	public void run() {
		while (isRun()) {
			printNextAction();
			this.sel = inputNumber(">> ");
			mainChoice(this.sel);
		}
		System.out.println("목적지 도착");
	}
}
