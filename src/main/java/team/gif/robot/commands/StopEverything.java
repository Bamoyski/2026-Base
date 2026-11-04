package team.gif.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import team.gif.robot.Robot;

/**
 * Panic stop: hold X and every mechanism stops.
 *
 * <p>Because this command requires every subsystem, it also interrupts the drive
 * and turret default commands while held. Releasing the button lets those defaults
 * start again, so the robot is immediately drivable.
 */
public class StopEverything extends Command {

    public StopEverything() {
        super();
        addRequirements(
                Robot.driveTrain,
                Robot.shooter,
                Robot.collector,
                Robot.indexerWheels,
                Robot.neoSpinDexer,
                Robot.turret);
    }

    @Override
    public void execute() {
        Robot.driveTrain.stop();
        Robot.shooter.stopMotors();
        Robot.collector.turn(0);
        Robot.indexerWheels.turn(0);
        Robot.neoSpinDexer.turn(0);
        Robot.turret.PerTurn(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
