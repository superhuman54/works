package kr.co.tabling.works.util;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Solution2 {

    private static final Map<String, String> CATEGORIES = new HashMap<>();

    static {
        CATEGORIES.put("mp3", "music");
        CATEGORIES.put("aac", "music");
        CATEGORIES.put("flac", "music");

        CATEGORIES.put("jpg", "image");
        CATEGORIES.put("bmp", "image");
        CATEGORIES.put("gif", "image");

        CATEGORIES.put("mp4", "movie");
        CATEGORIES.put("avi", "movie");
        CATEGORIES.put("mkv", "movie");
    }

    public String solution(String S) {

        Scanner scanner = new Scanner(S);

        Map<String, AtomicInteger> result = new HashMap<>();
        result.put("music", new AtomicInteger(0));
        result.put("image", new AtomicInteger(0));
        result.put("movie", new AtomicInteger(0));
        result.put("other", new AtomicInteger(0));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ", 2);

            String extension = tokens[0].substring(tokens[0].lastIndexOf('.') + 1);
            int size = Integer.parseInt(tokens[1].substring(0, tokens[1].length() - 1));

            String category = CATEGORIES.getOrDefault(extension, "other");
            result.get(category).addAndGet(size);
        }

        return "music " + result.get("music").get() + "b\n" +
                "images " + result.get("image").get() + "b\n" +
                "movies " + result.get("movie").get() + "b\n" +
                "other " + result.get("other").get() + "b";
    }

    public static void main(String[] args) {
        String solution = new Solution2().solution("my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b");

        System.out.println(solution);
    }

}
