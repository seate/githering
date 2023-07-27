package com.solution.loginSolution.Admin.Service;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final GeneralUserService generalUserService;

    @Override
    public Page<GeneralUser> getUsers(Pageable pageable) {
        return generalUserService.findAll(pageable);
    }
}
