package frc.robot.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.geom.GeneralPath;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.TestTrajectory;
import frc.robot.Astar.AStarAlgorithm;
import frc.robot.Astar.Grid;
import frc.robot.Astar.Layout;
import frc.robot.Astar.Node;
import frc.robot.Astar.Tile;

public class GridPanel extends JPanel implements PropertyChangeListener {

    /**
     *
     */
    private static final int SCALE = Tile.TILE_SIZE*Layout.Convert_m_cell(1);
    private static final long serialVersionUID = 1L;
    private Grid grid;
    private ArrayList<Tile> m_path=null;
    private List<Translation2d> m_pathWayPoints=null;
    private ControlsPanel controls;

    private Tile m_start;
    private Tile m_end;

    private BasicStroke defaultStroke;
    private BasicStroke widerStroke;

    public void drawCircle(Graphics2D g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval(x, y, r, r);
    }

    public GridPanel(ControlsPanel controls) {
        this.controls = controls;

        this.defaultStroke = new BasicStroke();
        this.widerStroke = new BasicStroke(2);

        setBorder(new LineBorder(Color.gray));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();

                int tileX = x / Tile.TILE_SIZE;
                int tileY = grid.getySize() - 1 - y / Tile.TILE_SIZE;

                Tile t = grid.find(tileX, tileY);

                if (t != null) {
                    controls.selectTile(t);
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);

        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // This is used to flip the Y-axis
        g.scale(1, -1);
        g.translate(0, -getHeight());

        g.setStroke(defaultStroke);

        if (grid != null && grid.getTiles() != null) {
            for (Tile t : grid.getTiles()) {
                g.setColor(new Color(220, 220, 220));
                g.drawRect(t.getX() * Tile.TILE_SIZE, t.getY() * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
                // g.fillOval(t.getX() * Tile.TILE_SIZE, t.getY() * Tile.TILE_SIZE,
                // Tile.TILE_SIZE, Tile.TILE_SIZE);
                if (t.getObsValue() != 0) {
                    int v = 255 - (int) t.getObsValue() * 2;
                    g.setColor(new Color(255, v, v));
                    int x = (t.getX() * Tile.TILE_SIZE);// + (Tile.TILE_SIZE / 2) - 5;
                    int y = (t.getY() * Tile.TILE_SIZE);// + (Tile.TILE_SIZE / 2) - 5;

                    g.fillOval(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
                }

            }
        }

        if (m_start != null) {

            int x = (m_start.getX() * Tile.TILE_SIZE) - 1;// + (Tile.TILE_SIZE / 2) - 6;
            int y = (m_start.getY() * Tile.TILE_SIZE) - 1;// + (Tile.TILE_SIZE / 2) - 6;
            // System.out.println("---");
            // System.out.println(y);
            g.setColor(new Color(20, 122, 17));
            g.setStroke(widerStroke);
            g.fillOval(x, y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
        }

        if (m_end != null) {
            int x = (m_end.getX() * Tile.TILE_SIZE) - 1;// + (Tile.TILE_SIZE / 2) - 6;
            int y = (m_end.getY() * Tile.TILE_SIZE) - 1;// + (Tile.TILE_SIZE / 2) - 6;
            // System.out.println(y);

            g.setColor(new Color(16, 49, 119));
            g.setStroke(widerStroke);
            g.fillOval(x, y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
        }

        if (m_path != null) {
            g.setColor(new Color(229, 142, 229));
            for (int i = 0; i < m_path.size() - 1; i++) {
                Tile t = m_path.get(i);
                Tile t2 = m_path.get(i + 1);

                int x = (t.getX() * Tile.TILE_SIZE);// + (Tile.TILE_SIZE / 2) - 5;
                int y = (t.getY() * Tile.TILE_SIZE);// + (Tile.TILE_SIZE / 2) - 5;

                int xx = (t2.getX() * Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2);
                int yy = (t2.getY() * Tile.TILE_SIZE) + (Tile.TILE_SIZE / 2);

                g.setStroke(widerStroke);
                g.fillOval(x+1, y+1, Tile.TILE_SIZE-2, Tile.TILE_SIZE-2);
                g.setStroke(defaultStroke);
                g.drawLine(x + Tile.TILE_SIZE / 2, y + Tile.TILE_SIZE / 2, xx, yy);
            }
        }

        g.drawRect(getWidth() - 1, 0, 1, getHeight());
        g.drawRect(0, getHeight() - 1, getWidth(), 1);

        //////////////////////////////////////////////////////////////
        // Plot trajectory path
        //////////////////////////////////////////////////////////////
        if (m_path != null) {
            g.setColor(new Color(0, 255, 0));

            TestTrajectory testTrajectory = new TestTrajectory(1, m_pathWayPoints);
            Trajectory traj = testTrajectory.getTrajectory();

            double dT = 0.02;
            int N = (int) (traj.getTotalTimeSeconds() / dT) + 1;

            GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, N);

            Pose2d p = traj.sample(0).poseMeters;

            path.moveTo(p.getTranslation().getX() * SCALE + Tile.TILE_SIZE / 2, 
                        p.getTranslation().getY() * SCALE + Tile.TILE_SIZE / 2);
            for (double t = 0.0; t <= traj.getTotalTimeSeconds(); t += dT) {
                p = traj.sample(t).poseMeters;
                path.lineTo(p.getTranslation().getX() * SCALE + Tile.TILE_SIZE / 2, 
                            p.getTranslation().getY() * SCALE + Tile.TILE_SIZE / 2);

                // Print to check velocity
                // System.out.printf("t:%5.2f, x:%5.2f, y:%5.2f, v:%5.2f\n", t,
                // p.getTranslation().getX(),
                // p.getTranslation().getY(), traj.sample(t).velocityMetersPerSecond);
            }
            g.setStroke(defaultStroke);
            g.draw(path);

            //////////////////////////////////////////////////////////////
            // Plot waypoints for comparison
            //////////////////////////////////////////////////////////////
            //Astar waypoints
            // Display for debug! Turn off as too cluttered
            // g.setPaint(Color.RED);
            // for (int i = 0; i < m_pathWayPoints.size(); i++) {
            //     drawCircle(g, (int) (m_pathWayPoints.get(i).getX() * SCALE) + Tile.TILE_SIZE / 2, 
            //             (int) (m_pathWayPoints.get(i).getY() * SCALE) + Tile.TILE_SIZE / 2, 4);
            // }

            //Trajectory waypoints
            g.setPaint(Color.BLUE);
            List<Pose2d> waypoints = testTrajectory.getTrajectoryWP();
            for (int i = 0; i < waypoints.size(); i++) {
                drawCircle(g, (int) (waypoints.get(i).getX() * SCALE) + Tile.TILE_SIZE / 2, 
                                (int) (waypoints.get(i).getY() * SCALE) + Tile.TILE_SIZE / 2, 4);
            }
        }

    }

    // @Override
    public void propertyChange(PropertyChangeEvent evt) {

        AStarAlgorithm alg = (AStarAlgorithm) evt.getNewValue();

        Grid grid = (Grid) alg.getNetwork();
        ArrayList<Node> path = alg.getPath();
        ArrayList<Node> pathWayPoints = alg.getPathWayPoints();
        Node start = alg.getStart();
        Node end = alg.getEnd();

        this.grid = grid;

        if (path==null) {
            m_path = null;
            m_pathWayPoints = null;
        } 
        else {
            m_path = new ArrayList<Tile>();
            m_pathWayPoints = new ArrayList<Translation2d>();
        }

        if (path != null) {
            for (Node n : path) {
                if (n instanceof Tile) {
                    m_path.add((Tile) n);
                }
            }
        }
        if (pathWayPoints != null) {
            for (Node n : pathWayPoints) {
                if (n instanceof Tile) {
                    Tile t = (Tile) n;
                    //Convert from cell unit to metre
                    Translation2d pt = new Translation2d(t.getX(), t.getY());
                    m_pathWayPoints.add(Layout.Convert_cell_m(pt));
                }
            }
        }

        if (start != null && start instanceof Tile) {
            m_start = (Tile) start;
        } else {
            m_start = null;
        }

        if (end != null && end instanceof Tile) {
            m_end = (Tile) end;
        } else {
            m_end = null;
        }

        repaint();

    }

}
