import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> myListOfWords = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("All of Kobe's words: %s", myListOfWords);
        } else if (url.getPath().equals("/add")) {
            
            //add string to myListOfWords
            String[] addParameters = url.getQuery().split("=");
            if (addParameters[0].equals("s")) {

                myListOfWords.add(addParameters[1]); 
                return String.format("You have added this word to the list: %s", addParameters[1]);
            }



            return String.format("Number incremented!");
        } else if (url.getPath().equals("/search")) {
            
            //add string to myListOfWords
            String[] searchParameters = url.getQuery().split("=");
            if (searchParameters[0].equals("s")) {

                List<String> wordsThatHaveSubstring = new ArrayList<>();

                for(String word : myListOfWords) {
                    if(word.contains(searchParameters[1])) {
                        wordsThatHaveSubstring.add(word); 
                    }
                }

                return String.format("These words contain your given substring of %s: %s", searchParameters[1], wordsThatHaveSubstring);
            }



            return String.format("Number incremented!");
        } else {
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}