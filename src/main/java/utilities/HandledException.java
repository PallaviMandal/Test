package utilities;

public class HandledException extends Exception {

    public HandledException(String message, Exception e) {
        System.out.println(message);
        System.out.println(e.fillInStackTrace());

    }

}
