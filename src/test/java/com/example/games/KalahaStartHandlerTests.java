package com.example.games;

import com.example.games.kalaha.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KalahaStartHandlerTests {

    @MockBean
    CacheBoardRepositoryImpl repository;

    @Autowired
    @Qualifier("kalahaStartHandler")
    StartHandlerImpl handler;

    @Test
    public void kalahaStartHandlerShouldReturnResult() throws Exception {

        StartCommandImpl command = new StartCommandImpl("Player1", "Player2");

        var board = new BoardImpl();
        board.setId(1);

        when(repository.save(any(BoardImpl.class))).thenReturn(board);
        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isInstanceOf(BoardImpl.class);
    }
}
