/*
 * Copyright (c) 2017 Red Hat, Inc. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowplugin.simple.test;

import static com.google.common.truth.Truth.assertThat;

import javax.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.opendaylight.aaa.web.testutils.WebTestModule;
import org.opendaylight.controller.simple.InMemoryControllerModule;
import org.opendaylight.infrautils.inject.guice.GuiceClassPathBinder;
import org.opendaylight.infrautils.inject.guice.testutils.AnnotationsModule;
import org.opendaylight.infrautils.inject.guice.testutils.GuiceRule2;
import org.opendaylight.infrautils.simple.DiagStatusModule;
import org.opendaylight.infrautils.simple.ReadyModule;
import org.opendaylight.infrautils.simple.testutils.AbstractSimpleDistributionTest;
import org.opendaylight.openflowplugin.api.openflow.OpenFlowPluginProvider;
import org.opendaylight.openflowplugin.simple.OpenFlowPluginModule;
import org.opendaylight.serviceutils.simple.ServiceUtilsModule;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflow.provider.config.rev160510.OpenflowProviderConfig;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.app.forwardingrules.manager.config.rev160511.ForwardingRulesManagerConfig;

public class OpenFlowPluginModuleTest extends AbstractSimpleDistributionTest {

    private static final GuiceClassPathBinder CLASS_PATH_BINDER = new GuiceClassPathBinder("org.opendaylight");

    public @Rule GuiceRule2 guice = new GuiceRule2(new OpenFlowPluginModule(CLASS_PATH_BINDER),
            new ServiceUtilsModule(), new InMemoryControllerModule(), new DiagStatusModule(), new WebTestModule(),
            new ReadyModule(), new AnnotationsModule());

    @Inject OpenFlowPluginProvider ofpProvider;
    @Inject OpenflowProviderConfig ofpConfig;
    @Inject ForwardingRulesManagerConfig frmConfig;

    @Test public void testConfig() throws InterruptedException {
        assertThat(ofpConfig.getGlobalNotificationQuota()).named("globalNotificationQuota").isEqualTo(64000L);
        assertThat(frmConfig.getReconciliationRetryCount()).named("reconciliationRetryCount").isEqualTo(5);
    }

}
