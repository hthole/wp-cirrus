/*
 * 	Plugin Name: WP-Cirrus
 *	Plugin URI: http://www.ga-ap.de/plugins/wp-cirrus/	
 *	Description: A 3d javascript tagcloud inspired by WP Cumulus
 *	Version: 0.7
 *	Author: Christian Kramer & Hendrik Thole
 *	Author URI: http://www.ga-ap.de
 *	
 *	Copyright 2010-2013, Christian Kramer & Hendrik Thole
 *	
 *	This file is part of WP-Cirrus Plugin.
 *	
 *	WP-Cirrus is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *   
 *	WP-Cirrus is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *   
 *	You should have received a copy of the GNU General Public License
 *	along with WP-Cirrus. If not, see <http://www.gnu.org/licenses/>.
 */

package de.ga_ap.cirrus.newcloud.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.ui.FlowPanel;

public class TheCloud extends FlowPanel {

	public TheCloud(final String[] words) {

		final Canvas canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceHeight(250);
		canvas.setCoordinateSpaceWidth(250);
		final Context2d context2d = canvas.getContext2d();

		context2d.fillText("Test", 10, 20);

		final double radius = canvas.getCanvasElement().getHeight() / 3.0;
		final int refreshrate = 100;

		final List<CloudItem> itemList = new ArrayList<CloudItem>(words.length);
		for (final String s : words) {
			final CloudItem ci = new CloudItem();
			ci.text = s;
			itemList.add(ci);
		}

		final int itemListSize = itemList.size();
		for (int i = 1; i <= itemListSize; i++) {
			final CloudItem ci = itemList.get(i - 1);
			ci.theta = Math
					.acos(-1.0 + (2.0 * (double) i - 1.0) / itemListSize);
			ci.phi = Math.sqrt(itemListSize * Math.PI) * ci.theta;

			ci.z = radius * Math.cos(ci.theta);
			ci.y = radius * Math.sin(ci.theta) * Math.cos(ci.phi);
			ci.x = radius * Math.sin(ci.theta) * Math.sin(ci.phi);

			// TODO heightOfAllItems ?!

			// TODO font size

			context2d.fillText(ci.text, ci.y, ci.z);
			System.out.println(ci.x + " " + ci.y + " " + ci.z + " - "
					+ ci.theta + " " + ci.phi);

		}

		final double ySteps = 0.06;
		final double xSteps = 0.06;

		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {

			@Override
			public boolean execute() {

				canvas.setCoordinateSpaceHeight(250);
				canvas.setCoordinateSpaceWidth(250);

				double theta = 0.1;
				double psi = 0.1;

				for (final CloudItem ci : itemList) {

					final double x, y, z;
					final double cosPsi = Math.cos(psi);
					final double sinPsi = Math.sin(psi);

					final double cosTheta = Math.cos(theta);
					final double sinTheta = Math.sin(theta);

					final double sinThetaCosPsi = sinTheta * cosPsi;
					final double sinThetaSinPsi = sinTheta * sinPsi;

					x = ci.x * cosTheta * cosPsi + ci.y * cosTheta * sinPsi
							- ci.z * sinTheta;
					y = ci.x * (-sinPsi) + ci.y * cosPsi;
					z = ci.x * sinThetaCosPsi + ci.y * sinThetaSinPsi + ci.z
							* cosTheta;
					ci.x = x;
					ci.y = y;
					ci.z = z;
					context2d.fillText(ci.text, 100 + ci.y, 100 + ci.z);

					System.out.println(ci.x + " " + ci.y + " " + ci.z + " - "
							+ ci.theta + " " + ci.phi + " " + ci.text);
				}
				theta += ySteps;
				psi += xSteps;

				if (theta > Math.PI * 2.0) {
					theta = 0.0;
				}
				if (psi > Math.PI * 2.0) {
					psi = 0.0;
				}
				return true;
			}
		}, refreshrate);

		System.out.println(radius);
		this.add(canvas);
	}
}
