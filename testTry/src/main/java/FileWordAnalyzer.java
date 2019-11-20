import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileWordAnalyzer {

    FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically () throws IOException {

        String str = filePartReader.readLines();

        String[] splited = str.split("\\W+");
        List<String> result = new ArrayList<>(Arrays.asList(splited));
        java.util.Collections.sort(result, Collator.getInstance());
        System.out.println(result);
        return result;
    }

    public List getWordsContainingSubstring (String subString ) throws IOException {
        String str = filePartReader.readLines();
        String[] splited = str.split("\\W+");

        List<String>result = new ArrayList<>();

        for (String word: splited){
            for (int wordI=0;wordI<word.length();wordI++){
                if (word.charAt(wordI) == subString.charAt(0)){
                    Boolean samePart=true;
                    for (int subSI=0;subSI<subString.length();subSI++){
                        if (subString.charAt(subSI)!= word.charAt(wordI+subSI)){
                            samePart=false;
                            break;
                        }
                    }
                    if (samePart){
                        result.add(word);
                    }
                }
            }
        }
        System.out.println(result.toString());
        return result;
    }

    public List getStringsWhichPalindromes () throws IOException {
        String str = filePartReader.readLines();
        String[] splited = str.split("\\W+");

        List<String[]> stringbyChar = new ArrayList<>();

        List<String> result = new ArrayList<>();

        for (String word: splited){
           String[] splitedWord = word.split("(?!^)");
           Arrays.sort(splitedWord);
           stringbyChar.add(splitedWord);
        }

        for (int i=0;i<stringbyChar.size();i++){
            String[] example = stringbyChar.get(i);
            for (int j=0;j<stringbyChar.size();j++){
                if (j==i){
                    ;
                }else {

                    if (Arrays.equals(example, stringbyChar.get(j))){
                        result.add(splited[i]+" - "+splited[j]);
                    }
                }
            }
        }
        System.out.println(result.toString());
        return result;

    }



    public static void main(String[] args) throws IOException {
        FilePartReader filePartReader = new FilePartReader(
                "/Users/sandornegyeliczky/Desktop/projektek/oop/filepartreader-testing-with-junit-snegyeliczky/testTry/src/main/resources/text.txt",
                10,
                12);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        fileWordAnalyzer.getWordsOrderedAlphabetically();
        fileWordAnalyzer.getWordsContainingSubstring("or");
        fileWordAnalyzer.getStringsWhichPalindromes();
    }
}