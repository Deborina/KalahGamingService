package com.assignment.backbase.dao;

import com.assignment.backbase.model.Game;

public interface GameDao {

	Game createGame();
	Game findGameById(String gameId);
	Game saveGame(Game game) throws Exception;
}
