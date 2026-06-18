package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

public class DriveDistance extends Command {
    private final XRPDrivetrain drivetrain;
    private final double speed;
    private final double distance; // In Inches
    private final double rightScale;

    public DriveDistance(XRPDrivetrain drivetrain, double speed, double inches, double rightScale) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        distance = inches;
        this.rightScale = rightScale;

        addRequirements(drivetrain);
    }

    public DriveDistance(XRPDrivetrain drivetrain, double speed, double inches) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        distance = inches;
        rightScale = 1;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.tankDrive(0.0, 0.0);
        drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        drivetrain.tankDrive(speed / rightScale, speed * rightScale);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drivetrain.getAverageDistanceInch()) >= distance;
    }
}
