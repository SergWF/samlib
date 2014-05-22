package my.wf.samlib.core.model.extender;

import my.wf.samlib.core.dataextract.DataFieldExtractor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Readable {
    String name();
    Class<? extends DataFieldExtractor> extractorClass() default DataFieldExtractor.class;
}
