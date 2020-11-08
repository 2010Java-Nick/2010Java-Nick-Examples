package SpellPointTracker.pojos;

import java.util.Arrays;

/**
 * An object representing the playable casting classes available in Dungeons and
 * Dragons 5th edition
 */
public class Caster {
    private int id;
    private String name;
    private boolean halfCaster;
    private Integer[] spellIds;

    public Caster() {
        super();
    }

    public Caster(int id, String name, boolean halfCaster, Integer[] spellIds) {
        this();
        this.id = id;
        this.name = name;
        this.halfCaster = halfCaster;
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

    public boolean getHalfCaster() {
        return halfCaster;
    }

    public void setHalfCaster(boolean halfCaster) {
        this.halfCaster = halfCaster;
    }

    public Integer[] getSpellIds() {
        return spellIds;
    }

    public void setSpellIds(Integer[] spellIds) {
        this.spellIds = spellIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(spellIds);
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
        Caster other = (Caster) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(spellIds, other.spellIds))
            return false;
        return true;
    }

}
