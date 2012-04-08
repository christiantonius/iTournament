import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.StyledText;


public class ManageFinance extends Dialog {

	protected Shell shell;
	Finance finance = new Finance();
	String result = "";

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ManageFinance(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		result = finance.printAllProfit();
		
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	public static void center(Shell shell) {
	    Rectangle bds = shell.getDisplay().getBounds();
	    Point p = shell.getSize();
	
	    int nLeft = (bds.width - p.x) / 2;
	    int nTop = (bds.height - p.y) / 2;
	
	    shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		shell.setText("Manage Finance");
		shell.setMinimumSize(1280,800);										//Set shell dimension
		center(shell);
		
		Button btn_back = new Button(shell, SWT.NONE);
		btn_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btn_back.setFont(SWTResourceManager.getFont("Lucida Grande", 36, SWT.BOLD));
		btn_back.setBounds(95, 600, 300, 150);
		btn_back.setText("Back");
		
		StyledText styledText = new StyledText(shell, SWT.BORDER);
		styledText.setFont(SWTResourceManager.getFont("Lucida Console", 16, SWT.NORMAL));
		styledText.setText(result);
		styledText.setDoubleClickEnabled(false);
		styledText.setEnabled(false);
		styledText.setEditable(false);
		styledText.setBounds(551, 20, 600, 730);

	}
}
