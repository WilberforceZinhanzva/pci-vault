package zw.co.jugaad.pcivault.exceptions;

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message) {
        super(message);
    }
}
