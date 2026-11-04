package team.gif.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import team.gif.robot.commands.Collect;
import team.gif.robot.commands.Shoot;
import team.gif.robot.commands.StopEverything;

/**
 * Operator interface: one Xbox controller does everything.
 *
 * <p>Binding table for the outreach robot:
 * <pre>
 *   Left stick Y   forward / back
 *   Right stick X  turn
 *   A (hold)       collect
 *   B (hold)       shoot (spins up, then feeds automatically)
 *   X (hold)       panic stop everything
 * </pre>
 *
 * <p>Turret aiming is automatic and has no button. Collector pivot and reverse
 * collector no longer have bindings at all.
 */
public class OI {

    /** The only controller. Driver station port comes from {@link RobotMap}. */
    public final CommandXboxController driver =
            new CommandXboxController(RobotMap.DRIVER_CONTROLLER_ID);

    public final Trigger dA = driver.a();
    public final Trigger dB = driver.b();
    public final Trigger dX = driver.x();

    public OI() {
        // No need to flood the console when the single controller is unplugged.
        DriverStation.silenceJoystickConnectionWarning(true);

        dA.and(DriverStation::isEnabled).whileTrue(new Collect());   // hold A: forward collect + index
        dB.and(DriverStation::isEnabled).whileTrue(new Shoot());     // hold B: spin up, then auto-feed
        dX.whileTrue(new StopEverything());                         // hold X: stop every mechanism
    }
}
