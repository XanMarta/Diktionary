public class ConsoleApplication {

    static Dictionary dictionary = new Dictionary();
    static DictionaryManagement manager = new DictionaryManagement(dictionary);
    static DictionaryCommandLine command = new DictionaryCommandLine(manager);

    static void showMenu() {
        System.out.println("        DIKTIONARY");
        System.out.println("1. Show all word");
        System.out.println("2. Look up");
        System.out.println("3. Add word");
        System.out.println("4. Remove word");
        System.out.println("5. Exit");
        System.out.println();
    }

     public static void main(String[] args) {

//         manager.insertFromFile();
         Files.importCsv(dictionary);

         boolean isRunning = true;
         while (isRunning) {
             showMenu();
             int choice = ConsoleC.scan.nextInt();
             ConsoleC.scan.nextLine();
             switch (choice) {
                 case 1:
                     command.showAllWords();
                     break;
                 case 2:
                     manager.dictionaryLookup();
                     break;
                 case 3:
                     manager.addWord();
                     break;
                 case 4:
                     manager.eraseWord();
                     break;
                 case 5:
                     isRunning = false;
                     break;
             }
         }

//         manager.dictionaryExportToFile();
         ConsoleC.scan.close();
     }

}
