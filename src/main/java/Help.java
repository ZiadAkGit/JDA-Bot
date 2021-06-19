import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.LogManager;

public class Help extends ListenerAdapter {
    String prefix = "!";
    static final ChromeOptions CHROME_OPTIONS = new ChromeOptions();
    protected WebDriver driver;

    public Help() {
        System.setProperty(Token.CHROME_DRIVER,Token.CHROME_DRIVER_LOCATION);
        CHROME_OPTIONS.addArguments("--headless");
        CHROME_OPTIONS.addArguments("window-size=7680,4320");

    }

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

        if (!event.getAuthor().isBot() && message_content.startsWith(prefix))
        {
            driver = new ChromeDriver(CHROME_OPTIONS);
            if(message_content.startsWith(prefix + "getip")){
                txt.sendTyping().queue();
                driver.get(Token.BASE_URL);
                for (WebElement ip : driver.findElements(By.className("addrLeft"))) {
                    txt.sendMessage("http://" + ip.getText()).queue();
                }
                driver.close();
                driver.quit();
            }

            if (message_content.startsWith(prefix + "shifts")) {
                txt.sendTyping().queue();
                driver.get(Token.BASE_URL2);
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);
                EmbedBuilder eb = new EmbedBuilder();
                eb.setFooter("Shifts This Week");
                eb.setColor(Color.GREEN);
                txt.sendMessage(eb.build()).addFile(SrcFile).queue();
                SrcFile.delete();
                driver.close();
                driver.quit();
            }

            if(message_content.startsWith(prefix + "link")){
                if((message_content.split("link").length == 2)){
                    if(Integer.parseInt(message_content.split("link")[1].trim()) > 0) {
                        for (int i = 0; i < Integer.parseInt(message_content.split("link")[1].trim()); i++) {
                            txt.sendTyping().queue();
                            txt.sendMessage(randomImage()).queue();
                        }
                    }
                }else{
                    txt.sendMessage("Give me number Human!").complete();
                }
            }

            /** A function that shows the top picture of an instagram hashtag (Works only coupel of times a day)
            if(message_content.startsWith(prefix + "instag")){
                String tag = message_content.split("instag")[1].trim();
                driver.get("https://www.instagram.com/explore/tags/" + tag);
            }
             **/



        }
    }

    public static String randomImage() {
        char[] AlphaNumericArray = "0123456789abcdefghijklmnopqrstuvxyz".toCharArray();
        Random r = new Random();
        String url = "";
        int x = r.nextInt(101);
        if (x <= 70) {
            for (int i = 0; i < 6; i++) {
                url += AlphaNumericArray[r.nextInt(AlphaNumericArray.length)];
            }
            //30% chance of getting into this else
        } else {
            for (int i = 0; i < 5; i++) {
                url += AlphaNumericArray[r.nextInt(AlphaNumericArray.length)];
            }
        }
        String furl = Token.BASE_URL3 + url;
        return furl;
    }

}
