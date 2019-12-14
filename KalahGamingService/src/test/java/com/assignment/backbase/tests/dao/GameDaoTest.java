package com.assignment.backbase.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.backbase.dao.GameDao;
import com.assignment.backbase.enums.Player;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.ApplicationCache;


@RunWith(SpringRunner.class)
public class GameDaoTest {


	@MockBean
    private GameDao gameDao;
	private ApplicationCache cache;
    private Game game;

	@Before
    public void setUp() {
       game = new Game();
       game.setId("1234");
    }
    @Test
    public void testSaveGame() {
    
        Game savedGame = gameDao.saveGame(game);
        assertNotNull(savedGame);
        assertEquals("1234", savedGame.getId());
        assertEquals(Status.IN_PROGRESS, savedGame.getStatus());
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
