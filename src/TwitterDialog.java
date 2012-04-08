import java.io.IOException;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import twitter4j.TwitterException;
import org.eclipse.swt.browser.Browser;


public class TwitterDialog extends Dialog {

	protected Object result;
	protected Shell shlSendingMessage;
	private Text text;
	private Label lbl_errorMessage;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TwitterDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	public static void center(Shell shell) {
	    Rectangle bds = shell.getDisplay().getBounds();
	    Point p = shell.getSize();
	
	    int nLeft = (bds.width - p.x) / 2;
	    int nTop = (bds.height - p.y) / 2;
	
	    shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	public Object open() {
		createContents();
		shlSendingMessage.open();
		shlSendingMessage.layout();
		Display display = getParent().getDisplay();
		while (!shlSendingMessage.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {	
		shlSendingMessage = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shlSendingMessage.setSize(390, 150);
		shlSendingMessage.setText("Sending Message");
		shlSendingMessage.setMinimumSize(390,150);										//Set shell dimension
		center(shlSendingMessage);
		
		text = new Text(shlSendingMessage, SWT.BORDER | SWT.MULTI);
		text.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text.getCharCount() <= 140) {
					int remainingChars = 140 - text.getCharCount();
					lbl_errorMessage.setText(Integer.toString(remainingChars));
				}
				
				else { 
					int exceedingChars = text.getCharCount() - 140;
					lbl_errorMessage.setText("-" + Integer.toString(exceedingChars));
				}	
			}
		});
		text.setBounds(20, 20, 350, 50);
		
		Button btnNewButton = new Button(shlSendingMessage, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text.getCharCount() <= 140 && text.getCharCount() > 0) {
					OAuthTwitterClient tt = new OAuthTwitterClient();
					try {
						tt.updateStatus(text.getText());
					} catch (TwitterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					shlSendingMessage.dispose();
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnNewButton.setBounds(276, 78, 94, 30);
		btnNewButton.setText("Send");
		
		lbl_errorMessage = new Label(shlSendingMessage, SWT.NONE);
		lbl_errorMessage.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl_errorMessage.setBounds(20, 80, 148, 20);
		lbl_errorMessage.setText("140");

	}
}
