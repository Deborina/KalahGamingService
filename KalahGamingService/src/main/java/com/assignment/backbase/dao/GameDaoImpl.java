package com.assignment.backbase.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.ApplicationCache;
@Repository
public class GameDaoImpl implements GameDao {

	@Autowired
	Game game;
	@Autowired
	ApplicationCache cache;
	
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


}
