package data.service;

import data.dto.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    String login(LoginRequest loginRequest) throws Exception;
}
