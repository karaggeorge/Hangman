import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;

public class Dictionary {
    
    private Map< Integer, Map< Integer, List<String> > > map = new HashMap<Integer, Map< Integer, List<String> >>();
    private int maxLen = -1, minLen = 100;

    public Dictionary (String filename) {
        load(filename);
        //calculateDif("hithere");
    }

    private void load(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename),StandardCharsets.UTF_8); 
            
            for(String word: lines){

                int wordLen = word.length();
                int wordDif = calculateDif(word);
                maxLen = Math.max(maxLen,wordLen);
                minLen = Math.min(minLen,wordLen);

                //System.out.println(word + " -> " + calculateDif(word));

                Map< Integer, List<String> > tmpMap = map.get(wordLen);
                if(tmpMap == null){
                    tmpMap = new HashMap< Integer, List<String> > ();
                }
                List<String> tmpList = tmpMap.get(wordDif);
                if(tmpList == null){
                    tmpList = new ArrayList<String> ();
                }

                tmpList.add(word);
                tmpMap.put(wordDif,tmpList);
                map.put(wordLen, tmpMap);

            }
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public String getWord(int length, int difficulty){
        
        if(map.get(length) == null) return getWord(length+1,difficulty);

        int dif = difficulty;
        int step = -1;
        
        List<String> wordList = null;
        
        while((wordList = map.get(length).get(dif)) == null){
            if(dif == 1){
                dif = difficulty;
                step = 1;
            }
            if(dif == 5) break;
            dif += step;
        }

        if(wordList == null) return getWord(length+1,difficulty);

        Collections.shuffle(wordList);
        return wordList.get(0);
    }

    public int getMaxLen(){
        return maxLen;
    }

    public int getMinLen(){
        return minLen;
    }

    private int calculateDif(String word){
        List<String> letters = Arrays.asList("etaoinshrdlcumwfgypbvkjxqz".split(""));
        Set<String> unique = new HashSet(Arrays.asList(word.split("")));
        Set<String> vowels = new HashSet(Arrays.asList("aeiou".split("")));

        vowels.retainAll(unique);

        Object[] uniqueWord = unique.toArray();
        int positions = 0;
        for(int i = 0;i < unique.size();i++){
            positions += letters.indexOf(uniqueWord[i]);
        }

        /*System.out.println(letters);
        System.out.println(unique);
        System.out.println(vowels);   
        System.out.println(positions);*/
        int difficulty = word.length() * unique.size() * (7 - vowels.size()) * positions;   
        
        if(difficulty < 5000) return 1;
        else if(difficulty < 1000) return 2;
        else if(difficulty < 20000) return 3;
        else if(difficulty < 35000) return 4;
        else return 5;
    }

    /*public static void main(String[] args){
        new Dictionary("dictionary.txt");
    }*/

}
