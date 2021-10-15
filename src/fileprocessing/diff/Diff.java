package fileprocessing.diff;

import java.util.ArrayList;

public class Diff {

    private ArrayList<Operation> diffOperations;

    public int[][] lcs(String s, String t) {
        int[][] lookup = new int[s.length() + 1][t.length() + 1];
        var m = s.length();
        var n = t.length();

        for (int i = 0; i <= m; i++) {
            lookup[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            lookup[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    lookup[i][j] = lookup[i - 1][j - 1] + 1;
                }

                else {
                    lookup[i][j] = Integer.max(lookup[i - 1][j],
                            lookup[i][j - 1]);
                }
            }
        }
        return lookup;
    }

    private void addToOperations(operations type, char value) {
        if (diffOperations.size() == 0)
            diffOperations.add(new Operation(type, String.valueOf(value)));
        else {
            var last = diffOperations.get(diffOperations.size() - 1);
            if (last.operation == type) {
                last.addToSequence(String.valueOf(value));
            } else {
                diffOperations.add(new Operation(type, String.valueOf(value)));
            }
        }
    }

    // This might cause a stackoverflow for large files diff, TODO: Find a better solution
    public ArrayList<Operation> genDiff(String X, String Y, int m, int n, int[][] lookup) {
        if (m > 0 && n > 0 && X.charAt(m - 1) == Y.charAt(n - 1)) {
            genDiff(X, Y, m - 1, n - 1, lookup);
            System.out.print(" " + X.charAt(m - 1));
            addToOperations(operations.neutral, X.charAt(m - 1));
        }

        else if (n > 0 && (m == 0 || lookup[m][n - 1] >= lookup[m - 1][n])) {
            genDiff(X, Y, m, n - 1, lookup);
            System.out.print(" +" + Y.charAt(n - 1));
            addToOperations(operations.insert, Y.charAt(n - 1));
        }

        else if (m > 0 && (n == 0 || lookup[m][n - 1] < lookup[m - 1][n])) {
            genDiff(X, Y, m - 1, n, lookup);
            System.out.print(" -" + X.charAt(m - 1));
            addToOperations(operations.delete, X.charAt(m - 1));
        }

        return diffOperations;
    }

    public String minDiff(String s1, String s2) {
        var diff1Count = 0;
        var diff2Count = 0;

        var opt1 = lcs(s2, s1);
        var opt2 = lcs(s1, s2);
        diffOperations = new ArrayList<>();
        var diff1 = genDiff(s2, s1, s2.length(), s1.length(), opt1);
        diffOperations = new ArrayList<>();
        var diff2 = genDiff(s1, s2, s1.length(), s2.length(), opt2);

        for (Operation op: diff1) {
            if (op.operation == operations.delete || op.operation == operations.insert) {
                diff1Count += op.sequence.length();
            }
        }

        for (Operation op: diff2) {
            if (op.operation == operations.delete || op.operation == operations.insert) {
                diff2Count += op.sequence.length();
            }
        }

        return diff1Count > diff2Count ? operationsToHtml(diff2) : operationsToHtml(diff1);

    }

    public String genRegexParsable(String s1, String s2) {
        String res = "";
        var opt = lcs(s1, s2);
        diffOperations = new ArrayList<>();
        ArrayList<Operation> operations = genDiff(s1, s2, s1.length(), s2.length(), opt);

        if (operations.size() == 0) return "";

        for (Operation operation : operations) {
            res += "@" + operation.operation + ":" + operation.sequence + "@";
        }

        return res;
    }


    public String genHTML(String s1, String s2) {
        var opt = lcs(s1, s2);
        diffOperations = new ArrayList<>();
        ArrayList<Operation> operations = genDiff(s1, s2, s1.length(), s2.length(), opt);
        if (operations.size() == 0) return "";

        return operationsToHtml(operations);
    }

    private String operationsToHtml(ArrayList<Operation> operations) {
        String res = "";

        for (Operation operation : operations) {
            res += "<span class='"+ operation.operation + "'>" + operation.sequence + "</span>";
        }

        return res;
    }

}
