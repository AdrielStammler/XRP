package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;

import static edu.wpi.first.wpilibj2.command.Commands.sequence;
import static edu.wpi.first.wpilibj2.command.Commands.waitSeconds;

public class TreeStoneBlue extends SequentialCommandGroup {
    private final XRPDrivetrain drivetrain;
    private final XRPArm arm;

    public TreeStoneBlue(XRPDrivetrain drivetrain, XRPArm arm) {
        this.drivetrain = drivetrain;
        this.arm = arm;
        addCommands(
            drive(1, 45, 0.99),
            eject(),
            drive(-1, 25, 0.99),
            // BLUE ONLY
            turn(0.8, 60),
            drive(1, 15, 0.99),
            turn(-0.5, 90),
            drive(1, 30, 0.99)
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

    private Command arm(double degrees) {
        return arm.move(degrees);
    }

    private Command eject() {
        return sequence(arm(140), waitSeconds(0.25), arm(80));
    }
}