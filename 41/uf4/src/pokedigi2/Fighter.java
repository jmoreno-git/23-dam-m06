package pokedigi2;

/**
 *
 * @author Jose
 */
public abstract class Fighter implements FighterInterface {

    protected String name;
    protected int lifePoints;

    public Fighter(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }
    
    public Fighter(Fighter other) {
        this.name = other.name;
        this.lifePoints = other.lifePoints;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    protected void combatScream(int force) {
        System.out.format(
                "fighter '%s' attacks with force %d\n", 
                name, 
                force);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fighter{");
        sb.append("name=").append(name);
        sb.append(", lifePoints=").append(lifePoints);
        sb.append('}');
        return sb.toString();
    }

}
