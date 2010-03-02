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
				e.setSliderStep("0.1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setSliderStep("0.01");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDataName("ƒf[ƒ^–¼‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDataName("ƒf[ƒ^–¼‚Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDefaultVal("ƒfƒtƒHƒ‹ƒg’l‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDefaultVal("ƒfƒtƒHƒ‹ƒg’l‚Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDescription("ŠT—v‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDescription("ŠT—v‚Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocUnit("’PˆÊ‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocUnit("’PˆÊ‚Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocRange("”ÍˆÍ‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocRange("”ÍˆÍ‚Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocConstraint("§–ñðŒ‚P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocConstraint("§–ñðŒ‚Q");
			}
		});
	}

}
