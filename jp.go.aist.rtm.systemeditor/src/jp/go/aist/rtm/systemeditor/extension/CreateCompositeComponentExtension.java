package jp.go.aist.rtm.systemeditor.extension;

import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

public abstract class CreateCompositeComponentExtension {

	/**
	 * 複合RTC生成のための基本設定情報
	 */
	public static class Base {
		public AbstractSystemDiagramEditor editor;
		public RTCManager manager;

		public String compositeType;
		public String instanceName;
		public List<Component> components;
		public String exportedPorts;
		public String pathId;
	}

	protected String message = "";

	/**
	 * オンラインエディタで生成可能な複合RTC種別のリストを返します。
	 */
	public abstract List<String> getOnlineCompositeTypes();

	/**
	 * オフラインエディタで生成可能な複合RTC種別のリストを返します。
	 */
	public abstract List<String> getOfflineCompositeTypes();

	/**
	 * 複合RTC作成アクションのポップアップメニューが有効かを判定します。
	 * 
	 * @param base
	 *            基本情報
	 * @return アクションが有効な場合は true (デフォルトは有効)
	 */
	public boolean isActionEnabled(Base base) {
		return true;
	}

	/**
	 * 設定ダイアログを開く前に、複合RTCが生成可能かを判定します。
	 * 
	 * @param base
	 *            基本情報
	 * @return 生成可能な場合は true
	 */
	public boolean canCreate(Base base) {
		return true;
	}

	/**
	 * 複合RTCが生成可能かを判定します。
	 * 
	 * @param base
	 *            基本情報
	 * @return 生成可能な場合は true
	 */
	public boolean isValid(Base base) {
		if (base == null || base.editor == null) {
			return false;
		}
		return true;
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * 複合RTCのコンポーネントを生成します。(１段階)
	 * 
	 * @param base
	 *            基本情報
	 * @return 生成された複合RTCのオブジェクト
	 */
	public abstract Component createComponent(Base base);

	/**
	 * 複合RTCのコンポーネントへ子を追加します。(２段階)
	 * 
	 * @param base
	 *            基本情報
	 * @param comp
	 *            複合RTCのオブジェクト
	 * @return 生成された複合RTCのオブジェクト
	 */
	public abstract Component setCompositeMembers(Base base, Component comp);

}
