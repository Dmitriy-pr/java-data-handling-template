package com.epam.izh.rd.online.service;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, ""); //TODO
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     * <p>
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        if (text.endsWith("?")) {
            return true;
        }
        return false; //TODO
    }

    /**
     * Реализовать функционал соединения переданных строк.
     * <p>
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder builder = new StringBuilder();
        for (String s : elements) {
            builder.append(s);
        }
        String str = builder.toString();
        return str; //TODO
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        String[] strings = text.split("");
        for (int index = 0; index < strings.length; index++) {
            String s = strings[index];
            if (index % 2 == 0) {
                strings[index] = s.toLowerCase();
            } else {
                strings[index] = s.toUpperCase();
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        text = builder.toString();
        return text; //TODO
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     * <p>
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     * <p>
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if (!string.isEmpty()) {
            String revers = new StringBuilder(string).reverse().toString()
                    .replace(" ", "").toLowerCase();
            System.out.println(revers);
            return string.replace(" ", "").toLowerCase().equals(revers);
        } else {
            return false;//TODO
        }
    }
}
