package net.corzdev.corzwarps.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import net.corzdev.corzwarps.CorzWarps;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class WarpCommands implements CommandExecutor
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
	if (args.getOne("name").isPresent())
		{
		
		String name = args.<String>getOne("name").get();
		CommentedConfigurationNode config = CorzWarps.getInstance().getconfig();
		if(!config.getNode(name.toUpperCase()).isVirtual())
		{
			Location<World> loc = new Location<>(
					Sponge.getServer().getWorld(
							config.getNode(name.toUpperCase(), "world").getString()
					).get(),
					config.getNode(name.toUpperCase(), "x").getDouble(),
					config.getNode(name.toUpperCase(), "y").getDouble(),
					config.getNode(name.toUpperCase(), "z").getDouble()
			);
			player.setLocationSafely(loc);
			player.sendMessage(Text.of(TextColors.GREEN, "Warped to: ", name));
			return CommandResult.success();
		}
		
		player.sendMessage(Text.of(TextColors.RED, "A warp with that name does not exist!"));
		
		}
	
	
	
	
	return CommandResult.success();
}
}
