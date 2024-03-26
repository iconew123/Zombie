package zombie;

public class Hero extends Unit implements Attack {
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
	public void setHealItem() {
		this.healItem++;
	}

	public void powerUp() {
		System.out.println("레벨업 파워 2 증가");
		this.setMax();
	}

	@Override
	public void attack(Unit target) {

	}

}
