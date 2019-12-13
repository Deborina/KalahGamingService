package com.assignment.backbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.assignment.backbase.command.GameCommand;
import com.assignment.backbase.dao.GameDao;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.model.Game;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gameDao;
	@Autowired
	@Qualifier("makeMoveCommand")
	GameCommand gameCommand;
	@Override
	public Game createGame() {
		
	return gameDao.createGame();
	}

	@Override
	public Game makeMove(String gameId, int pitId)  {
		if(null == gameDao.findGameById(gameId))
			try {
				throw new Exception("Game with id: " + gameId + " not found.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		Game game = gameDao.findGameById(gameId);
		 Status status = game.getStatus();
	        if (status != Status.IN_PROGRESS) {
	            try {
					throw new Exception("Game has been already terminated with status:" + status);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        gameCommand.execute(game, pitId);
	        Game savedGame = null;
			try {
				savedGame = gameDao.saveGame(game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return savedGame;
	}

}
