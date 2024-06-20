package test.mipe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public class Elonfuck {
    Pattern regex = Pattern.compile(
            "https?://(x\\.com|twitter\\.com|mobile\\.twitter\\.com)/@?[a-zA-Z0-9_]{1,15}/status/[0-9]{17,21}.*");

    public void FuckX(MessageReceivedEvent event) {
        // botだった場合反応してはいけない
        if (event.getMessage().getAuthor().isBot() || !event.getMessage().hasGuild()
                || !(event.getChannel() instanceof TextChannel)) {
            return;
        }
        // regexにマッチするか判定して、しない場合はreturn
        Matcher res = regex.matcher(event.getMessage().getContentRaw());
        if (!res.matches()) {
            return;
        }
        TextChannel channel = (TextChannel) (event.getChannel());
        Webhook wh = null;
        for (Webhook w : channel.retrieveWebhooks().complete()) {
            if (w.getOwnerAsUser().getId() == event.getJDA().getSelfUser().getId()) {
                wh = w;
            }
        }
        if (wh == null) {
            wh = channel.createWebhook("いろはでぶ").complete();
            if (wh == null) {
                return;
            }
        }
        String content = event.getMessage().getContentRaw().replace(res.group(1), "fxtwitter.com");
        wh.sendMessage(MessageCreateData.fromContent(content)).setAvatarUrl(event.getAuthor().getAvatarUrl())
                .setUsername(event.getMember().getEffectiveName()).queue();
        event.getMessage().delete().queue();;
    }
}
