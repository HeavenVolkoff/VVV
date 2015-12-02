package Server.Utility;

import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidatorFactory {
    private static Validator validator;
    private static final Object mutex = new Object();

    public static Validator getValidator() throws FactoryException {
        if(validator == null){
            synchronized (mutex){
                if(validator == null){
                    validator = Validation.buildDefaultValidatorFactory().getValidator();
                }
            }
        }

        return validator;
    }

    private ValidatorFactory(){}
}
