package test.mipe;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Discord extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw().strip();
        if (content.equals("ぐー") || content.equals("ちょき") || content.equals("ぱー")) {
            String result = Janken.janken(content);
            event.getChannel().sendMessage(result).queue();
            return;
        }
        if (content.contains("いろは")) {
            event.getChannel().sendMessage("<@970882801475928115>").queue();
        }
        if ("728963236715692123".equals(event.getMessage().getAuthor().getId())) {
            String result = new PostToAI().getContent("この発言内容から発言者が酔っているか酔っていないかを「酔っています」か「酔っていません」で判定してください", content);
            if (result.contains("酔っています")){event.getChannel().sendMessage(result).queue();}
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        JDA jda = event.getJDA();
        Uutanmennsyonn.start(jda);
    }
}
