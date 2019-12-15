package com.assignment.backbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.backbase.model.Game;
import com.assignment.backbase.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/games")
@Api(value = "Kalah online game", description = "Start a new game and keep playing")
public class KalahGameController {

	@Autowired
	GameService gameService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new game")
	public Game createNewGame() {
		return gameService.createGame();
	}

	@PutMapping("/{gameId}/pits/{pitId}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "make new move in the game")
	public Game makeMove(@PathVariable String gameId, @PathVariable int pitId) {
		Game game = null;
		game = gameService.makeMove(gameId, pitId);
		return game;
	}
}
