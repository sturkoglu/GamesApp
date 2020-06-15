package com.example.games.kalaha;

import com.example.games.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service("kalahaGetBoardHandler")
@RequestScope
public class GetBoardHandlerImpl implements Handler<GetBoardCommandImpl, MoveResultImpl> {
//todo: could be one ResultImpl
    private MoveResultImpl result = new MoveResultImpl();

    @Autowired
    CacheBoardRepositoryImpl repository;

    @Override
    public MoveResultImpl handle(GetBoardCommandImpl command) {

        if(!command.isValid()){
            result.addErrorMessages("Invalid command");
            return result;
        }

        var board = repository.getById(command.getBoardId());
        if(board == null){
            result.addErrorMessages("Board {" + command.getBoardId() + "} not found");
            return result;
        }

        result.setContent(board);

        return result;
    }
}