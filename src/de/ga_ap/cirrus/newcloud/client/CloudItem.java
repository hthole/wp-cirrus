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

public class CloudItem {

	String text;
	String fontFamily; // Arial, Times, ...
	String fontColor;
	String fontStyle; // normal, italic, ...
	String fontWeight; // bold, normal, ...
	double theta;
	double phi;
	double x;
	double y;
	double z;
	double offsetHeight;
	double offsetWeight;
	double origFontSize;
}
