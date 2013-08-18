package de.ga_ap.cirrus.newcloud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CanvasCloud implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {

		final String[] words = { "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine", "ten", "eleven", "twelve", "13" };

		final TheCloud cloud = new TheCloud(words);
		RootPanel.get().add(cloud);

	}
}
