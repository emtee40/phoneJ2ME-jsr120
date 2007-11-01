/*
 *
 *
 * Copyright  1990-2006 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version
 * 2 only, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included at /legal/license.txt).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa
 * Clara, CA 95054 or visit www.sun.com if you need additional
 * information or have any questions.
 */

package com.sun.midp.wma;

import com.sun.midp.push.reservation.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import javax.microedition.io.ConnectionNotFoundException;

import com.sun.j2me.security.AccessControlContext;

import com.sun.j2me.app.AppPackage;
import com.sun.j2me.security.WMAPermission;

/**
 * Implementation of push behaviour.
 */
public class ProtocolPushImpl implements ProtocolFactory {

    static void checkValidFilter(String filter) {
	if (filter == null) {
	    throw new IllegalArgumentException("invalid filter");
        }
        if (filter.length() == 0) {
            throw new IllegalArgumentException("invalid filter");
        }
	for (int i=0; i<filter.length(); i++) {
            char ch = filter.charAt(i);
            if (!Character.isDigit(ch) && ch != '*' && ch != '?') {
                throw new IllegalArgumentException("invalid filter");
            }
        }
    }

    public ReservationDescriptor createDescriptor(
            String protocol, String targetAndParams, 
            String filter, AccessControlContext context) {

        context.checkPermission(WMAPermission.SMS_SERVER.getName(), "sms:open");
        checkValidFilter(filter);
        return new ReservationDescriptorImpl(protocol, targetAndParams, 
					 filter, context);
    }
}
