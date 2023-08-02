package com.project.githering.Admin.Controller;

import com.project.githering.Admin.DTO.AdminGeneralUserInformDTO;
import com.project.githering.Admin.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<Page<AdminGeneralUserInformDTO>> getUsers(Pageable pageable) {
        return new ResponseEntity<>(adminService.getUsers(pageable).map(AdminGeneralUserInformDTO::new), HttpStatus.OK);
    }
}

