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

    private String code;

    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
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
}
