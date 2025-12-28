package tri.vo.dslearn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class BinaryIndexedTree<T> {

    private final List<T> bits;
    private final BinaryOperator<T> func;
    private final T identifyValue;
    private final BinaryOperator<T> invertFunc;

    BinaryIndexedTree(
            List<T> input, BinaryOperator<T> func, T identifyValue, BinaryOperator<T> invertFunc) {
        this.func = func;
        this.identifyValue = identifyValue;
        this.invertFunc = invertFunc;
        bits = new ArrayList<>(input.size());
        for (int i = 0; i < input.size(); i++) {
            bits.add(identifyValue);
        }
        for (int i = 0; i < input.size(); i++) {
            bits.set(i, func.apply(bits.get(i), input.get(i)));
            int nextIdx = i | (i + 1);
            if (nextIdx < input.size()) {
                bits.set(nextIdx, func.apply(bits.get(nextIdx), bits.get(i)));
            }
        }
    }

    void update(int i, T oldVal, T newVal) {
        while (i < bits.size()) {
            T removedValue = invertFunc.apply(bits.get(i), oldVal);
            T updatedValue = func.apply(removedValue, newVal);
            bits.set(i, updatedValue);
            i = i | (i + 1);
        }
    }

    T calculate(int i) {
        T result = identifyValue;
        while (i >= 0) {
            result = func.apply(result, bits.get(i));
            i = (i & (i + 1)) - 1;
        }
        return result;
    }

    T calculateRange(int left, int right) {
        if (left == 0) {
            return calculate(right);
        }
        return invertFunc.apply(calculate(right), calculate(left - 1));
    }
}
