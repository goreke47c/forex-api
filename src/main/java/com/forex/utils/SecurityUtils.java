package com.forex.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {

    public static String calculateMD5(String input) {
        return DigestUtils.md5Hex(input);
    }
}
