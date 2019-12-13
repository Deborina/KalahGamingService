package com.assignment.backbase.command;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.assignment.backbase.enums.Player;
import com.assignment.backbase.enums.Status;
import com.assignment.backbase.model.Game;
import com.assignment.backbase.util.GamingConstants;

@Service
public class MakeMoveCommand implements GameCommand {

	@Override
	public void execute(Game game, int pitId) {
		validatePitNumber(pitId, game);

		Map<Integer, Integer> board = game.getBoard();
		int amount = board.get(pitId);
		int lastIndex = pitId + amount;
		clearPit(pitId, game.getBoard());

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
			game.setStatus(winner);
		}
	}

	private void clearPit(int pitId, Map<Integer, Integer> board) {
		// TODO Auto-generated method stub

	}

	private Status findTheWinner(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean gameIsTerminated(Game game) {
		Player player = game.getPlayer();
		List<Integer> pits = player.getPits();
		Map<Integer, Integer> board = game.getBoard();

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

	private boolean playerHasAnotherTurn(int lastPit, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	private void checkLastPit(int lastPit, Game game) {
		if (lastPitWasOwnEmptyPit(lastPit, game)) {
			int oppositePit = getOppositePit(lastPit);
			int oppositePitAmount = game.getBoard().get(oppositePit);
			if (oppositePitAmount != 0) {
				clearPit(oppositePit, game.getBoard());
				clearPit(lastPit, game.getBoard());
				addStonesToPit(game.getPlayer().getKalahId(), game.getBoard(), oppositePitAmount + 1);
			}
		}
	}

	private void addStonesToPit(int currentPit, Map<Integer, Integer> board, int i) {

	}

	private void validatePitNumber(int pitId, Game game) {
		// TODO Auto-generated method stub

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
}
