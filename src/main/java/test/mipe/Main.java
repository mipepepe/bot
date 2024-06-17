package test.mipe;

import java.util.EnumSet;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        JDABuilder
                .createLight(dotenv.get("DISCORD_TOKEN"),
                        EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
                .addEventListeners(new Discord())
                .build();
    }
}
