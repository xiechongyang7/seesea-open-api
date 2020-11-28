//package com.seesea.seeseacommon.aspect;
//
//import com.seesea.seeseacommon.util.JsonUtil;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Description 参数拦截器 如果服务想使用应在@Configuration配置下的类中注入bean
// * @Since JDK1.8
// * @Createtime 2018/10/2 下午 6:20
// * @Author xiechongyang
// */
//@Aspect
//public class ControllerIntercept extends BaseController {
//
//    @Value("${spring.application.name}")
//    private String serviceName;
//
//    //    execution(* set*(..))
//    @Pointcut("execution(public * com.seesea.*.controller..*.*(..))")
//    public void pointCut() {
//
//    }
//
//    // @Before("pointCut()")
//    // public void before() {
//    // log.info("被拦截方法调用之前调用此方法，输出此语句");
//    // }
//    //
//    // @After("pointCut()")
//    // public void after() {
//    //        Method method = methodSignature.getMethod();
////        Annotation[] annotations = method.getAnnotations();
//    // logger.info("被拦截方法调用之后调用此方法，输出此语句");
//    // }
//
//    @Around("pointCut()")
//    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
//        //类名
//        String className = pjp.getTarget().getClass().getName();
//        System.out.println(className);
//        //方法中参数
//        Object[] args = pjp.getArgs();
//        //获取方法的注释
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        Method method = methodSignature.getMethod();
//        Annotation annotations = method.getAnnotation(PostMapping.class);
//
//        String reqId = "";
//
//        if (annotations != null) {
//            reqId = ((ReqVoCommon) args[0]).getReqId();
//            Map<String, String> resultMap = new HashMap<>();
//            for (int i = 0; i < args.length; i++) {
//                Object obj = args[i];
//                resultMap = CheckUtil.checking(obj);
//            }
//            logInfo(reqId, serviceName + "请求报文", JsonUtil.objToJson(args));
//            if ("true".equals(resultMap.get("paramError"))) {
//                throw new BizException(ResultCode.ER_9003, String.format(ResultCode.ER_9003_MSG, resultMap.get("errorField")));
//            }
//        } else {
//            String log = "";
//            for (Object a : args) {
//                log += a;
//            }
//            logInfo(reqId, serviceName + "请求报文", log);
//        }
//        //此处拦截的方法返回值，如果不执行此方法，则不会执行被拦截的方法，利用环绕方法可以很好的做好权限管理
//        Object obj;
//        obj = pjp.proceed();
//        Object logObj = obj;
//        if (obj != null) {
//            if (obj instanceof List && ((List) obj).size() > 10) {
//                logObj = ((List) obj).size();
//            }
//        }
//
//        logInfo(reqId, serviceName + "返回报文", JsonUtil.objToJson(logObj));
//        return obj;
//    }
//}