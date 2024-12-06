
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zimp
 */
public class AutoCompleteCLI {
    
    public static void main(String[] main) {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word that you need autocomplete suggestions for: ");
            var line = scanner.nextLine();
            if (line.isBlank()) {
                break;
            }
            var split = line.strip().split(" ");
            var lastWord = split[split.length - 1].replaceAll("\\W", "");

            FileReader fr;
            try {
                fr = new FileReader("/home/zimp/IdeaProjects/AutoComplete/src/duet data.txt");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                return;
            }

            var handler = new AutoCompleteHandler(fr.getCompleteData());

            var possibilities = handler.getSucceedingWords(lastWord, 2);

            System.out.println(possibilities.toString());
        }
    }
    
}
