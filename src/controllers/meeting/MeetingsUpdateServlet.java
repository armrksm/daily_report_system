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

import models.Meeting;
import models.validators.MeetingValidator;
import utils.DBUtil;

@WebServlet("/meetings/update")
public class MeetingsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MeetingsUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Meeting m = em.find(Meeting.class, (Integer)(request.getSession().getAttribute("meeting_id")));

            m.setMeeting_date(Date.valueOf(request.getParameter("meeting_date")));
            m.setTitle(request.getParameter("title"));
            m.setContent(request.getParameter("content"));
            m.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = MeetingValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("report", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meetings/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("meeting_id");

                response.sendRedirect(request.getContextPath() + "/meetings/index");
            }
        }
    }

}