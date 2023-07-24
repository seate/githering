package com.solution.loginSolution.User.Normal.Controller;

import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.Normal.DTO.*;
import com.solution.loginSolution.User.Normal.Exception.UserExistException;
import com.solution.loginSolution.User.Normal.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO)
            throws UserExistException {
        userService.register(userRegisterRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> withdrawal() {
        Long userId = userService.findIdByAuthentication();
        userService.withdrawal(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // login은 JwtAuthenticationFilter에서 진행

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessTokenByRequest(request)
                        .orElseThrow(AccessTokenNotExistException::new);

        userService.logout(accessToken);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/inform")
    public ResponseEntity<UserInformResponseDTO> findInform() {
        return new ResponseEntity<>(userService.getInformation(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserInformResponseDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/findIdByUserEmail")
    public ResponseEntity<Long> findIdByUserEmail(@RequestParam(value = "userEmail") String userEmail) {
        return new ResponseEntity<>(userService.findIdByUserEmail(userEmail), HttpStatus.OK);
    }

    @GetMapping("/findEmailById")
    public ResponseEntity<String> findEmailById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(userService.findUserEmailById(id), HttpStatus.OK);
    }


    @GetMapping("/emailCheck")
    public ResponseEntity<Void> userEmailDupCheck(@RequestParam(value = "email") String email) {
        if (userService.userEmailDupCheck(email)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO) {
        userService.changePassword(userChangePasswordRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

