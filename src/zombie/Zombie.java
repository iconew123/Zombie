package zombie;

abstract class Zombie extends Unit {

	public Zombie(String type, int hp, int max) {
		super(type, hp, max);
	}
	
	abstract public void healSkill(Unit target);

}
