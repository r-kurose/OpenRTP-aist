package jp.go.aist.rtm.toolscommon.fsm.editor.editor.scxml.eleditor;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

import com.mxgraph.model.mxCell;

import jp.go.aist.rtm.toolscommon.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.toolscommon.fsm.editor.editor.fileimportexport.SCXMLNode;

public class SCXMLStateEditor extends SCXMLEditorRoot {
	private static final long serialVersionUID = -1937899100474024531L;
	
	private static final String STR_ON = "<log label=\"ON\"></log>";

	public SCXMLStateEditor(JFrame parent, mxCell nn, mxCell rootOfGraph, SCXMLGraphEditor editor, Point pos) {
		super(parent, editor, nn);
//		setTitle(mxResources.get("titleNodeEditor"));
		setTitle("State Editor");
		setLocation(pos);
		
		final SCXMLNode node = (SCXMLNode) nn.getValue();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		DocumentChangeListener changeListener = new DocumentChangeListener(editor);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		editor.toString();
		
        addUIParts(panel, new JLabel("State Name"), gbl, 0, 0, 1, 1);
        JTextField txtName = null;
        Document docName = null;
		if (nn!=rootOfGraph) {
	        txtName = new JTextField(node.getID());
	        docName = txtName.getDocument();
			node.setIDDoc(docName);
		} else {
	        txtName = new JTextField(node.getName());
	        docName = txtName.getDocument();
			node.setNameDoc(docName);
		}
		docName.addDocumentListener(changeListener);
		
        txtName.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtName, gbl, 1, 0, 2, 1);
        //
        addUIParts(panel, new JLabel("Actions"), gbl, 0, 1, 1, 1);
        final JCheckBox chkEntry = new JCheckBox("On Entry");
        addUIParts(panel, chkEntry, gbl, 1, 1, 1, 1);
        if(node.getOnEntry().equals(STR_ON)) {
        	chkEntry.setSelected(true);
        }
        chkEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chkEntry.isSelected()) {
					node.setOnEntry(STR_ON);	
				} else {
					node.setOnEntry("");	
				}
			}
		});
        //
        final JCheckBox chkExit = new JCheckBox("On Exit");
        addUIParts(panel, chkExit, gbl, 2, 1, 1, 1);
        if(node.getOnExit().equals(STR_ON)) {
        	chkExit.setSelected(true);
        }
        chkExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chkExit.isSelected()) {
					node.setOnExit(STR_ON);	
				} else {
					node.setOnExit("");	
				}
			}
		});
		//
        addUIParts(panel, new JLabel("Data"), gbl, 0, 2, 1, 1);
        JTextField txtData = new JTextField(node.getDatamodel());
        txtData.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtData, gbl, 1, 2, 2, 1);
        Document docData = node.getDatamodelDoc();
		if (docData == null) {
			node.setDatamodelDoc(docData = txtData.getDocument());
		}
		docData.addDocumentListener(changeListener);
		//
		if (nn==rootOfGraph) {
			chkEntry.setEnabled(false);
			chkExit.setEnabled(false);
		}
		
		pack();
		setVisible(true);
	}
}
