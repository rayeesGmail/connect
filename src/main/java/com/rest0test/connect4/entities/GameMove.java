package com.rest0test.connect4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_moves")
public class GameMove extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private Game game;

    private String coinType;

    private Integer column_value;
    private Integer row_value;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public Integer getColumn_value() {
        return column_value;
    }

    public void setColumn_value(Integer column_value) {
        this.column_value = column_value;
    }

    public Integer getRow_value() {
        return row_value;
    }

    public void setRow_value(Integer row_value) {
        this.row_value = row_value;
    }
}
