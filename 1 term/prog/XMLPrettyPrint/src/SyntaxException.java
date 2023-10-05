public class SyntaxException extends Exception{
    private int row;
    private int symbol;
    public SyntaxException(int row, int symbol){
        this.row = row;
        this.symbol = symbol;
    }
    public String getDetails(){
        return "Invalid syntax at " + row + " " + symbol;
    }
}
