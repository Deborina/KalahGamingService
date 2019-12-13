package com.assignment.backbase.command;

import com.assignment.backbase.model.Game;

public interface GameCommand {

	public void execute(Game game, int pitId);
}
