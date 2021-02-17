import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BarCodeOcrTest {

    @Test
    public void given_9_value_matrix_should_parse_9(){
        BarCodeOcr testDouble = new BarCodeOcr();
        assert testDouble.parseNumberFromMatrix(TestHelper.nine_matrix) == 9;
    }

    @Test
    public void given_file_should_parse_account_numbers_list() throws IOException, URISyntaxException {
        BarCodeOcr testDouble = new BarCodeOcr();
        TestHelper testHelper = new TestHelper();
        List<String> accountNumbers = new ArrayList<>();
        accountNumbers.add("123456789");
        accountNumbers.add("490067715");
        accountNumbers.add("?23456789");
        List<String> parsedList = testDouble.parseListFromFile(testHelper.getTestFile());
        System.out.println(parsedList);
        assert parsedList.equals(accountNumbers);
    }

    @Test
    public void given_valid_account_number_should_pass(){
        BarCodeOcr testDouble = new BarCodeOcr();
        assert testDouble.isValidAccountNumber("123456789");
    }

    @Test
    public void given_invalid_account_number_should_report(){
        BarCodeOcr testDouble = new BarCodeOcr();
        assert !testDouble.isValidAccountNumber("490067715");
        assert !testDouble.isValidAccountNumber("?23456789");
    }

    @Test
    public void given_input_file_should_generate_output_file() throws IOException, URISyntaxException {
        BarCodeOcr testDouble = new BarCodeOcr();
        TestHelper helper = new TestHelper();
        File out = testDouble.generateOutputFile(helper.getTestFile(),"Output.txt");
        assert out.exists();

        FileReader reader = new FileReader(out);
        BufferedReader br = new BufferedReader(reader);
        String line;
        List<String> outputAccNumbers = new ArrayList<>();
        while ((line = br.readLine())!= null){
            outputAccNumbers.add(line);
        }
        br.close();
        reader.close();
        assert outputAccNumbers.size() == 3;

        List<String> targetOutput = new ArrayList<>();
        targetOutput.add("123456789");
        targetOutput.add("490067715 ERR");
        targetOutput.add("?23456789 ILL");

        assert targetOutput.equals(outputAccNumbers);
    }
}