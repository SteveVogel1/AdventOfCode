package twentythree.d5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DayFiveTest {

    @Test
    void test1(){

        DayFive d5 = new DayFive();

        List<IRange> list = new ArrayList<>();
        list.add(new RangeFix(5L, 25L));
        List<List<Converter>> converterList = new ArrayList<>();
        List<Converter> converters = new ArrayList<>();
        converters.add(new Converter("10", "10", "5"));
        converterList.add(converters);
        Stack<IRange> res = d5.getConvertedStack(list, converterList);


        assertEquals(3, res.size());
        IRange r3 = res.pop();
        IRange r2 = res.pop();
        IRange r1 = res.pop();
        assertEquals(5, r1.getStart());
        assertEquals(9, r1.getEnd());

//        IRange r2 = res.pop();
        assertEquals(10, r2.getStart());
        assertEquals(14, r2.getEnd());

//        IRange r3 = res.pop();
        assertEquals(15, r3.getStart());
        assertEquals(25, r3.getEnd());


    }

}