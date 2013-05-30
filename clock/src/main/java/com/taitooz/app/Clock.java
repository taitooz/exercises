package com.taitooz.app;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Observer;


//Top view(Jslider)
abstract class abstractTopView extends JPanel implements Observer {
  private TimeModel model;

  abstractTopView(TimeModel observedModel) {
    model = observedModel;
    model.addObserver(this);
  }

  public TimeModel getModel() {
    return model;
  }

  public void update(java.util.Observable o, Object arg) {
    updateView();
  }

  protected abstract void updateView();
}


//Bottom view(TextField)
abstract class abstractBottomView extends JPanel implements Observer {
  private TimeModel model;

  abstractBottomView(TimeModel observedModel) {
    model = observedModel;
    model.addObserver(this);
  }

  public TimeModel getModel() {
    return model;
  }

  public void update(java.util.Observable o, Object arg) {
    updateView();
  }

  protected abstract void updateView();
}

//Middle View(Clock)
abstract class abstractMediumView extends JPanel implements Observer {
  private TimeModel model;

  abstractMediumView(TimeModel observedModel) {
    model = observedModel;
    model.addObserver(this);
  }

  public TimeModel getModel() {
    return model;
  }

  public void update(java.util.Observable o, Object arg) {
    updateView();
  }

  protected abstract void updateView();

}

//Top view(Jslider)
class topView extends abstractTopView {
  boolean qUpdate = false;
  private JLabel hLabel = new JLabel("Hour");
  private JLabel mLabel = new JLabel("Minute");
  private JLabel sLabel = new JLabel("Second");
  private JLabel hLabel2 = new JLabel("");
  private JLabel mLabel2 = new JLabel("");
  private JLabel sLabel2 = new JLabel("");
  public JSlider hSlider = new JSlider();
  public JSlider mSlider = new JSlider();
  public JSlider sSlider = new JSlider();

  topView(TimeModel model) {
    super(model);
    setLayout(new GridLayout(3, 3));
    hSlider.setMaximum(12);
    mSlider.setMaximum(59);
    sSlider.setMaximum(59);

    controller();

    add(hLabel);
    add(hSlider);
    add(hLabel2);

    add(mLabel);
    add(mSlider);
    add(mLabel2);

    add(sLabel);
    add(sSlider);
    add(sLabel2);

    updateView();
  }

  protected void controller() {
    hSlider.addChangeListener(new changeSliderListener(this));
    mSlider.addChangeListener(new changeSliderListener(this));
    sSlider.addChangeListener(new changeSliderListener(this));
  }

  protected void updateView() {
    qUpdate = true;    //Indicate the updating process
    TimeModel model = getModel();
    hSlider.setValue(model.getHour());
    mSlider.setValue(model.getMin());
    sSlider.setValue(model.getSec());
    hLabel2.setText(model.getHour() + "");
    mLabel2.setText(model.getMin() + "");
    sLabel2.setText(model.getSec() + "");
    qUpdate = false;
  }
}

//Top view(Jslider)
class changeSliderListener implements ChangeListener {
  private topView controller;

  changeSliderListener(topView theController) {
    controller = theController;
  }

  public void stateChanged(ChangeEvent e) {
    if (!(controller.qUpdate)) {
      controller.getModel().doPreTime();  //save the previous time
      controller.getModel().setHour(controller.hSlider.getValue());
      controller.getModel().setMin(controller.mSlider.getValue());
      controller.getModel().setSec(controller.sSlider.getValue());
      controller.getModel().updateObserver();
    }
  }
}

//Bottom view(TextField)
class bottomView extends abstractBottomView {
  private JLabel label = new JLabel("Angle:");
  private JTextField text = new JTextField();

  bottomView(TimeModel model) {
    super(model);
    add(label);
    controller();  //add the controller
    updateView();
  }

  public void setText(String t) {
    text.setText(t);
  }

  protected void controller() {
    text.setPreferredSize(new Dimension(300, 20));
    text.setText("00:00:00");  //Initialise the TextField
    text.addActionListener(new actionButtonListener(this));
    add(text);
  }

  protected void updateView() {
    TimeModel model = getModel();
    if (text.getText().equals(model.getAngle()))
      text.setText(model.doString());
    }
}

//Bottom view(Label)
class actionButtonListener implements ActionListener {
  private bottomView controller;
  actionButtonListener(bottomView theController) {
    controller = theController;
  }

  public void actionPerformed(ActionEvent e) {
    controller.setText(controller.getModel().doString());
    controller.getModel().updateObserver();
  }
}

//Middle View(Clock)
class mediumView extends abstractMediumView {
  private int hours = 0;
  private int minutes = 0;
  private int seconds = 0;
  private int millis = 0;

  private static final int spacing = 10;
  private static final float pi = (float) ( Math.PI);
  // Angles for the trigonometric functions are measured in radians.
  // The following in the number of radians per sec or min.
  private static final float radPerSecMin = (float) (Math.PI / 30.0);

  private int size;       // height and width of clock face
  private int centerX;    // x coord of middle of clock
  private int centerY;    // y coord of middle of clock
  private BufferedImage clockImage;
  private javax.swing.Timer t;

  mediumView(TimeModel model) {
    super(model);
    this.setPreferredSize(new Dimension(300, 300));
    this.setBackground(Color.black);
    this.setForeground(Color.white);
    t = new javax.swing.Timer(1000, new actionClockListener(this));
    controller();
    start();  //start the timer
    updateView();
  }

  public void updateTime() {
    getModel().increaseTime();  //increase the sec by 1
  }

  public void start() {
    t.start();
  }

  protected void controller() {
    t.addActionListener(new actionClockListener(this));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);  // paint background, borders
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    // The panel may have been resized, get current dimensions
    int w = getWidth();
    int h = getHeight();
    size = ((w < h) ? w : h) - 2 * spacing;
    centerX = size / 2 + spacing;
    centerY = size / 2 + spacing;
    // Create the clock face background image if this is the first time,
    // or if the size of the panel has changed
    if (clockImage == null || clockImage.getWidth() != w
        || clockImage.getHeight() != h) {
      clockImage = (BufferedImage) (this.createImage(w, h));
      // now get a graphics context from this image
      Graphics2D gc = clockImage.createGraphics();
      gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      drawClockFace(gc);
    }
    // Now get the time and draw the hands.
    hours = getModel().getHour();
    minutes = getModel().getMin();
    seconds = getModel().getSec();
    millis = 0;
    // Draw the clock face from the precomputed image
    g2.drawImage(clockImage, null, 0, 0);
    // Draw the clock hands
    drawClockHands(g);
  }

  private void drawClockHands(Graphics g) {
    int secondRadius = size / 2;
    int minuteRadius = secondRadius * 3 / 4;
    int hourRadius = secondRadius / 2;
    // second hand
    g.setColor(Color.DARK_GRAY);
    float fseconds = seconds + (float) millis / 1000;
    float secondAngle = pi - (radPerSecMin * fseconds);
    drawRadius(g, centerX, centerY, secondAngle, 0, secondRadius);
    // minute hand
    g.setColor(Color.blue);
    float fminutes = (float) (minutes + fseconds / 60.0);
    float minuteAngle = pi - (radPerSecMin * fminutes);
    drawRadius(g, centerX, centerY, minuteAngle, 0, minuteRadius);
    // hour hand
    g.setColor(Color.red);
    float fhours = (float) (hours + fminutes / 60.0);
    float hourAngle = pi - (5 * radPerSecMin * fhours);
    drawRadius(g, centerX, centerY, hourAngle, 0, hourRadius);
  }

  private void drawClockFace(Graphics g) {
    // clock face
    g.setColor(Color.black);
    g.fillOval(spacing, spacing, size, size);
    g.setColor(Color.white);
    g.drawOval(spacing, spacing, size, size);
    // tic marks
    for (int sec = 0; sec < 60; sec++) {
      int ticStart;
      if (sec % 5 == 0) {
        ticStart = size / 2 - 10;
      } else {
        ticStart = size / 2 - 5;
      }
      drawRadius(g, centerX, centerY, radPerSecMin * sec, ticStart, size / 2);
    }
  }

  private void drawRadius(Graphics g, int x, int y, double angle, int minRadius,
                          int maxRadius) {
    float sine = (float) Math.sin(angle);
    float cosine = (float) Math.cos(angle);

    int dxmin = (int) (minRadius * sine);
    int dymin = (int) (minRadius * cosine);

    int dxmax = (int) (maxRadius * sine);
    int dymax = (int) (maxRadius * cosine);
    g.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);
  }

  protected void updateView() {
    this.repaint();
  }
}

//Middle View(Clock)
class actionClockListener implements ActionListener {
  private mediumView controller;

  actionClockListener(mediumView theController) {
    controller = theController;
  }

  public void actionPerformed(ActionEvent e) {
    controller.updateTime();
  }
}

//Main Class
public class Clock {
  TimeModel model = new TimeModel();
  Clock() {
    initTime();
    //creating top frame
    topClock topFrame = new topClock();
    topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    topFrame.pack();
    topFrame.setVisible(true);
    //creating bottom frame
    bottomClock bottomFrame = new bottomClock();
    bottomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    bottomFrame.pack();
    bottomFrame.setVisible(true);
    //creating middle frame
    mediumClock mediumFrame = new mediumClock();
    mediumFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mediumFrame.pack();
    mediumFrame.setVisible(true);
  }

  class topClock extends JFrame {
    topClock() {
      super("Input Settings");
      JPanel view = new topView(model);
      Container contentPane = getContentPane();
      contentPane.add(view);
      setResizable(false);
      setLocation(10, 100);
    }
  }

  class bottomClock extends JFrame {
    bottomClock() {
      super("Angle");
      JPanel view = new bottomView(model);
      this.setPreferredSize(new Dimension(500, 50));
      Container contentPane = getContentPane();
      contentPane.add(view);
      setResizable(false);
      setLocation(250, 540);
    }
  }

  class mediumClock extends JFrame {
    mediumClock() {
      super("Graphical Clock");
      JPanel view = new mediumView(model);
      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(view, BorderLayout.CENTER);
      setResizable(false);
      setLocation(150, 200);
    }
  }

  public void initTime() {
    Calendar now = Calendar.getInstance();
    model.setHour(now.get(Calendar.HOUR));
    model.setMin(now.get(Calendar.MINUTE));
    model.setSec(now.get(Calendar.SECOND));
  }

  public static void main(String[] args) {
    new Clock();
  }
}