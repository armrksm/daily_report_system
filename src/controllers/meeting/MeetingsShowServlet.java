package controllers.meeting;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Meeting;
import utils.DBUtil;

@WebServlet("/meetings/show")
public class MeetingsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MeetingsShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Meeting m = em.find(Meeting.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("meeting", m);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetings/show.jsp");
        rd.forward(request, response);
    }

}