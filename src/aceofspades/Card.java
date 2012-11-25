/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aceofspades;

/**
 * 
 * @author Player1os
 */


public class Card {
    String value;
    String suit;
    
    private LoadScript script;
    
    public Card(String card){
        /* Loads Lua script for this race.*/
        script = new LoadScript("src/aceofspades/scripts/" + card + ".lua");
        /*Call Lua create function.*/
        script.runScriptFunction("create", this);
        script.closeScript();
    }
    
    public void setZnak(String znak) {
        value = znak;
    }
    
    public void setFarba(String znak) {
        suit = znak;
    }
    
    public String getZnak() {
        return value;
    }
    
    public String getFarba() {
        return suit;
    }
    
}
