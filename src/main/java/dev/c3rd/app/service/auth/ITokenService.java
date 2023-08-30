package dev.c3rd.app.service.auth;

import dev.c3rd.app.model.user.User;

public interface ITokenService {

    String generateToken(User user);
    String validateToken(String token);

}
