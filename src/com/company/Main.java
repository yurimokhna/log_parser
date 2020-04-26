package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("���������� � ������� java -classpath ����������_����_�_������" +
            " ���_������ \"��������� ����_�����\\���_�����_���������\" \"���������_����_�����\\���_���������_�����\"");
            return;
        }

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(args[0], Charset.forName("windows-1251"));
            System.out.println("���� �������� �����");
            Scanner scanner = new Scanner(fileReader);
            fileWriter = new FileWriter(args[1]);
            System.out.println("�������� ���� ������");

            String lineSeparator = System.getProperty("line.separator");
            int hour = 0;
          //String search = " ";

            System.out.println("������ ����");
            String regex = "^.+(\\d{2}):00:0\\d.* ��������� ���� � ���� (\\d{1,3}) \\- (\\d{1,3})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                matcher = pattern.matcher(string);

                if(matcher.matches()){
                    int hourInLog = Integer.parseInt(matcher.group(1));
                    if (hour == hourInLog) {
                        fileWriter.write(hour + ";" + matcher.group(3) + lineSeparator);
                        if(hour == 0) System.out.print("������ � �������� ����");
                        else  System.out.print(". ");
                        hour++;
                    }

                   /* search = String.format("%02d:00:0", hour );
                    if(search.equals(matcher.group(1))){
                        fileWriter.write(hour + ";" + matcher.group(3) + lineSeparator);
                        if(hour == 0) System.out.print("������ � �������� ����");
                        else  System.out.print(". ");
                        hour++;
                    }*/

                }
            }
            System.out.println("\n������� ������� �������");
        }
        catch (FileNotFoundException e) {
            System.out.println("���� �������� �� ������ " + e);
        }
        catch (IOException e) {
            System.out.println("������ �����-������" + e);
        }
        finally {
            try {
                if (fileReader != null) fileReader.close();
                if (fileWriter != null) fileWriter.close();
            }
            catch (IOException e) {
                System.out.println("������ ��� �������� ������");
            }
            System.out.println("���������� ������");
        }
    }
}
