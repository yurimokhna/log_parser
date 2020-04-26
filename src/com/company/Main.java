package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Запускайте в формате java -classpath Абсолютный_путь_к_классу" +
            " Имя_класса \"Аболютнйы путь_файла\\Имя_файла_источника\" \"Аболютнйы_путь_файла\\Имя_выходного_файла\"");
            return;
        }

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(args[0], Charset.forName("windows-1251"));
            System.out.println("Файл источник окрыт");
            Scanner scanner = new Scanner(fileReader);
            fileWriter = new FileWriter(args[1]);
            System.out.println("Выходной файл создан");

            String lineSeparator = System.getProperty("line.separator");
            int hour = 0;
          //String search = " ";

            System.out.println("Читаем файл");
            String regex = "^.+(\\d{2}):00:0\\d.* Свободных мест в зоне (\\d{1,3}) \\- (\\d{1,3})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                matcher = pattern.matcher(string);

                if(matcher.matches()){
                    int hourInLog = Integer.parseInt(matcher.group(1));
                    if (hour == hourInLog) {
                        fileWriter.write(hour + ";" + matcher.group(3) + lineSeparator);
                        if(hour == 0) System.out.print("Запись в выходной файл");
                        else  System.out.print(". ");
                        hour++;
                    }

                   /* search = String.format("%02d:00:0", hour );
                    if(search.equals(matcher.group(1))){
                        fileWriter.write(hour + ";" + matcher.group(3) + lineSeparator);
                        if(hour == 0) System.out.print("Запись в выходной файл");
                        else  System.out.print(". ");
                        hour++;
                    }*/

                }
            }
            System.out.println("\nПарсинг окончен успешно");
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл источник не найден " + e);
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
        }
        finally {
            try {
                if (fileReader != null) fileReader.close();
                if (fileWriter != null) fileWriter.close();
            }
            catch (IOException e) {
                System.out.println("Ошибка при закрытии файлов");
            }
            System.out.println("Завершение работы");
        }
    }
}
