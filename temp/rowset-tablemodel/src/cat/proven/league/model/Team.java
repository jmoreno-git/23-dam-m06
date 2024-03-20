package cat.proven.league.model;

/**
 * Team ADT
 * @author Jose
 */
public class Team {
    private long id;
    private String name;
    private String coach;
    private String category;
    private double budget;

    public Team() {
    }

    public Team(long id) {
        this.id = id;
    }

    public Team(long id, String name, String coach, String category, double budget) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.category = category;
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", coach=").append(coach);
        sb.append(", category=").append(category);
        sb.append(", budget=").append(budget);
        sb.append('}');
        return sb.toString();
    }

}