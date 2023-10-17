package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.example.anotations.Login;
import org.example.services.LoginService;

import java.util.Optional;
import jakarta.enterprise.context.SessionScoped;

@ApplicationScoped

public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }

}
