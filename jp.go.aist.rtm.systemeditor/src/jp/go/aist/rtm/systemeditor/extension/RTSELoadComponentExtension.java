package jp.go.aist.rtm.systemeditor.extension;

import static jp.go.aist.rtm.toolscommon.model.component.Component.COMPOSITETYPE_NONE;

import jp.go.aist.rtm.repositoryView.RepositoryAccessor;
import jp.go.aist.rtm.toolscommon.extension.LoadComponentExtension;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.profiles.util.IDUtil;

public class RTSELoadComponentExtension extends LoadComponentExtension {

	public RTSELoadComponentExtension() {
	}

	@Override
	public boolean canCreate() {
		return true;
	}

	@Override
	public Component create() {
		if (isOnline()) {
			return createDefaultAsOnline();
		} else {
			if (COMPOSITETYPE_NONE.equals(target.getCompositeType())) {
				String componentid = target.getId();
				String pathId = target.getPathUri();
				Component spec = RepositoryAccessor.eINSTANCE.findComponent(
						componentid, pathId);
				if (spec == null) {
					throw new IllegalStateException("Target Component["
							+ componentid + "](" + pathId
							+ ") does not exist in RepositoryView.");
				}
				return spec.copy();
			}
			Component spec = ComponentFactory.eINSTANCE
					.createComponentSpecification();
			IDUtil.RTCId id = IDUtil.parseRTCId(target.getId());
			if (id != null) {
				spec.setVenderL(id.vendor);
				spec.setCategoryL(id.category);
				spec.setTypeNameL(id.name);
				spec.setVersionL(id.version);
			}
			return spec;
		}
	}

}
