import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public class Help extends ListenerAdapter {
    String prefix = "!";
    static final ChromeOptions CHROME_OPTIONS = new ChromeOptions();
    protected WebDriver driver;

    public Help() {
        System.setProperty(Token.CHROME_DRIVER, Token.CHROME_DRIVER_LOCATION);
        CHROME_OPTIONS.addArguments("--headless");
        CHROME_OPTIONS.addArguments("--disable-gpu");
        CHROME_OPTIONS.addArguments("--disable-infobars");
        CHROME_OPTIONS.addArguments("--disable-translate");
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

        if (!event.getAuthor().isBot() && message_content.startsWith(prefix)) {
            driver = new ChromeDriver(CHROME_OPTIONS);
            if (message_content.startsWith(prefix + "getip")) {
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

            if (message_content.startsWith(prefix + "link")) {
                if ((message_content.split("link").length == 2)) {
                    if (Integer.parseInt(message_content.split("link")[1].trim()) > 0) {
                        for (int i = 0; i < Integer.parseInt(message_content.split("link")[1].trim()); i++) {
                            txt.sendTyping().queue();
                            txt.sendMessage(randomImage()).queue();
                        }
                    }
                } else {
                    txt.sendMessage("Give me number Human!").complete();
                }
            }

            if (message_content.startsWith(prefix + "ipad")) {
                driver.get(Token.BASE_URL4);
                for (WebElement ipad : driver.findElements(By.className("product-item-info"))) {
                    txt.sendTyping().queue();
                    txt.sendMessage(ipad.getText() + "            --------------------------                      ").queue();
                }
            }
            //Jobs finder using google search -> inurl: /career
            if (message_content.startsWith(prefix + "jobs")) {
                txt.sendTyping().queue();
                driver.get(Token.Base_URL5);
                List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"rso\"]/div"));
                System.out.println(links.size());
                for (WebElement link : links) {
                    txt.sendMessage(link.findElement(By.tagName("a")).getText().trim()).queue();
                }
                driver.close();
                driver.quit();
            }

            if (message_content.startsWith(prefix + "interview")) {
                txt.sendTyping().queue();
                driver.get(Token.Base_URL6);
                List<WebElement> interviews = driver.findElements(By.xpath("/html/body/div[3]/div/div/div/div/div/div[1]/div[2]/article/div[1]/div/div[1]/div"));
                for (WebElement interview : interviews) {
                    if (interview.findElement(By.xpath("//*[@id=\"InterviewQuestionResult_2\"]/div/div/div/div/div[2]/table/tbody/tr/td/a")).isDisplayed()) {
                        interview.findElement(By.xpath("//*[@id=\"InterviewQuestionResult_2\"]/div/div/div/div/div[2]/table/tbody/tr/td/a")).click();
                    }
                    txt.sendMessage(interview.getText()).queue();
                    txt.sendMessage("----------------------").queue();
                }
                driver.close();
                driver.quit();
            }
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
        return Token.BASE_URL3 + url;
    }

}
