import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    static TextChannel textChannel;

    public void StartBot(){
        JDABuilder JdaBuilder = JDABuilder.createDefault(Token.token);
        JdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        JdaBuilder.addEventListeners(new Help(), new Bot());
        try {
            JDA jda = JdaBuilder.build();
            Thread.sleep(2000);
            Guild Server = jda.getGuilds().get(0);
            textChannel = Server.getTextChannels().get(0);
            textChannel.sendMessage("I'm ALIVE!").complete();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
