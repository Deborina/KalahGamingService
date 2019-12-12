package com.assignment.backbase.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.assignment.backbase.model.Game;

@Component
@Scope(scopeName = "singleton")
public class ApplicationCache {

	private   Map<String, Game> gameMap = new HashMap<>();
	
	public void setGameMap(Map<String, Game> gameMap) {
		this.gameMap = gameMap;
	}

	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Map<String, Game> getGameMap() {
		return gameMap;
	}
	
	
}
