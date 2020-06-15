package com.example.games;

import static org.assertj.core.api.Assertions.assertThat;


import com.example.games.kalaha.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    @Qualifier("kalaha")
    private Controller kalahaController;

    @Autowired
    @Qualifier("backgammon")
    private com.example.games.backgammon.Controller backgammonController;
//todo:

    @Test
    public void kalahaContexLoads() throws Exception {
        assertThat(kalahaController).isNotNull();
    }

    @Test
    public void backgammonContexLoads() throws Exception {
        assertThat(backgammonController).isNotNull();
    }
}