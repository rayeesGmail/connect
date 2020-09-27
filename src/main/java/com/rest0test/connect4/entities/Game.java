package com.rest0test.connect4.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private Long gameId;
    private String status;

    private String winner;

    @OneToMany(mappedBy = "game")
    private Set<GameMove> gameMoves;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Set<GameMove> getGameMoves() {
        return gameMoves;
    }

    public void setGameMoves(Set<GameMove> gameMoves) {
        this.gameMoves = gameMoves;
    }
}
