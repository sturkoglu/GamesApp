package com.example.games;

import com.example.games.kalaha.*;
import com.example.games.kalaha.request.MoveRequest;
import com.example.games.kalaha.request.PlayerRequest;
import com.example.games.kalaha.request.StartRequest;
import com.example.games.shared.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@AutoConfigureMockMvc
class GamesKalahaAPITests {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    TestRestTemplate restTemplate;
    @MockBean
    RestTemplateBuilder restTemplateBuilder;
    @MockBean
    StartHandlerImpl startHandler;
    @MockBean
    MoveHandlerImpl moveHandler;
    @MockBean
    GetBoardHandlerImpl getBoardHandler;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void kalahaStartShouldReturnOk() throws Exception {

        StartRequest request = new StartRequest(new PlayerRequest("Player1"), new PlayerRequest("Player2"));
        var board = new BoardImpl();
        board.setPlayers(new Player[] {new Player(), new Player()});
        board.setUp();

        StartResultImpl startResult = new StartResultImpl();
        startResult.setContent(board);

        when(startHandler.handle(any(StartCommandImpl.class))).thenReturn(startResult);

        this.mockMvc
                .perform(post("/kalaha/start").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void kalahaMoveShouldReturnOk() throws Exception {

        MoveRequest request = new MoveRequest("Player1");
        request.setId(1);
        request.setRowPosition(0);
        request.setColPosition(3);

        var board = new BoardImpl();
        board.setPlayers(new Player[] {new Player(), new Player()});
        board.setUp();

        MoveResultImpl moveResult = new MoveResultImpl();
        moveResult.setContent(board);

        when(moveHandler.handle(any(MoveCommandImpl.class))).thenReturn(moveResult);

        this.mockMvc
                .perform(put("/kalaha/move").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void kalahaGetBoardShouldReturnOk() throws Exception {

        var board = new BoardImpl();
        board.setPlayers(new Player[] {new Player(), new Player()});
        board.setUp();

        MoveResultImpl getBoardResult = new MoveResultImpl();
        getBoardResult.setContent(board);

        when(getBoardHandler.handle(any(GetBoardCommandImpl.class))).thenReturn(getBoardResult);

        this.mockMvc
                .perform(get("/kalaha/board/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
