package test.mipe;

import net.dv8tion.jda.api.JDA;
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
    }
    @Override
    public void onReady(ReadyEvent event) {
        JDA jda = event.getJDA();
        Uutanmennsyonn.start(jda);
    }
}
