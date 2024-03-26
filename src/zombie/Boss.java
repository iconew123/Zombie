package zombie;

public class Boss extends Unit implements Attack, Heal {

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

	}
}
