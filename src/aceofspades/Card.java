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
    protected String value;
    protected String suit;
    
    private String name;
    private String farba;
    
    private LoadScript script;
    
    public Card(String card) {
        this.script = new LoadScript(card + ".lua"); 
        script.runScriptFunction("create", this);        
        script.closeScript();   
    }
    
    
    public void setZnak(String _name) {
        this.name = _name;               
    }
    
    public void setFarba(String _farba) {
        this.farba = _farba;               
    }
    
    public String getZnak() {
        return this.name; 
    }
    
    public String getFarba() {
        return this.farba;
    }
    
    
}
