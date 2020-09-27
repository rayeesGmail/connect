package com.rest0test.connect4.services;

import com.rest0test.connect4.dtos.RequestDTO;
import com.rest0test.connect4.entities.GameMove;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Set;

public interface GameService {

    Map<String, Object> startGame();

    Map<String, Object> playMove(RequestDTO dto) throws Exception;

    Set<GameMove> getAllMoves(Long gameId) throws EntityNotFoundException;
}
