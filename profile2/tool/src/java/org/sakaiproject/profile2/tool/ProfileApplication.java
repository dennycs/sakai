/**
 * Copyright (c) 2008-2012 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.profile2.tool;

import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.sakaiproject.profile2.tool.pages.MyProfile;
import org.sakaiproject.util.ResourceLoader;

public class ProfileApplication extends WebApplication {    

	protected void init(){
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getResourceSettings().setThrowExceptionOnMissingResource(false);
		getMarkupSettings().setStripWicketTags(true);
		getMarkupSettings().setDefaultBeforeDisabledLink(null);
		getMarkupSettings().setDefaultAfterDisabledLink(null);

		// On Wicket session timeout, redirect to main page
		getApplicationSettings().setPageExpiredErrorPage(MyProfile.class);
		getApplicationSettings().setAccessDeniedPage(MyProfile.class);

		// Custom resource loader since our properties are not in the default location
		getResourceSettings().getStringResourceLoaders().add(new ProfileStringResourceLoader());		


		getRequestCycleListeners().add(new IRequestCycleListener() {

			public void onBeginRequest()
			{
				// optionally do something at the beginning of the request
			}

			public void onEndRequest()
			{
				// optionally do something at the end of the request
			}

			public IRequestHandler onException(RequestCycle cycle, Exception ex)
			{
				// optionally do something here when there's an exception

				// then, return the appropriate IRequestHandler, or "null"
				// to let another listener handle the exception
				ex.printStackTrace();
				return null;
			}

			@Override
			public void onBeginRequest(RequestCycle arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDetach(RequestCycle arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onEndRequest(RequestCycle arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onExceptionRequestHandlerResolved(
					RequestCycle arg0, IRequestHandler arg1, Exception arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onRequestHandlerExecuted(RequestCycle arg0,
					IRequestHandler arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onRequestHandlerResolved(RequestCycle arg0,
					IRequestHandler arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onRequestHandlerScheduled(RequestCycle arg0,
					IRequestHandler arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUrlMapped(RequestCycle arg0,
					IRequestHandler arg1, Url arg2) {
				// TODO Auto-generated method stub

			}
		});

	}



	//Custom resource loader
	private static class ProfileStringResourceLoader implements IStringResourceLoader {

		private ResourceLoader messages = new ResourceLoader("ProfileApplication");

		@Override
		public String loadStringResource(Component component, String key, Locale locale, String style, String variation) {
			return messages.getString(key, key);
		}

		@Override
		public String loadStringResource(Class clazz, String key, Locale locale, String style, String variation) {
			messages.setContextLocale(locale);
			return messages.getString(key, key);
		}


	}


	public ProfileApplication() {
	}

	//setup homepage		
	public Class<Dispatcher> getHomePage() {
		return Dispatcher.class;
	}

}
