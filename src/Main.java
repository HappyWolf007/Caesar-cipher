
    import java.util.*;
    import java.io.*;
    import java.io.IOException;

    
    
class EncryptionAndDecryption {
    private final String input;
    private int shift;


    public EncryptionAndDecryption(String input, int shift) {
        this.input = input;
        this.shift = shift;
    }

    char[] rus_alfabetLower = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    char[] rus_alfabetUpper = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Ч', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    char[] eng_alfabetLower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char[] eng_alfabetUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    StringBuilder result = new StringBuilder();
    boolean found = false;

    public String russianEncrypt() {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            found = false;
            for (int j = 0; j < rus_alfabetLower.length; j++) {
                if (currentChar == rus_alfabetLower[j]) {
                    result.append(rus_alfabetLower[(j + shift) % rus_alfabetLower.length]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int j = 0; j < rus_alfabetUpper.length; j++) {
                    if (currentChar == rus_alfabetUpper[j]) {
                        result.append(rus_alfabetUpper[(j + shift) % rus_alfabetUpper.length]);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public String russianDecrypt() {

        shift = rus_alfabetLower.length;
        int shiftForResult=0;
        StringBuilder results = new StringBuilder();

        while (shift > 0) {
            StringBuilder decrypt_result = new StringBuilder();
            shift--;

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                found=false;
                for (int j = 0; j < rus_alfabetLower.length; j++) {
                    if (currentChar == rus_alfabetLower[j]) {
                        decrypt_result.append(rus_alfabetLower[(j - shift + rus_alfabetLower.length) % rus_alfabetLower.length]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    for (int j = 0; j < rus_alfabetUpper.length; j++) {
                        if (currentChar == rus_alfabetUpper[j]) {
                            decrypt_result.append(rus_alfabetUpper[(j - shift + rus_alfabetUpper.length) % rus_alfabetUpper.length]);
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    decrypt_result.append(currentChar);
                }

            }
            shiftForResult+=1;
            results.append("Расшифрованная строка: ").append(decrypt_result).append(", сдвиг: ").append(shiftForResult).append("\n");
        }
        return results.toString();
    }


    public String englishEncrypt() {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            found = false;
            for (int j = 0; j < eng_alfabetLower.length; j++) {
                if (currentChar == eng_alfabetLower[j]) {
                    result.append(eng_alfabetLower[(j + shift) % eng_alfabetLower.length]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int j = 0; j < eng_alfabetUpper.length; j++) {
                    if (currentChar == eng_alfabetUpper[j]) {
                        result.append(eng_alfabetUpper[(j + shift) % eng_alfabetUpper.length]);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public String englishDecrypt() {

        shift = eng_alfabetLower.length;
        int shiftForResult=0;
        StringBuilder results = new StringBuilder();

        while (shift > 0) {
            StringBuilder decrypt_result = new StringBuilder();
            shift--;

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                found=false;
                for (int j = 0; j < eng_alfabetLower.length; j++) {
                    if (currentChar == eng_alfabetLower[j]) {
                        decrypt_result.append(eng_alfabetLower[(j - shift + eng_alfabetLower.length) % eng_alfabetLower.length]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    for (int j = 0; j < eng_alfabetUpper.length; j++) {
                        if (currentChar == eng_alfabetUpper[j]) {
                            decrypt_result.append(eng_alfabetUpper[(j - shift + eng_alfabetUpper.length) % eng_alfabetUpper.length]);
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    decrypt_result.append(currentChar);
                }

            }
            shiftForResult+=1;
            results.append("Расшифрованная строка: ").append(decrypt_result).append(", сдвиг: ").append(shiftForResult).append("\n");
        }
        return results.toString();
    }
}



class FileHandler {

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return content.toString();
    }

    public static void writeToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}


    
    
    
    
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String result;
        int leng;
        int rus_language_length = 32;
        int eng_language_length = 25;
        while (true) {
            System.out.println("Выберите режим работы:");
            System.out.println("1: Зашифровка");
            System.out.println("2: Расшифровка");
            System.out.println("3: Работа с файлом");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Выберите язык: (1 - русский, 2 - английский)");
                    leng = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Введите строку для шифрования: ");
                    String input = scanner.nextLine();

                    if (leng==1){
                        int shift = getShift(scanner, rus_language_length);
                        EncryptionAndDecryption encryption = new EncryptionAndDecryption(input, shift);
                        System.out.println("Зашифрованная строка: " + encryption.russianEncrypt());
                        System.out.println();
                    } else if(leng==2) {
                        int shift = getShift(scanner, eng_language_length);
                        EncryptionAndDecryption encryption = new EncryptionAndDecryption(input, shift);
                        System.out.println("Зашифрованная строка: " + encryption.englishEncrypt());
                        System.out.println();
                    }
                    else {
                        System.out.println("Неверный выбор действия.");
                    }
                    break;
                case 2:
                    System.out.println("Выберите язык: (1 - русский, 2 - английский)");
                    leng = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Введите строку для расшифровки:");
                    String input2 = scanner.nextLine();

                    if (leng == 1) {
                        EncryptionAndDecryption decryption = new EncryptionAndDecryption(input2, rus_language_length);
                        result = decryption.russianDecrypt();
                        System.out.println("Расшифрованный текст:\n" + result);
                    } else if (leng == 2) {
                        EncryptionAndDecryption decryption = new EncryptionAndDecryption(input2, eng_language_length);
                        result = decryption.englishDecrypt();
                        System.out.println("Расшифрованный текст:\n" + result);
                    } else {
                        System.out.println("Неверный выбор действия.");
                    }
                    break;


                case 3:

                    String inputFilePath = "input.txt";
                    String fileContent = FileHandler.readFile(inputFilePath);
                    if (fileContent.isEmpty()) {
                        System.out.println("Файл пуст или не был прочитан. Пожалуйста, попробуйте снова.");
                        System.out.println();
                        continue;
                    }

                    System.out.println("Содержимое файла:\n" + fileContent);

                    System.out.println("Выберите язык: (1 - русский, 2 - английский)");
                    leng = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Выберите действие: (1 - шифровать, 2 - дешифровать)");
                    int action = scanner.nextInt();
                    scanner.nextLine();

                    String resultContent;

                    if (leng==1 & action == 1) {
                        int shift_n_file = getShift(scanner, rus_language_length);
                        EncryptionAndDecryption processor = new EncryptionAndDecryption(fileContent, shift_n_file);
                        resultContent = processor.russianEncrypt();
                        FileHandler.writeToFile("output.txt", resultContent);
                        System.out.println("Полученный зашифрованный текст:\n" + FileHandler.readFile("output.txt"));
                    }
                    else if(leng==2 & action==1){
                        int shift_n_file = getShift(scanner, eng_language_length);
                        EncryptionAndDecryption processor = new EncryptionAndDecryption(fileContent, shift_n_file);
                        resultContent = processor.englishEncrypt();
                        FileHandler.writeToFile("output.txt", resultContent);
                        System.out.println("Полученный зашифрованный текст:\n" + FileHandler.readFile("output.txt"));
                    }
                    else if (leng==1 & action == 2) {
                        EncryptionAndDecryption processor = new EncryptionAndDecryption(fileContent, 0);
                        resultContent = processor.russianDecrypt();
                        System.out.println("Расшифрованный текст:\n" + resultContent);
                        FileHandler.writeToFile("decrypted_output.txt", resultContent);
                    }
                    else if(leng==2 & action==2){
                        EncryptionAndDecryption processor = new EncryptionAndDecryption(fileContent, 0);
                        resultContent = processor.englishDecrypt();
                        System.out.println("Расшифрованный текст:\n" + resultContent);
                        FileHandler.writeToFile("decrypted_output.txt", resultContent);
                    }

                    else {
                        System.out.println("Неверный выбор действия.");
                    }
                    break;



                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }

    }
    private static int getShift(Scanner scanner, int language_length) {
        System.out.println("Введите сдвиг (от " + -language_length + " до " + language_length + ", не считая 0):");
        int shift;

        while (true) {

            shift = scanner.nextInt();
            if (shift > language_length || shift < -language_length || shift == 0) {
                System.out.println("Введите корректный сдвиг.");
            } else {
                break;
            }
        }
        return shift;
    }

}

