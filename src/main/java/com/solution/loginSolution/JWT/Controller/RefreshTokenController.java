package com.solution.loginSolution.JWT.Controller;

import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Exception.RefreshTokenExpiredException;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://**", maxAge = 3600) //TODO originPatterns 수정
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/reIssue")
    public ResponseEntity<JwtToken> reIssueAccessToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) throws RefreshTokenExpiredException {
        JwtToken jwtToken = refreshTokenService.reIssueTokens(refreshTokenRequestDTO);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
