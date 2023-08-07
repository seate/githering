package com.project.githering.User.General.Controller;

import com.project.githering.JWT.Exception.AccessTokenNotExistException;
import com.project.githering.JWT.auth.JwtTokenProvider;
import com.project.githering.User.General.DTO.GeneralUserInformResponseDTO;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final GeneralUserService generalUserService;
    private final JwtTokenProvider jwtTokenProvider;

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

    @GetMapping("/findIdByLoginUser")
    public ResponseEntity<Long> findIdByLoginUser(@RequestParam(value = "loginUser") String loginUser) {
        return new ResponseEntity<>(generalUserService.findIdByLoginUser(loginUser), HttpStatus.OK);
    }

    @GetMapping("/findEmailById")
    public ResponseEntity<String> findEmailById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(generalUserService.findLoginUserById(id), HttpStatus.OK);
    }
}

