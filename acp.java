import java.util.Scanner;

class acp {
    int countOccurrences(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        if (M == 0 || N == 0 || M > N) return 0;
        
        int[] lps = new int[M];
        int j = 0;
        computeLPSArray(pat, M, lps);
        
        int count = 0;
        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                count++; // Increment count when pattern is found
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return count;
    }

    void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        // Input matching the format: P first, then T[cite: 1]
        String pat = sc.nextLine();
        String txt = sc.nextLine();
        
        int result = new act().countOccurrences(pat, txt);
        
        System.out.println("OUTPUT:");
        System.out.println(result);
    }
}