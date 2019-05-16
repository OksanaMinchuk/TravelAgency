package by.epam.javatr.minchuk.project.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Class {@code EncodingFilter}
 * Filter intercepts the request and changes its encoding
 * for correct interpretation of Cyrillic characters transmitted with the request
 *
 * @author Oksana Minchuk
 * @version 1.0 09/05/2019
 */

public class EncodingFilter implements Filter {

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String code;

    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }

//    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";
//
//    private static final String ENCODING_DEFAULT = "UTF-8";
//
//    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
//
//    private String encoding;
//
//    public void destroy(){
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws ServletException, IOException{
//        String contentType = request.getContentType();
//        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
//            request.setCharacterEncoding(encoding);
//        chain.doFilter(request, response);
//    }
//
//    public void init(FilterConfig config) throws ServletException{
//        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
//        if (encoding == null)
//            encoding = ENCODING_DEFAULT;
//    }
}
