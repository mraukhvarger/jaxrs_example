package com.ystu.jaxrs.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OnStartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        new DB().insertWithStatement();
    }

}
