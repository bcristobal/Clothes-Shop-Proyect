package clothes.shop.clases;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class MySpinnerEditor extends DefaultCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SpinnerModel mSpinner;
	JSpinner sp;
    DefaultEditor defaultEditor;
    JTextField text;
    // Initialize the spinner
    public MySpinnerEditor() {
        super(new JTextField());
        mSpinner =
                new SpinnerNumberModel(0, //initial value
                   0, //minimum value
                   100, //maximum value
                   5); //step
        sp = new JSpinner(mSpinner);
        defaultEditor = ((DefaultEditor)sp.getEditor());
        text = defaultEditor.getTextField();
    }
    // Prepare the spinner component and return it
    public Component getTableCellEditorComponent(JTable table, Object 
    value, boolean isSelected, int row, int column) 
    {
        sp.setValue(value);
        return sp;
    }
    // Returns the current value of the spinners
    public Object getCellEditorValue() {
        return sp.getValue();
    }
	
}
