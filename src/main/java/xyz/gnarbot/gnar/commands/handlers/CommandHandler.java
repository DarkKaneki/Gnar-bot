package xyz.gnarbot.gnar.commands.handlers;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import xyz.gnarbot.gnar.Bot;
import xyz.gnarbot.gnar.members.User;
import xyz.gnarbot.gnar.servers.Host;
import xyz.gnarbot.gnar.utils.Note;

import java.util.Arrays;

public class CommandHandler extends CommandRegistry
{
    
    private final Host host;
    
    private int requests = 0;
    
    public CommandHandler(Host host)
    {
        this.host = host;
    }
    
    /**
     * Extract command classes/instances from CommandDistributor
     * and register it in this handler.
     *
     * @param distributor CommandDistributor instance.
     */
    public void receiveFrom(CommandTable distributor)
    {
        distributor.getCommands().forEach(this::registerCommand);
    }
    
    /**
     * Call the command based on the message content.
     *
     * @param event Message event.
     */
    public void callCommand(MessageReceivedEvent event)
    {
        String content = event.getMessage().getContent();
    
        if (!content.startsWith(Bot.getToken())) return;
        
        // Tokenize the message.
        String[] tokens = content.split(" ");
    
        String label = tokens[0].substring(Bot.getToken().length());
        
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
    
        Note note = host.noteOf(event.getMessage());
        User user = host.getUserHandler().asUser(event.getMember());
    
        CommandExecutor cmd = getRegistry().get(label);
        if (cmd == null) return;
    
        if (cmd.getClearance().getValue() > user.getClearance().getValue())
        {
            note.error("Insufficient clearance.");
            return;
        }
    
        try
        {
            requests++;
//
//            if (debug)
//            {
//                long start = System.currentTimeMillis();
//
//                cmd.execute(note, label, args);
//
//                long end = System.currentTimeMillis();
//
//                StringJoiner sj = new StringJoiner("\n");
//
//                note.replyEmbed("Debug", sj.toString());
//            }
            cmd.execute(note, label, args);
            
        }
        catch (RuntimeException e)
        {
            note.error("**Exception**: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Return the amount of successful requests on this command handler.
     *
     * @return the amount of successful requests on this command handler.
     */
    public int getRequests()
    {
        return requests;
    }
}
