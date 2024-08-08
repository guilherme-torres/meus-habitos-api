package guilherme.meus_habitos_api.controllers;

import guilherme.meus_habitos_api.dto.UserDto;
import guilherme.meus_habitos_api.dto.AuthResponseDto;
import guilherme.meus_habitos_api.entities.User;
import guilherme.meus_habitos_api.repositories.UserRepository;
import guilherme.meus_habitos_api.security.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenService jwtTokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(JwtTokenService jwtTokenService, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserDto body) {
        User user = userRepository.findByUsername(body.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
        if (passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            String token = jwtTokenService.generateToken(user);
            return ResponseEntity.ok().body(new AuthResponseDto(token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserDto body) {
        if (userRepository.existsByUsername(body.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
        String token = jwtTokenService.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDto(token));
    }
}
