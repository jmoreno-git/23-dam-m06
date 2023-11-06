package cat.proven.game.model;

import java.util.List;
import java.util.Objects;

/**
 * ADT class for a game
 * @author ProvenSoft
 */
public class Game {
    private String id;
    private List<Player> players;

    public Game(String id, List<Player> players) {
        this.id = id;
        this.players = players;
    }

    public Game() {
    }

    public Game(String id) {
        this.id = id;
    }
    
    public Game(Game other) {
        this.id = other.id;
        this.players = other.players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Game other = (Game) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game{");
        sb.append("id=").append(id);
        sb.append(", players=").append(players);
        sb.append('}');
        return sb.toString();
    }
    
}
