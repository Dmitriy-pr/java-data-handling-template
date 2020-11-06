package com.epam.izh.rd.online.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        File file = new File(
                "D:/epam-projects/java-data-handling-template/src/main/resources/sensitive_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String regex = "(?<=(?:[^\\d]|^))(\\d{4}[ \\t])(?:\\d{4}[ \\t]){2}(\\d{4})(?=(?:[^\\d]|$))";
                String line = scanner.nextLine();
                String subst = "$1**** **** $2";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(line);
                String result = matcher.replaceAll(subst);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File file = new File(
                "D:/epam-projects/java-data-handling-template/src/main/resources/sensitive_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String regexPaymentAmount = "[$]{1}[{]{1}[a-z]{7}[_]{1}[a-z]{6}[}]{1}";
                String regexBalance = "[$]{1}[{]{1}[a-z]{7}[}]{1}";
                String line = scanner.nextLine();

                Pattern pattern1 = Pattern.compile(regexPaymentAmount);
                Pattern pattern2 = Pattern.compile(regexBalance);
                Matcher matcher1 = pattern1.matcher(line);
                String result1 = matcher1.replaceAll(Integer.toString((int) paymentAmount));
                Matcher matcher2 = pattern2.matcher(result1);
                String result2 = matcher2.replaceAll(Integer.toString((int) balance));
                return result2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
