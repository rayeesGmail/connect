package com.rest0test.connect4.repositories;

import com.rest0test.connect4.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByGameId(@Param("gameId") Long gameId);

}
