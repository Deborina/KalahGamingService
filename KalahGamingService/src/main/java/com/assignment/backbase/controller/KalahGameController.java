package com.assignment.backbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.backbase.model.Game;
import com.assignment.backbase.service.GameService;

@RestController
@RequestMapping("/games")
public class KalahGameController {
	
@Autowired
GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createNewGame() {
    	 return ResponseEntity.status(HttpStatus.CREATED).body(gameService.createGame());
    }

    @PutMapping("/{gameId}/pits/{pitId}")
    public Game makeMove(@PathVariable int gameId,
                            @PathVariable int pitId) {
								return null;

       // return service.makeMove(gameId, pitId);
    }
}
