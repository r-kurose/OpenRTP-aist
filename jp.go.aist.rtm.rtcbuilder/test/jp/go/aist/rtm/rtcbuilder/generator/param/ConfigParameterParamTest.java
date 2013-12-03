package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ConfigParameterParamTest extends
		ParamTestCase<ConfigParameterParam> {

	ConfigParameterParam cpp;

	@Override
	protected void setUp() throws Exception {
		cpp = new ConfigParameterParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(cpp, new UpdateChecker() {
			@Override
			void execute1(ConfigParameterParam e) {
				e.setConfigName("name1");
			}

			@Override
			void execute2(ConfigParameterParam e) {
				e.setConfigName("name2");
			}
		});
		//
		assertUpdated(cpp, new UpdateChecker() {
			@Override
			void execute1(ConfigParameterParam e) {
				e.setDefaultVal("value1");
			}

			@Override
			void execute2(ConfigParameterParam e) {
				e.setDefaultVal("value2");
			}
		});
	}

}
