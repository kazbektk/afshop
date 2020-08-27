package com.epam.afshop.controller;

import com.epam.afshop.acition.Action;
import com.epam.afshop.acition.factory.ActionFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    //TODO make actionFactory and actions
    //TODO Restructure Controller

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAction(req, resp);
    }

    public void init(ServletConfig config) throws ServletException{
        super.init();
    }

    private void getAction(HttpServletRequest request, HttpServletResponse response){
        Action action = ActionFactory.getInstance().getAction(request);
        try {
            action.execute(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
