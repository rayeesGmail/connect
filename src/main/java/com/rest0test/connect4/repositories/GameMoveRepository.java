package com.rest0test.connect4.repositories;

import com.rest0test.connect4.entities.Game;
import com.rest0test.connect4.entities.GameMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameMoveRepository extends JpaRepository<GameMove, Long> {

    @Query(value = "select count(gm) from GameMove gm where gm.game = :game " +
            " and gm.column_value = :columnValue")
    Integer getMoveCountForColumn(@Param("game") Game game,
                                  @Param("columnValue") Integer columnValue);


}
