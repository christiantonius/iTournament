import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class EnterScore extends Dialog {

	protected Object result;
	protected Shell shlEnterMatchScore;
	private Label lbl_firstTeam;
	private Label lbl_secondTeam;
	private Spinner spinner_firstTeamScore;
	private Spinner spinner_secondTeamScore;
	private int[] scores = new int[2];
	private Button btnCancel;
	private boolean isUserCancel = false;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EnterScore(Shell parent, int style) {
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
	
	public int[] open(String firstTeam, String secondTeam) {
		createContents();
		setTeams(firstTeam, secondTeam);
		shlEnterMatchScore.open();
		shlEnterMatchScore.layout();
		Display display = getParent().getDisplay();
		while (!shlEnterMatchScore.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		if(isUserCancel == true)
			return null;

		return scores;
	}

	private void createContents() {
		shlEnterMatchScore = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shlEnterMatchScore.setSize(300, 200);
		shlEnterMatchScore.setText("Enter Match Score");
		shlEnterMatchScore.setMinimumSize(300,200);										//Set shell dimension
		center(shlEnterMatchScore);
		
		lbl_firstTeam = new Label(shlEnterMatchScore, SWT.WRAP);
		lbl_firstTeam.setAlignment(SWT.CENTER);
		lbl_firstTeam.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl_firstTeam.setBounds(20, 30, 100, 40);
		
		lbl_secondTeam = new Label(shlEnterMatchScore, SWT.WRAP);
		lbl_secondTeam.setAlignment(SWT.CENTER);
		lbl_secondTeam.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl_secondTeam.setBounds(180, 30, 100, 40);
		
		Label lbl_vs = new Label(shlEnterMatchScore, SWT.NONE);
		lbl_vs.setAlignment(SWT.CENTER);
		lbl_vs.setText("VS");
		lbl_vs.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl_vs.setBounds(135, 30, 30, 20);
		
		spinner_firstTeamScore = new Spinner(shlEnterMatchScore, SWT.BORDER);
		spinner_firstTeamScore.setMaximum(20);
		spinner_firstTeamScore.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_firstTeamScore.setBounds(60, 85, 60, 25);
		
		spinner_secondTeamScore = new Spinner(shlEnterMatchScore, SWT.BORDER);
		spinner_secondTeamScore.setMaximum(20);
		spinner_secondTeamScore.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		spinner_secondTeamScore.setBounds(180, 85, 60, 25);
		
		Button btnNewButton = new Button(shlEnterMatchScore, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scores[0] = Integer.parseInt(spinner_firstTeamScore.getText());
				scores[1] = Integer.parseInt(spinner_secondTeamScore.getText());
				
				if(scores[0] != scores[1])
					shlEnterMatchScore.dispose();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnNewButton.setBounds(200, 127, 80, 30);
		btnNewButton.setText("Save");
		
		btnCancel = new Button(shlEnterMatchScore, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isUserCancel = true;
				shlEnterMatchScore.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		btnCancel.setBounds(109, 127, 80, 30);
	}
	
	private void setTeams(String firstTeam, String secondTeam) {
		lbl_firstTeam.setText(firstTeam);
		lbl_secondTeam.setText(secondTeam);
	}
}
