package com.seesea.seeseabilling.filter;

import com.seesea.seeseacommon.base.Result;
import com.seesea.seeseacommon.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description 请求参数过滤器 如果服务想使用应在@Configuration配置下的类中注入bean
 * @Since JDK1.8
 * @Createtime 2018/10/3 下午 2:15
 * @Author xiechongyang
 */

@Component
@WebFilter(urlPatterns = "/*")
public class ParamFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Map ParamMap = new HashMap(/* request.getParameterMap() */);

        System.out.println("请求地址:::::" + request.getRemoteAddr() + "请求主机::::" + request.getRemoteHost() + "端口::::" + request.getRemotePort());
        String httpMethod = request.getMethod();
        InputStream is = request.getInputStream();
        String contentType = request.getContentType();
//        if(is!=null&&"POST".equalsIgnoreCase(httpMethod) && "APPLICATION/JSON".equalsIgnoreCase(contentType)){

        if (is != null && "POST".equalsIgnoreCase(httpMethod) && contentType.toUpperCase().contains("APPLICATION/JSON")) {


            Result result = new Result();
            try {

                String body = IOUtils.toString(is, "UTF-8");
                ParamMap = JsonUtil.jsonToObj(body, Map.class);
            } catch (Exception e) {
                result.setCode("9900");
                result.setMsg("zz");
                servletResponse.setContentType("text/html; charset=UTF-8");
                PrintWriter out = servletResponse.getWriter();
                out.println(JsonUtil.objToJson(result));
                out.flush();
                out.close();
                return;

            }
            ParamFilterWrapper wrapRequest = new ParamFilterWrapper(request, ParamMap);
            filterChain.doFilter(wrapRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
