package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File[] listFiles = new File(getFileFromResource(path)).listFiles();

        if (listFiles == null || listFiles.length == 0) {
            return 0;
        }

        long count = 0;

        List<File> files = new ArrayList<>(Arrays.asList(listFiles));

        for (File file : files) {
            if (file.isDirectory()) {
                count += countFilesInDirectory(path + File.separator + file.getName());
            } else {
                count++;
            }
        }

        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File directory = new File(getFileFromResource(path));

        File[] listFiles = directory.listFiles();

        if (listFiles == null || listFiles.length == 0) {
            return 1;
        }

        long count = 1;

        List<File> files = new ArrayList<>(Arrays.asList(listFiles));

        for (File file : files) {
            if (file.isDirectory()) {
                count += countDirsInDirectory(path + File.separator + file.getName());
            }
        }

        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        try {
            Path target = Paths.get(to);
            Files.createDirectories(target.getParent());
            Files.copy(Paths.get(from), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        try {
            String fileFromResource = getFileFromResource("");
            Path path1 = Paths.get(fileFromResource + File.separator + path + File.separator + name);
            Files.createDirectories(path1.getParent());
            if (Files.exists(path1)) {
                return true;
            } else {
                Files.createFile(path1);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        try {
            return Files.lines(Paths.get(getFileFromResource(fileName))).reduce("", String::concat);
        } catch (IOException e) {
            return "";
        }
    }


    private String getFileFromResource(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        try {
            return new File(resource.toURI()).getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}
