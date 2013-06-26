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
    private String _name;
    private Invocable _engine;
    
    public AIStrategy(File file) throws GameException, NullPointerException, 
            ScriptException, FileNotFoundException {
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
    
    @Override
    public String toString() {
        return "AI : " + _name;
    }
    
    public void playTurn(GameManager gameManager) throws ScriptException, NoSuchMethodException {
        _engine.invokeFunction("onTurn", gameManager);
    }
    
}
