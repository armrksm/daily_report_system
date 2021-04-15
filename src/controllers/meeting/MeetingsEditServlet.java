package controllers.meeting;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Meeting;
import utils.DBUtil;

@WebServlet("/meetings/edit")
public class MeetingsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MeetingsEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Meeting m = em.find(Meeting.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        if(m != null && login_employee.getId() == m.getEmployee().getId()) {
            request.setAttribute("meeting", m);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("meeting_id", m.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetings/edit.jsp");
        rd.forward(request, response);
    }

}