package SpellPointTracker.pojos;

/**
 * An object representing the user and their current class and level
 */
public class Player {
    private int id;
    private String username;
    private String password;
    private int currentPoints;
    private int currentLevel;
    private int casterType;

    public Player() {
        super();
    }

    public Player(int id, int currentPoints, int currentLevel, int casterId) {
        this.id = id;
        this.currentPoints = currentPoints;
        this.currentLevel = currentLevel;
        this.casterType = casterId;
    }

    public Player(int id, String username, String password, int currentPoints, int currentLevel, int casterId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.currentPoints = currentPoints;
        this.currentLevel = currentLevel;
        this.casterType = casterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCasterType() {
        return casterType;
    }

    public void setCasterType(int caster) {
        this.casterType = caster;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Player " + id + ": " + username + ", Level " + currentLevel + ", Type: " + casterType
                + ", current points = " + currentPoints;
    }
}
