package controllers.companies;


import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import models.validators.CompanyValidator;
import utils.DBUtil;

@WebServlet("/companies/update")
public class CompaniesUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CompaniesUpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = request.getParameter("_token");
		if(_token !=null && _token.equals(request.getSession().getId())){
			EntityManager em = DBUtil.createEntityManager();

			Company c = em.find(Company.class,(Integer)(request.getSession().getAttribute("company_id")));

			c.setName(request.getParameter("name"));
			c.setClient(request.getParameter("client"));
			c.setTell(request.getParameter("tell"));
			c.setAddress(request.getParameter("address"));

			List<String> errors = CompanyValidator.validate(c,true);
			if(errors.size() >0) {
				em.close();

				request.setAttribute("_token",request.getSession().getId());
				request.setAttribute("company", c);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/companies/edit.jsp");
				rd.forward(request, response);
			}else{
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "更新が完了しました。");

				request.getSession().removeAttribute("company_id");

				response.sendRedirect(request.getContextPath() + "/companies/index");
			}
		}

	}

}
