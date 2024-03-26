package zombie;

import java.util.Random;

public class Hero extends Unit implements Attack {
	private Random random = new Random();

	private int pos;
	private int healItem;

	public Hero(int hp, int pos, int max) {
		super("용사", hp, max);
		this.pos = pos;
		this.healItem = 3;
	}

	public int getPos() {
		return this.pos;
	}

	public void setPos() {
		this.pos++;
	}

	public int getHealItem() {
		return this.healItem;
	}

	// 드롭아이템
	public void setHealItemPlus() {
		this.healItem++;
	}

	public void setHealItemMinus() {
		this.healItem--;
	}

	public void powerUp() {
		System.out.println("레벨업 파워 2 증가");
		this.setMax();
	}

	@Override
	public void attack(Unit target) {
		Unit unit = (Unit) target;
		int attack = random.nextInt(this.getMax()) + 1;

		boolean crit = random.nextInt(5) < 1 ? true : false;
		if (crit)
			attack *= 2;

		if (unit.getType().equals("보스좀비")) {
			Boss tmp = (Boss) unit;
			attackBoss(tmp, attack);
		} else if (unit.getType().equals("일반좀비"))
			unit.setHp(attack * (-1));
		System.out.printf("%s의 " + (crit ? "크리티컬 공격!" : "일반 공격") + "으로 %d피해를 %s에게 입혔습니다.\n", this.getType(), attack,
				unit.getType());
	}

	private void attackBoss(Boss boss, int attack) {
		int sub = boss.getShield() - attack;
		if (sub <= 0) {
			boss.setShield(boss.getShield() * -1);
			boss.setHp(sub);
		} else
			boss.setShield(attack * -1);
	}

}
