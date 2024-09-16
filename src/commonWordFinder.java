import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class commonWordFinder {
    static ArrayList<String> invalid = new ArrayList<>();
    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<String> updated = new ArrayList<>();
    static ArrayList<Integer> counter = new ArrayList<>();
    static String fileName;

    public static void main(String[] args) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        fileName = "textTwo";
        readInvalid("commonWords");
        readFile(fileName);
        updateCounter();
        bubbleSort();
        for(int i = updated.size() - 1; i >= updated.size()-5; i--){
            System.out.println(updated.get(i) + " " + counter.get(i));
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    public commonWordFinder(String fileName){
        invalid = new ArrayList<>();
        words = new ArrayList<>();
        updated = new ArrayList<>();
        counter = new ArrayList<>();
        this.fileName = fileName;
    }

    private static void readInvalid(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String word = scanner.nextLine();
            word = word.toLowerCase();
            invalid.add(word);
        }
    }

    private static boolean checkValid(String word){
        for(String str:invalid){
            if(word.equals(str)) return false;
        }
        return true;
    }

    private static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            str = str.toLowerCase();
            str += " ";
            String curWord = "";
            for(int i = 0; i< str.length(); i++){
                if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                    curWord += str.charAt(i);
                }
                else if(str.charAt(i) == ' '){
                    if(checkValid(curWord) && !curWord.equals("")){
                        words.add(curWord);
                    }
                    curWord = "";
                }
            }
        }
    }

    private static int findIndex(String str){
        for(int i = 0; i < updated.size();i++){
            if(updated.get(i).equals(str)) return i;
        }
        return -1;
    }

    public static void updateCounter(){
        for(int i = 0; i<words.size();i++){
            int index = findIndex(words.get(i));
            if(index == -1){
                updated.add(words.get(i));
                counter.add(1);
            }else{
                counter.set(index,counter.get(index)+1);
            }
        }
    }

    public static void bubbleSort(){
        int count = 0;
        while(count < 5){
            for(int i =0; i < updated.size()-1; i++){
                if(counter.get(i) > counter.get(i+1)){
                    String temp = updated.get(i);
                    updated.set(i,updated.get(i+1));
                    updated.set(i+1,temp);
                    int temp2 = counter.get(i);
                    counter.set(i,counter.get(i+1));
                    counter.set(i+1,temp2);
                }
            }
            count ++ ;
        }
    }

}
