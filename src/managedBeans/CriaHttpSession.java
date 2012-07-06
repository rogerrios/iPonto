package managedBeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CriaHttpSession {
	
	private HttpSession session;

	public HttpSession getSession() {
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		session = request.getSession();
		return session;
	}
	

}
