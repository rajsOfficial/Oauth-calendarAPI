package com.main;

import java.io.IOException;
import java.util.Enumeration;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.Credentials;
import com.main.GoogleHelper;
import com.main.PMF;
import com.main.UserPojo;

@Controller
@RequestMapping("/oauthWithGoogle")
public class GoogleLogin {
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void loginWithGoogle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("into the controller");
		resp.sendRedirect(
				"https://accounts.google.com/o/oauth2/v2/auth?"
				+ "scope=https://www.googleapis.com/auth/calendar.readonly&access_type=offline"
				+ "&include_granted_scopes=false&state=/googleresp&"
				+ "redirect_uri=http://localhost:8989/oauthWithGoogle/response&response_type=code&client_id="
						+ Credentials.clientId + "&prompt=consent");
	}

	@RequestMapping(value = "/response", method = RequestMethod.GET)
	public ModelAndView getCredentials(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		System.out.println("into response method");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String authcode = req.getParameter("code");
		System.out.println("auth code is "+authcode);
		String accessToken = GoogleHelper.getAccessToken(authcode);

		if (accessToken == null) {
			resp.sendRedirect("login?error=token_fetch_error");

		}
		
		return new ModelAndView("show","msg",GoogleHelper.getUserInfo(accessToken));

		/*else {
			UserPojo pojo = GoogleHelper.getUserInfo(accessToken);
			pm.makePersistent(pojo);
		}*/
	}
}
