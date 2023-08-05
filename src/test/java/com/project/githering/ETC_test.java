package com.project.githering;

import com.project.githering.Admin.Service.AdminService;
import com.project.githering.User.General.Service.GeneralUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class ETC_test {

    @Autowired
    private GeneralUserService generalUserService;

    @Autowired
    private AdminService adminService;




    @Test
    void r1() {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return Sort.unsorted();
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        System.out.println("before general");
        generalUserService.findAll(pageable);
        System.out.println("after general");

        System.out.println("==============");

        System.out.println("before admin");
        adminService.getUsers(pageable);
        System.out.println("after admin");
    }


}
