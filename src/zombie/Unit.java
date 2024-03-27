package zombie;

public class Unit {

	public final int MAX_HP;
	private String type;
	private int hp;
	private int max;
	private boolean isDead;

	public Unit(String type, int hp, int max) {
		MAX_HP = hp;
		this.type = type;
		this.hp = hp;
		this.max = max;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp += hp;

		if (this.hp <= 0) {
			this.hp = 0;
			this.isDead = true;
		} else if (this.hp >= MAX_HP)
			this.hp = MAX_HP;
	}

	public boolean getIsDead() {
		return this.isDead;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax() {
		this.max += 2;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return isDead ? String.format("[%s유닛 사망]", this.type)
				: String.format("%s : [%d/%d]", this.type, this.hp, MAX_HP);
	}

}
