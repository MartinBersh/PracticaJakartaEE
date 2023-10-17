package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.anotations.Login;
import org.example.services.LoginService;

import java.util.Arrays;
import java.util.Optional;
@ApplicationScoped
@Login
public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

}
