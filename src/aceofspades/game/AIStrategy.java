package aceofspades.game;

import aceofspades.GameException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AIStrategy {
    
    private int _AIID;
    private String _name;
    private Invocable _engine;
    
    public AIStrategy(File file, int AIID) throws GameException, NullPointerException, 
            ScriptException, FileNotFoundException {
        _AIID = AIID;
        if (file == null) {
           throw new NullPointerException();
        }
        if (!file.exists()) {
            throw new GameException("The AIStrategy path does not exist");
        }
        
        _name = file.getName();
        
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        engine.eval(new FileReader(file));
        _engine = (Invocable)engine;
    }
    
    public int getAIID() {
        return _AIID;
    }
    
    @Override
    public String toString() {
        return "AI : " + _name;
    }
    
    public void playTurn(GameManager gameManager) throws ScriptException, NoSuchMethodException {
        _engine.invokeFunction("onTurn", gameManager);
    }
    
}
