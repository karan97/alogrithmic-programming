/*
Solution of Codechef Problem - Churu and Balls
Problem Code - CBALLS
Link - https://www.codechef.com/problems/CBALLS
*/

import java.util.*;
import java.io.*;
import java.lang.*;

class CBALLS {

    private static InputStream stream;
    private static byte[] buf = new byte[1024];
    private static int curChar;
    private static int numChars;
    private static SpaceCharFilter filter;
    static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
    public final static List<Integer> primeList = new ArrayList<>();
    public static boolean[] getPrimes(int n) {
        boolean[] a = new boolean[n + 1];
        Arrays.fill(a, true);
        a[0] = false;
        a[1] = false;
        for(int i = 2; i <= n; i++) {
            if(a[i]) {
                primeList.add(i);
                for(int j = 2; j * i <= n; j++) {
                    a[j * i] = false;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        InputReader(System.in);
        int testCases = nI();
        boolean[] a = getPrimes(10000);
        while(testCases-- > 0) {
            int n = nI();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = nI();
            }
            int result = Integer.MAX_VALUE;
            for(int prime : primeList) {
                int temp = 0;
                int current = 0;
                for(int i = 0; i < n; i++) {
                    if(arr[i] > current) {
                        current = ((arr[i] + prime - 1) / prime) * prime;
                    }
                    temp += current - arr[i];
                }
                result = Math.min(result, temp);
            }
            log.write(String.valueOf(result) + "\n");
            log.flush();
        }
    }

    public static void InputReader(InputStream stream1) {
        stream = stream1;
    }

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private static boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }

    private static int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    private static int nI() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    private static long nL() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    private static String nS() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    private static String nLi() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    private static boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    private interface SpaceCharFilter {

        public boolean isSpaceChar(int ch);
    }
}