package me.armar.plugins.autorank.playerchecker.requirement;

import java.util.ArrayList;
import java.util.List;

import me.armar.plugins.autorank.AutorankTools;
import me.armar.plugins.autorank.playerchecker.result.Result;

import org.bukkit.entity.Player;

public class GamemodeRequirement extends Requirement {

	int gamemode = -1;
	private boolean optional = false;
	List<Result> results = new ArrayList<Result>();

	@Override
	public boolean setOptions(String[] options, boolean optional, List<Result> results) {
		this.optional = optional;
		this.results = results;
		
		if (options.length > 0)
			this.gamemode = AutorankTools.stringtoInt(options[0]);
		return (gamemode != -1);
	}

	@Override
	public boolean meetsRequirement(Player player) {
		if (isCompleted(getReqID(this.getClass(), player), player.getName())) {
			return true;
		}
		
		return gamemode != -1 && gamemode == player.getGameMode().getValue();
	}

	@Override
	public String getDescription() {
		return "Be in " + gamemode + ".";
	}

	@Override
	public boolean isOptional() {
		return optional;
	}

	@Override
	public List<Result> getResults() {
		return results;
	}

}
