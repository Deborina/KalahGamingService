package com.assignment.backbase.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

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
		
		cache.setGame(game);
		Map<String, Game> gameMap = new HashMap<>();
		gameMap.put(game.getId(), game);
		cache.setGameMap(gameMap);
		return game;

	}

	@Override
	public Game findGameById(String gameId) {

		return cache.getGameMap().get(gameId);
	}

	@Override
	public Game saveGame(Game game) throws Exception {
		if(null== game)
		{
			throw new Exception("Game to be saved is null");
		}
		String gameId = game.getId();
		Game  gameObj = findGameById(gameId);
			gameObj.setStatus(game.getStatus());
			gameObj.setPlayer(game.getPlayer());
			gameObj.setGameStatus(game.getGameStatus());
			gameObj.setUrl(game.getUrl());
		return gameObj;
	}

}
