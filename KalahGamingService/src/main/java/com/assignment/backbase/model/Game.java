package com.assignment.backbase.model;


import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.assignment.backbase.enums.Player;
import com.assignment.backbase.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class Game {

	@Value("${game.id}")
	private  final int FIRST_PIT_INDEX;

	@Value("${game.id}")
	private  final int LAST_PIT_INDEX ;

	@Value("${game.id}")
	private  final int INITIAL_STONES_QUANTITY;


	private String id;
	

	private String url;

	   

	   private Map<Integer, Integer> board;

	   @JsonIgnore
	    private Status status;

	   
	    private Player player;  
	    
	    
	    

		public Game() {
			initializeBoard();
	        status = Status.IN_PROGRESS;
	        player = Player.FIRST_PLAYER;
		}

		private void initializeBoard() {
			  board = new HashMap<>();
		        for (int i = FIRST_PIT_INDEX; i <= LAST_PIT_INDEX; i++) {
		            int firstKhalIndex = Player.FIRST_PLAYER.getKalahId();
		            int secondKhalIndex = Player.SECOND_PLAYER.getKalahId();
		            int value = (i != firstKhalIndex && i != secondKhalIndex) ? INITIAL_STONES_QUANTITY : 0;
		            board.put(i, value);
		        }
		}

		public String getId() {
			return id;
		}

		@Value("${game.id}")
		public void setId(String id) {
			this.id = id;
		}

		public String getUrl() {
			return url;
		}
		@Value("${game.url}")
		public void setUrl(String url) {
			this.url = url;
		}

		public Map<Integer, Integer> getBoard() {
			return board;
		}

		public void setBoard(Map<Integer, Integer> board) {
			this.board = board;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

	    
	    
}
