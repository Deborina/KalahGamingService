package com.assignment.backbase.tests.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.backbase.dao.GameDao;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.ApplicationCache;

@RunWith(SpringRunner.class)
public class GameService {

	@MockBean
    private GameDao gameDao;
	private ApplicationCache cache;
    private Game game;
    
    
}
