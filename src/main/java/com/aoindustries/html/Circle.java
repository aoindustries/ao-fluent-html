/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-fluent-html.
 *
 * ao-fluent-html is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-fluent-html is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.html;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Minimal implementation of a circle class for similarity to {@link Rectangle}.
 * Methods are implemented via {@link Ellipse2D}.
 *
 * @see Area.Shape#CIRCLE
 *
 * @author  AO Industries, Inc.
 */
public class Circle implements Shape, Serializable {

	private final int x, y, radius;

	private final Ellipse2D ellipse;

	private static final long serialVersionUID = 1L;

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		int ex = x - radius;
		int ey = y - radius;
		int ewh = radius * 2;
		// If in a small'ish range, use the Float implementation for speed, as rounding should still be well in range
		if(
			ex >= Short.MIN_VALUE && ex <= Short.MAX_VALUE
			&& ey >= Short.MIN_VALUE && ey <= Short.MAX_VALUE
			&& ewh >= Short.MIN_VALUE && ewh <= Short.MAX_VALUE
		) {
			this.ellipse = new Ellipse2D.Float(ex, ey, ewh, ewh);
		} else {
			this.ellipse = new Ellipse2D.Double(ex, ey, ewh, ewh);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public Ellipse2D getEllipse() {
		return ellipse;
	}

	@Override
	public Rectangle getBounds() {
		return ellipse.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return ellipse.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return ellipse.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return ellipse.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return ellipse.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return ellipse.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return ellipse.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return ellipse.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return ellipse.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return ellipse.getPathIterator(at, flatness);
	}
}
