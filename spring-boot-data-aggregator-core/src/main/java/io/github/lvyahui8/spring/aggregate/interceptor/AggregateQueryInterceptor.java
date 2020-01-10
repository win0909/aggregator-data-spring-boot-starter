package io.github.lvyahui8.spring.aggregate.interceptor;

import io.github.lvyahui8.spring.aggregate.context.AggregationContext;
import io.github.lvyahui8.spring.aggregate.model.DataProvideDefinition;

/**
 * 查询过程中各个生命周期的拦截处理
 *
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/7/21 22:28
 */
public interface AggregateQueryInterceptor {
    /**
     * 查询正常提交, Context已经创建
     *
     * @param aggregationContext 查询上下文
     * @return 返回为true才继续执行
     */
    boolean querySubmitted(AggregationContext aggregationContext) ;

    /**
     * 每个Provider方法执行前, 将调用此方法. 存在并发调用
     *
     * @param aggregationContext 查询上下文
     * @param provideDefinition 将被执行的Provider
     */
    void queryBefore(AggregationContext aggregationContext, DataProvideDefinition provideDefinition);

    /**
     * 每个Provider方法执行成功之后, 调用此方法. 存在并发调用
     *
     * @param aggregationContext 查询上下文
     * @param provideDefinition 被执行的Provider
     * @param result 查询结果
     * @return 返回结果, 如不修改不, 请直接返回参数中的result
     */
    Object queryAfter(AggregationContext aggregationContext, DataProvideDefinition provideDefinition, Object result);

    /**
     * 每个Provider执行时, 如果抛出异常, 将调用此方法. 存在并发调用
     *
     * @param aggregationContext  查询上下文
     * @param provideDefinition 被执行的Provider
     * @param e Provider抛出的异常
     */
    void exceptionHandle(AggregationContext aggregationContext, DataProvideDefinition provideDefinition, Exception e);

    /**
     * 一次查询全部完成.
     *
     * @param aggregationContext 查询上下文
     */
    void queryFinished(AggregationContext aggregationContext);
}
