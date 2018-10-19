package com.royarn.mini.java8.chain;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */

/**
 * 责任链模式  --构造一个代理类来构建流程
 *  使用到了模板模式 --
 *  精髓在于每一个步骤处理结束后会返回该操作对象以供后续使用
 * @param <T>
 */
public abstract class ChainProcessing<T> {

    protected ChainProcessing<T> chainProcessing;

    public void setChainProcessing(ChainProcessing<T> chainProcessing) {
        this.chainProcessing = chainProcessing;
    }

    /**
     * 1.模板设计原则
     * 2.若后续流程没有完成，则传递结果继续处理直至没有代理实例
     * @param t
     * @return
     */
    public T handle(T t) {
        T result = handleWork(t);
        if (null != chainProcessing) {
            return chainProcessing.handle(result);
        }
        return result;
    }

    abstract T handleWork(T t);
}