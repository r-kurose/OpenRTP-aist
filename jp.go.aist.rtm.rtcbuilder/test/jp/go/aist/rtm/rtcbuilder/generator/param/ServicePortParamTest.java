package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ServicePortParamTest extends ParamTestCase<ServicePortParam> {

	ServicePortParam sp;

	@Override
	protected void setUp() throws Exception {
		sp = new ServicePortParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setName("srv1");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setName("srv2");
			}
		});
	}

	public void testIsUpdatedInterface() throws Exception {
		//
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		ServicePortInterfaceParam sip = new ServicePortInterfaceParam(sp);
		sip.setName("interface1");
		sip.resetUpdated();
		sp.getServicePortInterfaces().add(sip);
		assertTrue(sp.isUpdated());
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		sip.setDocPreCondition("事前条件１");
		assertTrue(sp.isUpdated());
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		sp.getServicePortInterfaces().remove(0);
		assertTrue(sp.isUpdated());
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setDocDescription("ポート概要１");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setDocDescription("ポート概要２");
			}
		});
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setDocIfDescription("インターフェース概要１");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setDocIfDescription("インターフェース概要２");
			}
		});
	}

}
