package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.model.component.BuildView;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DataTypePreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public abstract class AbstractEditorFormPage extends FormPage {

	protected RtcBuilderEditor editor;
	protected BuildView buildview;
	protected Font titleFont;

	/**
	 * コンストラクタ
	 * 
	 */
	public AbstractEditorFormPage(RtcBuilderEditor editor, String id, String name) {
		super(editor, id, name);
		this.editor = editor;
		this.buildview = editor.getEMFmodel();
	}

	protected ScrolledForm createBase(IManagedForm managedForm) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;

		managedForm.getForm().getBody().setLayout(gl);
		FormToolkit toolkit = managedForm.getToolkit();

		ScrolledForm form = toolkit.createScrolledForm(managedForm.getForm().getBody());
		gl = new GridLayout(2, false);
		gl.makeColumnsEqualWidth = true;
		form.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		form.setLayoutData(gd);

		form.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(form.getBody());

		form.getBody().setLayout(gl);
		
		return form;
	}
	
//	protected Composite createSectionBase(FormToolkit toolkit,
//			ScrolledForm form, String sectionName, int colNum) {
//		GridLayout gl;
//
//		Section section = toolkit.createSection(
//				form.getBody(),
//				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
//		section.setText(sectionName);
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalAlignment = GridData.FILL;
//		section.setLayoutData(gridData);
//		Composite composite = toolkit.createComposite(section, SWT.NULL);
//		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
//		toolkit.paintBordersFor(composite);
//		gl = new GridLayout(colNum, false);
//		composite.setLayout(gl);
//		section.setClient(composite);
//		return composite;
//	}
//	
	protected Composite createSectionBaseWithLabel(FormToolkit toolkit, ScrolledForm form,
			String title, String explain, int colnum) {
		Section sctBasic = toolkit.createSection(form.getBody(),
		Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		sctBasic.setText(title);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		sctBasic.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(sctBasic, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		GridLayout gl = new GridLayout(colnum, false);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		sctBasic.setClient(composite);
		//
		Label expl = toolkit.createLabel(composite, explain);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = colnum;
		expl.setLayoutData(gd);
		return composite;
	}

	protected Composite createHintSectionBase(FormToolkit toolkit, ScrolledForm form,
			int verSpan) {
		Section sctHint = toolkit.createSection(form.getBody(),
		Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		sctHint.setText(IMessageConstants.HINT_TITLE);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.verticalSpan = verSpan;
		sctHint.setLayoutData(gd);
		//
		Composite composite = toolkit.createComposite(sctHint, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		GridLayout gl = new GridLayout(2, false);
		composite.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		sctHint.setClient(composite);
		return composite;
	}

	protected void createHintLabel(String title, String desc, FormToolkit toolkit, Composite composite) {
		Label lblt1 = toolkit.createLabel(composite, title);
		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		lblt1.setLayoutData(gd);
		toolkit.createLabel(composite, desc);
	}
	protected void createHintSpace(FormToolkit toolkit, Composite composite) {
		Label sep = toolkit.createLabel(composite, "");
		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		sep.setLayoutData(gd);
	}

	protected Text createLabelAndText(FormToolkit toolkit, Composite composite,
			String labelString) {
		return createLabelAndText(toolkit, composite, labelString, SWT.NONE);
	}
	protected Text createLabelAndText(FormToolkit toolkit, Composite composite,
			String labelString, int style) {
		if( labelString!=null && labelString.length()>0 ) toolkit.createLabel(composite, labelString);
		final Text text = toolkit.createText(composite, "", style);
		text.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				update();
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		text.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
				text.setSelection(0, text.getText().length());
			}

			public void mouseUp(MouseEvent e) {
				text.setSelection(0, text.getText().length());
			}

		});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(gridData);
		return text;
	}
	
	protected Combo createLabelAndCombo(FormToolkit toolkit, Composite composite,
			String labelString, String[] items) {
		toolkit.createLabel(composite, labelString);
		Combo combo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setItems(items);
		combo.select(0);
		// combo.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		// toolkit.paintBordersFor(combo);
		combo.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				update();
			}
		});
		combo.addSelectionListener(new SelectionListener() {
			  public void widgetDefaultSelected(SelectionEvent e){
			  }
			  public void widgetSelected(SelectionEvent e){
					update();
			  }
			});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		combo.setLayoutData(gridData);

		return combo;
	}

	protected Text createLabelAndDirectory(FormToolkit toolkit,
								Composite composite, String labelString) {
		GridData gd;

		if(!labelString.equals("")) {
			toolkit.createLabel(composite, labelString);
		}
		final Text text = toolkit.createText(composite, "");
		text.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				update();
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		gd = new GridData(GridData.FILL_HORIZONTAL);

		text.setLayoutData(gd);

		Button checkButton = toolkit.createButton(composite, "Browse...",
				SWT.PUSH);
		checkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getEditorSite()
						.getShell());
				if (text.getText().length() > 0)
					dialog.setFilterPath(text.getText());
				String newPath = dialog.open();
				if (newPath != null) {
					text.setText(newPath);
					update();
				}
			}
		});

		return text;
	}

	protected String getValue(String value) {
		String result = value;
		if (result == null) {
			result = "";
		}

		return result;
	}

	protected String getText(String text) {
		String result = text;
		if ("".equals(result)) {
			result = null;
		}
		return result;
	}

	protected String getDocText(String text) {
		String result = text;
		if ("".equals(result)) {
			return "";
		}
		String sep = System.getProperty("line.separator");
		String lines[] = result.split(sep);
		StringBuffer buffer = new StringBuffer();
		for( int index=0; index<lines.length; index++ ) {
			buffer.append(lines[index]);
			if(index<lines.length-1) buffer.append(IRtcBuilderConstants.NEWLINE_CODE);
		}
		return buffer.toString();
	}

	protected String getDisplayDocText(String text) {
		String result = text;
		if( text==null || "".equals(result) ) {
			return "";
		}
		String sep = System.getProperty("line.separator");
		String lines[] = result.split(IRtcBuilderConstants.NEWLINE_CODE);
		StringBuffer buffer = new StringBuffer();
		for( int index=0; index<lines.length; index++ ) {
			buffer.append(lines[index]);
			if(index<lines.length-1) buffer.append(sep);
		}
		return buffer.toString();
	}

	protected Combo createEditableCombo(FormToolkit toolkit, Composite composite,
			String labelString, String key, String[] defaultValue) {
		toolkit.createLabel(composite, labelString);
		Combo combo = new Combo(composite, SWT.DROP_DOWN);
		for(int index=0;index<defaultValue.length;index++) {
			combo.add(defaultValue[index]);
		}
		loadDefaultComboValue(combo, key);

		combo.select(0);
		combo.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				update();
			}
		});
		combo.addSelectionListener(new SelectionListener() {
			  public void widgetDefaultSelected(SelectionEvent e){
			  }
			  public void widgetSelected(SelectionEvent e){
					update();
			  }
			});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		combo.setLayoutData(gridData);

		return combo;
	}

	protected Button createRadioCheckButton(FormToolkit toolkit,
			Composite composite, String labelString, int style) {
		Button radio = toolkit.createButton(composite, "", style);
		radio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				update();
			}
		});
		radio.setText(labelString);
		return radio;
	}

	/**
	 * ワークスペースの永続情報から、コンボのリストと選択インデックスをロードする
	 * 
	 * @param combo
	 */
	protected void loadDefaultComboValue(Combo combo, String key) {
		String defaultString = RtcBuilderPlugin.getDefault().getPreferenceStore()
				.getString(key);
		StringTokenizer tokenize = new StringTokenizer(defaultString, ",");
		while (tokenize.hasMoreTokens()) {
			combo.add(tokenize.nextToken());
		}
	}

	/**
	 * 入力したカテゴリを永続情報に設定する
	 * 
	 * @param combo
	 */
	protected void addDefaultComboValue(Combo combo, String key) {
		String value = combo.getText(); // local

		String storedString = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(key);
		StringTokenizer tokenize = new StringTokenizer(storedString, ",");
		ArrayList<String> storedList = new ArrayList<String>();
		while (tokenize.hasMoreTokens()) {
			storedList.add(tokenize.nextToken());
		}
		if (storedList.contains(value) == false) {
			String defaultString = RtcBuilderPlugin.getDefault()
					.getPreferenceStore().getString(key);

			String newString = "";
			if ("".equals(defaultString)) {
				newString = value;
			} else {
				newString = value + "," + defaultString;
			}

			RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(key, newString);
			combo.add(value);
		}
	}
	
	protected int searchIndex(String[] sources, String target) {
		for(int intIdx=0;intIdx<sources.length;intIdx++) {
			if( target.equals(sources[intIdx]) )
				return intIdx;
		}
		return 0;
	}
	
	protected String[] extractDataTypes() {
		List<String> sources = DataTypePreferenceManager.getInstance().getIdlFileDirectories();
		List<String> sourceContents = new ArrayList<String>();
		for(int intidx=0;intidx<sources.size();intidx++) {
			try {
				File idlDir = new File(sources.get(intidx));
				String[] idlNames = idlDir.list();
				for( int intidxFile=0; intidxFile<idlNames.length; intidxFile++ ) {
					if(idlNames[intidxFile].toLowerCase().endsWith(".idl") ) {
						String idlContent = FileUtil.readFile(
								sources.get(intidx) + System.getProperty( "file.separator" ) + idlNames[intidxFile]);
						sourceContents.add(idlContent);
					}
				}
			} catch(RuntimeException ex) {
			}
		}
		String[] defaultTypeList = new String[0];
		try {
			List<String> dataTypes = IDLParamConverter.extractTypeDef(sourceContents);
			defaultTypeList = new String[dataTypes.size()];
			defaultTypeList = dataTypes.toArray(defaultTypeList);
		} catch (ParseException ex) {
		}
		return defaultTypeList;
	}

	abstract protected void update();
	abstract protected String validateParam();

	@Override
	public void dispose() {
		if( titleFont!=null ) titleFont.dispose();
		super.dispose();
	}
	
	public void pageSelected(){
		// ページが選択されたときに処理が必要な場合は、これをオーバーライドする
	}

	/**
	 * CompositeにBackgroundColorを指定する。
	 * Compositeが子を持つ場合には子のBackgroundColorも指定する。
	 * 子がCompositeの場合には再起呼び出しを行う。
	 * 指定したCompositeの下にあるControlすべてが同じBackgroundColorになる。
	 * 
	 * @param composit
	 * @param color
	 */
	protected void setEnableBackground(Composite composit,Color color) {
		if(composit == null) return;
		composit.setBackground(color);
		if (composit.getChildren() == null ) return;
		for(Control child : composit.getChildren()) {
			if (child instanceof Composite) {
				setEnableBackground((Composite) child,color);
			} else {
				child.setBackground(color);
			}
		}
	}

}
