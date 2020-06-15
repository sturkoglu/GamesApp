package com.example.games.kalaha;

import com.example.games.kalaha.request.MoveRequest;
import com.example.games.kalaha.request.StartRequest;
import com.example.games.kalaha.viewModel.BoardViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController("kalaha")
@RequestMapping("kalaha")
public class Controller {

    final StartHandlerImpl startHandler;
    final MoveHandlerImpl moveHandler;
    final GetBoardHandlerImpl getBoardHandler;

    public Controller(StartHandlerImpl startHandler, MoveHandlerImpl moveHandler, GetBoardHandlerImpl getBoardHandler) {
        this.startHandler = startHandler;
        this.moveHandler = moveHandler;
        this.getBoardHandler = getBoardHandler;
    }

    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity start(@RequestBody StartRequest request) {

        StartCommandImpl command = new StartCommandImpl(request.getPlayer1().getName(), request.getPlayer2().getName());

        var result = startHandler.handle(command);

        if(!result.isSuccess())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getErrorMessages());

        return ResponseEntity.ok(new BoardViewModel(result.getContent()));
//        URI location = URI.create(String.format("/board/%s", result.getContent().getId()));
//        return ResponseEntity.created(location).build();
        //todo: change to create and introduce GET api
        //todo: map it to view model
    }

    @PutMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity move(@RequestBody MoveRequest request) {

        MoveCommandImpl command = new MoveCommandImpl(request.getId(), request.getPlayerName(), request.getRowPosition(), request.getColPosition());

        var result = moveHandler.handle(command);

        if(!result.isSuccess())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getErrorMessages());

        return ResponseEntity.ok(new BoardViewModel(result.getContent()));
    }

    @GetMapping(value = "/board/{boardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity get(@PathVariable(required = true) int boardId) {

        GetBoardCommandImpl command = new GetBoardCommandImpl(boardId);

        var result = getBoardHandler.handle(command);

        if(!result.isSuccess())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.getErrorMessages());

        return ResponseEntity.ok(new BoardViewModel(result.getContent()));
    }
}

