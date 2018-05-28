package jp.go.aist.rtm.rtcbuilder._test;

import jp.go.aist.rtm.rtcbuilder.generator.param.LibraryParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.TargetEnvParam;

public class TargetEnvParamTest extends ParamTestCase<TargetEnvParam> {

	TargetEnvParam ep;

	@Override
	protected void setUp() throws Exception {
		ep = new TargetEnvParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(ep, new UpdateChecker() {
			@Override
			void execute1(TargetEnvParam e) {
				e.setLangVersion("1.0");
			}

			@Override
			void execute2(TargetEnvParam e) {
				e.setLangVersion("1.2");
			}
		});
		//
		assertUpdated(ep, new UpdateChecker() {
			@Override
			void execute1(TargetEnvParam e) {
				e.setOs("Windows XP");
			}

			@Override
			void execute2(TargetEnvParam e) {
				e.setOs("Windows Vista");
			}
		});
		//
		assertUpdated(ep, new UpdateChecker() {
			@Override
			void execute1(TargetEnvParam e) {
				e.setOther("その他１");
			}

			@Override
			void execute2(TargetEnvParam e) {
				e.setOther("その他２");
			}
		});
		//
		assertUpdated(ep, new UpdateChecker() {
			@Override
			void execute1(TargetEnvParam e) {
				e.setCpuOther("その他CPU１");
			}

			@Override
			void execute2(TargetEnvParam e) {
				e.setCpuOther("その他CPU２");
			}
		});
	}

	public void testIsUpdatedOsAndCpu() throws Exception {
		//
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
		//
		ep.getOsVersions().add("Windows XP");
		assertTrue(ep.isUpdated());
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
		//
		ep.getCpus().add("Intel Core 2 Duo");
		assertTrue(ep.isUpdated());
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
	}

	public void testIsUpdatedLibraries() throws Exception {
		//
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
		//
		LibraryParam lp = new LibraryParam("name1", "1.0", "etc");
		lp.resetUpdated();
		ep.getLibraries().add(lp);
		assertTrue(ep.isUpdated());
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
		//
		lp.setVersion("1.2");
		assertTrue(ep.isUpdated());
		ep.resetUpdated();
		assertFalse(ep.isUpdated());
		//
		ep.getLibraries().remove(0);
		assertTrue(ep.isUpdated());
	}

}
