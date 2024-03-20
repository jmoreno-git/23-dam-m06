package pokedigi2;

import java.util.Random;

/**
 *
 * @author Jose
 */
public class Digimon extends Fighter {

    private boolean shield;

    private static final int INITIAL_LIFE_POINTS = 600;
    private static final boolean INITIAL_SHIELD_STATE = true;
    private static final int MIN_COMBAT_POINTS = 30;
    private static final int MAX_COMBAT_POINTS = 200;

    public Digimon(String name) {
        super(name, INITIAL_LIFE_POINTS);
        this.shield = INITIAL_SHIELD_STATE;
    }

    public Digimon(Digimon other) {
        super(other);
        this.shield = other.shield;
    }    
    
    @Override
    public int attack() {
        int force = (new Random()).nextInt(MIN_COMBAT_POINTS, MAX_COMBAT_POINTS + 1);
        combatScream(force);
        return force;
    }

    @Override
    public boolean receiveAttack(int force) {
        if (shield) {
            System.out.format("%s: shield down\n", this.name);
            shield = false;
        } else {
            lifePoints = Math.max(lifePoints - force, 0);
        }
        return (lifePoints > 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Digimon{");
        sb.append("name=").append(name);
        sb.append(", lifePoints=").append(lifePoints);
        sb.append(", shield=").append(shield);
        sb.append('}');
        return sb.toString();
    }
    
}
