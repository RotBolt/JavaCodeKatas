public class FizzBuzz {

    public String getFizzBuzzList(int start, int end){
        StringBuilder list = new StringBuilder();
        String str="";
        for (int i=start;i<=end;i++){
            if (i%3==0) str+="fizz";
            if(i%5==0) str+="buzz";
            if (str.isEmpty()){
                list.append(i).append("\n");
            }else {
                list.append(str).append("\n");
                str="";
            }
        }
        return list.toString();
    }
}
