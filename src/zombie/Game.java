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

	private final int PLAYER_MOVE = 1;
	private final int END = 2;

	private final int PLAYER_ATTACK = 1;
	private final int PLAYER_USE_HEALITEM = 2;
	private final int ZOMBIE_ATTACK = 1;
	private final int ZOMBIE_USE_HEALSKILL = 2;

	private int sel;
	private boolean isBattle;
	private Hero hero = new Hero(300, 1, 20);
	private Boss boss = new Boss(350, 30, 30);
	private Noraml normal;

	public void setIsBattle() {
		this.isBattle = !this.isBattle;
	}

	private boolean isRun() {
		return hero.getPos() == 10 || hero.getIsDead() || sel == 2 ? false : true;
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
		if (sel == PLAYER_MOVE) {
			if (hero.getPos() < 8)
				normal = randomCreateNormalZombie();
			hero.setPos();
		} else if (sel == END)
			System.out.println("게임종료");
		else
			System.err.println("없는 기능입니다.");
	}

	private Noraml randomCreateNormalZombie() {
		Noraml tmp = null;
		// 33%확률로 소환
		boolean create = random.nextInt(3) < 1 ? true : false;
		if (create) {
			// 생성된 좀비의 체력은 70~100사이 최대공격력은 7~10사이로 설정
			int hp = random.nextInt(31) + 70;
			int max = random.nextInt(4) + 7;
			tmp = new Noraml(hp, max);
			setIsBattle();
		}

		return tmp;
	}

	private void battle() {
		if (hero.getPos() == 9) {
			setIsBattle();
			bossBattle();
		} else
			normalBattle();
	}

	private void bossBattle() {
		System.out.printf("%s과 전투 시작!\n", boss.getType());
		while (!boss.getIsDead()) {
			printBattleAction();

			int battleSel = inputNumber(">> ");
			if (heroChoice(battleSel, boss))
				continue;

			int zombieAction = random.nextInt(6) < 1 ? ZOMBIE_USE_HEALSKILL : ZOMBIE_ATTACK;
			bossPattern(zombieAction);

			System.out.printf(hero + "\t" + boss + ", 쉴드 : [%d/%d]\n", boss.getShield(), boss.MAX_SHIELD);

			if (boss.getIsDead())
				setIsBattle();
		}
	}

	private void bossPattern(int zombieAction) {
		if (zombieAction == ZOMBIE_ATTACK)
			boss.attack(hero);
		else if (zombieAction == ZOMBIE_USE_HEALSKILL)
			boss.healSkill(boss);
	}

	private void normalBattle() {
		System.out.printf("%s와 전투 시작\n", normal.getType());
		while (!normal.getIsDead()) {
			printBattleAction();

			int battleSel = inputNumber(">> ");
			if (heroChoice(battleSel, normal))
				continue;

			int zombieAction = random.nextInt(6) < 1 ? ZOMBIE_USE_HEALSKILL : ZOMBIE_ATTACK;
			zombiePattern(zombieAction);

			System.out.println(hero + "\t" + normal);

			if (normal.getIsDead()) {
				boolean getItem = random.nextInt(2) == 1 ? true : false;
				getHealItem(getItem);
				hero.powerUp();
				setIsBattle();
			}
		}
	}

	private void zombiePattern(int zombieAction) {
		if (zombieAction == ZOMBIE_ATTACK)
			normal.attack(hero);
		else if (zombieAction == ZOMBIE_USE_HEALSKILL)
			normal.healSkill(normal);
	}

	private void printBattleAction() {
		System.out.println("[1] 공격하기");
		System.out.println("[2] 회복 아이템 사용");
	}

	private boolean heroChoice(int battleSel, Unit unit) {
		if (battleSel == PLAYER_ATTACK) {
			playerAttack(unit);
			return false;
		} else if (battleSel == PLAYER_USE_HEALITEM) {
			useHealItem();
			return true;
		} else
			return true;
	}

	private void playerAttack(Unit unit) {
		hero.attack(unit);
	}

	private void useHealItem() {
		if (hero.getHealItem() == 0) {
			System.err.println("사용가능한 포션이 없습니다.");
			return;
		}
		int curHp = hero.getHp();
		hero.setHealItemMinus();
		hero.setHp(100);
		System.out.printf("포션을 사용하여 %d을(를) 회복했습니다. 현재체력 : %d, 남은 포션갯수 : %d\n", hero.getHp() - curHp, hero.getHp(),
				hero.getHealItem());
	}

	private void getHealItem(boolean getItem) {
		if (getItem) {
			hero.setHealItemPlus();
			System.out.printf("좀비를 처치하여 아이템을 얻었습니다. 현재 남은 포션갯수 : %d\n", hero.getHealItem());
		}
	}

	public void run() {
		while (isRun()) {
			printNextAction();
			this.sel = inputNumber(">> ");
			mainChoice(this.sel);
			if (isBattle || hero.getPos() == 9)
				battle();
		}
		System.out.printf("현재위치 %d\n목적지 도착", hero.getPos());
	}
}
