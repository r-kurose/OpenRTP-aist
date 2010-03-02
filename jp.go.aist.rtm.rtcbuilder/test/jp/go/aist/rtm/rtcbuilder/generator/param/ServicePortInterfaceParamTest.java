package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ServicePortInterfaceParamTest extends
		ParamTestCase<ServicePortInterfaceParam> {

	ServicePortParam sp;

	ServicePortInterfaceParam sip;

	@Override
	protected void setUp() throws Exception {
		sp = new ServicePortParam();
		sip = new ServicePortInterfaceParam(sp);
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setName("interface1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setName("interface2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setInstanceName("instance1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setInstanceName("instance2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setVarName("if1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setVarName("if2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setIdlFile("idlFile1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setIdlFile("idlFile2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setInterfaceType("TYPE1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setInterfaceType("TYPE2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setIdlSearchPath("/path/to/idl1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setIdlSearchPath("/path/to/idl2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setIndex(1);
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setIndex(0);
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDirection("Required");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDirection("Provided");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocDescription("äTóvÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocDescription("äTóvÇQ");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocArgument("à¯êîÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocArgument("à¯êîÇQ");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocReturn("ï‘ÇËílÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocReturn("ï‘ÇËílÇQ");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocException("ó·äOÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocException("ó·äOÇQ");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocPreCondition("éñëOèåèÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocPreCondition("éñëOèåèÇQ");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocPostCondition("éñå„èåèÇP");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocPostCondition("éñå„èåèÇQ");
			}
		});
	}

}
