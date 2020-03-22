package tests.jUnit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Utils {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("init")
    @Test
    public @interface InitTest {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("regression")
    public @interface Regression {
    }
}
