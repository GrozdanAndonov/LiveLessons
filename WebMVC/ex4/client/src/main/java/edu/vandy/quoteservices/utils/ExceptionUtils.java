package edu.vandy.quoteservices.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ExceptionUtils {
    /**
     * .forEach(rethrowConsumer(name ->
     * System.out.println(Class.forName(name)))); or
     * .forEach(rethrowConsumer(ClassNameUtil::println));
     */
    public static <T> Consumer<T> rethrowConsumer(Consumer_WithExceptions<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    /**
     * .map(rethrowFunction(name -> Class.forName(name))) or
     * .map(rethrowFunction(Class::forName))
     */
    public static <T, R> Function<T, R> rethrowFunction(Function_WithExceptions<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * rethrowSupplier(() -> new StringJoiner(new String(new byte[]{77, 97, 114,
     * 107}, "UTF-8"))),
     */
    public static <T> Supplier<T> rethrowSupplier(Supplier_WithExceptions<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    /**
     * Calls the passed {@link Supplier} and either returns the
     * supplier result or else catches a checked exception and
     * casts and throws that exception as unchecked.
     *
     * Example:
     * supplyOrThrow(() -> executor.take())
     *
     */
    /**
     * @param supplier Any supplier
     * @param <T>      The supplier type.
     * @return a supplied value of type T
     * @throws RuntimeException (unchecked)
     */
    public static <T> T supplyOrThrow(Supplier_WithExceptions<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * uncheck(() -> Class.forName("xxx"));
     */
    public static void uncheck(Runnable_WithExceptions t) {
        try {
            t.accept();
        } catch (Exception exception) {
            throwAsUnchecked(exception);
        }
    }

    /**
     * uncheck(() -> Class.forName("xxx"));
     */
    public static <R> R uncheck(Supplier_WithExceptions<R> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * uncheck(Class::forName, "xxx");
     */
    public static <T, R> R uncheck(Function_WithExceptions<T, R> function,
                                   T t) {
        try {
            return function.apply(t);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAsUnchecked(Exception exception)
        throws E {
        throw (E) exception;
    }

    @SuppressWarnings({"unchecked", "RedundantThrows"})
    private static <E extends Throwable> E castToUncheckedException(Exception exception)
        throws E {
        return (E) exception;
    }

    @FunctionalInterface
    public interface Consumer_WithExceptions<T> {

        void accept(T t) throws Exception;
    }

    @FunctionalInterface
    public interface Function_WithExceptions<T, R> {

        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    public interface Supplier_WithExceptions<T> {

        T get() throws Exception;
    }

    @FunctionalInterface
    public interface Runnable_WithExceptions {

        void accept() throws Exception;
    }
}
