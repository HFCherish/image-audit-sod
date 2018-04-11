package com.tw.imageaudit.sod.conf;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public class SorConf {
    private static String sorBaseUrl() {
        return String.format("http://%s:%s",
                System.getenv().getOrDefault("SOR_HOST", "localhost"),
                System.getenv().getOrDefault("SOR_PORT", "8081")
        );
    }

    public static String sorUrl(String path) {
        return sorBaseUrl() + path;
    }

}
