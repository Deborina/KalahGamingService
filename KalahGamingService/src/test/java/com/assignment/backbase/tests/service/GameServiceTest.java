package com.assignment.backbase.tests.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.backbase.command.GameCommand;
import com.assignment.backbase.command.MakeMoveCommand;
import com.assignment.backbase.dao.GameDao;
import com.assignment.backbase.dao.GameDaoImpl;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.service.GameService;
import com.assignment.backbase.service.GameServiceImpl;
import com.assignment.backbase.util.ApplicationCache;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { GameServiceImpl.class, Game.class, GameDaoImpl.class, ApplicationCache.class,
		MakeMoveCommand.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameServiceTest {

	@MockBean
	private GameDao gameDao;
	@MockBean
	GameCommand command;
	@Autowired
	GameService gameService;
	@Autowired
	Game game;

	@Test
	public void testCreateGame() {
		when(gameDao.createGame()).thenReturn(game);
		Game newGame = gameService.createGame();
		assertEquals(game.getId(), newGame.getId());
		assertEquals(game.getUrl(), newGame.getUrl());
	}

	@Test
	public void testMakeMove() {
		when(gameDao.findGameById("1234")).thenReturn(game);
		when(gameDao.saveGame(game)).thenReturn(game);
		Game savedGame = gameService.makeMove("1234", 4);
		verify(command).execute(game, 4);
		assertEquals("1234", savedGame.getId());
		assertEquals(game.getUrl(), savedGame.getUrl());
		MatcherAssert.assertThat(savedGame.getStatus(), CoreMatchers.is(game.getStatus()));
	}

}
