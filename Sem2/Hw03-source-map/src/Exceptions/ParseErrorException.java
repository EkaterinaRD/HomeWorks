package Exceptions;

public class ParseErrorException extends Exception{

    @Override
    public void printStackTrace() {
        System.out.println("Error!");
        super.printStackTrace();
    }
}
