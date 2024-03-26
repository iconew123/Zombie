package zombie;

import java.util.Random;

public class Boss extends Unit implements Attack, Heal {

	private Random random = new Random();

	private int shield;

	public Boss(int hp, int max, int shield) {
		super("보스좀비", hp, max);
		this.shield = shield;
	}

	public int getShield() {
		return this.shield;
	}

	@Override
	public void attack(Unit target) {
		Unit unit = (Unit) target;
		int attack = random.nextInt(this.getMax()) + 1;

		boolean crit = random.nextInt(7) < 1 ? true : false;
		if (crit)
			attack *= 1.5;

		unit.setHp(attack);
		System.out.printf("%s의 " + (crit ? "크리티컬 공격!" : "일반 공격") + "으로 %d피해를 %s에게 입혔습니다.\n", this.getType(), attack,
				unit.getType());
	}

	public void healSkill(Heal target) {
		Unit unit = (Unit) target;
		int healing = 20;
		int curHp = unit.getHp();
		unit.setHp(healing);
		System.out.printf("일반 Zombie 힐스킬 사용으로 %d만큼 회복했습니다.\n", unit.getHp() - curHp);
	}
}
