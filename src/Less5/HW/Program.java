package Less5.HW;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Path;


public class Program {

    private static final Random random = new Random();

    private static final int CHAR_BOUND_L = 65;

    private static final int CHAR_BOUND_H = 90;

    private static final String TO_SEARCH = "GeekBrains";

    public static void main(String[] args) throws IOException {
        //путь к директории в которую будут сохранены все созданные файлы в процессе работы программы
        String directoryPath = "C:\\Users\\ВТБ\\IdeaProjects\\JavaCoreGb\\src\\Less5\\generation";

        System.out.println(generateSymbols(15));
        writeFileContents(directoryPath + "/sample01.txt", 35, 3);
        writeFileContents(directoryPath + "/sample02.txt", 35, 2);
        concatenate(directoryPath + "/sample01.txt", directoryPath + "/sample02.txt", directoryPath + "/sample_out.txt");

        long i = 0;

        while ( (i = searchInFile(directoryPath + "/sample_out.txt", i, TO_SEARCH)) > 0){
            System.out.printf("Файл содержит искомое слово, смещение: %d\n", i);
        }
        System.out.println("Завершение поиска.");

        String[] fileNames = new String[10];
        for(int j = 0; j < fileNames.length; j++){
            fileNames[j] = directoryPath + "\\file_" + j + ".txt";
            writeFileContents(fileNames[j], 30, 3);
            System.out.printf("Файл %s создан\n", fileNames[j]);
        }

        List<String> result = searchMatch(new File("."), TO_SEARCH);
        for (String s : result){
            System.out.printf("Файл %s содержит искомое слово '%s'\n", s, TO_SEARCH);
        }
        createBackup(directoryPath);
    }

    /**
     * Метод генерации некоторой последовательности символов
     * @param amount кол-во символов
     * @return последовательность символов
     */
    private static String generateSymbols(int amount){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++){
            stringBuilder.append((char)random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Записать последовательность символов в файл, при этом, случайным образом дописать осознанное слово
     * для поиска
     * @param fileName имя файла
     * @param length длина последовательности символов
     * @param words кол-во слов для поиска
     * @throws IOException
     */
    static void writeFileContents(String fileName, int length, int words) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            for (int i = 0; i < words; i++){
                fileOutputStream.write(generateSymbols(length).getBytes(StandardCharsets.UTF_8));
                if (random.nextInt(3) == 0){
                    fileOutputStream.write(TO_SEARCH.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.write(generateSymbols(length).getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }

    /**
     * @param fileIn1 1-й файл на запись
     * @param fileIn2 2-й файл на запись
     * @param fileOut файл на вывод информации из первых двух файлов в один fileOut
     */
    static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException{
        /*
        Сначала создаем объект FileOutputStream с помощью конструктора new FileOutputStream(fileOut),
        который открывает файл fileOut для записи данных. Объект fileOutputStream будет использоваться
        для записи данных в этот файл.
         */
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileOut)){
            /*
            1) В этом блоке, мы создаем объект FileInputStream, открываем файл fileIn1 для чтения данных.
            Объект fileInputStream будет использоваться для чтения данных из этого файла.

            2) Затем, мы используем цикл while, считывая данные из fileInputStream в переменную c.
            По мере чтения байтовых данных, мы вызываем метод write на объекте fileOutputStream, чтобы записать
            их в файл fileOut.
             */
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
            // во втором блоке ситуация повторяется и данные полученные из fileIn2 передаются побайтово в fileOut
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }



    /**
     * Определить, содержится ли в файле искомое слово
     * @param fileName имя файла
     * @param search строка для поиска
     * @return результат поиска
     */
    private static long searchInFile(String fileName, String search) throws IOException{
        return searchInFile(fileName, 0, search);
    }

    private static long searchInFile(String fileName, long offset, String search) throws IOException{
        try (FileInputStream fileInputStream = new FileInputStream(fileName)){
            fileInputStream.skipNBytes(offset);
            byte[] searchData = search.getBytes();
            int c;
            int i = 0;
            while ((c = fileInputStream.read()) != -1){
                if (c == searchData[i]){
                    i++;
                }
                else {
                    i = 0;
                    if (c == searchData[i])
                        i++;
                }
                if (i == searchData.length){
                    return offset;
                }
                offset++;
            }
            return -1;
        }
    }

    static List<String> searchMatch(File file, String search) throws IOException {
        List<String> list = new ArrayList<>();
        File[] files = file.listFiles();
        if (files == null)
            return list;
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){
                if (searchInFile(files[i].getCanonicalPath(), search) > -1){
                    list.add(files[i].getCanonicalPath());
                }
            }
        }
        return list;
    }

    /**
     * TODO Добавлен новый метод createBackup
     * Это метод который принимает путь к директории (directoryPath)
     * в качестве параметра. Метод проверяет, существует ли указанная директория, и если
     * директория существует, он создает директорию резервной копии ./BackupFiles. Затем
     * метод копирует все файлы из исходной директории в новую директорию резервной копии.
     * @param directoryPath
     * @throws IOException
     */
    static void createBackup(String directoryPath) throws IOException {
        File sourceDirectory = new File(directoryPath);
        File backupDirectory = new File("C:\\Users\\ВТБ\\IdeaProjects\\JavaCoreGb\\src\\Less5\\BackupFiles");

        if (!sourceDirectory.exists()) {
            System.out.println("Директория для резервного копирования не найдена: " + directoryPath);
            return;
        }

        if (!backupDirectory.exists()) {
            if (backupDirectory.mkdir()) {
                System.out.println("Создана новая папка для резервного копирования: " + backupDirectory.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать новую папку для резервного копирования: " + backupDirectory.getAbsolutePath());
                return;
            }
        }

        File[] files = sourceDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String backupFilePath = backupDirectory.getAbsolutePath() + File.separator + fileName;
                Path sourcePath = file.toPath();
                Path backupPath = new File(backupFilePath).toPath();
                Files.copy(sourcePath, backupPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Создана резервная копия файла: " + backupFilePath);
            }
        }
    }
}
