package zombie;

public class Zombie extends Unit implements Attack, Heal {

	public Zombie(int hp, int max) {
		super("일반좀비", hp, max);
	}

	@Override
	public void attack(Unit target) {

	}

	public void healSkill(Heal target) {

	}

}
