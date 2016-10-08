package com.gmail.hexragon.gn4rBot.command.general

import com.google.inject.Inject
import xyz.gnarbot.gnar.Host
import xyz.gnarbot.gnar.handlers.commands.Command
import xyz.gnarbot.gnar.handlers.commands.CommandExecutor
import xyz.gnarbot.gnar.textadventure.TextAdventure
import xyz.gnarbot.gnar.utils.Note

@Command(aliases = arrayOf("startadventure"), usage = "[command]", description = "Start a text-based aventure!")
class StartAdventureCommand : CommandExecutor()
{
    @Inject lateinit var host : Host
    
    override fun execute(message : Note, label : String, args : Array<out String>)
    {
        TextAdventure.getAdventure(message.author, message)
        message.deleteMessage();
    }
}