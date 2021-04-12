package controllers.meeting;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Meeting;
import utils.DBUtil;

@WebServlet("/meetings/index")
public class MeetingsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MeetingsIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Meeting> meetings = em.createNamedQuery("getAllMeetings", Meeting.class)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long meetings_count = (long)em.createNamedQuery("getMeetingsCount", Long.class)
                                     .getSingleResult();

        em.close();

        request.setAttribute("meetings", meetings);
        request.setAttribute("meetings_count", meetings_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetings/index.jsp");
        rd.forward(request, response);
    }

}