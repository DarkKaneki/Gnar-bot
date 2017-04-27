package xyz.gnarbot.gnar.commands.executors.mod

import net.dv8tion.jda.core.Permission
import xyz.gnarbot.gnar.BotConfiguration
import xyz.gnarbot.gnar.commands.Category
import xyz.gnarbot.gnar.commands.Command
import xyz.gnarbot.gnar.commands.CommandExecutor
import xyz.gnarbot.gnar.utils.Context

@Command(
        aliases = arrayOf("enable"),
        usage = "[labels...]",
        description = "Enable commands.",
        disableable = false,
        category = Category.MODERATION,
        permissions = arrayOf(Permission.ADMINISTRATOR)
)
class EnableCommand : CommandExecutor() {
    override fun execute(context: Context, args: Array<String>) {
        val enabled = args
                .map(context.guildData.commandHandler::enableCommand)
                .filterNotNull()
                .map { it.info.aliases[0] }

        context.send().embed("Enabling Commands") {
            color = BotConfiguration.ACCENT_COLOR
            description {
                if (enabled.isNotEmpty()) {
                    "Enabled `$enabled` command(s) on this server."
                } else {
                    "You didn't enter any disabled commands or commands that could be disabled."
                }
            }
        }.action().queue()
    }
}