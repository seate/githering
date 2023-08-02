package com.project.githering.Admin.Service;

import com.project.githering.User.General.Entity.GeneralUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<GeneralUser> getUsers(Pageable pageable);
}
