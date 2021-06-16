import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
    static TextChannel textChannel;

    public void StartBot(){
        JDABuilder JdaBuilder = JDABuilder.createDefault(Token.token);
        JdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        JdaBuilder.addEventListeners(new Help());
        JDA jda = null;
        try {
            jda = JdaBuilder.build();
            Thread.sleep(2000);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        Guild Server = jda.getGuilds().get(0);
        textChannel = Server.getTextChannels().get(0);
        textChannel.sendMessage("I'm ALIVE!").complete();
    }
}
