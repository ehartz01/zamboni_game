import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;

public class Main extends JFrame implements ComponentListener, ActionListener {
    // Window size limits
    private static final int LARGE = 800;
    private static final int MEDIUM = 600;
    private static final int SMALL = 400;

    private int frame = 1;
    private DeployButton deploy;
    protected StopButton stop;
    protected SkaterButton skate;
    protected Map canvasPanel;
	private Container content;
	private JPanel controlsPanel;
	private ZamboniColorChoice colorbox;
	protected ResumeButton resume;
	private SpeedChoice speedchoice;
	private DButton up;
	private DButton down;
	private DButton left;
	private DButton right;
	private DButton stopper;
	private JPanel operationsPanel;
	protected ZButton zoomin;
	protected ZButton zoomout;

;

    public static void main (String [] args) {
	java.awt.EventQueue.invokeLater (new Runnable() {
	    public void run() {
		new Main ();
            }
        });
    }

    public Main () {
	// Window setup
	setLocation (100, 100);
	setSize (LARGE, MEDIUM);
	setDefaultCloseOperation (EXIT_ON_CLOSE);

	content = getContentPane();
	content.setLayout (new BorderLayout());

	// Drawing canvas in middle, may be tweaked
	canvasPanel = new Map (this);
	content.add (canvasPanel, BorderLayout.CENTER);

	// Control panel, but may be moved to/from popup
	controlsPanel = new JPanel ();
	controlsPanel.setLayout (new FlowLayout ());

	// Contents of the control panel
	colorbox = new ZamboniColorChoice (this);
	deploy = new DeployButton (this);
	stop = new StopButton (this);
	skate = new SkaterButton(this);
	resume = new ResumeButton(this);
	speedchoice = new SpeedChoice(this, skate);
	zoomin = new ZButton(canvasPanel, 2, "+");
	zoomout = new ZButton(canvasPanel, 1, "-");
	controlsPanel.add (zoomin);
	controlsPanel.add (zoomout);
	controlsPanel.add (deploy);
	controlsPanel.add (colorbox);
	controlsPanel.add (stop);
	controlsPanel.add (resume);
	controlsPanel.add (skate);
	controlsPanel.add (speedchoice);
	
	//operations panel for controlling selected vehicle
	operationsPanel = new JPanel ();
	operationsPanel.setLayout (new BorderLayout ());


	//content of operationsPanel
	JPanel upper = new JPanel ();
	upper.setLayout (new FlowLayout());
	JPanel mid = new JPanel ();
	mid.setLayout (new FlowLayout());
	JPanel lower = new JPanel ();
	lower.setLayout(new FlowLayout());

	up = new DButton(canvasPanel, 3, "^");
	upper.add(up);
	operationsPanel.add(upper, BorderLayout.NORTH);

	down = new DButton(canvasPanel,1, "V");
	lower.add(down);
	operationsPanel.add(lower, BorderLayout.SOUTH);


	left = new DButton(canvasPanel,2, "<");
	mid.add(left);
	stopper = new DButton(canvasPanel,-1, "O");
	mid.add(stopper);
	right = new DButton(canvasPanel,0, ">");
	mid.add(right);
	operationsPanel.add(mid, BorderLayout.CENTER);


	content.add (operationsPanel, BorderLayout.SOUTH);
	content.add (controlsPanel, BorderLayout.NORTH);

	addComponentListener (this);

	setVisible (true);

	Timer timer = new Timer (100, this);
	timer.start();
    }

    public int getFrame() {return frame;}


    public void actionPerformed (ActionEvent e) {
	frame++;
	canvasPanel.ticker();
	canvasPanel.repaint ();
	if (canvasPanel.zamboni.deployed && stop.isEnabled())
		canvasPanel.scoreboard.time--;
	if (canvasPanel.scoreboard.time == 0){
		System.out.println("Final score: " + canvasPanel.scoreboard.score);
		System.exit(0);
	}
    }

    /*
     * Listener responds to resize of our whole JFrame
     * If resized, we modify or reassemble the components of our window
     */
    public void componentResized (ComponentEvent e) {
	Dimension size = getSize();

	if (size.width < SMALL || size.height < SMALL) {
	    colorbox.setVisible (false);
	    speedchoice.setVisible (false);
	    deploy.setVisible (false);
	    stop.setVisible (false);
	    resume.setVisible (false);
    	}

	// MEDIUM = Get rid of borders, use background colors instead
	else if (size.width < MEDIUM || size.height < MEDIUM) {
	    colorbox.setVisible (false);
	    speedchoice.setVisible (true);
	    deploy.setVisible (true);
	    stop.setVisible (false);
	    resume.setVisible (false);
	}
	
	// LARGE = Full layout
	else {
	    colorbox.setVisible (true);
	    speedchoice.setVisible (true);
	    deploy.setVisible (true);
	    stop.setVisible (true);
	    resume.setVisible (true);
	}

	// May need to force recompute layout
	revalidate();
	repaint();

    }
    // The other abstract methods
    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
	
}

