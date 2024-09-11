package org.mysqlmongomigration.model;

import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String str = "aaabbaanjkkllaajjaaabbnj";
        //extected: abnjkl
        String result = str.chars()
                .mapToObj(c -> (char) c + "")
                .distinct()
                .collect(Collectors.joining());

        System.out.println(result);
    }
}
