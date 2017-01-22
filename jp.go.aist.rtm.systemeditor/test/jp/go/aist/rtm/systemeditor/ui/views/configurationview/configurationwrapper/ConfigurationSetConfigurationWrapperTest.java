package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock.ConfigurationSetMock;

public class ConfigurationSetConfigurationWrapperTest {

	@Test
	public void testIsSecret() throws Exception {
		ConfigurationSetMock cs = new ConfigurationSetMock();
		ConfigurationSetConfigurationWrapper cw;
		cw = new ConfigurationSetConfigurationWrapper(cs, "key");
		assertFalse(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, "_key");
		assertFalse(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, "__key");
		assertTrue(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, "___key");
		assertTrue(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, "key_");
		assertFalse(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, "key__");
		assertFalse(cw.isSecret());
		cw = new ConfigurationSetConfigurationWrapper(cs, null);
		assertFalse(cw.isSecret());
	}

}
