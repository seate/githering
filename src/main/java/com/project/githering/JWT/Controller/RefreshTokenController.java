package com.project.githering.JWT.Controller;

import com.project.githering.JWT.DTO.RefreshTokenRequestDTO;
import com.project.githering.JWT.Exception.RefreshTokenExpiredException;
import com.project.githering.JWT.Service.RefreshTokenService;
import com.project.githering.JWT.auth.JwtToken;
import com.project.githering.JWT.auth.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    private final JwtTokenProvider jwtTokenProvider;

    @RequestMapping(method = RequestMethod.POST, value = "/reIssue")
    public ResponseEntity<JwtToken> reIssueAccessToken(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) throws RefreshTokenExpiredException {
        JwtToken jwtToken = refreshTokenService.reIssueTokens(refreshTokenRequestDTO.toEntity(jwtTokenProvider));
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
