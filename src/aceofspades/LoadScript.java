package aceofspades;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

/**
 *
 * @author Wryxo
 */

public class LoadScript {
    LuaState luaState;
    
    /**
    * Constructor
    * @param fileName File name with Lua script.
    */
    public LoadScript(final String fileName) {
        this.luaState = LuaStateFactory.newLuaState();
        this.luaState.openLibs();
        this.luaState.LdoFile("scripts/" + fileName);
    }
    
    /**
    * Ends the use of Lua environment.
    */
    public void closeScript() {
        this.luaState.close();
    }
    
    /**
    * Call a Lua function inside the Lua script to insert
    * data into a Java object passed as parameter
    * @param functionName Name of Lua function.
    * @param obj A Java object.
    */
    public void runScriptFunction(String functionName, Object obj) {
        this.luaState.getGlobal(functionName);
        if (obj != null) this.luaState.pushJavaObject(obj);
        this.luaState.call(1, 0);
    }
}
