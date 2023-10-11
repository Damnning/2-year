public class SyntaxException extends Exception{
    private int symbol;
    public SyntaxException( int symbol){
        this.symbol = symbol;
    }
    public String getDetails(){
        return "Invalid syntax at " + symbol;
    }
}
