import java.nio.file.Files;
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


class RussianEncryptionAndDecryption {
    private String input;
    private int shift;


    public RussianEncryptionAndDecryption(String input, int shift) {
        this.input = input;
        this.shift = shift;
    }

    char[] alfabetLower = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    char[] alfabetUpper = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'М', 'Т', 'У', 'Ф', 'Ч', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    StringBuilder result = new StringBuilder();
    boolean found = false;
    public void russianEncrypt() {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            for (int j = 0; j < alfabetLower.length; j++) {
                if (currentChar == alfabetLower[j]) {
                    result.append(alfabetLower[(j + shift) % alfabetLower.length]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int j = 0; j < alfabetUpper.length; j++) {
                    if (currentChar == alfabetUpper[j]) {
                        result.append(alfabetUpper[(j + shift) % alfabetUpper.length]);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                result.append(currentChar);
            }
        }
        System.out.println("Зашифрованная строка: " + result);
    }

    public void russianDecrypt() {

        shift = alfabetLower.length;
        int shiftForResult=0;
        while (shift > 0) {
            StringBuilder result = new StringBuilder();
            shift--;

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                found=false;
                for (int j = 0; j < alfabetLower.length; j++) {
                    if (currentChar == alfabetLower[j]) {
                        result.append(alfabetLower[(j - shift + 33) % alfabetLower.length]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    for (int j = 0; j < alfabetUpper.length; j++) {
                        if (currentChar == alfabetUpper[j]) {
                            result.append(alfabetUpper[(j - shift + 33) % alfabetUpper.length]);
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    result.append(currentChar);
                }

            }
            shiftForResult+=1;
            System.out.println("Расшифрованная строка: " + result + ", сдвиг: " + shiftForResult);
        }

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
        while (true) {
            System.out.println("Выберите режим работы:");
            System.out.println("1: Зашифровка на русском языке");
            System.out.println("2: Расшифровка на русском языке");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите строку для шифрования:");
                    String input = scanner.nextLine();

                    int shift = 0;
                    while (true) {
                        System.out.println("Введите сдвиг (от -32 до 32, не считая 0):");
                        shift = scanner.nextInt();
                        if (shift > 32 || shift < -32 || shift == 0) {
                            System.out.println("Введите корректный сдвиг.");
                        } else {
                            break;
                        }
                    }
                    RussianEncryptionAndDecryption encryption = new RussianEncryptionAndDecryption(input, shift);
                    encryption.russianEncrypt();

                    break;
                case 2:
                    System.out.println("Введите строку для расшифрования на русском языке:");
                    String input2 = scanner.nextLine();
                    RussianEncryptionAndDecryption decryption = new RussianEncryptionAndDecryption(input2, 33);
                    decryption.russianDecrypt();
                case 3:
                    String inputFilePath = "input.txt";
                    String outputFilePath = "output.txt";
                    String encryptedText = FileHandler.readFile(inputFilePath);

                    //////
                    RussianEncryptionAndDecryption fileEncryption= new RussianEncryptionAndDecryption(encryptedText, 10);

                    // Расшифровка текста
                    //String decryptedText = fileEncryption.russianDecrypt();

                    // Запись расшифрованного текста в файл
                    //FileHandler.writeToFile(outputFilePath, decryptedText);

                    //System.out.println("Расшифрованная строка: " + decryptedText);



                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }

    }
}

