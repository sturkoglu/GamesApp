package com.example.games.kalaha;

import com.example.games.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service("kalahaMoveHandler")
@RequestScope
public class MoveHandlerImpl implements Handler<MoveCommandImpl, MoveResultImpl> {

    private MoveResultImpl result = new MoveResultImpl();

    @Autowired
    CacheBoardRepositoryImpl repository;

    @Override
    public MoveResultImpl handle(MoveCommandImpl command) {

        if(!command.isValid()){
            result.addErrorMessages("Invalid command");
            return result;
        }

        var board = repository.getById(command.getBoardId());
        if(board == null){
            result.addErrorMessages("Board {" + command.getBoardId() + "} not found");
            return result;
        }

        var player = board.getPlayer(command.getPlayerName());
        if(player == null){
            result.addErrorMessages("Player {" + command.getPlayerName() + "} not found");
            return result;
        }
        if(!player.isPlayerTurn()){
            result.addErrorMessages("It is not Player {" + command.getPlayerName() + "} turn");
            return result;
        }

        if(!board.canMove(player, command.getRowPosition(), command.getColPosition())){
            result.addErrorMessages("Player {" + player.getName() + "} cannot move");
            return result;
        }

        board.move(player, command.getRowPosition(), command.getColPosition());

        repository.update(board);

        result.setContent(repository.getById(board.getId()));

        return result;
    }
}