package jp.go.aist.rtm.rtcbuilder.generator.param;

public class DataPortParamTest extends ParamTestCase<DataPortParam> {

	DataPortParam dp;

	@Override
	protected void setUp() throws Exception {
		dp = new DataPortParam();
	}

	public void testIsUpdatedPosision() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setPositionByIndex(1);
			}

			@Override
			void execute2(DataPortParam e) {
				e.setPositionByIndex(2);
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setPosition("BOTTOM");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setPosition("RIGHT");
			}
		});
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setName("in1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setName("in2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setType("TimedFloat");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setType("TimedLong");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setVarName("in1Port");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setVarName("in2Port");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setIdlFile("IDLFile1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setIdlFile("IDLFile2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDataFlowType("TYPE1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDataFlowType("TYPE2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setInterfaceType("INTERFACE1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setInterfaceType("INTERFACE2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setSubscriptionType("SUBSCRIPTION1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setSubscriptionType("SUBSCRIPTION2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setConstraint("CONSTRAINT1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setConstraint("CONSTRAINT2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setUnit("UNIT1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setUnit("UNIT2");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocDescription("ポート概要１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocDescription("ポート概要２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocType("ポート種別１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocType("ポート種別２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocNum("データ数１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocNum("データ数２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocSemantics("ポート説明１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocSemantics("ポート説明２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocUnit("ポート単位１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocUnit("ポート単位２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocOccurrence("発生頻度１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocOccurrence("発生頻度２");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocOperation("実行頻度１");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocOperation("実行頻度２");
			}
		});
	}

}
