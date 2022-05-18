package irina.jft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        //List<String> languages = new ArrayList<String();
        // слева - интерфейс, справа конкретный класс, который реализует этот интерфейс
        //languages.add("Java");
      //  languages.add("C#");
      //  languages.add("PHP");
// или
        List<String> languagesTU = Arrays.asList("Java", "C#", "Python", "PHP");


        //      for (int i = 0; i < langs.length; i++) {
  //          System.out.println("Я хочу выучить " + langs[i]);
  //      }


        for (String l : languagesTU) {
                      System.out.println("Я хочу выучить " + l);
                  }

    }
}
