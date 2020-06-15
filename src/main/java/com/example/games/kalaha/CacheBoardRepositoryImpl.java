package com.example.games.kalaha;

import com.example.games.core.BoardRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Comparator.comparing;

@Service("CacheBoardRepository")
@Scope("singleton")
public class CacheBoardRepositoryImpl implements BoardRepository<BoardImpl> {

    List<BoardImpl> boards = new ArrayList<>();

    @Override
    public BoardImpl getById(int boardId) {
        return boards.stream()
                .filter(b -> boardId == b.getId())
                .findAny()
                .orElse(null);
    }

    @Override
    public BoardImpl save(BoardImpl board) {
        board.setId(getNextId());
        boards.add(board);

        return getById(board.getId());
    }

    @Override
    public BoardImpl update(BoardImpl board) {
        //todo: don t need this method for memoryCache
        return getById(board.getId());
    }

    private int getNextId() {
        if(boards.isEmpty()) return 1;

        int latestId = boards
                .stream()
                .max(comparing(BoardImpl::getId))
                .orElseThrow(NoSuchElementException::new)
                .getId();
        return ++latestId;
    }
}
