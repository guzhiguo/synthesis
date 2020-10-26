

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: bill
 * @create: 2020/10/26 22:51
 */
public class arrayListVSLinkList {

    private static final String DATA="DUMMY DATA";
    private static final int MAX_CAPACITY = 1_000_000;
    private static final int MAX_ITERATIONS = 10;

    private static void test(List<String> list){
        for (int i=0;i<MAX_CAPACITY;i++){
            list.add(DATA);
        }
    }

    private static void ArrayListTest(int iterations){
        for (int i=0;i<iterations;i++){
            final List<String> list = new ArrayList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.printf(String.valueOf(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        }
    }

    private static void LinkListTest(int iterations){
        for (int i=0;i<iterations;i++){
            final List<String> list = new LinkedList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.printf(String.valueOf(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        }
    }

    public static void main(String[] args) {
        ArrayListTest(MAX_ITERATIONS);
        System.out.printf(Strings.repeat("#",100));
        LinkListTest(MAX_ITERATIONS);
    }


}
