package nl.workingtalent.abnassignment.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.repository.LoginRepository;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationToken = request.getHeader("Authorization");
		
		if (authorizationToken != null && !authorizationToken.isBlank()) {
			// Remove the part "Bearer " at the start of the token
			String token = authorizationToken.substring(7);

			Optional<Login> loginOptional = loginRepository.findByToken(token);
			if (loginOptional.isPresent()) {
				request.setAttribute("XYZ_LOGIN", loginOptional.get());
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
