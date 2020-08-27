package com.epam.afshop.acition.impl;

import com.epam.afshop.acition.Action;
import com.epam.afshop.dao.impl.UserDaoImpl;
import com.epam.afshop.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.epam.afshop.acition.impl.ActionConstant.INDEX_URL;

public class RegisterAction implements Action {
    private static final int DEFAULT_USER_TYPE_ID = 1;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.setUserTypeId(DEFAULT_USER_TYPE_ID);

        if(userDao.getByEmail(email) != null){
            userDao.create(newUser);
            
        }


        
        request.getRequestDispatcher(INDEX_URL).forward(request, response);
    }
}
