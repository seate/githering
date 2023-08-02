package com.project.githering.Admin.Service;

import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Service.GeneralUserService;
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
