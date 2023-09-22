package com.example.springboot.service;

import org.springframework.expression.spel.ast.Literal;
import us.codecraft.webmagic.selector.Html;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public static String getCurrentDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        return dtf.format(localDate);
    }

    public static void save(String siteName, String directory, String content)
    {
        String fileAdr = directory + File.separator+ siteName+ "-"+ getCurrentDate();

        // Creating an instance of file
        Path path = Paths.get(fileAdr);

        // Custom string as an input
        String str = content;

        // Try block to check for exceptions
        try {
            // Now calling Files.writeString() method
            // with path , content & standard charsets
            Files.writeString(path, str, StandardCharsets.UTF_8);
        }

        // Catch block to handle the exception
        catch (IOException ex) {
            // Print messqage exception occurred as
            // invalid. directory local path is passed
            System.out.print("Invalid Path");
        }
    }

    // list all files from this path
    public static List<Path> listFiles(Path path) throws IOException {

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;

    }

    public static boolean pageTodayFetched(String siteName, String filePath)  {
        List<Path> paths = null;
        String todayFileName = siteName+"-"+ getCurrentDate();

        File f = new File(filePath);
        boolean b = Arrays.stream(f.list()).anyMatch(s -> s.equals(todayFileName));
        return b;
//        try {
//            paths = listFiles(Paths.get(filePath));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return paths.contains(Paths.get(todayFileName));

    }

    public static Html readHtmlSavedPage(String siteName, String path) {
        String todayFileName = siteName+"-"+ getCurrentDate();
        String fileAdr = path + File.separator + todayFileName;
        try {
            String readString = Files.readString(Paths.get(fileAdr));
            Html html = new Html(readString);
            return  html;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
