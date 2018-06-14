package com.examresults;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BCryptTest {

    @Test
    void hashpw() {
        List<String> plainPasses = Arrays.asList("admin", "bob", "john");
        for (int i = 1; i <= plainPasses.size(); i++) {
            String plainPass = plainPasses.get(i - 1);
            String hashedPass = BCrypt.hashpw(plainPass, BCrypt.gensalt());

            String sql = "INSERT INTO users(id, username, password) " +
                    "\nVALUES (%d, '%s', '%s');\n";

            System.out.println(String.format(sql, i, plainPass, hashedPass));
        }
    }

    @Test
    void checkpw() {
        String s = "$2a$10$THmT55GjpH5WztbdP.p6QOhEEXoC6QrWI4TkwOUPrRFOcEuSkeH3y";
        Assertions.assertTrue(BCrypt.checkpw("admin", s));
    }
}