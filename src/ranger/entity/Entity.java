package ranger.entity;

import ranger.Game;
import ranger.Util;
import ranger.item.Inventory;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;
import ranger.map.Direction;
import ranger.map.Location;
import ranger.name.Name;
import ranger.name.Named;
import ranger.time.EntityMove;
import ranger.time.GameEvent;

public class Entity implements Named {
	
	public GameEvent getNextAction(Game game, long current) {
		Direction move = Util.random(game.getRegion().getValidDirections(location));
		return new EntityMove(this, move, current + 15);
	}
	
	public void setCurrentLocation(Location location) {
		this.location = location;
	}
	
	public Location getCurrentLocation() {
		return location;
	}

	public Name getName() {
		return name;
	}

	public void setEquip(Weapon weapon) {
		equip = weapon;
	}

	public Weapon getEquip() {
		return equip;
	}

	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}
	
	public Ammo getAmmo() {
		return ammo;
	}
	
	public boolean weaponDrawn() {
		return drawn;
	}
	
	public void setWeaponDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void takeDamage(int damage) {
		health = Math.max(0, health-damage);
	}

	public int getHealth() {
		return health;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public boolean isDead() {
		return health == 0;
	}
	
	public double getPerception(Game game) {
		double perception = 0.5;
		if (darkVision && game.getTime().isDark())
			perception += 0.2;
		
		return perception;
	}
	
	public Entity(Name name, boolean darkVision) {
		this.name = name;
		inventory = new Inventory();
		health = 100;
	}
	
	protected Inventory inventory;
	protected Weapon equip;
	private Ammo ammo;
	protected Name name;
	protected Location location;
	protected int health;
	protected boolean drawn;
	
	protected boolean darkVision;
}
