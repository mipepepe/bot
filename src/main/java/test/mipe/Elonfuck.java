package test.mipe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.entities.Message;

public class Elonfuck {
    public Elonfuck() {}
    Pattern regex = Pattern.compile("https?://(x\\.com|twitter\\.com|mobile\\.twitter\\.com)/(@?[a-zA-Z0-9_]{1,15}/status/[0-9]{17,21})"); 
    public void FuckX(Message message) {
        // botだった場合反応してはいけない
        if (message.getAuthor().isBot()){
            return;
        }
        // regexにマッチするか判定して、しない場合はreturn
        Matcher res=regex.matcher(message.getContentRaw());
        if (!res.matches()){
            return;
        }
        String url = res.group(2);
        message.reply("https://fxtwitter.com/" + url).queue();;
    }
}
