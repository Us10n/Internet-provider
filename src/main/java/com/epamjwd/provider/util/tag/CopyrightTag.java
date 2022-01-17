package com.epamjwd.provider.util.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CopyrightTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();

    private static final String COPYRIGHT_SYMBOL = "© ";
    private static final String SPACE = " ";
    private static final String COPYRIGHT_HOLDER_STRING = "Copyright holder: ";

    private String year;
    private String copyrightHolder;

    public void setYear(String year) {
        this.year = year;
    }

    public void setCopyrightHolder(String copyrightHolder) {
        this.copyrightHolder = copyrightHolder;
    }



    @Override
    public int doStartTag() throws JspException {
        String copyrightString = new StringBuilder(COPYRIGHT_SYMBOL)
                .append(year).append(SPACE)
                .append(COPYRIGHT_HOLDER_STRING)
                .append(copyrightHolder)
                .toString();

        try {
            JspWriter out = pageContext.getOut();
            out.write(copyrightString);
        } catch (IOException e) {
            logger.error("Output stream jspwriter error", e);
            throw new JspException("Output stream jspwriter error", e);
        }

        return SKIP_BODY;
    }

}
