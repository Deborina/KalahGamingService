package com.assignment.backbase.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.assignment.backbase.model.Game;

@Component
@Scope(scopeName = "singleton")
public class ApplicationCache {

	private   Map<String, Game> gameMap ;
	
	
	
	public ApplicationCache() {
		super();
		this.gameMap = new HashMap<>();
	}



	public Map<String, Game> getGameMap() {
		return gameMap;
	}
	
	public void put(String key, Game game) {
		gameMap.put(key, game);
	}
	
	public Game get(String key) {
		return gameMap.get(key);
	}
}
