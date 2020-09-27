package com.rest0test.connect4.controllers;

import com.rest0test.connect4.dtos.RequestDTO;
import com.rest0test.connect4.entities.GameMove;
import com.rest0test.connect4.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity startGame(){

        Map<String, Object> map = gameService.startGame();

        return ResponseEntity.ok(map);

    }

    @PostMapping("/move")
    public ResponseEntity playMove(@RequestBody RequestDTO requestDTO){

        ResponseEntity<Map<String, Object>> response = null;

        try {

            Map<String, Object> map = gameService.playMove(requestDTO);

            response = ResponseEntity.ok(map);

        }catch (EntityNotFoundException e){

            Map<String, Object> map = new HashMap<>();
            map.put("status", "Error");
            map.put("message", e.getMessage());

            response = ResponseEntity.badRequest().body(map);

        }
        catch (Exception e) {

            Map<String, Object> map = new HashMap<>();
            map.put("status", "Invalid");
            map.put("message", e.getMessage());

            response = ResponseEntity.ok(map);

        }

        return response;

    }


    @GetMapping("/moves")
    public ResponseEntity getAllMoves(@RequestParam("gameId")Long gameId){

        Set<GameMove> allMoves = gameService.getAllMoves(gameId);

        Map<String, Object> map = new HashMap<>();
        map.put("moves", allMoves);

        return ResponseEntity.ok(map);

    }


}
