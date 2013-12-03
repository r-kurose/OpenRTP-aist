package jp.go.aist.rtm.rtcbuilder.ui.compare;

/**
 * 比較対象の情報を表すクラス
 */
public class CompareTarget {
	private String targetName;

	private String originalSrc;

	private String generateSrc;

	private boolean canMerge;

	public boolean canMerge() {
		return canMerge;
	}

	public void setCanMerge(boolean canMerge) {
		this.canMerge = canMerge;
	}

	public String getGenerateSrc() {
		return generateSrc;
	}

	public void setGenerateSrc(String generateSrc) {
		this.generateSrc = generateSrc;
	}

	public String getOriginalSrc() {
		return originalSrc;
	}

	public void setOriginalSrc(String originalSrc) {
		this.originalSrc = originalSrc;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

}
