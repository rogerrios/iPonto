package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		Usuario u = null;
		
		if (session.getAttribute("usuario") != null){
			u = (Usuario) session.getAttribute("usuario");
			String permissao = u.getPermissao();
			
			if (permissao.equalsIgnoreCase("admin") || permissao.equalsIgnoreCase("master")){
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
