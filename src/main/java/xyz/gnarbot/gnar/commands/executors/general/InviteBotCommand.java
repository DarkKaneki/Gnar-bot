package xyz.gnarbot.gnar.commands.executors.general;

import net.dv8tion.jda.core.Permission;
import xyz.gnarbot.gnar.commands.Command;
import xyz.gnarbot.gnar.commands.CommandExecutor;
import xyz.gnarbot.gnar.utils.Context;

@Command(aliases = {"invite", "invitebot"}, description = "Get a link to invite GN4R to your server.")
public class InviteBotCommand extends CommandExecutor {
    @Override
    public void execute(Context context, String[] args) {
        String link = context.getShard().asBot().getInviteUrl(Permission.ADMINISTRATOR);

        context.send().embed("Get Gnar on your server!")
                .setColor(context.getConfig().getAccentColor())
                .setDescription("__**[Click to invite Gnar to your server.](" + link + ")**__")
                .action().queue();
    }
}
