package frc.robot;

import java.awt.Dimension;

import frc.robot.Astar.AStarAlgorithm;
import frc.robot.Astar.Grid;
import frc.robot.Astar.Layout;
import frc.robot.Astar.Tile;
import frc.robot.ui.ControlsPanel;
import frc.robot.ui.GridPanel;
import frc.robot.ui.MainFrame;

import javax.swing.JPanel;

public class Main {

    private static MainFrame frame;
    private static JPanel container;
    private static GridPanel canvas;
    private static ControlsPanel controls;

    private static AStarAlgorithm astar;

    public static void main(String[] args) {

        // Create grid
        Layout layout = new Layout();
        Grid grid = new Grid(layout);

        grid.ExpandObstacles(150); // 100
        // Create solver
        astar = new AStarAlgorithm(grid);

        initUI();

        astar.addPropertyChangeListener(canvas);
        astar.updateUI();
    }

    private static void initUI() {

        int w = Layout.X_SIZE * Tile.TILE_SIZE;
        int h = Layout.Y_SIZE * Tile.TILE_SIZE;
        int controlsW = 200;
        int margin = 10;

        frame = new MainFrame();
        frame.setPreferredSize(new Dimension(w + controlsW + 15 + (margin * 3), h + 40 + (margin * 2)));

        container = new JPanel();
        container.setLayout(null);

        controls = new ControlsPanel(controlsW, 120, astar);
        controls.setBounds(w + (margin * 2), margin, controlsW, h);

        canvas = new GridPanel(controls);
        canvas.setBounds(margin, margin, w, h);

        container.add(controls);
        container.add(canvas);

        frame.setContentPane(container);
        frame.setVisible(true);
        frame.pack();
    }

}
