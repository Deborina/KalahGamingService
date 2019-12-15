package com.assignment.backbase.service;

import com.assignment.backbase.model.Game;

public interface GameService {

	Game createGame();

	Game makeMove(String gameId, int pitId);
}
