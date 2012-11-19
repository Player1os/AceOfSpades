/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aceofspades;

/**
 * 
 * @author Player1os
 */

/*
 * Samo toto kludne cele zmen, zatial som si nieco vyrobil na test Lua :)
 */

public class Card {
    protected String znak;
    protected String farba;
    private LoadScript script;    
    
    public Card(String card){
        /* Loads Lua script for this race.*/
        this.script = new LoadScript("src/aceofspades/scripts/"+card+".lua");
        /*Call Lua create function.*/
        script.runScriptFunction("create", this);
    }
    
    public String getZnak() {
        return this.znak;
    }
    
    public String getFarba() {
        return this.farba;
    }
    
    public void setZnak(String znak) {
        this.znak = znak;
    }
    
    public void setFarba(String farba) {
        this.farba = farba;
    }
}
