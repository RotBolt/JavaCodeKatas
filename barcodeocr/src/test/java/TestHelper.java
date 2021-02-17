import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

public class TestHelper {
    public static final char[][] one_matrix={
            {' ',' ',' '},
            {' ',' ','|'},
            {' ',' ','|'}
    };

    public static final char[][] two_matrix={
            {' ','_',' '},
            {' ','_','|'},
            {'|','_',' '}
    };

    public static final char[][] three_matrix={
            {' ','_',' '},
            {' ','_','|'},
            {' ','_','|'}
    };

    public static final char[][] four_matrix={
            {' ',' ',' '},
            {'|','_','|'},
            {' ',' ','|'}
    };

    public static final char[][] five_matrix={
            {' ','_',' '},
            {'|','_',' '},
            {' ','_','|'}
    };

    public static final char[][] six_matrix={
            {' ','_',' '},
            {'|','_',' '},
            {'|','_','|'}
    };

    public static final char[][] seven_matrix={
            {' ','_',' '},
            {' ',' ','|'},
            {' ',' ','|'}
    };

    public static final char [][] eight_matrix={
            {' ','_',' '},
            {'|','_','|'},
            {'|','_','|'}
    };

    public static final char [][] nine_matrix={
            {' ','_',' '},
            {'|','_','|'},
            {' ','_','|'}
    };

    public static final char [][] zero_matrix={
            {' ','_',' '},
            {'|',' ','|'},
            {'|','_','|'}
    };

    private static final String testFileName= "AccountNumbers";

    public  File getTestFile() throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(testFileName);
        if (resource == null){
            throw new FileNotFoundException("Test file "+testFileName+" not found");
        }
        return new File(resource.toURI());
    }
}
