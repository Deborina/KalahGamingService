package com.assignment.backbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.backbase.dao.GameDao;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.model.Game;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gameDao;
	@Override
	public Game createGame() {
		
	return gameDao.createGame();
	}

	@Override
	public Game makeMove(String gameId, int pitId) throws Exception {
		if(null == gameDao.findGameById(gameId))
			throw new Exception("Game with id: " + gameId + " not found.");
		
		Game game = gameDao.findGameById(gameId);
		 Status status = game.getStatus();
	        if (status != Status.IN_PROGRESS) {
	            throw new Exception("Game has been already terminated with status:" + status);
	        }
		return null;
	}

}
