package com.assignment.backbase.command;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.assignment.backbase.enums.Player;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.exceptions.ApplicationException;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.GamingConstants;

@Service
public class MakeMoveCommand implements GameCommand {

	@Override
	public void execute(Game game, int pitId) {
		validatePitNumber(pitId, game);

		Map<Integer, Integer> board = game.getStatus();
		int amount = board.get(pitId);
		int lastIndex = pitId + amount;
		clearPit(pitId, game.getStatus());

		int lastPit = lastIndex;
		for (int currentIndex = pitId + 1; currentIndex <= lastIndex; currentIndex++) {
			int currentPit = currentIndex;
			if (currentIndex == GamingConstants.LAST_PIT_INDEX && lastIndex != GamingConstants.LAST_PIT_INDEX) {
				lastIndex = lastIndex - currentIndex;
				currentIndex = 0;
			}
			if (game.getPlayer().getOppositePlayer().getKalahId() != currentPit) {
				addStonesToPit(currentPit, board, 1);
			} else {
				if (currentIndex != GamingConstants.LAST_PIT_INDEX) {
					lastIndex++;
				} else {
					lastIndex = GamingConstants.FIRST_PIT_INDEX;
					currentIndex = 0;
				}
			}
		}
		lastPit = lastPit > GamingConstants.LAST_PIT_INDEX ? lastIndex : lastPit;
		checkLastPit(lastPit, game);

		if (!playerHasAnotherTurn(lastPit, game.getPlayer())) {
			game.setPlayer(game.getPlayer().getOppositePlayer());
		}
		if (gameIsTerminated(game)) {
			Status winner = findTheWinner(game);
			game.setGameStatus(winner);
		}
	}

	private void checkLastPit(int lastPit, Game game) {
		if (lastPitWasOwnEmptyPit(lastPit, game)) {
			int oppositePit = getOppositePit(lastPit);
			int oppositePitAmount = game.getStatus().get(oppositePit);
			if (oppositePitAmount != 0) {
				clearPit(oppositePit, game.getStatus());
				clearPit(lastPit, game.getStatus());
				addStonesToPit(game.getPlayer().getKalahId(), game.getStatus(), oppositePitAmount + 1);
			}
		}
	}

	private boolean gameIsTerminated(Game game) {
		Player player = game.getPlayer();
		List<Integer> pits = player.getPits();
		Map<Integer, Integer> board = game.getStatus();

		boolean playerPitsAreEmpty = pits.stream().map(board::get).allMatch(stoneNumbers -> stoneNumbers == 0);

		boolean oppositePlayerPitsAreEmpty = player.getOppositePlayer().getPits().stream().map(board::get)
				.allMatch(stoneNumbers -> stoneNumbers == 0);

		if (playerPitsAreEmpty || oppositePlayerPitsAreEmpty) {
			addAllRemainedStonesToKalah(player, board);
			addAllRemainedStonesToKalah(player.getOppositePlayer(), board);
			return true;
		}
		return false;
	}

	private void addAllRemainedStonesToKalah(Player player, Map<Integer, Integer> board) {
		player.getPits().forEach(pit -> {
			int amount = board.get(pit);
			if (amount != 0) {
				int kalahId = player.getKalahId();
				board.replace(kalahId, board.get(kalahId) + amount);
				clearPit(pit, board);
			}
		});
	}

	private Status findTheWinner(Game game) {
		Map<Integer, Integer> board = game.getStatus();
		int firstPlayerStones = board.get(Player.FIRST_PLAYER.getKalahId());
		int secondPlayerStones = board.get(Player.SECOND_PLAYER.getKalahId());
		if (firstPlayerStones > secondPlayerStones) {
			return Status.FIRST_PLAYER_WIN;
		} else if (firstPlayerStones < secondPlayerStones) {
			return Status.SECOND_PLAYER_WIN;
		} else {
			return Status.DRAW;
		}
	}

	private void validatePitNumber(int pitId, Game game) {
		Player player = game.getPlayer();
		if (pitId == player.getKalahId() || pitId == player.getOppositePlayer().getKalahId()) {
			throw new ApplicationException("You are not authorized to select   Kalah!");
		}

		if (pitId < GamingConstants.FIRST_PIT_INDEX || pitId > GamingConstants.LAST_PIT_INDEX) {
			throw new ApplicationException("Invalid Pit Id");
		}

		if (!isUserPit(pitId, player)) {
			throw new ApplicationException("It is not your turn");
		}
		if (game.getStatus().get(pitId) == 0) {
			throw new ApplicationException("Selected pit is empty");
		}
	}

	private boolean lastPitWasOwnEmptyPit(int lastPitId, Game game) {
		Map<Integer, Integer> board = game.getStatus();
		return board.get(lastPitId) == 1 && isUserPit(lastPitId, game.getPlayer());
	}

	private boolean isUserPit(int pitId, Player player) {
		return player.getPits().contains(pitId);
	}

	private int getOppositePit(int pitId) {
		return GamingConstants.LAST_PIT_INDEX - pitId;
	}

	private boolean playerHasAnotherTurn(int lastPitId, Player player) {
		return player.getKalahId() == lastPitId;
	}

	private void addStonesToPit(int pitId, Map<Integer, Integer> board, int amount) {
		board.replace(pitId, board.get(pitId) + amount);
	}

	private void clearPit(int pitId, Map<Integer, Integer> board) {
		board.replace(pitId, 0);
	}
}
