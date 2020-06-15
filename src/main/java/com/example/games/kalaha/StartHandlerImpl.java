package com.example.games.kalaha;

import com.example.games.core.Handler;
import com.example.games.shared.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service("kalahaStartHandler")
@RequestScope
public class StartHandlerImpl implements Handler<StartCommandImpl, StartResultImpl> {

    private StartResultImpl result = new StartResultImpl();

    @Autowired
    CacheBoardRepositoryImpl repository;

    public StartHandlerImpl(CacheBoardRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public StartResultImpl handle(StartCommandImpl command) {

        if(!command.isValid()){
            result.addErrorMessages("Invalid command");
            return result;
        }

        var player1 = new Player();
        player1.setName(command.getPlayer1());
        player1.setPlayerTurn(true);

        var player2 = new Player();
        player2.setName(command.getPlayer2());
        player2.setPlayerTurn(false);

        Player[] players = new Player[] { player1, player2 };

        var board = new BoardImpl();
        board.setPlayers(players);

        board.setUp();

        //todo: does not make sense repository supposed return entity
        var entity = repository.save(board);

        result.setContent(repository.getById(entity.getId()));

        return result;
    }
}

