/**
 * @author jorge.torner
 * @since 04/01/2012
 */
package com.datacode.avon_ots_admin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Clase para filtrar cada peticion antes de ser atendida. Revisa la sesión y de no existir redirecciona al login
 * @author jorge.torner
 * @since 04/01/2012
 */
public class SessionFilter implements Filter {
	 
    private ArrayList<String> urlList;
 
    public void destroy() {
    }
 
    public void doFilter(ServletRequest request, 
    		ServletResponse response, 
    		FilterChain chain)
    				throws IOException, ServletException
    				{
    	boolean authorized = false;
    	//Revisamos la sesión y las variables de configuración
    	if (request instanceof HttpServletRequest) {
    		HttpSession session = ((HttpServletRequest)request).getSession(false);
    		if (session != null) {
    			Configuracion config = (Configuracion) session.getAttribute("configuracion");
    			if (config != null)
    				authorized = true;
    		}
    	}
    	
    	boolean idPostBack = Utils.isPostback();
    	
    	//Revisamos si se solicita el login, se atiende aunque no haya sesión
    	HttpServletRequest req = (HttpServletRequest) request;
//        String url = req.getServletPath();
        StringBuffer url = req.getRequestURL();
        //La solicitud del login o cualquier otra cosa que no termine en .jsp (como estilos, richfaces, imágenes) se va a enviar normalmente aunque no haya sesión
        if((url.toString().contains("login.jsp") || !url.toString().endsWith(".jsp")) && !url.toString().endsWith("avon_ots_admin/"))
        	authorized = true;
        
    	ServletContext filterConfig = ((HttpServletRequest)request).getSession().getServletContext();
//    	ExternalContext filterConfig = FacesContext.getCurrentInstance().getExternalContext();

    	if (authorized) {
    		//Aqui se atiende la petición normalmente
    		chain.doFilter(request, response);
    		return;
    	} else if (filterConfig != null) {
    		//Redireccionamos al login
//    		String login_page = filterConfig.getInitParameter("loginPage");
    		String login_page = "/avon_ots_admin/faces/Paginas/login.jsp";
    		//filterConfig.getRequestDispatcher(login_page).forward(request, response);
    		((HttpServletResponse)response).sendRedirect(login_page);
    		return;
    	}

    	throw new ServletException
    	("No se pudo redirigir a la página de login, por favor indique la url manualmente");

}
 
    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
 
        urlList = new ArrayList<String>();
 
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
 
        }
    }
}
