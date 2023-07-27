package com.solution.loginSolution.Admin.Service;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    public Page<GeneralUser> getUsers(Pageable pageable);
}
