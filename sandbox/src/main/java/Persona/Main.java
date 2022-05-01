package Persona;

public class Main {
    public static void main (String[] args) {
        Persona i = new Persona ("Irina","Nosova", 31, "dog");
        Persona a = new Persona ("Artem","Nosov", 29, "dog");
        a.thirdName = "Olegovich";
        printPersona (i);
        printPersona (a);
        printPersona (new Persona ("Igor","Melnik", 36, "" ));


    }
    public static void printPersona (Persona p) {
        System.out.println ("Name" + " = " + p.name);
        System.out.println ("Surname" + " = " + p.surname);
        System.out.println ("thirdName" + " = " + p.thirdName);
        System.out.println ("Age" + " = " + p.age);
        System.out.println ("Pet" + " = " + p.pet);
        System.out.println ("-");
    }
}
