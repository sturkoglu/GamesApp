package com.example.games.backgammon;

import com.example.games.core.Result;
import org.springframework.web.bind.annotation.*;

@RestController("backgammon")
@RequestMapping("backgammon")
public class Controller {

    @PostMapping("/start"/*, consumes = "application/json", produces = "application/json"*/)
    Result start(/*@RequestBody Player[] players*/) {

        return null;
    }

    @PutMapping("/move")
    Result move() {
        return null;
    }
}
