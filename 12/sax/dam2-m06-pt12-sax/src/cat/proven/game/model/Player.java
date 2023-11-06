package cat.proven.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ADT for a player
 * @author ProvenSoft
 */
public class Player {
    private String id;
    private String name;
    private int level;
    private double score;
    private List<Game> games;

    public Player(String id, String name, int level, double score, List<Game> games) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.score = score;
        this.games = games;
    }

    public Player(String id) {
        this.id = id;
    }

    public Player(String id, String name, int level, double score) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.score = score;
        this.games = new ArrayList<>();
    }

    public Player() {
    }
    
    public Player(Player other) {
        this.id = other.id;
        this.name = other.name;
        this.level = other.level;
        this.score = other.score;
        this.games = other.games;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Player other = (Player) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
        sb.append(", score=").append(score);
        sb.append(", games=").append(games);
        sb.append('}');
        return sb.toString();
    }
    
    
}
