package net.corzdev.corzwarps.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CreateWarps implements CommandExecutor
{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException 
	{
		Player player = (Player) src;
		
		if (!(src instanceof Player))
		{
			
			src.sendMessage(Text.of(TextColors.RED, "Only Players can use that command!"));
			return CommandResult.success();
			
		}
		
		
		
		
		return CommandResult.empty();
	}
	
	
	

}
