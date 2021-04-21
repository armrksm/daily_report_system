package controllers.companies;

import java.io.IOException;
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
import models.validators.CompanyValidator;
import utils.DBUtil;


@WebServlet("/companies/create")
public class CompaniesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CompaniesCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Company c = new Company();


            c.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            c.setName(request.getParameter("name"));

            c.setClient(request.getParameter("client"));
            c.setTell(request.getParameter("tell"));
            c.setAddress(request.getParameter("address"));


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);
            c.setDelete_flag(0);

            List<String> errors = CompanyValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("company", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/companies/new.jsp");
                rd.forward(request, response);

            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();

                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/companies/index");
            }
        }
    }

}