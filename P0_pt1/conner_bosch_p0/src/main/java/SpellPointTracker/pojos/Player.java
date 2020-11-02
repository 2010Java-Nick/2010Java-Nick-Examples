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
}
