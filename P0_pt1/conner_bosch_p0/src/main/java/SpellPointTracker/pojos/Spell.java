package SpellPointTracker.pojos;

/**
 * An object representing a spell from Dungeons and Dragons 5th edition
 */
public class Spell {
    private int id;
    private String name;
    private int level;
    private int cost;

    public Spell(){
        super();
    }
    
    public Spell(int id, String name, int level, int cost) {
        this();
        this.id = id;
        this.name = name;
        this.level = level;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
