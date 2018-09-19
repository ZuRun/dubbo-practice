package cn.zull.study.test.proxy.cglib;

import cn.zull.study.test.proxy.SampleClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zurun
 * @date 2018/9/19 14:34:49
 */
public class CGLIBProxy implements MethodInterceptor {

    private Object obj;

    public Object createObject(Object target) {
        this.obj = target;
        //加载需要创建子类的类
        Enhancer hancer = new Enhancer();
        //设置代理目标
        hancer.setSuperclass(this.obj.getClass());
        //设置回调
        hancer.setCallback(this);

        hancer.setClassLoader(target.getClass().getClassLoader());
        //返回子类对象
        return hancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        if("send".equals(method.getName())){

        }
        System.out.println("拦截前，做些事情");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("拦截后，再做些事情");
        return result;
    }

    public static void main(String[] args) {
        SampleClass sampleClass = new SampleClass();
        SampleClass sampleProxy = (SampleClass) new CGLIBProxy().createObject(sampleClass);
        sampleProxy.print();
    }
}
