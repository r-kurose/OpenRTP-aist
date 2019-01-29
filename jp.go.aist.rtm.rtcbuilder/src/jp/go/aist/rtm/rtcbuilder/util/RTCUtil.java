package jp.go.aist.rtm.rtcbuilder.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.graphics.RGB;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.ui.preference.RTCBuilderPreferenceManager;

public class RTCUtil {

	public static String form(String fm, String[] args) {
		String result = fm;
		for (int index = 0; index < args.length; index++) {
			String p = "{" + index + "}";
			int j = result.indexOf(p);
			if (j == -1) {
				continue;
			}
			String head = result.substring(0, j);
			String tail = result.substring(j + p.length());
			result = head + args[index] + tail;
		}
		return result;
	}

	public static boolean isBlank(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}
		return false;
	}

	public static List<String> split(String list, String delimit) {
		List<String> result = new ArrayList<String>();
		for (String s : list.split(delimit)) {
			result.add(s);
		}
		return result;
	}

	public static String join(List<String> list, String delimit) {
		String result = "";
		for (String s : list) {
			if (!result.isEmpty()) {
				result += delimit;
			}
			result += s;
		}
		return result;
	}

	public static void setDefaultUserDir() {
		if(RtcBuilderPlugin.getDefault()!=null) {
			String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(RTCBuilderPreferenceManager.HOME_DIRECTORY);
			if(resultTemp==null || resultTemp.isEmpty()) {
				boolean isWindows = false;
				String targetOS = System.getProperty("os.name").toLowerCase();
				if(targetOS.toLowerCase().startsWith("windows")) {
					isWindows = true;
				}
				String dirName = "";
				String FS = System.getProperty("file.separator");
				if(isWindows) {
					dirName = System.getenv("APPDATA");
				} else {
					dirName = System.getProperty("user.home");
				}
				String userHome = dirName + FS + ".openrtp";
				dirName += FS + ".openrtp" + FS + "idl";
				File newdir = new File(dirName);
				try {
					newdir.mkdirs();
				} catch (Exception ex) {
				}
				RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(RTCBuilderPreferenceManager.HOME_DIRECTORY, userHome);
			}
		}
	}

	public static List<IdlPathParam> getIDLPathes(RtcParam target) {
		List<IdlPathParam> result = new ArrayList<IdlPathParam>();
		List<String> added = new ArrayList<String>();

		String FS = System.getProperty("file.separator");

		String defaultPath = System.getenv("RTM_ROOT");
		if (defaultPath != null) {
			if(!defaultPath.endsWith(FS)) {
				defaultPath += FS;
			}
			result.add(new IdlPathParam(defaultPath + "rtm" + FS + "idl", true));
			added.add(defaultPath + "rtm" + FS + "idl");
		}
		//
		if(RtcBuilderPlugin.getDefault()!=null) {
			List<String> resultsetting = new ArrayList<String>();
			RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(RTCBuilderPreferenceManager.IDLFILE_DIRECTORIES, "");
			String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(RTCBuilderPreferenceManager.IDLFILE_DIRECTORIES);
			resultsetting = Arrays.asList(resultTemp.split(File.pathSeparator));
			for(String each : resultsetting) {
				if(each.length()==0) continue;
				if(added.contains(each)) continue;
				result.add(new IdlPathParam(each, false));
				added.add(each);
			}
			//
			RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(RTCBuilderPreferenceManager.HOME_DIRECTORY, "");
			String userHome = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(RTCBuilderPreferenceManager.HOME_DIRECTORY);
			String userDir = userHome + FS + "idl";
			if(added.contains(userDir)==false) {
				result.add(new IdlPathParam(userDir, false));
				added.add(userDir);
			}
		}
		//
		if(target!=null && target.getOutputProject()!=null && 0<target.getOutputProject().length()) {
			try {
				IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
				IProject project = workspaceHandle.getProject(target.getOutputProject());
				IFolder path = project.getFolder("idl");
				if(path!=null && path.exists()) {
					if(added.contains(path.getLocation().toOSString())==false) {
						result.add(new IdlPathParam(path.getLocation().toOSString(), false));
						added.add(path.getLocation().toOSString());
					}
				}
			} catch (Exception ex) {
			}
		}
		return result;
	}

	public static boolean checkDefault(String target, List<DataTypeParam> typeList) {
		for(DataTypeParam type : typeList) {
			if(type.isDefault()) {
				if(target.trim().equals(type.getFullPath().trim())) return true;
			}
		}
		return false;
	}

	public static String getRTMRootIdlPath() {
		String result = "";

		String FS = System.getProperty("file.separator");

		String defaultPath = System.getenv("RTM_ROOT");
		if (defaultPath != null) {
			if(!defaultPath.endsWith(FS)) {
				defaultPath += FS;
			}
			result = defaultPath + "rtm" + FS + "idl";
		}
		return result;
	}

	/**
	 * Component Colorのキー
	 */
	public static final String COLOR_COMPONENT = RTCUtil.class.getName()
			+ "COMPONENT_COLOR";
	/**
	 * DataPort Colorのキー
	 */
	public static final String COLOR_DATAPORT = RTCUtil.class.getName()
			+ "DATA_PORT_COLOR";

	/**
	 * ServicePort Colorのキー
	 */
	public static final String COLOR_SERVICEPORT = RTCUtil.class.getName()
			+ "SERVICE_PORT_COLOR";

	/**
	 * Service I/F Colorのキー
	 */
	public static final String COLOR_SERVICEIF = RTCUtil.class.getName()
			+ "SERVICE_IF_COLOR";
	/**
	 * デフォルトの色を管理するマップ
	 */
	public static final Map<String, RGB> defaultRGBMap = new HashMap<String, RGB>();
	static {
		defaultRGBMap.put(COLOR_COMPONENT, new RGB(136, 190, 240));
		defaultRGBMap.put(COLOR_DATAPORT, new RGB(55, 97, 217));
		defaultRGBMap.put(COLOR_SERVICEPORT, new RGB(192, 192, 192));
		defaultRGBMap.put(COLOR_SERVICEIF, new RGB(101, 136, 22));
	}

}
