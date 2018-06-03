package com.gracyya.interceptor;
import com.gracyya.model.Myuser;
import com.gracyya.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpSession;

import static com.gracyya.controller.UserController.getSession;

@Aspect
public class loginVerify {
    UserService userService;

    @Before("execution(* com.gracyya.controller.UserController.detail(..))")
    public Boolean verify() {
        HttpSession session = getSession();
        Myuser user = (Myuser) session.getAttribute("user");
        if ((user != null) && user.getPassword().equals(userService.getPasswordByName(user.getName()))) {
            return true;
        } else {
            return false;
        }
    }
}
