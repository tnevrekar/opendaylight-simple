/*
 * Copyright © 2018 Red Hat, Inc. and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.serviceutils.simple;

import com.google.inject.AbstractModule;

public class ServiceUtilsModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ServiceRecoveryModule());
        install(new UpgradeModule());
    }
}
