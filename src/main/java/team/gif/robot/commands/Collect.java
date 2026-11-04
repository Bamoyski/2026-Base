package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

/**
 * Forward-only collect command.
 *
 * <p>Runs the collector and the indexer wheels together so a single button pulls
 * fuel in and stages it. There is deliberately no reverse: young drivers should not
 * be able to launch fuel back out of the robot.
 */
public class Collect extends Command {

    public Collect() {
        super();
        addRequirements(Robot.collector, Robot.indexerWheels);
    }

    @Override
    public void execute() {
        Robot.collector.turn(Constants.COLLECTOR_IN_PERCENT);
        Robot.indexerWheels.turn(Constants.INDEX_WHEELS_SPEED_PER);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.collector.turn(0);
        Robot.indexerWheels.turn(0);
    }
}
