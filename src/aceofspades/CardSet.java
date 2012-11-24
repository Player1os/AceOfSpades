package aceofspades;

/**
 * 
 * @author Player1os
 */
public class CardSet {
    
    protected String race;
    protected int defense;
    protected int attack;
    protected int life;
    
    private LoadScript script;
    
    public CardSet(String race) {
        this.script = new LoadScript(race+".lua");
        script.runScriptFunction("create", this);
    }
    public String getRace() {
        return race;
    }
    public int getDefense() {
        return this.defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getLife() {
        return this.life;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack() {
        return this.attack;
    }
} 
