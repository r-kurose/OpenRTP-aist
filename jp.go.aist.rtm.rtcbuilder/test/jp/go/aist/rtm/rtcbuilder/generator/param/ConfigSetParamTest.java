package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ConfigSetParamTest extends ParamTestCase<ConfigSetParam> {

	ConfigSetParam csp;

	@Override
	protected void setUp() throws Exception {
		csp = new ConfigSetParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setName("name1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setName("name2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setType("type1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setType("type2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setVarName("var1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setVarName("var2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDefaultVal("default1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDefaultVal("default2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setConstraint("x>10");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setConstraint("x<=10");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setUnit("msec");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setUnit("usec");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setWidget("spin");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setWidget("slider");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setStep("0.1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setStep("0.01");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDataName("データ名１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDataName("データ名２");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDefaultVal("デフォルト値１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDefaultVal("デフォルト値２");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDescription("概要１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDescription("概要２");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocUnit("単位１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocUnit("単位２");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocRange("範囲１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocRange("範囲２");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocConstraint("制約条件１");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocConstraint("制約条件２");
			}
		});
	}

}
