package SpellPointTracker.pojos;

public class Caster {
    private int id;
    private String name;
    private int[] maxPoints; //Different max points based on level 
    private int[] spellIds;
    private int[] maxSpellLevels;

    public Caster() {
        super();
    }

    public Caster(int id, String name, int[] maxPoints, int[] spellIds, int[] maxSpellLevels) {
        this();
        this.id = id;
        this.name = name;
        this.maxPoints = maxPoints;
        this.spellIds = spellIds;
        this.maxSpellLevels = maxSpellLevels;
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

    public int[] getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int[] maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int[] getSpellIds() {
        return spellIds;
    }

    public void setSpellIds(int[] spellIds) {
        this.spellIds = spellIds;
    }

    public int[] getMaxSpellLevels() {
        return maxSpellLevels;
    }

    public void setMaxSpellLevels(int[] maxSpellLevels) {
        this.maxSpellLevels = maxSpellLevels;
    }
}
