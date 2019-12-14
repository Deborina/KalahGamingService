package com.assignment.backbase.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.assignment.backbase.exceptions.ApplicationException;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.ApplicationCache;

@Repository
public class GameDaoImpl implements GameDao {

	@Autowired
	ApplicationCache cache;
	@Autowired
	Game game;
	@Override
	public Game createGame() {
		
		cache.put(game.getId(), game);
		return game;

	}

	@Override
	public Game findGameById(String gameId) {

		return cache.get(gameId);
	}

	@Override
	public Game saveGame(Game game)  {
		if(null!= game)
		{
		String gameId = game.getId();
		Game  gameObj = findGameById(gameId);
			gameObj.setStatus(game.getStatus());
			gameObj.setPlayer(game.getPlayer());
			gameObj.setGameStatus(game.getGameStatus());
			gameObj.setUrl(game.getUrl());
			cache.put(gameId, gameObj);
		return cache.get(gameId);
		}
		return game;
	}

}
