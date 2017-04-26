package net.corzdev.corzwarps;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import net.corzdev.corzwarps.commands.WarpCommands;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.File;
import java.io.IOException;



@Plugin(id = "corzwarps", name = "Corz Warps", version = "0.0.1", authors = {"Corz"}, description = "Simple warps plugin")


public class CorzWarps 
{

	private static CorzWarps instance;
	
	public static CorzWarps getInstance(){
		return instance;
	}
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private File configurationFile = null;
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	public ConfigurationLoader<CommentedConfigurationNode> loader = null;
	
	private CommentedConfigurationNode node = null;
	
	@Inject
	private Game game;
	
	@Inject
	private Logger logger;
	
	public Logger getLogger() 
	{
		return logger;
	}
	
	@Listener
	public void onPreInit (GamePreInitializationEvent event)
	{
		instance = this;
		
		this.getLogger().info("Loading...");
		
		try{
			if(!configurationFile.exists()){
				configurationFile.createNewFile();
				node = loader.load();
			}
		node = loader.load();
		loader.save(node);;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Sponge.getCommandManager().register(this, warpSpec, Lists.newArrayList("warp", "warps"));
	
		this.getLogger().info("Enabled");
		
		
	}
	
	@Listener
	public void onServerStop(GameStoppingEvent event)
	{
		try{
			loader.save(node);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	private CommandSpec warpSpec = CommandSpec.builder()
			.description(Text.of("Warps user to specified warp. View warps with /warpslist"))
			.arguments(GenericArguments.string(Text.of("name")))
			.executor(new WarpCommands())
			.build();
	
	
	
	public CommentedConfigurationNode getconfig()
	{
		return node;
	}
	
	/**
	 * 	/warp create|delete|<name>
	 *		/warp create <name>
	 *		/warp del|delete <name>
	 *		/warp <name>
	 */

}
