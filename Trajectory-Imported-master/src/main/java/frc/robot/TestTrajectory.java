package frc.robot;

import java.util.List;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.CentripetalAccelerationConstraint;

//Create the necessary waypoints to test
public class TestTrajectory {
  HolonomicDriveController m;

  // Set max velocity, acceleration and centripedal acceleration (turn speed)
  private CentripetalAccelerationConstraint curveConstraint = new CentripetalAccelerationConstraint(1.0);
  private TrajectoryConfig config = new TrajectoryConfig(0.5, 0.5).addConstraint(curveConstraint);
  // Different waypoints for testing
  static private List<Translation2d> waypoints = List.of(
      new Translation2d(0.0, 0.0), // start
      new Translation2d(0.5, 0.5),
      new Translation2d(1.0, 0.5),
      new Translation2d(1.0, 1.0),
      new Translation2d(0.0, 1.0)

  );
  // private List<Translation2d> waypoints2 = List.of(
  // new Translation2d(0.5, 0.5), // start
  // new Translation2d(1.0, 0.5),
  // new Translation2d(1.0, 1.0),
  // new Translation2d(0.5, 1.0),
  // new Translation2d(0.25, 0.75),
  // new Translation2d(0.25, 0.5),
  // new Translation2d(0.5, 0.25),
  // new Translation2d(1.0, 0.25),
  // new Translation2d(1.25, 0.5),
  // new Translation2d(1.25, 1.0),
  // new Translation2d(1.0, 1.25),
  // new Translation2d(0.75, 1.25),
  // new Translation2d(0.75, 0.75) // end

  // );
  // private List<Translation2d> waypoints3 = List.of(
  // // Intermediate points manually created. For testing purpose
  // new Translation2d(0.5, 0.5), // start
  // new Translation2d(1.4, 0.5),
  // new Translation2d(1.5, 0.6),
  // new Translation2d(1.5, 1.4),
  // new Translation2d(1.4, 1.5),
  // new Translation2d(0.6, 1.5),
  // new Translation2d(0.5, 1.4),
  // new Translation2d(0.5, 1.1),
  // new Translation2d(0.4, 1.0),
  // new Translation2d(0.0, 1.0) // end
  // );

  private MyGenerateTrajectory myGenerateTrajectory;
  private Trajectory trajectory;
  // myGenerateTrajectory.generateTrajectoryQuinticHermite(waypoints, config,
  // 0.05);
  private List<Translation2d> myIntermediateWP;
  private List<Pose2d> myTrajectoryWP;

  /**
   * Generate a trajectory with builtin fixed waypoints
   * <p>
   * @param type 0: cubic, 1:quintic
   * 
   */
  public TestTrajectory(int type) {
    this(type, waypoints);
  }

  public TestTrajectory(int type, List<Translation2d> waypoints) {
    myGenerateTrajectory = new MyGenerateTrajectory();
    //Quintic is more consistent
    //Cubic can be unpredictable. Bug in library functions?????
    if (type==0)
      trajectory = myGenerateTrajectory.generateTrajectoryClampedCubic(waypoints, config, 0.05);
    else
      trajectory = myGenerateTrajectory.generateTrajectoryQuinticHermite(waypoints, config, 0.05);
      
      myIntermediateWP = myGenerateTrajectory.getIntermediateWP();
      myTrajectoryWP = myGenerateTrajectory.getTrajectoryWP();
    }

  public Trajectory getTrajectory() {
    return trajectory;
  }

  public List<Translation2d> getIntermediateWP() {
    return myIntermediateWP;
  }
  public List<Pose2d> getTrajectoryWP() {
    return myTrajectoryWP;
  }
}
