package brightspark.sparkshammers.customTools;

public class RegisterToolException extends RuntimeException
{
    public RegisterToolException(String message, Object... args)
    {
        super(String.format(message, args));
    }
}
