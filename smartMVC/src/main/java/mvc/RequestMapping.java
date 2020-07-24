package mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * 标注在控制器方法上
 * 用途将请求URL地址映射到方法上
 */
public @interface RequestMapping {
    //为注解定义参数
    public String value();
}
