package com.assignment.backbase.config;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.backbase.model.Game;

@Configuration
public class GameConfig {

	@Value("{game.id}")
	private String GAME_ID;
	
	@Value("${game.url}")
	private String URL;
	
}
