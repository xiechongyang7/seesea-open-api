package com.seesea.seeseabilling.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/12/14 15:40
 */
public class ParamFilterWrapper  extends HttpServletRequestWrapper {


    private Map param;

    public ParamFilterWrapper(HttpServletRequest request, Map newParams) {
        super(request);
        this.param = newParams;
    }

    public Map getParameterMap() {
        return param;
    }

    public Enumeration getParameterNames() {
        Vector l = new Vector(param.keySet());
        return l.elements();
    }

    public String[] getParameterValues(String name) {
        Object v = param.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            return (String[]) v;
        } else if (v instanceof String) {
            return new String[]{(String) v};
        } else {
            return new String[]{v.toString()};
        }
    }

    public String getParameter(String name) {
        Object v = param.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                return strArr[0];
            } else {
                return null;
            }
        } else if (v instanceof String) {
            return (String) v;
        } else {
            return v.toString();
        }
    }
}
