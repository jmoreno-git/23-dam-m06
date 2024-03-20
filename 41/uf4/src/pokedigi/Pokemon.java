
package pokedigi;

import java.util.Random;

/**
 *
 * @author Jose
 */
public class Pokemon extends Fighter {

    private int combatPoints;

    private static final int MIN_COMBAT_POINTS = 100;
    private static final int MAX_COMBAT_POINTS = 200;
    private static final int LIFE_POINTS_DOUBLE_FORCE = 100;
    
    public Pokemon(String name, int lifePoints) {
        super(name, lifePoints);
        Random rnd = new Random();
        this.combatPoints = rnd.nextInt(MIN_COMBAT_POINTS, MAX_COMBAT_POINTS+1);
    }

    public Pokemon(Pokemon other) {
        super(other);
        this.combatPoints = other.combatPoints;
    }    
    
    @Override
    public int attack() {
        int force = lifePoints;
        force *= (lifePoints >= LIFE_POINTS_DOUBLE_FORCE) ? 1 : 2;
        combatScream(force);
        return force;
    }

    @Override
    public boolean receiveAttack(int force) {
        lifePoints = Math.max(lifePoints - force, 0);
        return (lifePoints > 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pokemon{");
        sb.append("name=").append(name);
        sb.append(", lifePoints=").append(lifePoints);
        sb.append(", combatPoints=").append(combatPoints);
        sb.append('}');
        return sb.toString();
    }
    
}
