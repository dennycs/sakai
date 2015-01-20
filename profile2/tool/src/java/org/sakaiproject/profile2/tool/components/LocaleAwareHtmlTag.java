package org.sakaiproject.profile2.tool.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

/**
 * Tag that is Locale aware and will inject the correct language into the base HTML tag.
 * Note that the language comes from the session so this must already be configured
 * 
 * @author Steve Swinsburg (steve.swinsburg@gmail.com)
 * @see https://jira.sakaiproject.org/browse/PRFL-791
 *
 */
public class LocaleAwareHtmlTag extends WebMarkupContainer {

	private static final long serialVersionUID = 1L;

	public LocaleAwareHtmlTag(String id) { 
        super(id); 
        String language = getSession().getLocale().getLanguage(); 
        add(new AttributeModifier("lang", new Model<String>(language))); 
        add(new AttributeModifier("xml:lang", new Model<String>(language))); 
    } 

  
    
}
