package core.nmvc;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import core.mvc.Controller;
import org.reflections.ReflectionUtils;

public class AnnotationHandlerMapping implements HandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() throws InstantiationException, IllegalAccessException {
        List<Object> controllerList = ControllerScanner.controllerScan(this.basePackage);
        for(Object controller: controllerList){
            Set<Method> methodSet
                    = ReflectionUtils.getAllMethods(controller.getClass(),ReflectionUtils.withAnnotation(RequestMapping.class));

            for(Method method: methodSet){
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                handlerExecutions.put(createHandlerKey(rm), new HandlerExecution(controller, method));
            }
        }
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }

    private HandlerKey createHandlerKey(RequestMapping rm){
        return new HandlerKey(rm.value(), rm.method());
    }
}
