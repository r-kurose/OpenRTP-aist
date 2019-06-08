package jp.go.aist.rtm.toolscommon.fsm.editor.editor.scxml.eleditor;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxgraph.model.mxCell;

import jp.go.aist.rtm.toolscommon.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.toolscommon.fsm.editor.editor.fileimportexport.SCXMLEdge;

public class SCXMLTransitionEditor extends SCXMLEditorRoot {

	private static final long serialVersionUID = -6967742868602449986L;
	
	private SCXMLEdge edge;
	private JTextField txtEvent;
	private JTextField txtCondition;

	public SCXMLTransitionEditor(JFrame parent, mxCell en, SCXMLGraphEditor editor, Point pos) {
		super(parent, editor, en);
		setTitle("Transition Editor");
		setLocation(pos);
		
		edge = (SCXMLEdge) en.getValue();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		
        addUIParts(panel, new JLabel("Event"), gbl, 0, 0, 1, 1);
        txtEvent = new JTextField(edge.getEvent());
        txtEvent.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtEvent, gbl, 1, 0, 2, 1);
		//
        addUIParts(panel, new JLabel("Condition"), gbl, 0, 1, 1, 1);
        txtCondition = new JTextField(edge.getCondition());
        txtCondition.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtCondition, gbl, 1, 1, 2, 1);
		
		pack();
		setVisible(true);
	}
	
	protected boolean performOK() {
		edge.setEvent(txtEvent.getText());
		edge.setCondition(txtCondition.getText());
		return true;
	}
}
