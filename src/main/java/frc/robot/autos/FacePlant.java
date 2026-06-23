package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.TurnSeconds;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;

import static edu.wpi.first.wpilibj2.command.Commands.sequence;
import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

public class FacePlant extends SequentialCommandGroup {
    private final XRPDrivetrain drivetrain;
    private final XRPArm arm;

    public FacePlant(XRPDrivetrain drivetrain, XRPArm arm) {
        this.drivetrain = drivetrain;
        this.arm = arm;
        addCommands(
            drive(1, 45, 0.99),
                eject()
        );
    }

    private Command drive(double speed, double inches) {
        return new DriveDistance(drivetrain, -speed, inches);
    }

    private Command drive(double speed, double inches, double rightScale) {
        return new DriveDistance(drivetrain, -speed, inches, rightScale);
    }

    private Command turn(double speed, double degrees) {
        return new TurnDegrees(drivetrain, speed, degrees);
    }

    private Command turnSec(double speed, double seconds) {
        return new TurnSeconds(drivetrain, speed, seconds);
    }

    private Command arm(double degrees) {
        return arm.move(degrees);
    }

    private Command eject() {
        return sequence(arm(140), waitSeconds(0.25), arm(80));
    }
}