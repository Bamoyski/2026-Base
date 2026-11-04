package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

/**
 * Shoot command: spin the flywheel to a fixed RPM, then feed once it is up to speed.
 *
 * <p>Outreach robots shoot from roughly 2-3 meters, so the speed is fixed rather
 * than calculated from camera distance. That keeps the behavior the same no matter
 * where the driver is standing.
 *
 * <p>The feed is latched: once the shooter reaches the target it keeps feeding
 * until the button is released, so a small RPM dip does not make the note stutter.
 */
public class Shoot extends Command {

    private boolean feeding;

    public Shoot() {
        super();
        // These are all the mechanisms this command owns while the button is held.
        addRequirements(Robot.shooter, Robot.indexerWheels, Robot.neoSpinDexer);
    }

    @Override
    public void initialize() {
        feeding = false;
    }

    @Override
    public void execute() {
        Robot.shooter.setRPM(Constants.SHOOTER_RPM);

        // Latch as soon as the flywheel is close enough to the target speed.
        if (!feeding && Robot.shooter.isReady()) {
            feeding = true;
        }

        if (feeding) {
            Robot.indexerWheels.turn(Constants.INDEX_WHEELS_SPEED_PER);
            Robot.neoSpinDexer.setRPM(Constants.SPINDEXER_RPM);
        } else {
            // Still spinning up: keep the fuel path still so nothing jams.
            Robot.indexerWheels.turn(0);
            Robot.neoSpinDexer.turn(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.shooter.stopMotors();
        Robot.indexerWheels.turn(0);
        Robot.neoSpinDexer.turn(0);
        feeding = false;
    }
}
