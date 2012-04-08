import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;


public class PrintFixtures extends Dialog {

	protected Object result;
	protected Shell shell;
	private Ladder ldr;
	private int noOfTeams;
	private Label lbl_131;
	private Label lbl_133;
	private Label lbl_132;
	private Label lbl_141;
	private Label lbl_fixtures;
	private Label lbl_143;
	private Label lbl_142;
	private Label lbl_152;
	private Label lbl_151;
	private Label lbl_153;
	private Label lbl_91;
	private Label lbl_93;
	private Label lbl_92;
	private Label lbl_101;
	private Label lbl_103;
	private Label lbl_102;
	private Label lbl_111;
	private Label lbl_113;
	private Label lbl_112;
	private Label lbl_121;
	private Label lbl_123;
	private Label lbl_122;
	private Label lbl_11;
	private Label lbl_13;
	private Label lbl_12;
	private Label lbl_21;
	private Label lbl_23;
	private Label lbl_22;
	private Label lbl_31;
	private Label lbl_33;
	private Label lbl_32;
	private Label lbl_41;
	private Label lbl_43;
	private Label lbl_42;
	private Label lbl_53;
	private Label lbl_63;
	private Label lbl_51;
	private Label lbl_52;
	private Label lbl_61;
	private Label lbl_62;
	private Label lbl_71;
	private Label lbl_73;
	private Label lbl_72;
	private Label lbl_81;
	private Label lbl_83;
	private Label lbl_82;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PrintFixtures(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(Ladder l) {
		ldr = l;
		int noOfFirstRounds = ldr.getFirstNoOfRounds();
		noOfTeams = noOfFirstRounds*2;
		
		createContents();
		loadDataIntoContents();
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

	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shell.setSize(1280, 800);
		shell.setText("View History");
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
		
		lbl_11 = new Label(shell, SWT.NONE);
		lbl_11.setAlignment(SWT.CENTER);
		lbl_11.setBounds(127, 91, 105, 15);
		
		lbl_12 = new Label(shell, SWT.NONE);
		lbl_12.setAlignment(SWT.CENTER);
		lbl_12.setBounds(127, 144, 105, 15);
		
		lbl_21 = new Label(shell, SWT.NONE);
		lbl_21.setAlignment(SWT.CENTER);
		lbl_21.setBounds(127, 209, 105, 15);
		
		lbl_22 = new Label(shell, SWT.NONE);
		lbl_22.setAlignment(SWT.CENTER);
		lbl_22.setBounds(127, 258, 105, 15);
		
		lbl_31 = new Label(shell, SWT.NONE);
		lbl_31.setAlignment(SWT.CENTER);
		lbl_31.setBounds(127, 327, 105, 15);
		
		lbl_32 = new Label(shell, SWT.NONE);
		lbl_32.setAlignment(SWT.CENTER);
		lbl_32.setBounds(127, 376, 105, 15);
		
		lbl_41 = new Label(shell, SWT.NONE);
		lbl_41.setAlignment(SWT.CENTER);
		lbl_41.setBounds(127, 445, 105, 15);
		
		lbl_42 = new Label(shell, SWT.NONE);
		lbl_42.setAlignment(SWT.CENTER);
		lbl_42.setBounds(127, 494, 105, 15);
		
		lbl_51 = new Label(shell, SWT.NONE);
		lbl_51.setAlignment(SWT.CENTER);
		lbl_51.setBounds(1047, 91, 105, 15);
		
		lbl_52 = new Label(shell, SWT.NONE);
		lbl_52.setAlignment(SWT.CENTER);
		lbl_52.setBounds(1047, 144, 105, 15);
		
		lbl_61 = new Label(shell, SWT.NONE);
		lbl_61.setAlignment(SWT.CENTER);
		lbl_61.setBounds(1047, 209, 105, 15);
		
		lbl_62 = new Label(shell, SWT.NONE);
		lbl_62.setAlignment(SWT.CENTER);
		lbl_62.setBounds(1047, 258, 105, 15);
		
		lbl_71 = new Label(shell, SWT.NONE);
		lbl_71.setAlignment(SWT.CENTER);
		lbl_71.setBounds(1047, 327, 105, 15);
		
		lbl_72 = new Label(shell, SWT.NONE);
		lbl_72.setAlignment(SWT.CENTER);
		lbl_72.setBounds(1047, 376, 105, 15);
		
		lbl_81 = new Label(shell, SWT.NONE);
		lbl_81.setAlignment(SWT.CENTER);
		lbl_81.setBounds(1047, 445, 105, 15);
		
		lbl_82 = new Label(shell, SWT.NONE);
		lbl_82.setAlignment(SWT.CENTER);
		lbl_82.setBounds(1047, 494, 105, 15);
		
		lbl_91 = new Label(shell, SWT.NONE);
		lbl_91.setAlignment(SWT.CENTER);
		lbl_91.setBounds(267, 115, 105, 16);
		
		lbl_92 = new Label(shell, SWT.NONE);
		lbl_92.setAlignment(SWT.CENTER);
		lbl_92.setBounds(267, 233, 105, 16);
		
		lbl_101 = new Label(shell, SWT.NONE);
		lbl_101.setAlignment(SWT.CENTER);
		lbl_101.setBounds(267, 351, 105, 16);
		
		lbl_102 = new Label(shell, SWT.NONE);
		lbl_102.setAlignment(SWT.CENTER);
		lbl_102.setBounds(267, 469, 105, 16);
		
		lbl_111 = new Label(shell, SWT.NONE);
		lbl_111.setAlignment(SWT.CENTER);
		lbl_111.setBounds(909, 115, 105, 16);
		
		lbl_112 = new Label(shell, SWT.NONE);
		lbl_112.setAlignment(SWT.CENTER);
		lbl_112.setBounds(909, 233, 105, 16);
		
		lbl_121 = new Label(shell, SWT.NONE);
		lbl_121.setAlignment(SWT.CENTER);
		lbl_121.setBounds(909, 351, 105, 16);
		
		lbl_122 = new Label(shell, SWT.NONE);
		lbl_122.setAlignment(SWT.CENTER);
		lbl_122.setBounds(909, 469, 105, 16);
		
		lbl_131 = new Label(shell, SWT.NONE);
		lbl_131.setAlignment(SWT.CENTER);
		lbl_131.setBounds(427, 174, 105, 16);
		
		lbl_132 = new Label(shell, SWT.NONE);
		lbl_132.setAlignment(SWT.CENTER);
		lbl_132.setBounds(427, 410, 105, 16);
		
		lbl_141 = new Label(shell, SWT.NONE);
		lbl_141.setAlignment(SWT.CENTER);
		lbl_141.setBounds(749, 174, 105, 16);
		
		lbl_142 = new Label(shell, SWT.NONE);
		lbl_142.setAlignment(SWT.CENTER);
		lbl_142.setBounds(749, 410, 105, 16);
		
		lbl_151 = new Label(shell, SWT.NONE);
		lbl_151.setAlignment(SWT.CENTER);
		lbl_151.setBounds(587, 268, 105, 15);
		
		lbl_152 = new Label(shell, SWT.NONE);
		lbl_152.setAlignment(SWT.CENTER);
		lbl_152.setBounds(587, 317, 105, 15);
		
		lbl_13 = new Label(shell, SWT.NONE);
		lbl_13.setAlignment(SWT.CENTER);
		lbl_13.setBounds(127, 115, 105, 16);
		
		lbl_23 = new Label(shell, SWT.NONE);
		lbl_23.setAlignment(SWT.CENTER);
		lbl_23.setBounds(127, 233, 105, 16);
		
		lbl_33 = new Label(shell, SWT.NONE);
		lbl_33.setAlignment(SWT.CENTER);
		lbl_33.setBounds(127, 351, 105, 16);
		
		lbl_43 = new Label(shell, SWT.NONE);
		lbl_43.setAlignment(SWT.CENTER);
		lbl_43.setBounds(127, 469, 105, 16);
		
		lbl_93 = new Label(shell, SWT.NONE);
		lbl_93.setAlignment(SWT.CENTER);
		lbl_93.setBounds(267, 174, 105, 16);
		
		lbl_103 = new Label(shell, SWT.NONE);
		lbl_103.setAlignment(SWT.CENTER);
		lbl_103.setBounds(267, 410, 105, 16);
		
		lbl_133 = new Label(shell, SWT.NONE);
		lbl_133.setAlignment(SWT.CENTER);
		lbl_133.setBounds(427, 292, 105, 16);
		
		lbl_153 = new Label(shell, SWT.NONE);
		lbl_153.setAlignment(SWT.CENTER);
		lbl_153.setBounds(587, 292, 105, 16);
		
		lbl_53 = new Label(shell, SWT.NONE);
		lbl_53.setAlignment(SWT.CENTER);
		lbl_53.setBounds(1047, 115, 105, 16);
		
		lbl_63 = new Label(shell, SWT.NONE);
		lbl_63.setAlignment(SWT.CENTER);
		lbl_63.setBounds(1047, 233, 105, 16);
		
		lbl_73 = new Label(shell, SWT.NONE);
		lbl_73.setAlignment(SWT.CENTER);
		lbl_73.setBounds(1047, 351, 105, 16);
		
		lbl_83 = new Label(shell, SWT.NONE);
		lbl_83.setAlignment(SWT.CENTER);
		lbl_83.setBounds(1047, 469, 105, 16);
		
		lbl_113 = new Label(shell, SWT.NONE);
		lbl_113.setAlignment(SWT.CENTER);
		lbl_113.setBounds(909, 174, 105, 16);
		
		lbl_123 = new Label(shell, SWT.NONE);
		lbl_123.setAlignment(SWT.CENTER);
		lbl_123.setBounds(909, 410, 105, 16);
		
		lbl_143 = new Label(shell, SWT.NONE);
		lbl_143.setAlignment(SWT.CENTER);
		lbl_143.setBounds(749, 292, 105, 16);
		
		Image fixtures = null;
		if(noOfTeams == 4)
			fixtures = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("fixture3.png"));
		
		else if(noOfTeams == 8)
			fixtures = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("fixture2.png"));
		
		else if(noOfTeams == 16)
			fixtures = new Image(Display.getCurrent(), HomePage.class.getResourceAsStream("fixture.png"));
		
		lbl_fixtures = new Label(shell, SWT.NONE);
		lbl_fixtures.setBounds(0, 0, 1280, 600);
		lbl_fixtures.setImage(fixtures);
	}
	
	private void loadDataIntoContents() {
			int firstNoOfRounds = ldr.getFirstNoOfRounds();
			String eventName = ldr.getEventName();
			List<String> firstTeam = new ArrayList<String>();
			List<String> secondTeam = new ArrayList<String>();
			List<String> scores = new ArrayList<String>();

			while(firstNoOfRounds != 0) {
				Ladder newLadder = new Ladder(eventName);
				newLadder.loadLadder(eventName, firstNoOfRounds);

				int noOfMatches = newLadder.getCurrNoOfRounds();				

				for(int i=0; i<noOfMatches; i++) {	
					firstTeam.add(newLadder.getFirstTeamOfTheMatch(i));
					secondTeam.add(newLadder.getSecondTeamOfTheMatch(i));
					
					String winner = newLadder.getWinner(i);
					int[] tempScores = newLadder.getScores(i);
					
					if(winner.equals(newLadder.getFirstTeamOfTheMatch(i)))
						scores.add(tempScores[0] + " - " + tempScores[1]);
				
					else	scores.add(tempScores[1] + " - " + tempScores[0]);
				}
				firstNoOfRounds /= 2;
			}
			
			/*for(int i=0; i<7; i++) {
				System.out.println(firstTeam.get(i));
			System.out.println(secondTeam.get(i));
			System.out.println(scores.get(i));
		}*/
			if(noOfTeams == 4) {
				lbl_131.setText(firstTeam.get(0));
				lbl_132.setText(secondTeam.get(0));
				lbl_133.setText(scores.get(0));
				lbl_141.setText(firstTeam.get(1));
				lbl_142.setText(secondTeam.get(1));
				lbl_143.setText(scores.get(1));
				lbl_151.setText(firstTeam.get(2));
				lbl_152.setText(secondTeam.get(2));
				lbl_153.setText(scores.get(2));
			}
			
			else if(noOfTeams == 8) {	
				lbl_91.setText(firstTeam.get(0));
				lbl_92.setText(secondTeam.get(0));
				lbl_93.setText(scores.get(0));
				lbl_101.setText(firstTeam.get(1));
				lbl_102.setText(secondTeam.get(1));
				lbl_103.setText(scores.get(1));
				lbl_111.setText(firstTeam.get(2));
				lbl_112.setText(secondTeam.get(2));
				lbl_113.setText(scores.get(2));
				lbl_121.setText(firstTeam.get(3));
				lbl_122.setText(secondTeam.get(3));
				lbl_123.setText(scores.get(3));
				lbl_131.setText(firstTeam.get(4));
				lbl_132.setText(secondTeam.get(4));
				lbl_133.setText(scores.get(4));
				lbl_141.setText(firstTeam.get(5));
				lbl_142.setText(secondTeam.get(5));
				lbl_143.setText(scores.get(5));
				lbl_151.setText(firstTeam.get(6));
				lbl_152.setText(secondTeam.get(6));
				lbl_153.setText(scores.get(6));
		}
			
			else if(noOfTeams == 16) {	
				lbl_11.setText(firstTeam.get(0));
				lbl_12.setText(secondTeam.get(0));
				lbl_13.setText(scores.get(0));
				lbl_21.setText(firstTeam.get(1));
				lbl_22.setText(secondTeam.get(1));
				lbl_23.setText(scores.get(1));
				lbl_31.setText(firstTeam.get(2));
				lbl_32.setText(secondTeam.get(2));
				lbl_33.setText(scores.get(2));
				lbl_41.setText(firstTeam.get(3));
				lbl_42.setText(secondTeam.get(3));
				lbl_43.setText(scores.get(3));
				lbl_51.setText(firstTeam.get(4));
				lbl_52.setText(secondTeam.get(4));
				lbl_53.setText(scores.get(4));
				lbl_61.setText(firstTeam.get(5));
				lbl_62.setText(secondTeam.get(5));
				lbl_63.setText(scores.get(5));
				lbl_71.setText(firstTeam.get(6));
				lbl_72.setText(secondTeam.get(6));
				lbl_73.setText(scores.get(6));
				lbl_81.setText(firstTeam.get(7));
				lbl_82.setText(secondTeam.get(7));
				lbl_83.setText(scores.get(7));
				lbl_91.setText(firstTeam.get(8));
				lbl_92.setText(secondTeam.get(8));
				lbl_93.setText(scores.get(8));
				lbl_101.setText(firstTeam.get(9));
				lbl_102.setText(secondTeam.get(9));
				lbl_103.setText(scores.get(9));
				lbl_111.setText(firstTeam.get(10));
				lbl_112.setText(secondTeam.get(10));
				lbl_113.setText(scores.get(10));
				lbl_121.setText(firstTeam.get(11));
				lbl_122.setText(secondTeam.get(11));
				lbl_123.setText(scores.get(11));
				lbl_131.setText(firstTeam.get(12));
				lbl_132.setText(secondTeam.get(12));
				lbl_133.setText(scores.get(12));
				lbl_141.setText(firstTeam.get(13));
				lbl_142.setText(secondTeam.get(13));
				lbl_143.setText(scores.get(13));
				lbl_151.setText(firstTeam.get(14));
				lbl_152.setText(secondTeam.get(14));
				lbl_153.setText(scores.get(14));
		}
		
	}
}
