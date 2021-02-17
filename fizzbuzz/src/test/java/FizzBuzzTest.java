import org.junit.jupiter.api.Test;
import sun.text.resources.zh.FormatData_zh;

public class FizzBuzzTest {

    @Test
    public void given_1_to_100_contains_fizz(){
        FizzBuzz testDouble = new FizzBuzz();
        assert(testDouble.getFizzBuzzList(1,100).contains("fizz"));
    }

    @Test
    public void given_1_to_100_contains_buzz(){
        FizzBuzz testDouble = new FizzBuzz();
        assert(testDouble.getFizzBuzzList(1,100).contains("buzz"));
    }

    @Test
    public void given_1_to_100_contains_fizzbuzz(){
        FizzBuzz testDouble = new FizzBuzz();
        assert(testDouble.getFizzBuzzList(1,100).contains("fizzbuzz"));
    }

}
