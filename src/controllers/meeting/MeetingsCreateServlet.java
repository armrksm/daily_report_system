package controllers.meeting;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import models.Employee;
import models.Meeting;
import models.validators.MeetingValidator;
import utils.DBUtil;

@WebServlet("/meetings/create")
public class MeetingsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MeetingsCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Meeting m = new Meeting();

            m.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            m.setCompany((Company)request.getSession().getAttribute("select_company"));

            Date meeting_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("meeting_date");
            if(rd_str != null && !rd_str.equals("")) {
                meeting_date = Date.valueOf(request.getParameter("meeting_date"));
            }

            m.setMeeting_date(meeting_date);

            m.setTitle(request.getParameter("title"));
            m.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            List<String> errors = MeetingValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("meeting", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetings/new.jsp");
                rd.forward(request, response);

            } else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/meetings/index");
            }
        }
    }

}