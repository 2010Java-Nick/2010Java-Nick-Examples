package SpellPointTracker.pojos;

public class Caster {
    private int id;
    private String name;
    private int[] spellIds;

    public Caster() {
        super();
    }

    public Caster(int id, String name, int[] spellIds) {
        this();
        this.id = id;
        this.name = name;
        this.spellIds = spellIds;
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

    public int[] getSpellIds() {
        return spellIds;
    }

    public void setSpellIds(int[] spellIds) {
        this.spellIds = spellIds;
    }

}
