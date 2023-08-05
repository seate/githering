package com.project.githering.Admin.Annotation;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class GeneralUserSoftDeleteAOP {

    private final EntityManager entityManager;

    private final ThreadLocal<Boolean> isExclude = new ThreadLocal<>();

    // 여기를 바꾸면 Entity의 @FilterDef(~) 의 name도 바꿔줘야 함
    private final String filterName = "softDeleteGeneralUserFilter";

    public GeneralUserSoftDeleteAOP(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.isExclude.set(true);
    }

    @Around("execution(* com.project.githering.User.General.Service.*.*(..))")
    public Object exclude(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!isExclude.get()) return joinPoint.proceed();

        Session session = entityManager.unwrap(Session.class);
        session.enableFilter(filterName);

        try {
            return joinPoint.proceed();
        } finally {
            session.disableFilter(filterName);
        }
    }

    @Around("execution(* com.project.githering.Admin.Service.*.*(..))" +
            // 재생성 시 soft deleted된 것도 찾아야 함
            "|| execution(* com.project.githering.User.General.Service.GeneralUserServiceImpl.loadUser(..))")
    public Object include(ProceedingJoinPoint joinPoint) throws Throwable {
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter(filterName);

        try {
            isExclude.set(false);
            return joinPoint.proceed();
        } finally {
            session.enableFilter(filterName);
            isExclude.set(true);
        }
    }
}
