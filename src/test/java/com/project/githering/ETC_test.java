package com.project.githering;

import org.junit.jupiter.api.Test;


public class ETC_test {
    @Test
    void r1() {
        ThreadLocal<Boolean> tl = new ThreadLocal<>();

        tl.set(true);

        System.out.println(tl.get());

    }


}
