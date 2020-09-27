package com.rest0test.connect4.services;

import com.rest0test.connect4.dtos.RequestDTO;
import com.rest0test.connect4.entities.Game;
import com.rest0test.connect4.entities.GameMove;
import com.rest0test.connect4.repositories.GameMoveRepository;
import com.rest0test.connect4.repositories.GameRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private final Integer totalColumns = 7;
    private final Integer totalRows = 6;

    private final GameRepository gameRepository;
    private final GameMoveRepository gameMoveRepository;


    public GameServiceImpl(GameRepository gameRepository, GameMoveRepository gameMoveRepository) {
        this.gameRepository = gameRepository;
        this.gameMoveRepository = gameMoveRepository;
    }

    @Override
    public Map<String, Object> startGame() {

        Game game = new Game();

        game.setGameId(new Date().getTime());
        game.setStatus("READY");

        Game save = gameRepository.save(game);

        Map<String, Object> map = new HashMap<>();

        map.put("status", game.getStatus());
        map.put("gameId", game.getGameId());

        return map;
    }

    @Override
    public Map<String, Object> playMove(RequestDTO dto) throws Exception {

//        validate column value
        if(dto.getValue() == null || dto.getValue() < 1 || dto.getValue() > totalColumns){
            throw new Exception("Invalid");
        }

//        validate game token
        Optional<Game> optional = gameRepository.findByGameId(dto.getGameId());

        if(!optional.isPresent()) throw new EntityNotFoundException("Game Not Found");

        Game game = optional.get();

//        validate move
        Integer moveCountForColumn = gameMoveRepository.getMoveCountForColumn(game, dto.getValue());

        if(moveCountForColumn >= totalRows) throw new Exception("Invalid");


        if((moveCountForColumn + 1) % 2 > 0){

            // Move for Yellow Coin
            GameMove gameMove = new GameMove();

            gameMove.setGame(game);
            gameMove.setColumn_value(dto.getValue());
            gameMove.setRow_value(moveCountForColumn + 1);
            gameMove.setCoinType("YELLOW");

            GameMove save = gameMoveRepository.save(gameMove);

            Map<String, Object> map = new HashMap<>();

            map.put("status", "Valid");
            map.put("moveId", save.getId());

            return map;

        }else {

            // Move for Red Coin
            GameMove gameMove = new GameMove();

            gameMove.setGame(game);
            gameMove.setColumn_value(dto.getValue());
            gameMove.setRow_value(moveCountForColumn + 1);
            gameMove.setCoinType("RED");

            GameMove save = gameMoveRepository.save(gameMove);

            Map<String, Object> map = new HashMap<>();

            map.put("status", "Valid");
            map.put("moveId", save.getId());

            return map;
        }

    }

    @Override
    public Set<GameMove> getAllMoves(Long gameId) throws EntityNotFoundException {

        //        validate game token
        Optional<Game> optional = gameRepository.findByGameId(gameId);

        if(!optional.isPresent()) throw new EntityNotFoundException("Game Not Found");

        Game game = optional.get();

        return game.getGameMoves();
    }

}
