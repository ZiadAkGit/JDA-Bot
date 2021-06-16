import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Help extends ListenerAdapter {
    String prefix = "!";

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getAuthor().getName().equalsIgnoreCase(Token.Discord_name)) {
            event.getChannel().sendTyping().complete();
            event.getChannel().sendMessage("Hey " + event.getAuthor().getName() + " it's me " + event.getJDA().getSelfUser().getName()).queue();
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Guild server = event.getGuild();
        User author = event.getAuthor();
        Member member = event.getMember();
        TextChannel txt = event.getChannel();
        Message message = event.getMessage();
        String message_content = message.getContentRaw();

        //Checks if user that sent the message is not the bot so it wont loop itself and if it's starts with the prefix.
        if (!event.getAuthor().isBot() && message_content.startsWith(prefix))
        {

        }
    }

}
