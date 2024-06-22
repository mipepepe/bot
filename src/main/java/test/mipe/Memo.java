package test.mipe;

import java.io.File;
import java.nio.file.Files;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Memo {
    private class MemoMap {
        public void put(String key, String value) {
            try {
            File file = new File("./memos/" + key);
            if(!file.exists()) {
                file.createNewFile();
            }
            Files.write(file.toPath(),value.getBytes());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        public String get(String key) {
            try {
                File file = new File("./memos/" + key);
                if(!file.exists()) {
                    return null;
                }
                return Files.readString(file.toPath());
            }catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public String remove(String key) {
            try {
                File file = new File("./memos/" + key);
                if(file.exists()) {
                    String content = Files.readString(file.toPath());
                    Files.delete(file.toPath());
                    return content;
                }
                return null;
            }catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    MemoMap db = new MemoMap();

    public Memo() {
    }

    public void onMessage(MessageReceivedEvent event) {
        // !memoで始まってなかったらreturn
        if (!event.getMessage().getContentRaw().startsWith("!memo ")) {
            return;
        }
        String author = event.getAuthor().getId();
        // !memoの後の文字列を取得
        String[] args = event.getMessage().getContentRaw().substring(6).split(" +");
        if (args[0].equals("set")) {
            if (args.length < 2) {
                event.getChannel().sendMessage("内容を教えやがれ").queue();
                return;
            }
            String toSet = args[1];
            // toSetの中身を保存
            // db.put
            db.put(author, toSet);
            event.getChannel().sendMessage("保存しました").queue();
            return;
        } else if (args[0].equals("delete")) {
            // メモを削除
            // db.remove
            if (null == db.remove(author)) {
                event.getChannel().sendMessage("保存されていません").queue();
                return;
            } else {
                event.getChannel().sendMessage("削除しました").queue();
                return;
            }
        } else if (args[0].equals("show")) {
            // メモの内容を表示
            // db.get
            String v = db.get(author);
            if (v == null) {
                event.getChannel().sendMessage("保存されていません").queue();
                return;
            } else {
                event.getChannel().sendMessage("内容:\n```\n" + v + "\n```").queue();
                return;
            }
        }
    }
}
