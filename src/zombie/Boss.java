package zombie;

public class Boss extends Zombie implements Attackable {

	public final int MAX_SHIELD;
	private int shield;

	public Boss(int hp, int max, int shield) {
		super("보스좀비", hp, max);
		this.shield = shield;
		MAX_SHIELD = shield;
	}

	public int getShield() {
		return this.shield;
	}

	public void setShield(int shield) {
		this.shield += shield;
	}

	@Override
	public void attack(Unit target) {
		Unit unit = (Unit) target;
		int attack = random.nextInt(this.getMax()) + 1;

		boolean crit = random.nextInt(7) < 1 ? true : false;
		if (crit)
			attack *= 1.5;

		unit.setHp(attack * (-1));
		System.out.printf("%s의 " + (crit ? "크리티컬 공격!" : "일반 공격") + "으로 %d피해를 %s에게 입혔습니다.\n", this.getType(), attack,
				unit.getType());
	}

	@Override
	public void healSkill(Unit target) {
		Unit unit = (Unit) target;
		int healing = 20;
		int curHp = unit.getHp();
		unit.setHp(healing);
		System.out.printf("보스 Zombie 힐스킬 사용으로 %d만큼 회복했습니다.\n", unit.getHp() - curHp);

	}

}
