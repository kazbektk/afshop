package com.epam.afshop.acition.factory;

import com.epam.afshop.acition.Action;
import com.epam.afshop.acition.impl.RegisterAction;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {
    private static Map<String, Action> actionMap = new ConcurrentHashMap<>();

    private static ActionFactory instance = null;

    static {
        actionMap.put("/register", new RegisterAction());
    }

    public static ActionFactory getInstance(){
        if(instance == null){
            instance = new ActionFactory();
        }
        return instance;
    }

    public Action getAction(HttpServletRequest request){
        return actionMap.get(request.getPathInfo().toLowerCase());
    }
}
