package test.mipe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DiceRoll {
    public void event(MessageReceivedEvent event) {
        Optional<DiceRollDict> parsed = parse(event.getMessage().getContentStripped());
        if (parsed.isPresent()) {
            execute(event, parsed.get());
        }
    }

    private class DiceRollDict {
        public int times; // 回数
        public int shape; // 形

        public DiceRollDict(int times, int shape) {
            this.shape = shape;
            this.times = times;
        }
    }

    public Optional<DiceRollDict> parse(String content) {
        // dを境目に分割
        List<String> splitted = List.of(content.split("d"));
        // 分割した後を全部数字に変換する
        try {
            List<Integer> converted = splitted.stream().filter(s -> !s.isBlank()).map(s -> Integer.parseInt(s))
                    .toList();
            // 変換した後の長さが1か2じゃなかったらempty ry
            if (converted.size() != 1 && converted.size() != 2) {
                return Optional.empty();
            }
            if (converted.size() == 1) {
                return Optional.of(new DiceRollDict(
                        1,
                        converted.get(0)));
            } else {
                return Optional.of(new DiceRollDict(
                        converted.get(0),
                        converted.get(1)));
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void execute(MessageReceivedEvent event, DiceRollDict roll) {
        // 実行
        // roll.shape 形
        // roll.tims 回数
        int total = 0;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < roll.times; i++) {
            int result = rollDice(roll.shape);
            total += result;
            list.add(
                    Integer.toString(result));
        }
        String content = String.format("-->%s-->合計%d", String.join(",", list), total);
        event.getChannel().sendMessageEmbeds(new EmbedBuilder().setDescription(content).setColor(Color.GREEN).build())
                .queue();
        return;
    }

    public int rollDice(int shape) {
        Random rand = new Random();

        int diceResult = rand.nextInt((shape - 1) + 1) + 1;

        return diceResult;
    }
}
