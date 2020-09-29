package ca.gc.tri_agency.granting_data.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);

		SecurityContextHolder.clearContext();
		
		if (session != null) {
			session.invalidate();
		}

		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
		
		redirectAttributes.addFlashAttribute("logout", true);

		return "redirect:/login";
	}

}
