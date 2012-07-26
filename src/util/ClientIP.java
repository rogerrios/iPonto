package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ClientIP {
	private FacesContext context = FacesContext.getCurrentInstance();
	private HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

	public String getIp(){
		return request.getRemoteAddr();
	}
}
