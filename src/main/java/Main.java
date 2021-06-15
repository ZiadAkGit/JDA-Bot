import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import javax.swing.event.ChangeEvent;
import java.io.*;
import java.security.PrivilegedAction;
import java.util.*;

import static com.sun.tools.internal.xjc.model.CBuiltinLeafInfo.TOKEN;

public class Main {
    static TextChannel textChannel;

    public static void main(String[] args)  throws LoginException, InterruptedException  {
        JDABuilder JdaBuilder = JDABuilder.createDefault(TOKEN.toString());
        JdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        JdaBuilder.addEventListeners(new Help());
        JDA jda = JdaBuilder.build();
        Thread.sleep(2000);
        Guild Server = jda.getGuilds().get(0);
        textChannel = Server.getTextChannels().get(0);
        //textChannel.sendMessage("I'm ALIVE!").complete();
    }
}
