package tri.vo.dslearn.binaryindexedtree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    // called f(x) = (longest increasing subsequence, its frequency) that end value <= x
    // f(x) =
    //      * (1,1) if can not find any f(0),...f(x-1)
    //      * (x+1, x.fre)) if x = combine(f(0),...f(x-1)))
    // Combine(a,b) =
    //      * a if a.len > b.len
    //      * b if a.len < b.len
    //      * (a, a.fre + b.fre) if a.len == b.len
    // without any special data structure, we will have O(n^2) time complexity
    //
    // Using Binary indexed tree, say d[i] = combine(f(0), ... f(i))
    // As combine is associative, and the update is monotone => we can use the binary index tree for this case
    // Using binary index teree, we will have O(nlog(n)) time complexity


    public int findNumberOfLIS(int[] nums) {
        BitForLenFre bit = new BitForLenFre(nums);
        for (int num : nums) {
            LenFre preLenFre = bit.calculatePre(num);
            LenFre lenFre;
            if (preLenFre == null) {
                lenFre = new LenFre(1, 1);
            } else {
                lenFre = new LenFre(preLenFre.len + 1, preLenFre.fre);
            }

            bit.update(num, lenFre);
        }

        return bit.calculateFinal().fre;
    }

    class BitForLenFre {
        private int n;
        private LenFre[] lenFres;
        private Map<Integer, Integer> valueToIndex;

        BitForLenFre(int[] nums) {
            int[] uniqueSorted = Arrays.stream(nums)
                    .sorted()
                    .distinct()
                    .toArray();

            n = uniqueSorted.length;
            lenFres = new LenFre[n + 1];

            valueToIndex = new HashMap<>();
            for (int i = 0; i < uniqueSorted.length; i++) {
                valueToIndex.put(uniqueSorted[i], i + 1);
            }
        }

        LenFre calculatePre(int val) {
            int i = getIndex(val);
            return calculate(i - 1);
        }

        LenFre calculateFinal() {
            return calculate(n);
        }

        LenFre calculate(int i) {
            LenFre acc = null;

            while (i > 0) {
                LenFre cur = lenFres[i];
                acc = combine(acc, cur);
                i -= i & (-i);
            }

            return acc;
        }

        void update(int val, LenFre lenFre) {
            int i = getIndex(val);

            while (i <= n) {
                lenFres[i] = combine(lenFres[i], lenFre);

                i += i & (-i);
            }
        }

        int getIndex(int value) {
            return valueToIndex.get(value);
        }
    }

    LenFre combine(LenFre lenFre1, LenFre lenFre2) {
        if (lenFre1 == null && lenFre2 == null) {
            return null;
        }

        if (lenFre1 == null || lenFre2 == null) {
            if (lenFre1 != null) {
                return lenFre1;
            }

            return lenFre2;
        }

        if (lenFre1.len > lenFre2.len) {
            return lenFre1;
        }

        if (lenFre1.len < lenFre2.len) {
            return lenFre2;
        }

        return new LenFre(lenFre1.len, lenFre1.fre + lenFre2.fre);
    }
}

class LenFre {
    int len;
    int fre;

    LenFre(int len, int fre) {
        this.len = len;
        this.fre = fre;
    }
}



