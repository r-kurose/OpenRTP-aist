package jp.go.aist.rtm.systemeditor.ui.util;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class UIUtil {

	public static final String COLOR_WHITE = "COLOR_WHITE";

	public static final String COLOR_ERROR = "COLOR_ERROR";

	public static final String COLOR_UNEXIST = "COLOR_UNEXIST";

	public static final String COLOR_MODIFY = "COLOR_MODIFY";

	public static final String COLOR_UNEDITABLE = "COLOR_UNEDITABLE";

	static ColorRegistry colorRegistry = null;

	public static Color getColor(String name) {
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(COLOR_WHITE, new RGB(255, 255, 255));
			colorRegistry.put(COLOR_ERROR, new RGB(255, 0, 0));
			colorRegistry.put(COLOR_UNEXIST, new RGB(128, 128, 128));
			colorRegistry.put(COLOR_MODIFY, new RGB(255, 192, 192));
			colorRegistry.put(COLOR_UNEDITABLE, new RGB(240, 240, 240));
		}
		return colorRegistry.get(name);
	}

}
