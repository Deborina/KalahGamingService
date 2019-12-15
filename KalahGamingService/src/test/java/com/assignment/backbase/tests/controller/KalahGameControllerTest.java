// package com.assignment.backbase.tests.controller;
//
// import static org.mockito.Mockito.when;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import org.junit.Before;
// import org.junit.FixMethodOrder;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.junit.runners.MethodSorters;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
//
// import com.assignment.backbase.command.MakeMoveCommand;
// import com.assignment.backbase.controller.KalahGameController;
// import com.assignment.backbase.dao.GameDaoImpl;
// import com.assignment.backbase.model.Game;
// import com.assignment.backbase.service.GameService;
// import com.assignment.backbase.service.GameServiceImpl;
// import com.assignment.backbase.util.ApplicationCache;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = { KalahGameController.class, GameServiceImpl.class,
// Game.class, GameDaoImpl.class,
// ApplicationCache.class, MakeMoveCommand.class, MockMvc.class })
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
// @TestConfiguration
// public class KalahGameControllerTest {
//
// @Autowired
// private WebApplicationContext wac;
//
// private MockMvc mockMvc;
//
// @Before
// public void setup() {
// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
// }
//
// @MockBean
// private GameService gameService;
//
// @Test
// public void testCreateNewGame() throws Exception {
// Game game = new Game();
// game.setId("1234");
// game.setUrl("http://localhost:8080/games/1234");
// when(gameService.createGame()).thenReturn(game);
//
// mockMvc.perform(post("/games")).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(game.getId()))
// .andExpect(jsonPath("$.url").value(game.getUrl()));
// }
//
// @Test
// public void testMakeMove() throws Exception {
// Game game = new Game();
// game.setId("1234");
// game.setUrl("http://localhost:8080/games/1234");
// when(gameService.makeMove("1234", 4)).thenReturn(game);
//
// mockMvc.perform(put("/games/1234/pits/4")).andExpect(status().isOk());
// //
// //
// //
// .andExpect(jsonPath("$.id").value(game.getId())).andExpect(jsonPath("$.url").value(game.getUrl()));
// }
// }