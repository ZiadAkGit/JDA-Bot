import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Help extends ListenerAdapter {
    String prefix = "!";

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getAuthor().getName().equalsIgnoreCase(new Token().discord_Name())) {
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
        String msg = event.getMessage().getContentRaw();



        if (!event.getAuthor().isBot() && msg.startsWith(prefix)) {
            if (msg.equalsIgnoreCase(prefix + "ping")) {
                txt.sendTyping().complete();
                txt.sendMessage("Pong!").complete();
            }
            if (msg.equalsIgnoreCase(prefix + "status")) {
                txt.sendTyping().complete();
                System.out.println();
            }
        }
    }

}
