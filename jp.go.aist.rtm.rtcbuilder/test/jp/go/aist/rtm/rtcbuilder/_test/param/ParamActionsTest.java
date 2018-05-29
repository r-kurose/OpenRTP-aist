package jp.go.aist.rtm.rtcbuilder._test.param;

import jp.go.aist.rtm.rtcbuilder.generator.param.ActionsParam;

public class ParamActionsTest extends ParamTestCase<ActionsParam> {

	ActionsParam ap;

	@Override
	protected void setUp() throws Exception {
		ap = new ActionsParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setImplemaented("TRUE");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setImplemaented("FALSE");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setImplemaented(true);
			}

			@Override
			void execute2(ActionsParam e) {
				e.setImplemaented(false);
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setOverview("概要１");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setOverview("概要２");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setPreCondition("事前条件１");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setPreCondition("事前条件２");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setPostCondition("事後条件１");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setPostCondition("事後条件２");
			}
		});
	}

}
