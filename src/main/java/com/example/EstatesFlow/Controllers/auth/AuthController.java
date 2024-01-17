package com.example.EstatesFlow.Controllers.auth;

import com.example.EstatesFlow.DTO.auth.LoginDTO;
import com.example.EstatesFlow.DTO.auth.RegisterDTO;
import com.example.EstatesFlow.Services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDto) {return authService.register(registerDto);}
    @PostMapping("/login")
    public ResponseEntity<Object>  login(@RequestBody LoginDTO loginDto)
    {
        return authService.login(loginDto);
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") final String token)
    {
        return authService.confirmToken(token);
    }

    @GetMapping("/refresh/{refreshToken}")
    public ResponseEntity<Object> renewAccessToken(@RequestParam("expiredToken") final String expiredToken, @PathVariable("refreshToken") final String refreshToken)
    {
        return authService.renewAccessToken(refreshToken,expiredToken);
    }
}
