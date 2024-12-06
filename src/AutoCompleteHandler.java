import java.util.LinkedList;
import java.util.ArrayList;
import java.util.logging.*;

public class AutoCompleteHandler {
    private final LinkedList<String> words;
    public final Logger log = Logger.getLogger("AutoCompleteHandler");

    public AutoCompleteHandler(ArrayList<String> array){
        log.addHandler(new ConsoleHandler());
        this.words = new LinkedList<>(array);
    }

    public LinkedList<String> getWords() {
        return this.words;
    }

    public ArrayList<String> getSucceedingWords(String prefix) {
        return this.getSucceedingWords(prefix, -1); // -1 means all words
    }

    public ArrayList<String> getSucceedingWords(String prefix, int n) {
        var possibilities = new ArrayList<String>();
        var sb = new StringBuilder();
        var found = false;
        var length = words.size();
        for (int i = 0; i < length; i++) {

            var word = words.get(i);
            log.finer(word);
            if (found) {
                sb.append(word).append(" ");
            }
            if (!found && word.equalsIgnoreCase(prefix)) {
                found = true;
                sb.append(" ");
            } else if (!found && word.toLowerCase().startsWith(prefix.toLowerCase())) {
                found = true;
                sb.append(word, prefix.length(), word.length()).append(" ");
            }

            if (sb.toString().split(" ").length == n) {
                found = false;
                possibilities.add(sb.toString());
                sb.setLength(0);
                i-=n-1;
            }
        }
        log.finer(possibilities.toString());
        return possibilities;
    }
}
