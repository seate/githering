package com.solution.loginSolution.User.General.Controller;

import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.General.DTO.*;
import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Exception.UserExistException;
import com.solution.loginSolution.User.General.Exception.UserNotExistException;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
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

    private final GeneralUserService generalUserService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid GeneralUserRegisterRequestDTO generalUserRegisterRequestDTO)
            throws UserExistException {
        generalUserService.register(generalUserRegisterRequestDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> withdrawal(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessTokenByRequest(request)
                .orElseThrow(AccessTokenNotExistException::new);

        Long userId = generalUserService.findIdByAuthentication();
        generalUserService.withdrawal(userId, accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // login은 JwtAuthenticationFilter에서 진행

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessTokenByRequest(request)
                        .orElseThrow(AccessTokenNotExistException::new);

        generalUserService.logout(accessToken);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/inform")
    public ResponseEntity<GeneralUserInformResponseDTO> findInform() {
        Long userId = generalUserService.findIdByAuthentication();
        GeneralUser generalUser = generalUserService.findById(userId).orElseThrow(UserNotExistException::new);
        return new ResponseEntity<>(new GeneralUserInformResponseDTO(generalUser), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<GeneralUserInformResponseDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(generalUserService.findAll(pageable).map(GeneralUserInformResponseDTO::new), HttpStatus.OK);
    }

    @GetMapping("/findIdByUserEmail")
    public ResponseEntity<Long> findIdByUserEmail(@RequestParam(value = "userEmail") String userEmail) {
        return new ResponseEntity<>(generalUserService.findIdByUserEmail(userEmail), HttpStatus.OK);
    }

    @GetMapping("/findEmailById")
    public ResponseEntity<String> findEmailById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(generalUserService.findUserEmailById(id), HttpStatus.OK);
    }


    @GetMapping("/emailCheck")
    public ResponseEntity<Void> userEmailDupCheck(@RequestParam(value = "email") String email) {
        if (generalUserService.userEmailDupCheck(email)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody GeneralUserChangePasswordRequestDTO generalUserChangePasswordRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        generalUserService.changePassword(userId, generalUserChangePasswordRequestDTO.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

