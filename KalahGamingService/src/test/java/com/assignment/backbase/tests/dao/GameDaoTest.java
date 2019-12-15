package com.assignment.backbase.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.backbase.dao.GameDaoImpl;
import com.assignment.backbase.enums.Player;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.ApplicationCache;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { GameDaoImpl.class, Game.class, ApplicationCache.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameDaoTest {

	@Autowired
	ApplicationCache cache;
	@Autowired
	private GameDaoImpl gameDao;
	@Autowired
	private Game game;

	@BeforeClass
	public static void setUp() {
	}

	@Test
	public void testSaveGame() {

		Game savedGame = gameDao.saveGame(game);
		assertNotNull(savedGame);
		assertEquals("1234", savedGame.getId());
		assertEquals(Status.IN_PROGRESS, savedGame.getGameStatus());
		assertEquals(Player.FIRST_PLAYER, savedGame.getPlayer());
	}

	@Test
	public void testFindGameById() {
		Game fetchedGameObj = gameDao.findGameById(game.getId());
		assertNotNull(fetchedGameObj);
		assertEquals("1234", fetchedGameObj.getId());
	}

	@Test
	public void testCreateGame() {
		Game createdGame = gameDao.createGame();
		assertNotNull(createdGame);
		assertEquals("1234", createdGame.getId());
	}

}
