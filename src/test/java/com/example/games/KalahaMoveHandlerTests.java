package com.example.games;

import com.example.games.kalaha.*;
import com.example.games.shared.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KalahaMoveHandlerTests {

    @MockBean
    CacheBoardRepositoryImpl repository;

    @Autowired
    @Qualifier("kalahaMoveHandler")
    MoveHandlerImpl handler;

    @Test
    public void ShouldReturnCorrectResultContent() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(true);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(false);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player1", 0, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getPlayer("Player1").getStack()).isEqualTo(1);
        assertThat(result.getContent().getPlayer("Player1").isPlayerTurn()).isTrue();
        assertThat(result.getContent().getPlayer("Player2").isPlayerTurn()).isFalse();

        var tiles = result.getContent().getTiles();
        assertThat(tiles[0][2].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][1].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][0].getPieceCount()).isEqualTo(5);
    }

    @Test
    public void ShouldReturnCorrectResultContentForOtherPlayersTiles() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 1, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getPlayer("Player1").getStack()).isEqualTo(0);
        assertThat(result.getContent().getPlayer("Player2").getStack()).isEqualTo(1);
        assertThat(result.getContent().getPlayer("Player1").isPlayerTurn()).isTrue();
        assertThat(result.getContent().getPlayer("Player2").isPlayerTurn()).isFalse();

        var tiles = result.getContent().getTiles();
        assertThat(tiles[1][4].getPieceCount()).isEqualTo(5);
        assertThat(tiles[1][5].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][5].getPieceCount()).isEqualTo(5);
    }

    @Test
    public void ShouldReturnCorrectResultContentForLastPieceInEmptyTile() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        var modifiedTiles = board.getTiles();
        modifiedTiles[1][4].setPieceCount(0);
        board.setTiles(modifiedTiles);

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 1, 0);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getPlayer("Player1").getStack()).isEqualTo(0);
        assertThat(result.getContent().getPlayer("Player2").getStack()).isEqualTo(5);
        assertThat(result.getContent().getPlayer("Player1").isPlayerTurn()).isTrue();
        assertThat(result.getContent().getPlayer("Player2").isPlayerTurn()).isFalse();

        var tiles = result.getContent().getTiles();
        assertThat(tiles[1][1].getPieceCount()).isEqualTo(5);
        assertThat(tiles[1][2].getPieceCount()).isEqualTo(5);
        assertThat(tiles[1][3].getPieceCount()).isEqualTo(5);
        assertThat(tiles[1][4].getPieceCount()).isEqualTo(0);
        assertThat(tiles[0][4].getPieceCount()).isEqualTo(0);
    }

    @Test
    public void ShouldReturnCorrectResultContentForLastPieceInEmptyTileSecondRound() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        var modifiedTiles = board.getTiles();
        modifiedTiles[1][0].setPieceCount(0);
        modifiedTiles[1][5].setPieceCount(8);
        board.setTiles(modifiedTiles);

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 1, 5);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getPlayer("Player1").getStack()).isEqualTo(0);
        assertThat(result.getContent().getPlayer("Player2").getStack()).isEqualTo(7);
        assertThat(result.getContent().getPlayer("Player1").isPlayerTurn()).isTrue();
        assertThat(result.getContent().getPlayer("Player2").isPlayerTurn()).isFalse();

        var tiles = result.getContent().getTiles();
        assertThat(tiles[0][5].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][4].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][3].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][2].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][1].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][0].getPieceCount()).isEqualTo(0);
        assertThat(tiles[1][0].getPieceCount()).isEqualTo(0);
    }

    @Test
    public void ShouldReturnCorrectResultContentForLastPieceInOtherPlayerEmptyTile() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        var modifiedTiles = board.getTiles();
        modifiedTiles[0][5].setPieceCount(0);
        board.setTiles(modifiedTiles);

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 1, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getPlayer("Player1").getStack()).isEqualTo(0);
        assertThat(result.getContent().getPlayer("Player2").getStack()).isEqualTo(1);
        assertThat(result.getContent().getPlayer("Player1").isPlayerTurn()).isTrue();
        assertThat(result.getContent().getPlayer("Player2").isPlayerTurn()).isFalse();

        var tiles = result.getContent().getTiles();
        assertThat(tiles[1][4].getPieceCount()).isEqualTo(5);
        assertThat(tiles[1][5].getPieceCount()).isEqualTo(5);
        assertThat(tiles[0][5].getPieceCount()).isEqualTo(1);
    }

    @Test
    public void ShouldReturnResultErrorForInvalidPosition() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 0, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        var result = handler.handle(command);

        assertThat(result.getContent()).isNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorMessages()).contains("Player {" + "Player2" + "} cannot move");
    }

    @Test
    public void ShouldReturnResultErrorForInvalidRow() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 3, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        handler.handle(command);
        var result = handler.handle(command);

        assertThat(result.getContent()).isNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorMessages()).contains("Invalid command");
    }

    @Test
    public void ShouldReturnResultErrorForInvalidColumn() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(true);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(false);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 0, 6);

        when(repository.getById(board.getId())).thenReturn(board);

        handler.handle(command);
        var result = handler.handle(command);

        assertThat(result.getContent()).isNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorMessages()).contains("Invalid command");
    }

    @Test
    public void ShouldReturnResultErrorForEmptyTile() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(1, "Player2", 0, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        handler.handle(command);
        var result = handler.handle(command);

        assertThat(result.getContent()).isNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorMessages()).contains("Player {" + "Player2" + "} cannot move");
    }

    @Test
    public void ShouldReturnResultErrorForNotFoundBoard() throws Exception {

        var player1 = new Player();
        player1.setName("Player1");
        player1.setPlayerTurn(false);

        var player2 = new Player();
        player2.setName("Player2");
        player2.setPlayerTurn(true);

        var board = new BoardImpl();
        board.setId(1);
        board.setPlayers(new Player[] {player1, player2});
        board.setUp();

        MoveCommandImpl command = new MoveCommandImpl(99, "Player2", 0, 3);

        when(repository.getById(board.getId())).thenReturn(board);

        handler.handle(command);
        var result = handler.handle(command);

        assertThat(result.getContent()).isNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getErrorMessages()).contains("Board {" + command.getBoardId() + "} not found");
    }
}
