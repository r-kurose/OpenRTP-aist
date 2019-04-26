package jp.go.aist.rtm.systemeditor.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jp.go.aist.rtm.toolscommon.model.component.Component;

public class ComponentIconStore {

	public static ComponentIconStore eINSTANCE;

	static {
		eINSTANCE = new ComponentIconStore();
		SystemEditorPreferenceManager.getInstance().loadComponentIconStore(
				eINSTANCE);
	}

	Map<String, ImageDescriptor> path2DescripterMap;
	Map<String, String> type2PathMap;
	Map<String, String> category2PathMap;

	ComponentIconStore() {
		path2DescripterMap = new HashMap<String, ImageDescriptor>();
		type2PathMap = new HashMap<String, String>();
		category2PathMap = new HashMap<String, String>();
	}

	/** コンポーネントタイプに対するアイコンパスを登録 */
	public void registTypeImage(String type, String path) {
		if (type == null || type.isEmpty()) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		String absPath = file.getAbsolutePath();
		if (path2DescripterMap.get(absPath) == null) {
			ImageDescriptor desc = createDescriptorByFile(file);
			if (desc == null) {
				return;
			}
			path2DescripterMap.put(absPath, desc);
		}
		type2PathMap.put(type, absPath);
	}

	/** コンポーネントカテゴリに対するアイコンパスを登録 */
	public void registCategoryImage(String category, String path) {
		if (category == null || category.isEmpty()) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		String absPath = file.getAbsolutePath();
		if (path2DescripterMap.get(absPath) == null) {
			ImageDescriptor desc = createDescriptorByFile(file);
			if (desc == null) {
				return;
			}
			path2DescripterMap.put(absPath, desc);
		}
		category2PathMap.put(category, absPath);
	}

	ImageDescriptor createDescriptorByFile(File file) {
		try {
			URL url = file.toURI().toURL();
			return ImageDescriptor.createFromURL(url);
		} catch (Exception e) {
			return null;
		}
	}

	/** コンポーネントに対するアイコンを検索 */
	public Image findImageByComp(Component comp) {
		String type = comp.getTypeNameL();
		String category = comp.getCategoryL();
		String path = null;
		for (String p : type2PathMap.keySet()) {
			if (type != null && type.indexOf(p) != -1) {
				path = type2PathMap.get(p);
				break;
			}
		}
		if (path != null) {
			ImageDescriptor desc = path2DescripterMap.get(path);
			return (desc == null) ? null : desc.createImage();
		}
		for (String p : category2PathMap.keySet()) {
			if (category != null && category.indexOf(p) != -1) {
				path = category2PathMap.get(p);
				break;
			}
		}
		if (path != null) {
			ImageDescriptor desc = path2DescripterMap.get(path);
			return (desc == null) ? null : desc.createImage();
		}
		return null;
	}

	/** アイコン設定リストとして取得 */
	public List<Entry> toEntries() {
		List<Entry> result = new ArrayList<Entry>();
		for (String p : type2PathMap.keySet()) {
			String path = type2PathMap.get(p);
			ImageDescriptor desc = path2DescripterMap.get(path);
			if (path == null || desc == null) {
				continue;
			}
			Entry e = Entry.createType(p, path, desc);
			result.add(e);
		}
		for (String p : category2PathMap.keySet()) {
			String path = category2PathMap.get(p);
			ImageDescriptor desc = path2DescripterMap.get(path);
			if (path == null || desc == null) {
				continue;
			}
			Entry e = Entry.createCategory(p, path, desc);
			result.add(e);
		}
		return result;
	}

	/** アイコン設定リストから変換 */
	public static ComponentIconStore getByEntries(List<Entry> entries) {
		ComponentIconStore result = new ComponentIconStore();
		for (ComponentIconStore.Entry entry : entries) {
			if (entry.isType()) {
				String type = entry.getType();
				result.registTypeImage(type, entry.getPath());
			} else {
				String category = entry.getCategory();
				result.registCategoryImage(category, entry.getPath());
			}
		}
		return result;
	}

	/** アイコン設定リストをクリア */
	public void clearAllImages() {
		type2PathMap.clear();
		category2PathMap.clear();
		path2DescripterMap.clear();
	}

	/** アイコン設定をプロファイル(XML)へ保存 */
	public void saveProfile(String fileName) throws Exception {
		IconProfileHandler handler = new IconProfileHandler();
		String xml = handler.save(this);
		IPath path = new Path(fileName);
		if (!path.toFile().exists()) {
			path.toFile().createNewFile();
		}
		String xmlSplit[] = xml.split("\n");
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toOSString()), "UTF-8")) ) {
			for (String s : xmlSplit) {
				writer.write(s);
				writer.newLine();
			}
		}
	}

	/** アイコン設定をプロファイル(XML)から読込 */
	public static ComponentIconStore loadProfile(String fileName)
			throws Exception {
		IPath path = new Path(fileName);
		if (!path.toFile().exists()) {
			return null;
		}
		String xmlString = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(path.toOSString()), "UTF-8")) ) {
			while (true) {
				String s = reader.readLine();
				if (s == null) {
					break;
				}
				xmlString += s;
			}
		}
		IconProfileHandler handler = new IconProfileHandler();
		ComponentIconStore result = handler.parse(xmlString);
		return result;
	}

	public void parsePreference(String pref) {
		if (pref == null) {
			return;
		}
		clearAllImages();
		for (String s : pref.split("\\|")) {
			if (s == null || s.isEmpty()) {
				continue;
			}
			String[] ss = s.split(";");
			if (ss.length != 3) {
				continue;
			}
			if (Entry.KIND_TYPE.equals(ss[0])) {
				registTypeImage(ss[1], ss[2]);
			} else if (Entry.KIND_CATEGORY.equals(ss[0])) {
				registCategoryImage(ss[1], ss[2]);
			}
		}
	}

	public String toPreference() {
		String result = "";
		for (String s : type2PathMap.keySet()) {
			if (!result.isEmpty()) {
				result += "|";
			}
			result += Entry.KIND_TYPE + ";" + s + ";" + type2PathMap.get(s);
		}
		for (String s : category2PathMap.keySet()) {
			if (!result.isEmpty()) {
				result += "|";
			}
			result += Entry.KIND_CATEGORY + ";" + s + ";"
					+ category2PathMap.get(s);
		}
		return result;
	}

	/** アイコン設定のエントリを表す */
	public static class Entry {
		public static final String KIND_TYPE = "type";
		public static final String KIND_CATEGORY = "category";
		public static List<String> KINDS;

		static {
			KINDS = new ArrayList<String>();
			KINDS.add(KIND_TYPE);
			KINDS.add(KIND_CATEGORY);
		}

		String type;
		String category;
		String path;
		ImageDescriptor desc;

		public static Entry createType(String type, String path,
				ImageDescriptor desc) {
			Entry entry = new Entry();
			entry.type = type;
			entry.path = path;
			entry.desc = desc;
			return entry;
		}

		public static Entry createCategory(String category, String path,
				ImageDescriptor desc) {
			Entry entry = new Entry();
			entry.category = category;
			entry.path = path;
			entry.desc = desc;
			return entry;
		}

		Entry() {
		}

		public void setType(String type) {
			this.type = type;
			this.category = null;
		}

		public void setCategory(String category) {
			this.type = null;
			this.category = category;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public void setImageDescriptor(ImageDescriptor desc) {
			this.desc = desc;
		}

		public String getKind() {
			return (isType()) ? KIND_TYPE : KIND_CATEGORY;
		}

		public String getType() {
			return type;
		}

		public String getCategory() {
			return category;
		}

		public boolean isType() {
			return type != null;
		}

		public boolean isCategory() {
			return category != null;
		}

		public String getPath() {
			return path;
		}

		public ImageDescriptor getImageDescriptor() {
			return desc;
		}
	}

	/** アイコン設定プロファイルの読み書きを実施 */
	public static class IconProfileHandler {

		/** アイコン設定(XML)の読込 */
		public ComponentIconStore parse(String xmlString) throws Exception {
			ComponentIconStore result = new ComponentIconStore();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream in = new ByteArrayInputStream(xmlString.getBytes());
			Document dc = builder.parse(in);
			NodeList prefs = dc.getElementsByTagName("iconPreference");
			Node attr = null;
			for (int i = 0; i < prefs.getLength(); i++) {
				Node n = prefs.item(i);
				for (Node img : findByName(n.getChildNodes(), "image")) {
					attr = img.getAttributes().getNamedItem("path");
					if (attr == null) {
						continue;
					}
					// 画像ファイルをURI形式、もしくはパスから読込
					String pathUri = attr.getTextContent();
					String path = null;
					try {
						URI uri = new URI(pathUri);
						path = uri.getPath();
					} catch (URISyntaxException e) {
						File f = new File(pathUri);
						path = f.getAbsolutePath();
					}
					for (Node pat : findByName(img.getChildNodes(), "pattern")) {
						attr = pat.getAttributes().getNamedItem("kind");
						if (attr == null) {
							continue;
						}
						String kind = attr.getTextContent();
						String pattern = pat.getTextContent();
						//
						if (kind != null && kind.equals("type")) {
							result.registTypeImage(pattern, path);
						} else if (kind != null && kind.equals("category")) {
							result.registCategoryImage(pattern, path);
						}
					}
				}
			}
			return result;
		}

		/** アイコン設定(XML)の保存 */
		public String save(ComponentIconStore store) {
			String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			result += "<iconPreference>\n";
			for (String path : store.path2DescripterMap.keySet()) {
				File f = new File(path);
				// 画像ファイルをURI形式で保存
				result += "  <image path=\"" + f.toURI() + "\">\n";
				for (String key : store.type2PathMap.keySet()) {
					String value = store.type2PathMap.get(key);
					if (value == null || !value.equals(path)) {
						continue;
					}
					result += "    <pattern kind=\"type\">";
					result += key + "</pattern>\n";
				}
				for (String key : store.category2PathMap.keySet()) {
					String value = store.category2PathMap.get(key);
					if (value == null || !value.equals(path)) {
						continue;
					}
					result += "    <pattern kind=\"category\">";
					result += key + "</pattern>\n";
				}
				result += "  </image>\n";
			}
			result += "</iconPreference>";
			return result;
		}

		List<Node> findByName(NodeList list, String name) {
			List<Node> result = new ArrayList<Node>();
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				if (n.getNodeName().equals(name)) {
					result.add(n);
				}
			}
			return result;
		}
	}

}
