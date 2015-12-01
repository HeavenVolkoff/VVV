package Server.Utility;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ValidatorFactory {
    private static ValidatorFactory instance;
    private static final Object mutex = new Object();

    public static final class Validator implements javax.validation.Validator, AutoCloseable{
        private javax.validation.Validator validator;
        private UUID id;

        private Validator(javax.validation.Validator validator, UUID id){
            this.validator = validator;
            this.id = id;
        }

        @Override
        public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {return validator.validate(object, groups);}
        @Override
        public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {return validator.validateProperty(object, propertyName, groups);}
        @Override
        public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {return validator.validateValue(beanType, propertyName, value, groups);}
        @Override
        public BeanDescriptor getConstraintsForClass(Class<?> clazz) {return validator.getConstraintsForClass(clazz);}
        @Override
        public <T> T unwrap(Class<T> type) {return validator.unwrap(type);}
        @Override
        public ExecutableValidator forExecutables() {return validator.forExecutables();}
        @Override
        public void close() throws Exception{
            if(!instance.closing.get()){
                instance.idleConnections.addFirst(instance.busyConnections.remove(this.id));
            }
        }
    }

    public static Validator getValidator() throws FactoryException {
        if(instance == null){
            synchronized (mutex){
                if(instance == null){
                    instance = new ValidatorFactory();
                }
            }
        }

        if(instance.closing.get()){
            throw new FactoryException("Shutting Down");
        }

        javax.validation.Validator reserved = instance.idleConnections.pollLast();
        UUID id = UUID.randomUUID();

        if(reserved == null){
            reserved = instance.factory.getValidator();
        }

        instance.busyConnections.put(id, reserved);

        return new Validator(reserved, id);
    }

    public static void closeAllConnections(){
        if(instance.closing.compareAndSet(false, true)) {
            instance.busyConnections.clear();
            instance.idleConnections.clear();
        }
    }

    private ConcurrentHashMap<UUID, javax.validation.Validator> busyConnections;
    private ConcurrentLinkedDeque<javax.validation.Validator> idleConnections;
    private AtomicBoolean closing;
    private javax.validation.ValidatorFactory factory;

    private ValidatorFactory(){
        this.busyConnections = new ConcurrentHashMap<>();
        this.idleConnections = new ConcurrentLinkedDeque<>();
        this.factory = Validation.buildDefaultValidatorFactory();
        this.closing = new AtomicBoolean();
    }
}
