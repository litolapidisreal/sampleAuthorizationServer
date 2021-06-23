package com.sampleAuth.server.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleAuth.server.models.APIResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Exception exception = (Exception) request.getAttribute("exception");
		String message = "";

		if (exception != null) {
			message = exception.toString();
		} else if (authException.getCause() != null) {
		    message = authException.getCause().toString() + " " + authException.getMessage();
		} else {
			message = authException.getMessage();
		}
        response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new APIResponse(message, "401")));

    }

}
