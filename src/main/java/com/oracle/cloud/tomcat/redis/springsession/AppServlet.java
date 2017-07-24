package com.oracle.cloud.tomcat.redis.springsession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "AppServlet",
        urlPatterns = {"/"}
)
public class AppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        try (PrintWriter out = resp.getWriter()) {
            out.println("Session ID - " + session.getId() +"\n");
            out.println("ACCS Container - " + System.getenv("APAAS_CONTAINER_NAME") +"\n");
            out.println("Server IP:Port " + InetAddress.getLocalHost().getHostAddress()+":"+req.getServerPort() +"\n");
            out.println("Session Created - " + new Date(session.getCreationTime()) +"\n");
            out.println("Session accessed - " + new Date(session.getLastAccessedTime()) +"\n");
            out.println("Session inactive time - " + session.getMaxInactiveInterval() +"\n");

            
            Enumeration<String> valueNames = session.getAttributeNames();
            if (valueNames.hasMoreElements()) {
                out.println("Session attributes \n");
                while (valueNames.hasMoreElements()) {
                    String param = (String) valueNames.nextElement();
                    String value = session.getAttribute(param).toString();
                    out.println(param + " = " + value);
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String attributeName = req.getParameter("name");
        String attributeValue = req.getParameter("val");
        req.getSession().setAttribute(attributeName, attributeValue);
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
