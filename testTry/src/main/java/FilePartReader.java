import java.io.*;
import java.util.Scanner;

public class FilePartReader {

    String filePath;
    Integer fromLine;
    Integer toLine;

    public FilePartReader(String filePath, Integer fromLine, Integer toLine) {
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public void setup(){
        if (this.fromLine>this.toLine || fromLine<1){
            throw new IllegalArgumentException("From Line must be smaller then To line");
        }
    }

    public String read() throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder result= new StringBuilder();

        String st;
        while ((st = br.readLine()) != null){
            result.append(st+"\n");
        }
        return result.toString();
    }

    public String readLines() throws IOException {
        setup();
        StringBuilder result = new StringBuilder();

        String allText = read();
        Scanner scanner = new Scanner(allText);
        Integer counter = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (counter>=fromLine && counter<=toLine){
                result.append(counter+": "+line+"\n");
            }
            counter++;
        }
        scanner.close();

        return result.toString();
    }

    /*
    public String readLinesBeta(String myString){
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(myString);

        Integer counter = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (counter>=fromLine && counter<=toLine){
                result.append(counter+": "+line+"\n");
            }
            counter++;
        }
        scanner.close();

        return result.toString();

    }
    */

    public static void main(String[] args)throws Exception
    {
       FilePartReader filePartReader = new FilePartReader(
               "/Users/sandornegyeliczky/Desktop/projektek/oop/filepartreader-testing-with-junit-snegyeliczky/testTry/src/main/resources/text.txt",
               10,
               12);
        System.out.println(filePartReader.readLines());

    }


}
