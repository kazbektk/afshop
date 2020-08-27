package com.epam.afshop.acition.impl;

import com.epam.afshop.acition.Action;
import com.epam.afshop.dao.UserDao;
import com.epam.afshop.dao.UserTypeDao;
import com.epam.afshop.dao.impl.UserDaoImpl;
import com.epam.afshop.dao.impl.UserTypeDaoImpl;
import com.epam.afshop.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        HttpSession session = request.getSession();
        UserTypeDao userTypeDao = new UserTypeDaoImpl();
        String userType;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDao.getByEmailAndPassword(email, password);

        if(user != null){
            userType = userTypeDao.getById(user.getUserTypeId()).getName();
            session.setAttribute("user", user);
            session.setAttribute("userType", userType);
            response.sendRedirect(ActionConstant.INDEX_URL);
        }

    }
}
