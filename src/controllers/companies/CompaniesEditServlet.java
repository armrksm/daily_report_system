package controllers.companies;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import utils.DBUtil;

@WebServlet("/companies/edit")
public class CompaniesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CompaniesEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Company c = em.find(Company.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("company", c);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("company_id", c.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/companies/edit.jsp");
        rd.forward(request, response);
    }

}