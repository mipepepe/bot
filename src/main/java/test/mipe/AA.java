package test.mipe;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AA {
    public void AAEvent(MessageReceivedEvent event) {
        String c = event.getMessage().getContentRaw();
        if (!c.startsWith("!aa ")) {
            return;
        }
        String[] cs = c.substring(4).split(" +");
        if (cs.length < 1) {
            return;
        }
        if (cs[0].equals("square")) {
            if (cs.length < 3) {
                return;
            }
            int w = Integer.parseInt(cs[1]);
            int h = Integer.parseInt(cs[2]);
            square(event, w, h);
            return;
        } else if (cs[0].equals("stairs")) {
            if (cs.length < 2) {
                return;
            }
            int s = Integer.parseInt(cs[1]);
            stairs(event, s);
            return;
        }
    }

    public void square(MessageReceivedEvent event, int w, int h) {
        // 幅、幅の■を送る
        String result = "";
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                result = result + "■";
            }
            result = result + "\n";
        }
        event.getChannel().sendMessage(result).queue();
    }

    public void stairs(MessageReceivedEvent event, int s) {
        String result = "";
        int t = s;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < t; j++) {
                if (j < s - i - 1) {
                    result = result + " ";
                }else {
                    result = result + "■";
                }
            }
            t = t + 1;
            result = result + "\n";
        }
        /*
         * for
         * s = 3
         *   ■
         *  ■■■
         * ■■■■■
         * 1 < 2 && 2 < 3
         */
        // Send the result as a message to the Discord channel
        event.getChannel().sendMessage("```\n"+result+"\n```").queue();
    }
}