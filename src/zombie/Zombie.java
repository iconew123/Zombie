package zombie;

import java.util.Random;

public class Zombie extends Unit implements Attack, Heal {

	private Random random = new Random();

	public Zombie(int hp, int max) {
		super("일반좀비", hp, max);
	}

	@Override
	public void attack(Unit target) {
		Unit unit = (Unit) target;
		int attack = random.nextInt(this.getMax()) + 1;

		unit.setHp(attack * (-1));
		System.out.printf("%s의 일반공격으로 %d의 데미지를 %s에게 입혔습니다.\n", this.getType(), attack, unit.getType());

	}

	public void healSkill(Heal target) {
		Unit unit = (Unit) target;
		int healing = 10;
		int curHp = unit.getHp();
		unit.setHp(healing);
		System.out.printf("일반 Zombie 힐스킬 사용으로 %d만큼 회복했습니다.\n", unit.getHp() - curHp);
	}

}
