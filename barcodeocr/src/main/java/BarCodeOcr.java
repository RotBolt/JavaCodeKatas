import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BarCodeOcr {

    private final char[][] one_matrix={
            {' ',' ',' '},
            {' ',' ','|'},
            {' ',' ','|'}
    };

    private final char[][] two_matrix={
            {' ','_',' '},
            {' ','_','|'},
            {'|','_',' '}
    };

    private final char[][] three_matrix={
            {' ','_',' '},
            {' ','_','|'},
            {' ','_','|'}
    };

    char[][] four_matrix={
            {' ',' ',' '},
            {'|','_','|'},
            {' ',' ','|'}
    };

    private final char[][] five_matrix={
            {' ','_',' '},
            {'|','_',' '},
            {' ','_','|'}
    };

    private final char[][] six_matrix={
            {' ','_',' '},
            {'|','_',' '},
            {'|','_','|'}
    };

    private final char[][] seven_matrix={
            {' ','_',' '},
            {' ',' ','|'},
            {' ',' ','|'}
    };

    private final char [][] eight_matrix={
            {' ','_',' '},
            {'|','_','|'},
            {'|','_','|'}
    };

    private final char [][] nine_matrix={
            {' ','_',' '},
            {'|','_','|'},
            {' ','_','|'}
    };

    private final char [][] zero_matrix={
            {' ','_',' '},
            {'|',' ','|'},
            {'|','_','|'}
    };

    public File generateOutputFile(File inputFile, String outputFileName) throws IOException {
        List<String> accountNoList = parseListFromFile(inputFile);
        File outFile = new File(outputFileName);
        FileWriter writer = new FileWriter(outFile);
        BufferedWriter bfWriter = new BufferedWriter(writer);

        for(String accountNo:accountNoList){
            if(accountNo.contains("?")){
                accountNo+=" ILL";
            }else if (!isValidAccountNumber(accountNo)){
                accountNo+=" ERR";
            }
            bfWriter.write(accountNo);
            bfWriter.newLine();
        }

        bfWriter.close();
        writer.close();
        return outFile;
    }

    public List<String> parseListFromFile(File file) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String[] accountNumbermatrix = new String[4];
        int index=0;
        String line;
        while ((line = br.readLine()) != null){
            accountNumbermatrix[index++] = line;
            if (line.isEmpty()){
//                for (String numbermatrix : accountNumbermatrix) {
//                    System.out.println(numbermatrix);
//                }
                String accountNumber = parseAccountNumber(accountNumbermatrix);
                list.add(accountNumber);
                index = 0;
            }
        }
        br.close();
        fileReader.close();
        return list;
    }

    private String parseAccountNumber(String[] accountNumberMatrix){
        char[][] numMatrix = new char[3][3];
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<accountNumberMatrix[0].length();i+=3){
            String firstLine = accountNumberMatrix[0];
            numMatrix[0][i%3]=firstLine.charAt(i);
            numMatrix[0][(i+1)%3]=firstLine.charAt(i+1);
            if (i+2 < firstLine.length()) {
                numMatrix[0][(i + 2) % 3] = firstLine.charAt(i + 2);
            }else {
                numMatrix[0][(i + 2) % 3]=' ';
            }

            String secondLine = accountNumberMatrix[1];
            numMatrix[1][i%3]=secondLine.charAt(i);
            numMatrix[1][(i+1)%3]=secondLine.charAt(i+1);
            if (i+2 < secondLine.length()) {
                numMatrix[1][(i + 2) % 3] = secondLine.charAt(i + 2);
            }else {
                numMatrix[1][(i + 2) % 3] = ' ';
            }

            String thirdLine = accountNumberMatrix[2];
            numMatrix[2][i%3]=thirdLine.charAt(i);
            numMatrix[2][(i+1)%3]=thirdLine.charAt(i+1);
            if (i+2 < thirdLine.length()) {
                numMatrix[2][(i + 2) % 3] = thirdLine.charAt(i + 2);
            }else {
                numMatrix[2][(i + 2) % 3] = ' ';
            }

            int number = parseNumberFromMatrix(numMatrix);
            if (number != -1){
                sb.append(number);
            }else {
                sb.append('?');
            }
        }
        return sb.toString();
    }



    public int parseNumberFromMatrix(char[][] matrix){
        if (matrixCompare(matrix,zero_matrix)){
            return 0;
        }else if(matrixCompare(matrix,one_matrix)){
            return 1;
        }else if(matrixCompare(matrix,two_matrix)){
            return 2;
        }else if(matrixCompare(matrix,three_matrix)){
            return 3;
        }else if(matrixCompare(matrix,four_matrix)){
            return 4;
        }else if(matrixCompare(matrix,five_matrix)){
            return 5;
        }else if (matrixCompare(matrix,six_matrix)){
            return 6;
        }else if(matrixCompare(matrix,seven_matrix)){
            return 7;
        }else if (matrixCompare(matrix,eight_matrix)){
            return 8;
        }else if(matrixCompare(matrix,nine_matrix)){
            return 9;
        }else {
            return -1;
        }
    }

    public boolean matrixCompare(char[][] matrix1, char[][] matrix2){
        if (matrix1.length != matrix2.length){
            return false;
        }

        if (matrix1[0].length != matrix2[0].length){
            return false;
        }

        for (int i=0;i<matrix1.length;i++){
            for (int j=0;j<matrix1[0].length;j++){
                if (matrix1[i][j] != matrix2[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidAccountNumber(String accountNumber){
        try{
            int accountNoInt = Integer.parseInt(accountNumber);
            int multiplier = 1;
            int sum = 0;
            while (accountNoInt != 0){
                int dig = accountNoInt%10;
                sum+=(dig*multiplier);
                multiplier++;
                accountNoInt/=10;
            }
            return sum%11 == 0;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
}
